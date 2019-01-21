package com.systemsjr.motshelo.instance.web;

import javax.annotation.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import com.systemsjr.motshelo.transaction.vo.TransactionVO;

@ManagedBean
@ViewScoped
public class MotsheloInstanceController {
	public void doAddTransaction() throws Throwable {
		System.out.println("Adding transaction");
		TransactionVO transactionVO = (TransactionVO) FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.get("transactionVO");
		System.out.println("transactionVO = " + transactionVO);
		/*TransactionVO newTransaction = getTransactionService().saveTransaction(transactionVO);
		System.out.println("transactionVO = " + transactionVO);
		if (newTransaction.getId() != null) {
			getEditMotsheloInstanceSaveForm().getMotsheloInstanceVO().getTransactions().add(newTransaction);
		}
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("motsheloInstanceVO",
				transactionVO.getMotsheloInstance());
		transactionVO = new TransactionVO();
		transactionVO.setTransactionDate(new Date());
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("transactionVO", transactionVO);*/
	}
}
