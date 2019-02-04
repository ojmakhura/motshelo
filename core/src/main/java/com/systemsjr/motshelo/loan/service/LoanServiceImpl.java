// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::motshelo::com.systemsjr.motshelo::loan::service::LoanService
 * STEREOTYPE:  Service
 */
package com.systemsjr.motshelo.loan.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.systemsjr.motshelo.Motshelo;
import com.systemsjr.motshelo.instance.MotsheloInstance;
import com.systemsjr.motshelo.instance.member.InstanceMember;
import com.systemsjr.motshelo.instance.member.vo.InstanceMemberVO;
import com.systemsjr.motshelo.instance.period.InstancePeriod;
import com.systemsjr.motshelo.instance.period.vo.InstancePeriodSearchCriteria;
import com.systemsjr.motshelo.instance.period.vo.InstancePeriodVO;
import com.systemsjr.motshelo.instance.vo.MotsheloInstanceVO;
import com.systemsjr.motshelo.interest.InterestType;
import com.systemsjr.motshelo.interest.vo.InterestVO;
import com.systemsjr.motshelo.loan.Loan;
import com.systemsjr.motshelo.loan.LoanStatus;
import com.systemsjr.motshelo.loan.LoanType;
import com.systemsjr.motshelo.loan.payment.LoanPayment;
import com.systemsjr.motshelo.loan.payment.vo.LoanPaymentVO;
import com.systemsjr.motshelo.loan.vo.LoanSearchCriteria;
import com.systemsjr.motshelo.loan.vo.LoanVO;
import com.systemsjr.motshelo.transaction.Transaction;
import com.systemsjr.motshelo.transaction.vo.TransactionSearchCriteria;
import com.systemsjr.motshelo.vo.MotsheloVO;

/**
 * @see com.systemsjr.motshelo.loan.service.LoanService
 */
