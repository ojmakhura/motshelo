// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::motshelo::com.systemsjr.motshelo::instance::member::service::InstanceMemberService
 * STEREOTYPE:  Service
 */
package com.systemsjr.motshelo.instance.member.service;

import com.systemsjr.motshelo.instance.member.InstanceMember;
import com.systemsjr.motshelo.instance.member.vo.InstanceMemberSearchCriteria;
import com.systemsjr.motshelo.instance.member.vo.InstanceMemberVO;
import com.systemsjr.motshelo.instance.vo.MotsheloInstanceSearchCriteria;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * @see com.systemsjr.motshelo.instance.member.service.InstanceMemberService
 */
@Service("com.systemsjr.motshelo.instance.member.service.InstanceMemberService")
public class InstanceMemberServiceImpl
    extends InstanceMemberServiceBase
{

    /**
     * @see com.systemsjr.motshelo.instance.member.service.InstanceMemberService#findById(Long)
     */
    @Override
    protected  InstanceMemberVO handleFindById(Long id)
        throws Exception
    {
    	if(id != null)
    	{
    		return getInstanceMemberDao().toInstanceMemberVO(getInstanceMemberDao().load(id));
    	}
    	
    	return null;
    }

    /**
     * @see com.systemsjr.motshelo.instance.member.service.InstanceMemberService#saveInstanceMember(InstanceMemberVO)
     */
    @Override
    protected  InstanceMemberVO handleSaveInstanceMember(InstanceMemberVO instanceMemberVO)
        throws Exception
    {
    	InstanceMember instanceMember = getInstanceMemberDao().instanceMemberVOToEntity(instanceMemberVO);
    	instanceMember = getInstanceMemberDao().createOrUpdate(instanceMember);
    	return getInstanceMemberDao().toInstanceMemberVO(instanceMember);
    }

    /**
     * @see com.systemsjr.motshelo.instance.member.service.InstanceMemberService#removeInstanceMember(InstanceMemberVO)
     */
    @Override
    protected  boolean handleRemoveInstanceMember(InstanceMemberVO instanceMemberVO)
        throws Exception
    {
    	
    	if(instanceMemberVO.getId() != null)
    	{
    		getInstanceMemberDao().remove(instanceMemberVO.getId());
    	}
    	
    	return false;
    }

    /**
     * @see com.systemsjr.motshelo.instance.member.service.InstanceMemberService#getAllInstanceMembers()
     */
    @Override
    protected  Collection<InstanceMemberVO> handleGetAllInstanceMembers()
        throws Exception
    {
    	Collection<InstanceMember> instanceMembers = getInstanceMemberDao().loadAll();
    	return getInstanceMemberDao().toInstanceMemberVOCollection(instanceMembers);
    }

    /**
     * @see com.systemsjr.motshelo.instance.member.service.InstanceMemberService#getAllInstanceMembersArray()
     */
    @Override
    protected  InstanceMemberVO[] handleGetAllInstanceMembersArray()
        throws Exception
    {
    	Collection<InstanceMember> instanceMembers = getInstanceMemberDao().loadAll();
    	return getInstanceMemberDao().toInstanceMemberVOArray(instanceMembers);
    }

    /**
     * @see com.systemsjr.motshelo.instance.member.service.InstanceMemberService#searchMetshelo(InstanceMemberSearchCriteria)
     */
    @Override
    protected  Collection<InstanceMemberVO> handleSearchMetshelo(InstanceMemberSearchCriteria searchCriteria)
        throws Exception
    {
    	Collection<InstanceMember> instanceMembers = getInstanceMemberDao().findByCriteria(searchCriteria);
    	return getInstanceMemberDao().toInstanceMemberVOCollection(instanceMembers);
    }

    /**
     * @see com.systemsjr.motshelo.instance.member.service.InstanceMemberService#searchInstanceMembersArray(MotsheloInstanceSearchCriteria)
     */
    @Override
    protected  InstanceMemberVO[] handleSearchInstanceMembersArray(InstanceMemberSearchCriteria searchCriteria)
        throws Exception
    {
    	Collection<InstanceMember> instanceMembers = getInstanceMemberDao().findByCriteria(searchCriteria);
    	return getInstanceMemberDao().toInstanceMemberVOArray(instanceMembers);
    }

}