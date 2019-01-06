// license-header java merge-point
// Generated by andromda-jsf cartridge (controllers\ControllerImpl.java.vsl)
package com.systemsjr.motshelo.instance.member.web.search;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.systemsjr.motshelo.instance.member.vo.InstanceMemberSearchCriteria;
import com.systemsjr.motshelo.instance.member.vo.InstanceMemberVO;
import com.systemsjr.motshelo.instance.vo.MotsheloInstanceVO;
import com.systemsjr.motshelo.member.vo.MemberVO;
import com.systemsjr.motshelo.vo.MotsheloVO;

/**
 * @see com.systemsjr.motshelo.instance.member.web.search.InstanceMemberSearchController
 */
public class InstanceMemberSearchControllerImpl
    extends InstanceMemberSearchController
{
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = 7401084150476278772L;

    /**
     * @see com.systemsjr.motshelo.instance.member.web.search.InstanceMemberSearchController#doInitialiseSearchScreen(com.systemsjr.motshelo.instance.member.vo.InstanceMemberSearchCriteria searchCriteria)
     */
    @Override
    public void doInitialiseSearchScreen(DoInitialiseSearchScreenForm form)
    {
    	InstanceMemberSearchCriteria criteria = form.getSearchCriteria();
    	if(criteria.getMember() == null)
    	{
    		criteria.setMember(new MemberVO());
    	}
    	
    	if(criteria.getMotsheloInstance() == null)
    	{
    		criteria.setMotsheloInstance(new MotsheloInstanceVO());
    	}
    	
    	Collection<SelectItem> motsheloInstanceBackingList = new ArrayList<SelectItem>();
		for(MotsheloInstanceVO instance : getMotsheloInstanceService().getAllMotsheloInstances())
		{
			motsheloInstanceBackingList.add(new SelectItem(instance.getId(), instance.getInstanceName()));
		}

		form.setSearchCriteriaMotsheloInstanceBackingList(motsheloInstanceBackingList);
		getSearchInstanceMembersSearchForm().setSearchCriteriaMotsheloInstanceBackingList(motsheloInstanceBackingList);
		
        try {
			updateSearchForm();
		} catch (Throwable e) {
			e.printStackTrace();
		}
    }

    /**
     * @see com.systemsjr.motshelo.instance.member.web.search.InstanceMemberSearchController#doInstanceMemberSearch()
     */
    @Override
    public void doInstanceMemberSearch()
    {
    	InstanceMemberSearchCriteria criteria = getSearchInstanceMembersSearchForm().getSearchCriteria();
    	Collection<InstanceMemberVO> members = getInstanceMemberService().searchInstanceMembers(criteria);
    	getSearchInstanceMembersSearchForm().setInstanceMembers(members);
    }

    /**
     * @see com.systemsjr.motshelo.instance.member.web.search.InstanceMemberSearchController#doInstanceMemberEdit(java.lang.Long id, com.systemsjr.motshelo.instance.member.vo.InstanceMemberVO instanceMemberVO)
     */
    @Override
    public void doInstanceMemberEdit(DoInstanceMemberEditForm form)
    {
    	getSearchInstanceMembersForm().setId(form.getId());
    	try {
			getSelectedInstanceMember();
		} catch (Throwable e) {
			e.printStackTrace();
		}
    }

    /**
     * @see com.systemsjr.motshelo.instance.member.web.search.InstanceMemberSearchController#doInstanceMemberDetails(java.lang.Long id, com.systemsjr.motshelo.instance.member.vo.InstanceMemberVO instanceMemberVO)
     */
    @Override
    public void doInstanceMemberDetails(DoInstanceMemberDetailsForm form)
    {
    	getSearchInstanceMembersForm().setId(form.getId());
    	try {
    		getSelectedInstanceMember();
		} catch (Throwable e) {
			e.printStackTrace();
		}
    }

	@Override
	public void updateSearchForm() throws Throwable {
		InstanceMemberSearchCriteria criteria = getSearchInstanceMembersSearchForm().getSearchCriteria();
		Collection<MemberVO> members = null;
		if(criteria.getMotsheloInstance() != null && criteria.getMotsheloInstance().getId() != null) {
			MotsheloInstanceVO instance = getMotsheloInstanceService().findById(criteria.getMotsheloInstance().getId());
			MotsheloVO motshelo = getMotsheloService().findById(instance.getMotshelo().getId());
			members = motshelo.getMembers();
		} else {
			members = getMemberService().getAllMembers();
		}
		Collection<SelectItem> memberBackingList = new ArrayList<SelectItem>();
		for(MemberVO member : members)
		{
			memberBackingList.add(new SelectItem(member.getId(), member.getName() + " " + member.getSurname()));
		}
		getSearchInstanceMembersSearchForm().setSearchCriteriaMemberBackingList(memberBackingList);
	}

	@Override
	protected InstanceMemberVO getSelectedInstanceMember() throws Throwable {
		InstanceMemberVO instanceMemberVO = getInstanceMemberService().findById(getSearchInstanceMembersForm().getId());
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("instanceMemberVO", instanceMemberVO);
		return instanceMemberVO;
	}

    
}