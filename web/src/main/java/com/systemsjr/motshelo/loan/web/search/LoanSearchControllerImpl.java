// license-header java merge-point
// Generated by andromda-jsf cartridge (controllers\ControllerImpl.java.vsl)
package com.systemsjr.motshelo.loan.web.search;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.systemsjr.motshelo.instance.member.vo.InstanceMemberVO;
import com.systemsjr.motshelo.instance.vo.MotsheloInstanceVO;
import com.systemsjr.motshelo.loan.vo.LoanSearchCriteria;
import com.systemsjr.motshelo.loan.vo.LoanVO;

/**
 * @see com.systemsjr.motshelo.loan.web.search.LoanSearchController
 */
public class LoanSearchControllerImpl
    extends LoanSearchController
{
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = 3877559525103219476L;

    /**
     * @see com.systemsjr.motshelo.loan.web.search.LoanSearchController#doInitialiseSearchScreen(com.systemsjr.motshelo.loan.vo.LoanSearchCriteria searchCriteria)
     */
    @Override
    public void doInitialiseSearchScreen(DoInitialiseSearchScreenForm form)
    {
        LoanSearchCriteria searchCriteria = form.getSearchCriteria();   
        
        if(searchCriteria.getMotsheloInstance() == null)
        {
        	searchCriteria.setMotsheloInstance(new MotsheloInstanceVO());
        }
        
        if(searchCriteria.getInstanceMember() == null)
        {
        	searchCriteria.setInstanceMember(new InstanceMemberVO());
        }
        
        Collection<MotsheloInstanceVO> motsheloInstances = getMotsheloInstanceService().getAllMotsheloInstances();
        final Collection<SelectItem> motsheloInstanceBackingList = new ArrayList<SelectItem>();
        for(MotsheloInstanceVO instance : motsheloInstances)
        {
        	motsheloInstanceBackingList.add(new SelectItem(instance.getId(), instance.getInstanceName()));
        }
        form.setSearchCriteriaMotsheloInstanceBackingList(motsheloInstanceBackingList);

        // Get members
        //Collection<InstanceMemberVO> members = getInstanceMemberService().getAllInstanceMembers();
    }

    /**
     * @see com.systemsjr.motshelo.loan.web.search.LoanSearchController#doLoanSearch()
     */
    @Override
    public void doLoanSearch()
    {
    	Collection<LoanVO> loans = getLoanService().searchLoans(getSearchLoansSearchForm().getSearchCriteria());
    	getSearchLoansSearchForm().setLoans(loans);
    }

    /**
     * @see com.systemsjr.motshelo.loan.web.search.LoanSearchController#doLoanEdit(java.lang.Long id, com.systemsjr.motshelo.loan.vo.LoanVO loanVO)
     */
    @Override
    public void doLoanEdit(DoLoanEditForm form)
    {
    	getSearchLoansForm().setId(form.getId());
    	try {
			form.setLoanVO(getSelectedLoan());
		} catch (Throwable e) {
			e.printStackTrace();
		}
    }
    /**
     * @see com.systemsjr.motshelo.loan.web.search.LoanSearchController#doLoanDetails(java.lang.Long id, com.systemsjr.motshelo.loan.vo.LoanVO loanVO)
     */
    @Override
    public void doLoanDetails(DoLoanDetailsForm form)
    {
    	getSearchLoansForm().setId(form.getId());
    	try {
			form.setLoanVO(getSelectedLoan());
		} catch (Throwable e) {
			e.printStackTrace();
		}
    }

	@Override
	protected LoanVO getSelectedLoan() throws Throwable {
		LoanVO loanVO = getLoanService().findById(getSearchLoansForm().getId());
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("loanVO", loanVO);
		return loanVO;
	}
}