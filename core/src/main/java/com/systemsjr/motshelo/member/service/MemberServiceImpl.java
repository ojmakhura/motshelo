// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::motshelo::com.systemsjr.motshelo::member::service::MemberService
 * STEREOTYPE:  Service
 */
package com.systemsjr.motshelo.member.service;

import com.systemsjr.motshelo.member.vo.MemberSearchCriteria;
import com.systemsjr.motshelo.member.vo.MemberVO;
import java.util.Collection;
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
        // TODO implement protected  MemberVO handleFindById(Long id)
        throw new UnsupportedOperationException("com.systemsjr.motshelo.member.service.MemberService.handleFindById(Long id) Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.member.service.MemberService#saveMember(MemberVO)
     */
    @Override
    protected  MemberVO handleSaveMember(MemberVO memberVO)
        throws Exception
    {
        // TODO implement protected  MemberVO handleSaveMember(MemberVO memberVO)
        throw new UnsupportedOperationException("com.systemsjr.motshelo.member.service.MemberService.handleSaveMember(MemberVO memberVO) Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.member.service.MemberService#removeMember(MemberVO)
     */
    @Override
    protected  boolean handleRemoveMember(MemberVO memberVO)
        throws Exception
    {
        // TODO implement protected  boolean handleRemoveMember(MemberVO memberVO)
        throw new UnsupportedOperationException("com.systemsjr.motshelo.member.service.MemberService.handleRemoveMember(MemberVO memberVO) Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.member.service.MemberService#getAllMembers()
     */
    @Override
    protected  Collection handleGetAllMembers()
        throws Exception
    {
        // TODO implement protected  Collection handleGetAllMembers()
        throw new UnsupportedOperationException("com.systemsjr.motshelo.member.service.MemberService.handleGetAllMembers() Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.member.service.MemberService#getAllMembersArray()
     */
    @Override
    protected  MemberVO[] handleGetAllMembersArray()
        throws Exception
    {
        // TODO implement protected  MemberVO[] handleGetAllMembersArray()
        throw new UnsupportedOperationException("com.systemsjr.motshelo.member.service.MemberService.handleGetAllMembersArray() Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.member.service.MemberService#searchMembers(MemberSearchCriteria)
     */
    @Override
    protected  Collection handleSearchMembers(MemberSearchCriteria searchCriteria)
        throws Exception
    {
        // TODO implement protected  Collection handleSearchMembers(MemberSearchCriteria searchCriteria)
        throw new UnsupportedOperationException("com.systemsjr.motshelo.member.service.MemberService.handleSearchMembers(MemberSearchCriteria searchCriteria) Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.member.service.MemberService#searchMembersArray(MemberSearchCriteria)
     */
    @Override
    protected  MemberVO[] handleSearchMembersArray(MemberSearchCriteria searchCriteria)
        throws Exception
    {
        // TODO implement protected  MemberVO[] handleSearchMembersArray(MemberSearchCriteria searchCriteria)
        throw new UnsupportedOperationException("com.systemsjr.motshelo.member.service.MemberService.handleSearchMembersArray(MemberSearchCriteria searchCriteria) Not implemented!");
    }

}