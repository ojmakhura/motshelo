// license-header java merge-point
// Generated by andromda-jsf cartridge (controllers\ControllerImpl.java.vsl)
package com.systemsjr.motshelo.transaction.web.search;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.systemsjr.motshelo.instance.member.vo.InstanceMemberVO;
import com.systemsjr.motshelo.instance.vo.MotsheloInstanceVO;
import com.systemsjr.motshelo.transaction.vo.TransactionSearchCriteria;
import com.systemsjr.motshelo.transaction.vo.TransactionVO;

/**
 * @see com.systemsjr.motshelo.transaction.web.search.TransactionSearchController
 */
public class TransactionSearchControllerImpl
    extends TransactionSearchController
{
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = 2701783572994785197L;

    /**
     * @see com.systemsjr.motshelo.transaction.web.search.TransactionSearchController#doInitialiseSearchScreen(com.systemsjr.motshelo.transaction.vo.TransactionSearchCriteria searchCriteria)
     */
    @Override
    public void doInitialiseSearchScreen(DoInitialiseSearchScreenForm form)
    {
    	TransactionSearchCriteria criteria = form.getSearchCriteria();
    	if(criteria.getInstanceMember() == null)
    	{
    		criteria.setInstanceMember(new InstanceMemberVO());
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
        getSearchTransactionsSearchForm().setSearchCriteriaMotsheloInstanceBackingList(motsheloInstanceBackingList);
        
        try {
			updateSearchForm();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * @see com.systemsjr.motshelo.transaction.web.search.TransactionSearchController#doTransactionsSearch()
     */
    @Override
    public void doTransactionsSearch()
    {
    	Collection<TransactionVO> transactions = getTransactionService().searchTransactions(getSearchTransactionsSearchForm().getSearchCriteria());
    	getSearchTransactionsSearchForm().setTransactions(transactions);
    }
    
    /**
     * @see com.systemsjr.motshelo.transaction.web.search.TransactionSearchController#doEditTransaction(java.lang.Long id, com.systemsjr.motshelo.transaction.vo.TransactionVO transactionVO)
     */
    @Override
    public void doEditTransaction(DoEditTransactionForm form)
    {
    	getSearchTransactionsForm().setId(form.getId());
    	try {
			TransactionVO transactionVO = getSelectedTransaction();
			form.setTransactionVO(transactionVO);
		} catch (Throwable e) {
			e.printStackTrace();
		}
    }

    /**
     * @see com.systemsjr.motshelo.transaction.web.search.TransactionSearchController#doTransactionDetails(java.lang.Long id, com.systemsjr.motshelo.transaction.vo.TransactionVO transactionVO)
     */
    @Override
    public void doTransactionDetails(DoTransactionDetailsForm form)
    {
    	getSearchTransactionsForm().setId(form.getId());
    	try {
			TransactionVO transactionVO = getSelectedTransaction();
			form.setTransactionVO(transactionVO);
		} catch (Throwable e) {
			e.printStackTrace();
		}
    }

	@Override
	protected TransactionVO getSelectedTransaction() throws Throwable {
		TransactionVO transactionVO = getTransactionService().findById(getSearchTransactionsForm().getId());		
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("transactionVO", transactionVO);
		
		return transactionVO;
	}

	@Override
	public void updateSearchForm() throws Throwable {
		TransactionSearchCriteria searchCriteria = getSearchTransactionsSearchForm().getSearchCriteria();
		if(searchCriteria.getMotsheloInstance() != null)
		{
			MotsheloInstanceVO motshelo = getMotsheloInstanceService().findById(searchCriteria.getMotsheloInstance().getId());
			
			if(motshelo == null || motshelo.getId() == null)
			{
				return;
			}
			
			Collection<SelectItem> memberBackingList = new ArrayList<SelectItem>();
			for(InstanceMemberVO member : motshelo.getInstanceMembers())
			{
				memberBackingList.add(new SelectItem(member.getId(), member.getMember().getName() + " " + member.getMember().getSurname()));
			}
			getSearchTransactionsSearchForm().setSearchCriteriaInstanceMemberBackingList(memberBackingList);
		}
	}

}