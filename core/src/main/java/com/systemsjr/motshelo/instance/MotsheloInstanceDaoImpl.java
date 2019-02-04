// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on 12/19/2018 14:07:02+0200.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.systemsjr.motshelo.instance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.systemsjr.motshelo.instance.member.InstanceMember;
import com.systemsjr.motshelo.instance.member.vo.InstanceMemberVO;
import com.systemsjr.motshelo.instance.vo.MotsheloInstanceSearchCriteria;
import com.systemsjr.motshelo.instance.vo.MotsheloInstanceVO;
import com.systemsjr.motshelo.interest.Interest;
import com.systemsjr.motshelo.interest.vo.InterestVO;
import com.systemsjr.motshelo.loan.Loan;
import com.systemsjr.motshelo.loan.LoanType;
import com.systemsjr.motshelo.loan.vo.LoanVO;
import com.systemsjr.motshelo.transaction.Transaction;
import com.systemsjr.motshelo.transaction.vo.TransactionVO;

/**
 * @see MotsheloInstance
 */
@Repository
public class MotsheloInstanceDaoImpl extends MotsheloInstanceDaoBase {
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Collection<MotsheloInstance> handleFindByCriteria(MotsheloInstanceSearchCriteria searchCriteria) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<MotsheloInstance> query = builder.createQuery(MotsheloInstance.class);
		Root<MotsheloInstance> root = query.from(MotsheloInstance.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (searchCriteria.getInstanceName() != null) {
			predicates
					.add(builder.like(root.<String>get("instanceName"), "%" + searchCriteria.getInstanceName() + "%"));
		}

		if (searchCriteria.getMaxYear() != null) {
			predicates.add(builder.le(root.<Integer>get("maxYear"), searchCriteria.getMaxYear()));
		}

		if (searchCriteria.getMinYear() != null) {
			predicates.add(builder.ge(root.<Integer>get("minYear"), searchCriteria.getMinYear()));
		}

		if (!predicates.isEmpty()) {
			query.where();
			Predicate[] pr = new Predicate[predicates.size()];
			predicates.toArray(pr);
			query.where(pr);
		}

		query.orderBy(builder.asc(root.get("instanceName")));
		TypedQuery<MotsheloInstance> typedQuery = getSession().createQuery(query);
		return typedQuery.getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void toMotsheloInstanceVO(MotsheloInstance source, MotsheloInstanceVO target) {
		// TODO verify behavior of toMotsheloInstanceVO
		super.toMotsheloInstanceVO(source, target);
		// WARNING! No conversion for target.motshelo (can't convert
		// source.getMotshelo():com.systemsjr.motshelo.Motshelo to
		// com.systemsjr.motshelo.vo.MotsheloVO
		if (source.getMotshelo() != null) {
			target.setMotshelo(getMotsheloDao().getBasicMotsheloVO(source.getMotshelo()));
		}

		// WARNING! No conversion for target.members (can't convert
		// source.getInstanceMembers():com.systemsjr.motshelo.member.Member to
		// com.systemsjr.motshelo.member.vo.MemberVO
		target.setInstanceMembers(new ArrayList<InstanceMemberVO>());
		for (InstanceMember member : source.getInstanceMembers()) {
			target.getInstanceMembers().add(getInstanceMemberDao().getBasicInstanceMemberVO(member));
		}

		// WARNING! No conversion for target.transactions (can't convert
		// source.getTransactions():com.systemsjr.motshelo.transaction.Transaction to
		// com.systemsjr.motshelo.transaction.vo.TransactionVO
		target.setLoans(new ArrayList<LoanVO>());
		for (Loan loan : source.getLoans()) {
			LoanVO lv = getLoanDao().getBasicLoanVO(loan);
			target.getLoans().add(lv);
		}

		// WARNING! No conversion for target.loans (can't convert
		// source.getLoans():com.systemsjr.motshelo.loan.Loan to
		// com.systemsjr.motshelo.loan.vo.LoanVO
		target.setTransactions(new ArrayList<TransactionVO>());
		for (Transaction trans : source.getTransactions()) {
			target.getTransactions().add(getTransactionDao().getBasicTransactionVO(trans));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MotsheloInstanceVO toMotsheloInstanceVO(final MotsheloInstance entity) {
		// TODO verify behavior of toMotsheloInstanceVO
		return super.toMotsheloInstanceVO(entity);
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the object
	 * store, a new, blank entity is created
	 */
	private MotsheloInstance loadMotsheloInstanceFromMotsheloInstanceVO(MotsheloInstanceVO motsheloInstanceVO) {
		MotsheloInstance instance = MotsheloInstance.Factory.newInstance();

		if (motsheloInstanceVO != null && motsheloInstanceVO.getId() != null) {
			instance = this.load(motsheloInstanceVO.getId());
		}

		return instance;
	}

	/**
	 * {@inheritDoc}
	 */
	public MotsheloInstance motsheloInstanceVOToEntity(MotsheloInstanceVO motsheloInstanceVO) {
		// TODO verify behavior of motsheloInstanceVOToEntity
		MotsheloInstance entity = this.loadMotsheloInstanceFromMotsheloInstanceVO(motsheloInstanceVO);
		this.motsheloInstanceVOToEntity(motsheloInstanceVO, entity, true);

		if (motsheloInstanceVO.getMotshelo() != null) {
			entity.setMotshelo(getMotsheloDao().getBasicMotsheloEntity(motsheloInstanceVO.getMotshelo()));
		}

		Collection<InstanceMemberVO> instanceMembers = motsheloInstanceVO.getInstanceMembers();
		entity.setInstanceMembers(new ArrayList<InstanceMember>());
		for (InstanceMemberVO instanceMember : instanceMembers) {
			entity.addInstanceMembers(getInstanceMemberDao().getBasicInstanceMemberEntity(instanceMember));
		}

		Collection<LoanVO> loans = motsheloInstanceVO.getLoans();
		entity.setLoans(new ArrayList<Loan>());
		for (LoanVO loan : loans) {
			entity.addLoans(getLoanDao().getBasicLoanEntity(loan));
		}

		Collection<TransactionVO> transactions = motsheloInstanceVO.getTransactions();
		entity.setTransactions(new ArrayList<Transaction>());
		for (TransactionVO transaction : transactions) {
			entity.addTransactions(getTransactionDao().getBasicTransactionEntity(transaction));
		}

		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void motsheloInstanceVOToEntity(MotsheloInstanceVO source, MotsheloInstance target, boolean copyIfNull) {
		// TODO verify behavior of motsheloInstanceVOToEntity
		super.motsheloInstanceVOToEntity(source, target, copyIfNull);
	}

	@Override
	protected Collection<MotsheloInstance> handleFindByInstanceName(String instanceName) throws Exception {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<MotsheloInstance> query = builder.createQuery(MotsheloInstance.class);
		Root<MotsheloInstance> root = query.from(MotsheloInstance.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (instanceName != null) {
			predicates.add(builder.equal(root.<String>get("instanceName"), instanceName));
		}

		if (!predicates.isEmpty()) {
			query.where();
			Predicate[] pr = new Predicate[predicates.size()];
			predicates.toArray(pr);
			query.where(pr);
		}

		TypedQuery<MotsheloInstance> typedQuery = getSession().createQuery(query);
		return typedQuery.getResultList();
	}

	@Override
	protected MotsheloInstanceVO handleGetBasicMotsheloInstanceVO(MotsheloInstance motsheloInstance) throws Exception {

		MotsheloInstanceVO vo = new MotsheloInstanceVO();
		super.toMotsheloInstanceVO(motsheloInstance, vo);
		if (motsheloInstance.getMotshelo() != null) {
			vo.setMotshelo(getMotsheloDao().getBasicMotsheloVO(motsheloInstance.getMotshelo()));
		}

		return vo;
	}

	@Override
	protected MotsheloInstance handleGetBasicMotsheloInstanceEntity(MotsheloInstanceVO motsheloInstanceVO)
			throws Exception {
		MotsheloInstance entity = MotsheloInstance.Factory.newInstance();
		entity.setId(motsheloInstanceVO.getId());
		super.motsheloInstanceVOToEntity(motsheloInstanceVO, entity, true);
		if (motsheloInstanceVO.getMotshelo() != null) {
			entity.setMotshelo(getMotsheloDao().getBasicMotsheloEntity(motsheloInstanceVO.getMotshelo()));
		}

		return entity;
	}

	@Override
	protected MotsheloInstance handleUpdateMotsheloInstance(MotsheloInstance motsheloInstance) throws Exception {
		/// Update cummulative balance
		BigDecimal cummulative = motsheloInstance.getStartingBalance();
		BigDecimal balance = motsheloInstance.getStartingBalance();

		for (Loan loan : motsheloInstance.getLoans()) {
			if (loan.getType() == LoanType.CONTRIBUTION) {
				cummulative = cummulative.add(loan.getAmount());
			} else {
				// Subtract the standard loans from the main balance
				balance = balance.subtract(loan.getAmount());
			}

			for (Interest interest : loan.getInterests()) {
				cummulative = cummulative.add(interest.getAmount());
			}
		}

		// Update main balance
		for (Transaction transaction : motsheloInstance.getTransactions()) {
			balance = balance.add(transaction.getTransactionAmount());
		}
		
		for(InstanceMember member : motsheloInstance.getInstanceMembers())
		{
			getInstanceMemberDao().updateInstanceMemberBalance(member);
		}

		motsheloInstance.setCummulativeBalance(cummulative);
		motsheloInstance.setBalance(balance);

		this.update(motsheloInstance);
		return motsheloInstance;
	}
}