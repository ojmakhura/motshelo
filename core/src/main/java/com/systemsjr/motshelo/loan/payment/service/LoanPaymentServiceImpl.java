// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::motshelo::com.systemsjr.motshelo::loan::payment::service::LoanPaymentService
 * STEREOTYPE:  Service
 */
package com.systemsjr.motshelo.loan.payment.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.systemsjr.motshelo.loan.payment.LoanPayment;
import com.systemsjr.motshelo.loan.payment.vo.LoanPaymentSearchCriteria;
import com.systemsjr.motshelo.loan.payment.vo.LoanPaymentVO;

/**
 * @see com.systemsjr.motshelo.loan.payment.service.LoanPaymentService
 */
@Service("com.systemsjr.motshelo.loan.payment.service.LoanPaymentService")
public class LoanPaymentServiceImpl
    extends LoanPaymentServiceBase
{

    /**
     * @see com.systemsjr.motshelo.loan.payment.service.LoanPaymentService#findById(Long)
     */
    @Override
    protected  LoanPaymentVO handleFindById(Long id)
        throws Exception
    {
    	return id == null ? null : getLoanPaymentDao().toLoanPaymentVO(getLoanPaymentDao().load(id));
    }

    /**
     * @see com.systemsjr.motshelo.loan.payment.service.LoanPaymentService#saveLoanPayment(LoanPaymentVO)
     */
    @Override
    protected  LoanPaymentVO handleSaveLoanPayment(LoanPaymentVO loanPaymentVO)
        throws Exception
    {
    	LoanPayment payment = getLoanPaymentDao().loanPaymentVOToEntity(loanPaymentVO);
    	payment = getLoanPaymentDao().createOrUpdate(payment);
    	
    	return getLoanPaymentDao().toLoanPaymentVO(payment);
    }

    /**
     * @see com.systemsjr.motshelo.loan.payment.service.LoanPaymentService#removeLoanPayment(LoanPaymentVO)
     */
    @Override
    protected  boolean handleRemoveLoanPayment(LoanPaymentVO loanPaymentVO)
        throws Exception
    {
    	if(loanPaymentVO != null && loanPaymentVO.getId() != null)
    	{
    		getLoanPaymentDao().remove(loanPaymentVO.getId());
    		return true;
    	}
    	
    	return false;
    }

    /**
     * @see com.systemsjr.motshelo.loan.payment.service.LoanPaymentService#getAllLoanPayments()
     */
    @Override
    protected  Collection<LoanPaymentVO> handleGetAllLoanPayments()
        throws Exception
    {
    	return getLoanPaymentDao().toLoanPaymentVOCollection(getLoanPaymentDao().loadAll());
    }

    /**
     * @see com.systemsjr.motshelo.loan.payment.service.LoanPaymentService#getAllLoanPaymentsArray()
     */
    @Override
    protected  LoanPaymentVO[] handleGetAllLoanPaymentsArray()
        throws Exception
    {
    	return getLoanPaymentDao().toLoanPaymentVOArray(getLoanPaymentDao().loadAll());
    }

    /**
     * @see com.systemsjr.motshelo.loan.payment.service.LoanPaymentService#searchLoanPayments(LoanPaymentSearchCriteria)
     */
    @Override
    protected  Collection<LoanPaymentVO> handleSearchLoanPayments(LoanPaymentSearchCriteria searchCriteria)
        throws Exception
    {
    	return getLoanPaymentDao().toLoanPaymentVOCollection(getLoanPaymentDao().findByCriteria(searchCriteria));
    }
       

    /**
     * @see com.systemsjr.motshelo.loan.payment.service.LoanPaymentService#searchLoanPaymentsArray(LoanPaymentSearchCriteria)
     */
    @Override
    protected  LoanPaymentVO[] handleSearchLoanPaymentsArray(LoanPaymentSearchCriteria searchCriteria)
        throws Exception
    {
    	return getLoanPaymentDao().toLoanPaymentVOArray(getLoanPaymentDao().findByCriteria(searchCriteria));
    }

}