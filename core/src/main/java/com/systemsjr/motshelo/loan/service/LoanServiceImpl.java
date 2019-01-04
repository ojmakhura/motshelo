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
import com.systemsjr.motshelo.instance.period.InstancePeriod;
import com.systemsjr.motshelo.instance.period.vo.InstancePeriodSearchCriteria;
import com.systemsjr.motshelo.instance.vo.MotsheloInstanceVO;
import com.systemsjr.motshelo.interest.InterestType;
import com.systemsjr.motshelo.interest.vo.InterestVO;
import com.systemsjr.motshelo.loan.Loan;
import com.systemsjr.motshelo.loan.LoanStatus;
import com.systemsjr.motshelo.loan.LoanType;
import com.systemsjr.motshelo.loan.vo.LoanSearchCriteria;
import com.systemsjr.motshelo.loan.vo.LoanVO;
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
    	
    	if(loanVO == null)
    	{
    		return null;
    	}
    	
    	Date expectedEnd = calculateLoanEndDate(loanVO);
    	InterestVO interest = getLoanInterest(loanVO);
    	if(loanVO.getId() == null) // this is a new loan
    	{
    		loanVO.setExpectedEndDate(expectedEnd);
    	} else {
    		if(expectedEnd.compareTo(loanVO.getExpectedEndDate()) > 0)
    		{
    			loanVO.setStatus(LoanStatus.DAFAULTED);
    		}
    	}
    	
    	Loan loan = getLoanDao().loanVOToEntity(loanVO);   
    	loan.setInstanceMember(getInstanceMemberDao().load(loan.getInstanceMember().getId()));
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
    			balance.subtract(interest.getAmount());
    			loanVO.setBalance(balance);
    		}
    	}
    	    	
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
		
		Motshelo motshelo = getMotsheloDao().load(loanVO.getMotsheloInstance().getMotshelo().getId());
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
			double interestAmount = interestRate * loanVO.getAmount().doubleValue() / 100;
			interest.setAmount(new BigDecimal(interestAmount));
		}
		
		return interest;
	}

	@Override
	protected Date handleCalculateLoanEndDate(LoanVO loanVO) throws Exception {
		Date date = null;
		MotsheloVO motshelo = loanVO.getMotsheloInstance().getMotshelo();
		// With a new loan we start from the startDate
		if(loanVO.getId() == null) { 
			date = loanVO.getStartDate();
			if(loanVO.getType() == LoanType.INTERESTFREE) // Contribution loan has no interest
			{
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);				
				int lastDate = cal.getActualMaximum(Calendar.DATE);
				// set the expected date to the last day of the month
				cal.set(Calendar.DAY_OF_MONTH, lastDate);
				date = cal.getTime();
			} else {
				InstancePeriodSearchCriteria criteria = new InstancePeriodSearchCriteria();
				criteria.setDate(date);
				criteria.setMotsheloInstance(loanVO.getMotsheloInstance());
				ArrayList<InstancePeriod> periods = (ArrayList<InstancePeriod>) getInstancePeriodDao().findByCriteria(criteria);
				InstancePeriod period = periods.size() > 0 ? periods.get(0) : null;
				
				// If the loan was taken out before the loanByDate, 
				// then the first period is the next one
				if(period != null && period.getNextPeriod() != null)
				{
					if(date.compareTo(period.getLoanByDate()) > 0);
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
			}
		} else { /// If this is not a new loan
			date = loanVO.getExpectedEndDate();
			if(date.compareTo(new Date()) < 0) /// The loan is overdue
			{
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(Calendar.MONTH, motshelo.getRepaymentTerm()); /// increase the expected date by the number of repayment months
				
				date = cal.getTime();
			}
		}		
		
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

}