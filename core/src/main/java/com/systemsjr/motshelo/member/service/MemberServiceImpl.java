// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::motshelo::com.systemsjr.motshelo::member::service::MemberService
 * STEREOTYPE:  Service
 */
package com.systemsjr.motshelo.member.service;

import com.systemsjr.motshelo.loan.vo.LoanVO;
import com.systemsjr.motshelo.member.Member;
import com.systemsjr.motshelo.member.vo.MemberSearchCriteria;
import com.systemsjr.motshelo.member.vo.MemberVO;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Service;

/**
 * @see com.systemsjr.motshelo.member.service.MemberService
 */
@Service("com.systemsjr.motshelo.member.service.MemberService")
public class MemberServiceImpl
    extends MemberServiceBase
{

    /**
     * @see com.systemsjr.motshelo.member.service.MemberService#findById(Long)
     */
    @Override
    protected  MemberVO handleFindById(Long id)
        throws Exception
    {
    	if(id != null)
    	{
    		return getMemberDao().toMemberVO(getMemberDao().load(id));
    	}
    	
    	return null;
    }

    /**
     * @see com.systemsjr.motshelo.member.service.MemberService#saveMember(MemberVO)
     */
    @Override
    protected  MemberVO handleSaveMember(MemberVO memberVO)
        throws Exception
    {
    	Member member = getMemberDao().memberVOToEntity(memberVO);
    	member = getMemberDao().createOrUpdate(member);
    	return getMemberDao().toMemberVO(member);
    }

    /**
     * @see com.systemsjr.motshelo.member.service.MemberService#removeMember(MemberVO)
     */
    @Override
    protected  boolean handleRemoveMember(MemberVO memberVO)
        throws Exception
    {
    	if(memberVO.getId() != null)
    	{
    		getMemberDao().remove(memberVO.getId());
    		return true;
    	}
    	
    	return false;
    }

    /**
     * @see com.systemsjr.motshelo.member.service.MemberService#getAllMembers()
     */
    @Override
    protected  Collection handleGetAllMembers()
        throws Exception
    {
    	Collection members = getMemberDao().loadAll();
    	return getMemberDao().toMemberVOCollection(members);
    }

    /**
     * @see com.systemsjr.motshelo.member.service.MemberService#getAllMembersArray()
     */
    @Override
    protected  MemberVO[] handleGetAllMembersArray()
        throws Exception
    {
    	Collection members = getMemberDao().loadAll();
    	return getMemberDao().toMemberVOArray(members);
    }

    /**
     * @see com.systemsjr.motshelo.member.service.MemberService#searchMembers(MemberSearchCriteria)
     */
    @Override
    protected  Collection handleSearchMembers(MemberSearchCriteria searchCriteria)
        throws Exception
    {
    	Collection members = getMemberDao().findByCriteria(searchCriteria);
    	return getMemberDao().toMemberVOCollection(members);
    }

    /**
     * @see com.systemsjr.motshelo.member.service.MemberService#searchMembersArray(MemberSearchCriteria)
     */
    @Override
    protected  MemberVO[] handleSearchMembersArray(MemberSearchCriteria searchCriteria)
        throws Exception
    {
    	Collection members = getMemberDao().findByCriteria(searchCriteria);
    	return getMemberDao().toMemberVOArray(members);
    }

	@Override
	protected void handleUpdateMemberBalance(MemberVO memberVO) throws Exception {
		
		Collection<LoanVO> loanVOs = memberVO.getLoans();
		
	}

}