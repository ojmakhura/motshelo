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
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.systemsjr.motshelo.instance.vo.MotsheloInstanceVO;
import com.systemsjr.motshelo.interest.vo.InterestVO;
import com.systemsjr.motshelo.loan.Loan;
import com.systemsjr.motshelo.loan.LoanStatus;
import com.systemsjr.motshelo.loan.vo.LoanSearchCriteria;
import com.systemsjr.motshelo.loan.vo.LoanVO;

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
    	
    	if(loanVO != null && loanVO.getId() != null)
    	{
    		MotsheloInstanceVO motsheloInstanceVO = loanVO.getMotsheloInstance();
    		loanVO.setStartDate(new Date());
    		loanVO.setStatus(LoanStatus.ACTIVE);
    		
    		InterestVO interestVO = new InterestVO();
    		interestVO.setType("STANDARD");    		
    		double amount = loanVO.getAmount().floatValue();
    		double interestAmount = motsheloInstanceVO.getMotshelo().getLoanInterest()/100 * amount;
    		interestVO.setAmount(new BigDecimal(interestAmount));
    		
    		double loanBalance = amount + interestAmount;
    		loanVO.setBalance(new BigDecimal(loanBalance));
    		
    		Calendar cal = Calendar.getInstance();
    		cal.add(Calendar.MONTH, motsheloInstanceVO.getMotshelo().getRepaymentTerm());
    		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
    		cal.set(Calendar.HOUR_OF_DAY, 23);
    		cal.set(Calendar.MINUTE, 59);
    		cal.set(Calendar.SECOND, 59);
    		cal.set(Calendar.MILLISECOND, 999);
    		loanVO.setExpectedEndDate(cal.getTime());
    	} else
    	{
    		
    	}
    	
    	Loan loan = getLoanDao().loanVOToEntity(loanVO);
    	
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

}