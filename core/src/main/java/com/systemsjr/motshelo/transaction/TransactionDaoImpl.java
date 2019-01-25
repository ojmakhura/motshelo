// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on 12/04/2018 18:18:01+0200.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.systemsjr.motshelo.transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.systemsjr.motshelo.instance.MotsheloInstance;
import com.systemsjr.motshelo.instance.member.InstanceMember;
import com.systemsjr.motshelo.instance.member.vo.InstanceMemberVO;
import com.systemsjr.motshelo.instance.vo.MotsheloInstanceVO;
import com.systemsjr.motshelo.loan.payment.LoanPayment;
import com.systemsjr.motshelo.loan.payment.vo.LoanPaymentVO;
import com.systemsjr.motshelo.transaction.vo.TransactionSearchCriteria;
import com.systemsjr.motshelo.transaction.vo.TransactionVO;

/**
 * @see Transaction
 */
@Repository
public class TransactionDaoImpl
    extends TransactionDaoBase
{
    /**
     * {@inheritDoc}
     */
    @Override
    public void toTransactionVO(
        Transaction source,
        TransactionVO target)
    {
        // TODO verify behavior of toTransactionVO
        super.toTransactionVO(source, target);
        // WARNING! No conversion for target.member (can't convert source.getMember():com.systemsjr.motshelo.member.Member to com.systemsjr.motshelo.member.vo.MemberVO
        if(source.getInstanceMember() != null)
        {
        	target.setInstanceMember(getInstanceMemberDao().getBasicInstanceMemberVO(source.getInstanceMember()));
        }
        
        if(source.getMotsheloInstance() != null)
        {
        	target.setMotsheloInstance(getMotsheloInstanceDao().getBasicMotsheloInstanceVO(source.getMotsheloInstance()));
        }
        
        target.setLoanPayments(new ArrayList<LoanPaymentVO>());
        for(LoanPayment loanPayment : source.getLoanPayments())
        {
        	target.getLoanPayments().add(getLoanPaymentDao().getBasicLoanPaymentVO(loanPayment));        	
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactionVO toTransactionVO(final Transaction entity)
    {
        // TODO verify behavior of toTransactionVO
        return super.toTransactionVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private Transaction loadTransactionFromTransactionVO(TransactionVO transactionVO)
    {
        Transaction transaction = Transaction.Factory.newInstance();
        
        if(transactionVO.getId() != null)
        {
        	transaction = this.load(transactionVO.getId());
        }
        
        return transaction;
    }

    /**
     * {@inheritDoc}
     */
    public Transaction transactionVOToEntity(TransactionVO transactionVO)
    {
        // TODO verify behavior of transactionVOToEntity
        Transaction entity = this.loadTransactionFromTransactionVO(transactionVO);
        this.transactionVOToEntity(transactionVO, entity, true);
        
        if(transactionVO.getInstanceMember() != null)
        {
        	entity.setInstanceMember(getInstanceMemberDao().getBasicInstanceMemberEntity(transactionVO.getInstanceMember()));
        }
        
        if(transactionVO.getMotsheloInstance() != null)
        {
        	entity.setMotsheloInstance(getMotsheloInstanceDao().getBasicMotsheloInstanceEntity(transactionVO.getMotsheloInstance()));
        }
        
        entity.setLoanPayments(new ArrayList<LoanPayment>());
        for(LoanPaymentVO loanPaymentVO : transactionVO.getLoanPayments())
        {
        	entity.addLoanPayments(getLoanPaymentDao().getBasicLoanPaymentEntity(loanPaymentVO));
        }

        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transactionVOToEntity(
        TransactionVO source,
        Transaction target,
        boolean copyIfNull)
    {
        // TODO verify behavior of transactionVOToEntity
        super.transactionVOToEntity(source, target, copyIfNull);
    }

	@Override
	protected Collection<Transaction> handleFindByCriteria(TransactionSearchCriteria searchCriteria) throws Exception 
	{
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
    	CriteriaQuery<Transaction> query = builder.createQuery(Transaction.class);
    	Root<Transaction> root = query.from(Transaction.class);   
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(searchCriteria.getInstanceMember() != null && searchCriteria.getInstanceMember().getId() != null)
		{
			Join<Transaction, InstanceMember> join = root.join("instanceMember", JoinType.INNER);
			predicates.add(builder.equal(join.<Long>get("id"), searchCriteria.getInstanceMember().getId()));
		}

		if(searchCriteria.getMotsheloInstance() != null && searchCriteria.getMotsheloInstance().getId() != null)
		{
			Join<Transaction, MotsheloInstance> join = root.join("motsheloInstance", JoinType.INNER);
			predicates.add(builder.equal(join.<Long>get("id"), searchCriteria.getMotsheloInstance().getId()));
		}
		
		if(searchCriteria.getRemainingAmount() != null)
		{
			predicates.add(builder.greaterThan(root.<BigDecimal>get("remainingAmount"), searchCriteria.getRemainingAmount()));
		}
		
		if(searchCriteria.getMaxDate() != null)
		{
			predicates.add(builder.lessThanOrEqualTo(root.<Date>get("transactionDate"), searchCriteria.getMaxDate()));
		}
		
		if(searchCriteria.getMinDate() != null)
		{
			predicates.add(builder.greaterThanOrEqualTo(root.<Date>get("transactionDate"), searchCriteria.getMinDate()));
		}
		
		if(searchCriteria.getTransactionMode() != null)
		{
			predicates.add(builder.equal(root.<String>get("transactionMode"), searchCriteria.getTransactionMode().getValue()));
		}

		if(searchCriteria.getTransactionType() != null)
		{
			predicates.add(builder.equal(root.<String>get("transactionType"), searchCriteria.getTransactionType().getValue()));
		}

		if(!predicates.isEmpty()) {
			query.where();
	        Predicate[] pr = new Predicate[predicates.size()];
	        predicates.toArray(pr);
	        query.where(pr); 
		}
		
		query.orderBy(builder.desc(root.get("transactionDate")));
		TypedQuery<Transaction> typedQuery = getSession().createQuery(query);
		return typedQuery.getResultList();
	}

	@Override
	protected Transaction handleGetBasicTransactionEntity(TransactionVO transactionVO) throws Exception {
		
		Transaction transaction = Transaction.Factory.newInstance();
		transaction.setId(transactionVO.getId());
		super.transactionVOToEntity(transactionVO, transaction, true);
		transaction.setInstanceMember(getInstanceMemberDao().load(transactionVO.getInstanceMember().getId()));
		transaction.setMotsheloInstance(getMotsheloInstanceDao().load(transactionVO.getMotsheloInstance().getId()));
		
		Collection<LoanPayment> payments = new ArrayList<LoanPayment>();
		for(LoanPaymentVO payment : transactionVO.getLoanPayments())
		{
			payments.add(getLoanPaymentDao().getBasicLoanPaymentEntity(payment));
		}
		transaction.setLoanPayments(payments);
		
		return transaction;
	}

	@Override
	protected TransactionVO handleGetBasicTransactionVO(Transaction transaction) throws Exception {
		
		TransactionVO vo = new TransactionVO();
		super.toTransactionVO(transaction, vo);
		
		if(transaction.getInstanceMember() != null)
		{
			vo.setInstanceMember(getInstanceMemberDao().getBasicInstanceMemberVO(transaction.getInstanceMember()));
		}
		
		if(transaction.getMotsheloInstance() != null)
		{
			vo.setMotsheloInstance(getMotsheloInstanceDao().getBasicMotsheloInstanceVO(transaction.getMotsheloInstance()));
		}
		
		return vo;
	}
}