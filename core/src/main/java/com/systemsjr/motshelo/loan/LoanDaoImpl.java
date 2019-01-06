// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on 12/10/2018 08:47:25+0200.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.systemsjr.motshelo.loan;

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
import com.systemsjr.motshelo.instance.period.InstancePeriod;
import com.systemsjr.motshelo.interest.Interest;
import com.systemsjr.motshelo.interest.vo.InterestVO;
import com.systemsjr.motshelo.loan.payment.LoanPayment;
import com.systemsjr.motshelo.loan.payment.vo.LoanPaymentVO;
import com.systemsjr.motshelo.loan.vo.LoanSearchCriteria;
import com.systemsjr.motshelo.loan.vo.LoanVO;

/**
 * @see Loan
 */
@Repository
public class LoanDaoImpl
    extends LoanDaoBase
{
    /**
     * {@inheritDoc}
     */
    @Override
    public void toLoanVO(
        Loan source,
        LoanVO target)
    {
        // TODO verify behavior of toLoanVO
        super.toLoanVO(source, target);
        BigDecimal amount = new BigDecimal(0);
        amount = amount.add(source.getAmount());
        if(source.getInstanceMember() != null)
        {
        	target.setInstanceMember(getInstanceMemberDao().getBasicInstanceMemberVO(source.getInstanceMember()));
        }
        // WARNING! No conversion for target.motshelo (can't convert source.getMotshelo():com.systemsjr.motshelo.Motshelo to com.systemsjr.motshelo.vo.MotsheloVO
        if(source.getMotsheloInstance() != null)
        {
        	target.setMotsheloInstance(getMotsheloInstanceDao().getBasicMotsheloInstanceVO(source.getMotsheloInstance()));
        }
        target.setInterests(new ArrayList<InterestVO>());
        for(Interest interest : source.getInterests())
        {
        	target.getInterests().add(getInterestDao().getBasicInterestVO(interest));
        	amount = amount.add(interest.getAmount());
        	
        }
        target.setLoanPayments(new ArrayList<LoanPaymentVO>());
        for(LoanPayment loanPayment : source.getLoanPayments())
        {
        	target.getLoanPayments().add(getLoanPaymentDao().getBasicLoanPaymentVO(loanPayment));        	
        	amount = amount.subtract(loanPayment.getPaymentAmount());
        }
        target.setBalance(amount);
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LoanVO toLoanVO(final Loan entity)
    {
        // TODO verify behavior of toLoanVO
        return super.toLoanVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private Loan loadLoanFromLoanVO(LoanVO loanVO)
    {
    	Loan loan = Loan.Factory.newInstance();
    	
    	if(loanVO.getId() != null)
    	{
    		loan = this.load(loanVO.getId());
    	}
    	
    	return loan;
    }

    /**
     * {@inheritDoc}
     */
    public Loan loanVOToEntity(LoanVO loanVO)
    {
        // TODO verify behavior of loanVOToEntity
        Loan entity = this.loadLoanFromLoanVO(loanVO);
        this.loanVOToEntity(loanVO, entity, true);
        
        if(loanVO.getInstanceMember() != null)
        {
        	entity.setInstanceMember(getInstanceMemberDao().getBasicInstanceMemberEntity(loanVO.getInstanceMember()));
        }
        
        if(loanVO.getMotsheloInstance() != null)
        {
        	entity.setMotsheloInstance(getMotsheloInstanceDao().getBasicMotsheloInstanceEntity(loanVO.getMotsheloInstance()));
        }
        
        Collection<InterestVO> interests = loanVO.getInterests();
        entity.setInterests(new ArrayList<Interest>());
        for(InterestVO interest : interests)
        {
        	entity.addInterests(getInterestDao().getBasicInterestEntity(interest));
        }
        
        entity.setLoanPayments(new ArrayList<LoanPayment>());
        for(LoanPaymentVO loanPaymentVO : loanVO.getLoanPayments())
        {
        	entity.addLoanPayments(getLoanPaymentDao().getBasicLoanPaymentEntity(loanPaymentVO));
        }
        
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loanVOToEntity(
        LoanVO source,
        Loan target,
        boolean copyIfNull)
    {
        // TODO verify behavior of loanVOToEntity
        super.loanVOToEntity(source, target, copyIfNull);
    }

	@Override
	protected Collection<Loan> handleFindByCriteria(LoanSearchCriteria searchCriteria) throws Exception {

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
    	CriteriaQuery<Loan> query = builder.createQuery(Loan.class);
    	Root<Loan> root = query.from(Loan.class);   
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(searchCriteria.getInstanceMember() != null && searchCriteria.getInstanceMember().getId() != null)
		{
			Join<Loan, InstanceMember> joinMember = root.join("instanceMember", JoinType.INNER);
			predicates.add(builder.equal(joinMember.<Long>get("id"), searchCriteria.getInstanceMember().getId() ));
		}
		
		if(searchCriteria.getMotsheloInstance() != null && searchCriteria.getMotsheloInstance().getId() != null)
		{
			Join<Loan, MotsheloInstance> joinMotsheloInstance = root.join("instanceMember", JoinType.INNER);
			predicates.add(builder.equal(joinMotsheloInstance.<Long>get("id"), searchCriteria.getMotsheloInstance().getId() ));
		}
		
		if(searchCriteria.getStatus() != null)
		{
			predicates.add(builder.equal(root.<String>get("status"), searchCriteria.getStatus().getValue()));
		}
		
		if(searchCriteria.getType() != null)
		{
			predicates.add(builder.equal(root.<String>get("type"), searchCriteria.getType().getValue()));
		}
		
		if(searchCriteria.getMaxDate() != null)
		{
			predicates.add(builder.lessThanOrEqualTo(root.<Date>get("startDate"), searchCriteria.getMaxDate()));
		}
		
		if(searchCriteria.getMinDate() != null)
		{
			predicates.add(builder.greaterThanOrEqualTo(root.<Date>get("startDate"), searchCriteria.getMinDate()));
		}
		
		if(searchCriteria.getMaxAmount() != null)
		{
			predicates.add(builder.lessThanOrEqualTo(root.<BigDecimal>get("amount"), searchCriteria.getMaxAmount()));
		}
		
		if(searchCriteria.getMinAmount() != null)
		{
			predicates.add(builder.greaterThanOrEqualTo(root.<BigDecimal>get("amount"), searchCriteria.getMaxAmount()));
		}
		
		if(!predicates.isEmpty()) {
			query.where();
	        Predicate[] pr = new Predicate[predicates.size()];
	        predicates.toArray(pr);
	        query.where(pr); 
		}
		
		query.orderBy(builder.desc(root.get("startDate")));
		TypedQuery<Loan> typedQuery = getSession().createQuery(query);
		return typedQuery.getResultList();
	}

	@Override
	protected Loan handleGetBasicLoanEntity(LoanVO loanVO) throws Exception {
		
		Loan loan = Loan.Factory.newInstance();
		loan.setId(loanVO.getId());
		super.loanVOToEntity(loanVO, loan, true);
		
		if(loanVO.getInstanceMember() != null) {
			loan.setInstanceMember(getInstanceMemberDao().getBasicInstanceMemberEntity(loanVO.getInstanceMember()));
		}
		
		if(loanVO.getMotsheloInstance() != null) {
			loan.setMotsheloInstance(getMotsheloInstanceDao().getBasicMotsheloInstanceEntity(loanVO.getMotsheloInstance()));
		}
		
		Collection<Interest> interests = new ArrayList<Interest>();
		for(InterestVO interest : loanVO.getInterests())
		{
			interests.add(getInterestDao().getBasicInterestEntity(interest));
		}
		loan.setInterests(interests);
		
		Collection<LoanPayment> payments = new ArrayList<LoanPayment>();
		for(LoanPaymentVO payment : loanVO.getLoanPayments())
		{
			payments.add(getLoanPaymentDao().getBasicLoanPaymentEntity(payment));
		}
		loan.setLoanPayments(payments);
				
		return loan;
	}

	@Override
	protected LoanVO handleGetBasicLoanVO(Loan loan) throws Exception {
		
		LoanVO loanVO = new LoanVO();
		super.toLoanVO(loan, loanVO);
		BigDecimal balance = new BigDecimal(0);
		balance = balance.add(loan.getAmount());
		if(loan.getInstanceMember() != null) {			
			loanVO.setInstanceMember(getInstanceMemberDao().getBasicInstanceMemberVO(loan.getInstanceMember()));
		}
		
		if(loan.getMotsheloInstance() != null) {
			
			loanVO.setMotsheloInstance(getMotsheloInstanceDao().getBasicMotsheloInstanceVO(loan.getMotsheloInstance()));
		}
				
		for(Interest interest : loan.getInterests())
		{
			balance = balance.add(interest.getAmount());
		}

		for(LoanPayment payment : loan.getLoanPayments())
		{
			balance = balance.subtract(payment.getPaymentAmount());	
		}
		loanVO.setBalance(balance);
		
		return loanVO;
	}

	@Override
	protected BigDecimal handleCalculateLoanBalance(Loan loan) throws Exception {
		BigDecimal balance = loan.getAmount();
		
		for(Interest interest : loan.getInterests())
		{
			balance = balance.add(interest.getAmount());
		}
		
		for(LoanPayment payment : loan.getLoanPayments())
		{
			balance = balance.subtract(payment.getPaymentAmount());
		}
		
		return balance;
	}

	@Override
	protected Loan handleCreateContributionLoan(InstanceMember instanceMember, InstancePeriod instancePeriod)
			throws Exception {
		
		Loan loan = new Loan();
		MotsheloInstance instance = instancePeriod.getMotsheloInstance();
		loan.setAmount(instance.getMotshelo().getMonthlyContribution());
		loan.setExpectedEndDate(instancePeriod.getEndDate());
		loan.setStartDate(instancePeriod.getStartDate());
		loan.setInstanceMember(instanceMember);
		loan.setMotsheloInstance(instance);
		loan.setType(LoanType.INTERESTFREE);
		loan.setStatus(LoanStatus.ACTIVE);
		
		return this.create(loan);
	}
}