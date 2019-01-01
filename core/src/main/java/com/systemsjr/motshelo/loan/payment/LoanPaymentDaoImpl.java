// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on 01/01/2019 09:05:34+0200.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.systemsjr.motshelo.loan.payment;

import com.systemsjr.motshelo.loan.payment.vo.LoanPaymentSearchCriteria;
import com.systemsjr.motshelo.loan.payment.vo.LoanPaymentVO;
import java.util.Collection;
import org.springframework.stereotype.Repository;

/**
 * @see LoanPayment
 */
@Repository
public class LoanPaymentDaoImpl
    extends LoanPaymentDaoBase
{
    /**
     * {@inheritDoc}
     */
    @Override
    protected Collection<LoanPayment> handleFindByCriteria(LoanPaymentSearchCriteria searchCriteria)
    {
        // TODO implement public Collection<LoanPayment> handleFindByCriteria(LoanPaymentSearchCriteria searchCriteria)
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected LoanPayment handleGetBasicLoanPaymentEntity(LoanPaymentVO loanPaymentVO)
    {
        // TODO implement public LoanPayment handleGetBasicLoanPaymentEntity(LoanPaymentVO loanPaymentVO)
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected LoanPaymentVO handleGetBasicLoanPaymentVO(LoanPayment loanPayment)
    {
        // TODO implement public LoanPaymentVO handleGetBasicLoanPaymentVO(LoanPayment loanPayment)
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toLoanPaymentVO(
        LoanPayment source,
        LoanPaymentVO target)
    {
        // TODO verify behavior of toLoanPaymentVO
        super.toLoanPaymentVO(source, target);
        // WARNING! No conversion for target.loan (can't convert source.getLoan():com.systemsjr.motshelo.loan.Loan to com.systemsjr.motshelo.loan.vo.LoanVO
        // WARNING! No conversion for target.transaction (can't convert source.getTransaction():com.systemsjr.motshelo.transaction.Transaction to com.systemsjr.motshelo.transaction.vo.TransactionVO
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LoanPaymentVO toLoanPaymentVO(final LoanPayment entity)
    {
        // TODO verify behavior of toLoanPaymentVO
        return super.toLoanPaymentVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private LoanPayment loadLoanPaymentFromLoanPaymentVO(LoanPaymentVO loanPaymentVO)
    {
        // TODO implement loadLoanPaymentFromLoanPaymentVO
        throw new UnsupportedOperationException("com.systemsjr.motshelo.loan.payment.loadLoanPaymentFromLoanPaymentVO(LoanPaymentVO) not yet implemented.");

        /* A typical implementation looks like this:
        if (loanPaymentVO.getId() == null)
        {
            return  LoanPayment.Factory.newInstance();
        }
        else
        {
            return this.load(loanPaymentVO.getId());
        }
        */
    }

    /**
     * {@inheritDoc}
     */
    public LoanPayment loanPaymentVOToEntity(LoanPaymentVO loanPaymentVO)
    {
        // TODO verify behavior of loanPaymentVOToEntity
        LoanPayment entity = this.loadLoanPaymentFromLoanPaymentVO(loanPaymentVO);
        this.loanPaymentVOToEntity(loanPaymentVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loanPaymentVOToEntity(
        LoanPaymentVO source,
        LoanPayment target,
        boolean copyIfNull)
    {
        // TODO verify behavior of loanPaymentVOToEntity
        super.loanPaymentVOToEntity(source, target, copyIfNull);
    }
}