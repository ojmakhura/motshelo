// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on 12/04/2018 18:18:01+0200.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.systemsjr.motshelo.member;

import com.systemsjr.motshelo.instance.member.vo.InstanceMemberVO;
import com.systemsjr.motshelo.loan.vo.LoanVO;
import com.systemsjr.motshelo.member.vo.MemberSearchCriteria;
import com.systemsjr.motshelo.member.vo.MemberVO;
import com.systemsjr.motshelo.transaction.vo.TransactionVO;
import com.systemsjr.motshelo.vo.MotsheloVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

/**
 * @see Member
 */
@Repository
public class MemberDaoImpl
    extends MemberDaoBase
{
    /**
     * {@inheritDoc}
     */
    @Override
    public void toMemberVO(
        Member source,
        MemberVO target)
    {
        // TODO verify behavior of toMemberVO
        super.toMemberVO(source, target);
        // WARNING! No conversion for target.transactions (can't convert source.getTransactions():com.systemsjr.motshelo.transaction.Transaction to java.util.Collection
        if(!CollectionUtils.isEmpty(source.getInstanceMembers()))
        {
        	target.setInstanceMembers(getInstanceMemberDao().toInstanceMemberVOCollection(source.getInstanceMembers()));
        }
        
        if(!CollectionUtils.isEmpty(source.getMotshelos()))
        {
        	target.setMotshelos(getMotsheloDao().toMotsheloVOCollection(source.getMotshelos()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MemberVO toMemberVO(final Member entity)
    {
        // TODO verify behavior of toMemberVO
        return super.toMemberVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private Member loadMemberFromMemberVO(MemberVO memberVO)
    {
        Member member = Member.Factory.newInstance();
        
        if(memberVO.getId() != null)
        {
        	member = this.load(memberVO.getId());
        }
        
        return member;
    }

    /**
     * {@inheritDoc}
     */
    public Member memberVOToEntity(MemberVO memberVO)
    {
        // TODO verify behavior of memberVOToEntity
        Member entity = this.loadMemberFromMemberVO(memberVO);
        this.memberVOToEntity(memberVO, entity, true);
        
        
        Collection<MotsheloVO> motsheloVOs = memberVO.getMotshelos();
        for(MotsheloVO motsheloVO : motsheloVOs)
        {
        	entity.addMotshelos(getMotsheloDao().motsheloVOToEntity(motsheloVO));
        }
        
        Collection<InstanceMemberVO> instanceMembers = memberVO.getInstanceMembers();
        for(InstanceMemberVO instanceMember : instanceMembers)
        {
        	entity.addInstanceMembers(getInstanceMemberDao().instanceMemberVOToEntity(instanceMember));
        }
        
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void memberVOToEntity(
        MemberVO source,
        Member target,
        boolean copyIfNull)
    {
        // TODO verify behavior of memberVOToEntity
        super.memberVOToEntity(source, target, copyIfNull);
    }

	@Override
	protected Collection<Member> handleFindByCriteria(MemberSearchCriteria searchCriteria) throws Exception {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
    	CriteriaQuery<Member> query = builder.createQuery(Member.class);
    	Root<Member> root = query.from(Member.class);   
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(searchCriteria.getName() != null)
		{
			predicates.add(builder.like(root.<String>get("name"), "%" + searchCriteria.getName() + "%"));
		}
		
		if(searchCriteria.getStatus() != null)
		{
			predicates.add(builder.equal(root.<String>get("status"), searchCriteria.getStatus().getValue()));
		}
		
		if(searchCriteria.getSurname() != null)
		{
			predicates.add(builder.like(root.<String>get("surname"), "%" + searchCriteria.getSurname() + "%"));
		}
		
		if(searchCriteria.getUsername() != null)
		{
			predicates.add(builder.like(root.<String>get("username"), "%" + searchCriteria.getUsername() + "%"));
		}
		
		if(!predicates.isEmpty()) {
			query.where();
	        Predicate[] pr = new Predicate[predicates.size()];
	        predicates.toArray(pr);
	        query.where(pr); 
		}
		
		query.orderBy(builder.asc(root.get("username")));
		TypedQuery<Member> typedQuery = getSession().createQuery(query);
		return typedQuery.getResultList();
	}

	@Override
	protected Member handleFindByUsername(String username) throws Exception {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
    	CriteriaQuery<Member> query = builder.createQuery(Member.class);
    	Root<Member> root = query.from(Member.class);   
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(username != null)
		{
			predicates.add(builder.equal(root.<String>get("username"), username));
		}
		
		if(!predicates.isEmpty()) {
			query.where();
	        Predicate[] pr = new Predicate[predicates.size()];
	        predicates.toArray(pr);
	        query.where(pr); 
		}
		
		//query.orderBy(builder.asc(root.get("username")));
		TypedQuery<Member> typedQuery = getSession().createQuery(query);
		
		return typedQuery.getResultList().get(0);
	}

}