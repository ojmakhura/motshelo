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
import com.systemsjr.motshelo.interest.Interest;
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
    
    private Interest getLoanInterest(LoanVO loanVO)
    {
    	Interest interest = Interest.Factory.newInstance();
    	Motshelo motshelo = getMotsheloDao().load(loanVO.getMotsheloInstance().getMotshelo().getId());
    	
    	// This is a new loan so create the necessary interest
    	if(loanVO.getId() == null && loanVO.getType() == LoanType.STANDARD)
    	{
    		double interestAmount = motshelo.getLoanInterest() * loanVO.getAmount().doubleValue() / 100;
    		interest.setAmount(new BigDecimal(interestAmount));
    		interest.setType("STANDARD INTEREST");
    		double loanBalance = interestAmount + interest.getAmount().doubleValue();
    		loanVO.setBalance(new BigDecimal(loanBalance));
    		loanVO = getLoanExpectedEndDate(loanVO, motshelo.getRepaymentTerm());
    	} else {
    		Calendar cal = Calendar.getInstance();
    		Date today = cal.getTime();
    		
    		/// Check if the loan is overdue and add interest on the balance
    		if(today.compareTo(loanVO.getExpectedEndDate()) > 0)
    		{
    			interest.setType("DEFAULTING INTEREST");
    			double interestAmount = motshelo.getLoanDefaultInterest() * loanVO.getBalance().doubleValue() / 100;
    			interest.setAmount(new BigDecimal(interestAmount));
    			double loanBalance = interestAmount + interest.getAmount().doubleValue();
    			loanVO.setBalance(new BigDecimal(loanBalance));
    			loanVO.setStatus(LoanStatus.DAFAULTED);
    			loanVO = getLoanExpectedEndDate(loanVO, motshelo.getRepaymentTerm());
    		}
    	}
    	
    	return interest;
    }
    
    private LoanVO getLoanExpectedEndDate(LoanVO loanVO, Integer repaymentTerm) {
    	
    	Date start = loanVO.getStartDate();
    	    	
    	if(loanVO.getStatus() == LoanStatus.DAFAULTED)
    	{
    		start = loanVO.getExpectedEndDate();
    	}
    	
    	InstancePeriodSearchCriteria searchCriteria = new InstancePeriodSearchCriteria();
    	searchCriteria.setDate(start);
    	ArrayList<InstancePeriod> periods = (ArrayList<InstancePeriod>) getInstancePeriodDao().findByCriteria(searchCriteria);
    	InstancePeriod last = periods.get(0);
    	
    	if(loanVO.getStatus() != LoanStatus.DAFAULTED && start.compareTo(last.getLoanByDate()) > 0)
    	{
    		last = last.getNextPeriod();
    	}
    	
    	for(int i = 1; i < repaymentTerm; i++)
    	{
    		last = last.getNextPeriod();
    	}
    	
    	loanVO.setExpectedEndDate(last.getEndDate());
    	
    	return loanVO;
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
    	
    	//loanVO = getLoanExpectedEndDate(loanVO, motshelo.getRepaymentTerm());
    	Interest interest = getLoanInterest(loanVO);     	
    	
    	//loanVO.setInstanceMember(getInstanceMemberDao().load(id));
    	Loan loan = getLoanDao().loanVOToEntity(loanVO);   
    	loan.setInstanceMember(getInstanceMemberDao().load(loan.getInstanceMember().getId()));
    	loan = getLoanDao().createOrUpdate(loan);
    	
    	// we have created a new interest on the load so we need to save it
    	if(interest.getAmount() != null)
    	{
    		interest.setLoan(loan);
    		interest = getInterestDao().createOrUpdate(interest);
    	}
    	
    	return getLoanDao().toLoanVO(loan);
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
	protected LoanVO handleAddInterest(LoanVO loanVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected LoanVO handleCalculateLoanEndDate(LoanVO loanVO) throws Exception {
		MotsheloInstanceVO motsheloInstanceVO = loanVO.getMotsheloInstance();
		Calendar cal = Calendar.getInstance();
		cal.setTime(loanVO.getStartDate());		
		cal.add(Calendar.MONTH, motsheloInstanceVO.getMotshelo().getRepaymentTerm());
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		loanVO.setExpectedEndDate(cal.getTime());
		
		return loanVO;
	}

	@Override
	protected Collection<LoanVO> handleGenerateContributionLoans(MotsheloInstanceVO motsheloInstanceVO)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}