@Service("com.systemsjr.motshelo.loan.service.LoanService")
public class LoanServiceImpl
    extends LoanServiceBase
{

    /**
     * @see com.systemsjr.motshelo.loan.service.LoanService#findById(Long)
     */
    @Override
    protected  LoanVO handleFindById(Long id)
        throws Exception
    {
    	return id == null ? null : getLoanDao().toLoanVO(getLoanDao().load(id));
    }
    
    /**
     * @see com.systemsjr.motshelo.loan.service.LoanService#saveLoan(LoanVO)
     */
    @Override
    protected  LoanVO handleSaveLoan(LoanVO loanVO)
        throws Exception
    {
    	boolean isNew = false;
    	if(loanVO == null)
    	{
    		return null;
    	}
    	InstanceMember member = getInstanceMemberDao().load(loanVO.getInstanceMember().getId());
    	MotsheloInstance instance = getMotsheloInstanceDao().load(loanVO.getMotsheloInstance().getId());
    	Date expectedEnd = calculateLoanEndDate(loanVO);
    	InterestVO interest = getLoanInterest(loanVO);
    	if(loanVO.getId() == null) // this is a new loan
    	{
    		loanVO.setBalance(new BigDecimal(loanVO.getAmount().doubleValue()));
    		loanVO.setExpectedEndDate(expectedEnd);
    		isNew = true;
    	} else {
    		
	    	if(expectedEnd.compareTo(loanVO.getExpectedEndDate()) > 0 && loanVO.getBalance().doubleValue() > 0.0)
	    	{
	    		loanVO.setStatus(LoanStatus.DAFAULTED);
	    		loanVO.setExpectedEndDate(this.calculateLoanEndDate(loanVO));
	    	}
	    	
	    	if(loanVO.getActualEndDate() == null && loanVO.getBalance().doubleValue() == 0.00)
	    	{
	    		loanVO.setActualEndDate(new Date());
	    	}
    	}
    	
    	
    	Loan loan = getLoanDao().loanVOToEntity(loanVO);   
    	loan.setInstanceMember(member);
    	loan = getLoanDao().createOrUpdate(loan);
    	loanVO = getLoanDao().toLoanVO(loan);
    	
    	if(interest != null) {
    		interest.setLoan(loanVO);
    		interest = getInterestService().saveInterest(interest);
    		if(interest.getId() != null)
    		{
    			if(loanVO.getInterests() == null)
    			{
    				loanVO.setInterests(new ArrayList<InterestVO>());
    			}
    			
    			loanVO.getInterests().add(interest);
    			BigDecimal balance = loanVO.getBalance();
    			balance.add(interest.getAmount());
    			loanVO.setBalance(balance);
    			
    			/// Update cummulative balance
    			BigDecimal instanceBalance = instance.getCummulativeBalance();
    			instanceBalance = instanceBalance.add(interest.getAmount());
    			instance.setCummulativeBalance(instanceBalance);
    		}
    	}
    	
    	if(loanVO.getId() != null && isNew)
    	{
			BigDecimal balance = member.getBalance();
			balance = balance.subtract(loanVO.getAmount());
			member.setBalance(balance);
			getInstanceMemberDao().update(member);
			
			// need to update instance balance			
			if(loanVO.getType() != LoanType.CONTRIBUTION)
			{
				BigDecimal instanceBalance = instance.getBalance();
				instanceBalance = instanceBalance.subtract(loanVO.getAmount());
				instance.setBalance(instanceBalance);
				
			} else {
				BigDecimal instanceBalance = instance.getCummulativeBalance();
				instanceBalance = instanceBalance.add(loanVO.getAmount());
				instance.setCummulativeBalance(instanceBalance);
			}
			
    		
    		// Search for any of the transactions that still have money left
    		TransactionSearchCriteria criteria = new TransactionSearchCriteria();
    		criteria.setInstanceMember(loanVO.getInstanceMember());
    		criteria.setMotsheloInstance(loanVO.getMotsheloInstance());
    		criteria.setRemainingAmount(new BigDecimal(0.0));
    		
    		double loanBalance = calculateLoanBalance(loanVO).doubleValue();
    		Collection<Transaction> transactions = getTransactionDao().findByCriteria(criteria);
    		for(Transaction transaction : transactions)
    		{
    			if(loanVO.getStatus() == LoanStatus.COMPLETED)
    			{
    				break;
    			}
    			
    			LoanPayment payment = new LoanPayment();
    			payment.setLoan(getLoanDao().load(loanVO.getId()));
    			payment.setTransaction(transaction);
    			double transAmount = transaction.getRemainingAmount().doubleValue();
    			if(loanBalance <= transAmount)
    			{
    				payment.setPaymentAmount(new BigDecimal(loanBalance));
    				transAmount -= loanBalance;
    				loanVO.setStatus(LoanStatus.COMPLETED);
    				loanVO.setActualEndDate(new Date());
    				loanBalance = 0;
    			} else {
    				payment.setPaymentAmount(new BigDecimal(transAmount));
    				loanBalance -= transAmount;
    			}
    			payment = getLoanPaymentDao().create(payment);
    			if(loanVO.getLoanPayments() == null)
    			{
    				loanVO.setLoanPayments(new ArrayList<LoanPaymentVO>());
    			}
    			loanVO.getLoanPayments().add(getLoanPaymentDao().getBasicLoanPaymentVO(payment));
    			transaction.setRemainingAmount(new BigDecimal(transAmount));
    			getTransactionDao().update(transaction);
    		}
    	}
    	
    	getMotsheloInstanceDao().update(instance);
    	loanVO.setBalance(calculateLoanBalance(loanVO));
    	getLoanDao().update(getLoanDao().loanVOToEntity(loanVO));
    	return loanVO;
    }

    /**
     * @see com.systemsjr.motshelo.loan.service.LoanService#removeLoan(LoanVO)
     */
    @Override
    protected  boolean handleRemoveLoan(LoanVO loanVO)
        throws Exception
    {
    	if(loanVO.getId() != null)
    	{
    		getLoanDao().remove(loanVO.getId());
    	}
    	
    	return false;
    }

    /**
     * @see com.systemsjr.motshelo.loan.service.LoanService#getAllLoans()
     */
    @Override
    protected  Collection<LoanVO> handleGetAllLoans()
        throws Exception
    {
        return getLoanDao().toLoanVOCollection(getLoanDao().loadAll());
    }

    /**
     * @see com.systemsjr.motshelo.loan.service.LoanService#getAllLoansArray()
     */
    @Override
    protected  LoanVO[] handleGetAllLoansArray()
        throws Exception
    {
    	return getLoanDao().toLoanVOArray(getLoanDao().loadAll());
    }

    /**
     * @return 
     * @see com.systemsjr.motshelo.loan.service.LoanService#searchLoans(LoanSearchCriteria)
     */
    @Override
    protected  Collection<LoanVO> handleSearchLoans(LoanSearchCriteria searchCriteria)
        throws Exception
    {
    	Collection<Loan> loans = getLoanDao().findByCriteria(searchCriteria);
        return getLoanDao().toLoanVOCollection(loans);
    }

    /**
     * @see com.systemsjr.motshelo.loan.service.LoanService#searchLoansArray(LoanSearchCriteria)
     */
    @Override
    protected  LoanVO[] handleSearchLoansArray(LoanSearchCriteria searchCriteria)
        throws Exception
    {
    	Collection<Loan> loans = getLoanDao().findByCriteria(searchCriteria);
        return getLoanDao().toLoanVOArray(loans);
    }

	@Override
	protected InterestVO handleGetLoanInterest(LoanVO loanVO) throws Exception {

		MotsheloInstance instance = getMotsheloInstanceDao().load(loanVO.getMotsheloInstance().getId());
		Motshelo motshelo = instance.getMotshelo();
		InterestType type = null;
		Float interestRate = null;

		// If this is a new loan and it is a standard loan
		if(loanVO.getId() == null && loanVO.getType() == LoanType.STANDARD) {
			type = InterestType.STANDARDINTEREST;
			interestRate = motshelo.getLoanInterest();
			
		} else if(loanVO.getId() != null)
		{
			if(loanVO.getExpectedEndDate().compareTo(new Date()) < 0 && loanVO.getBalance().doubleValue() > 0) /// Is the loan overdue and not finished
			{
				type = InterestType.DEFAULTINGINTEREST;
				interestRate = motshelo.getLoanDefaultInterest();
			}
		}
		
		InterestVO interest = null;
		if(type != null)
		{
			interest = new InterestVO();
			interest.setType(type);
			double interestAmount = interestRate * loanVO.getBalance().doubleValue() / 100;
			interest.setAmount(new BigDecimal(interestAmount));
		}

		return interest;
	}

	@Override
	protected Date handleCalculateLoanEndDate(LoanVO loanVO) throws Exception {
		Date date = null;
		MotsheloInstance instance = getMotsheloInstanceDao().load(loanVO.getMotsheloInstance().getId());
		Motshelo motshelo = instance.getMotshelo();
		Calendar cal = Calendar.getInstance();
		
		// With a new loan we start from the startDate
		if(loanVO.getId() == null) { 
			loanVO.setBalance(new BigDecimal(loanVO.getAmount().doubleValue()));
			date = loanVO.getStartDate();
			cal.setTime(date);
			
			InstancePeriodSearchCriteria criteria = new InstancePeriodSearchCriteria();
			criteria.setDate(date);
			criteria.setMotsheloInstance(loanVO.getMotsheloInstance());
			ArrayList<InstancePeriod> periods = (ArrayList<InstancePeriod>) getInstancePeriodDao().findByCriteria(criteria);
			InstancePeriod period = periods.size() > 0 ? periods.get(0) : null;
				
			if(loanVO.getType() == LoanType.CONTRIBUTION) // Contribution loan has no interest
			{
				if(period != null)
				{
					cal.setTime(period.getEndDate());
				}
				else {
					int lastDate = cal.getActualMaximum(Calendar.DATE);
					// set the expected date to the last day of the month
					cal.set(Calendar.DAY_OF_MONTH, lastDate);
				}
			} else {
				//cal.add(Calendar.MONTH, motshelo.getRepaymentTerm());
				// If the loan was taken out after the loanByDate, 
				// then the first period is the next one
				
				if(period != null && period.getNextPeriod() != null)
				{
					if(loanVO.getStartDate().compareTo(period.getLoanByDate()) > 0);
					{
						period.getNextPeriod();
					}

					int i = 1;
					
					while(i < motshelo.getRepaymentTerm() && period.getNextPeriod() != null) /// guard against the last period
					{
						period = period.getNextPeriod();
						i++;
					}
				}
				cal.setTime(period.getEndDate());
			}
		} else { /// If this is not a new loan
			date = loanVO.getExpectedEndDate();
			if(date.compareTo(new Date()) < 0) /// The loan is overdue
			{
				cal.setTime(date);
				cal.add(Calendar.MONTH, motshelo.getRepaymentTerm()); /// increase the expected date by the number of repayment months
			}
		}		
		date = cal.getTime();
		return date;
	}

	@Override
	protected Collection<LoanVO> handleGenerateContributionLoans(MotsheloInstanceVO motsheloInstanceVO)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BigDecimal handleCalculateLoanBalance(LoanVO loanVO) throws Exception {
		
		return getLoanDao().calculateLoanBalance(getLoanDao().loanVOToEntity(loanVO));
	}

	@Override
	protected LoanVO handleCreateMemberContribution(InstanceMemberVO instanceMemberVO,
			InstancePeriodVO instancePeriodVO) throws Exception {
		InstancePeriod period = getInstancePeriodDao().instancePeriodVOToEntity(instancePeriodVO);
		InstanceMember member = getInstanceMemberDao().instanceMemberVOToEntity(instanceMemberVO);
		Loan loan = getLoanDao().createContributionLoan(member, period);
		return this.saveLoan(getLoanDao().toLoanVO(loan));
	}

}