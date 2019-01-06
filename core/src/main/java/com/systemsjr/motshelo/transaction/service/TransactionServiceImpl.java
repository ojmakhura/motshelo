// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::motshelo::com.systemsjr.motshelo::transaction::service::TransactionService
 * STEREOTYPE:  Service
 */
package com.systemsjr.motshelo.transaction.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.systemsjr.motshelo.instance.member.vo.InstanceMemberVO;
import com.systemsjr.motshelo.loan.Loan;
import com.systemsjr.motshelo.loan.LoanStatus;
import com.systemsjr.motshelo.loan.LoanType;
import com.systemsjr.motshelo.loan.payment.LoanPayment;
import com.systemsjr.motshelo.loan.vo.LoanSearchCriteria;
import com.systemsjr.motshelo.transaction.Transaction;
import com.systemsjr.motshelo.transaction.vo.TransactionSearchCriteria;
import com.systemsjr.motshelo.transaction.vo.TransactionVO;

/**
 * @see com.systemsjr.motshelo.transaction.service.TransactionService
 */
@Service("com.systemsjr.motshelo.transaction.service.TransactionService")
public class TransactionServiceImpl
    extends TransactionServiceBase
{

    /**
     * @see com.systemsjr.motshelo.transaction.service.TransactionService#findById(Long)
     */
    @Override
    protected  TransactionVO handleFindById(Long id)
        throws Exception
    {
    	return id == null ? null : getTransactionDao().toTransactionVO(getTransactionDao().load(id));
    }
    
    private Collection<LoanPayment> getLoanPayments(TransactionVO transactionVO, LoanType type)
    {
    	Collection<LoanPayment> payments = new ArrayList<LoanPayment>();
    	double remaining = transactionVO.getRemainingAmount().doubleValue();
    	LoanSearchCriteria loanCriteria = new LoanSearchCriteria();
    	loanCriteria.setMotsheloInstance(transactionVO.getMotsheloInstance());
    	loanCriteria.setInstanceMember(transactionVO.getInstanceMember());
    	loanCriteria.setType(type);
    	
    	Collection<Loan> loans = getLoanDao().findByCriteria(loanCriteria);
    	
    	for(Loan loan : loans) {
    		if(loan.getStatus() == LoanStatus.COMPLETED)
    		{
    			continue;
    		}
    		
    		double loanAmount = getLoanDao().calculateLoanBalance(loan).doubleValue();
    		LoanPayment payment = new LoanPayment();
    		payment.setTransaction(getTransactionDao().load(transactionVO.getId()));
    		payment.setLoan(loan);
    		if(remaining < loanAmount)
    		{
    			payment.setPaymentAmount(new BigDecimal(remaining));    			
    			remaining = 0.0;
    		} else {
    			payment.setPaymentAmount(loan.getAmount());
    			remaining -= payment.getPaymentAmount().doubleValue();
    		}
    		payment = getLoanPaymentDao().create(payment);
    		payments.add(payment);
    	}
    	return payments;
    }

    /**
     * @see com.systemsjr.motshelo.transaction.service.TransactionService#saveTransaction(TransactionVO)
     */
    @Override
    protected  TransactionVO handleSaveTransaction(TransactionVO transactionVO)
        throws Exception
    {
    	boolean isNew = false;
    	
    	if(transactionVO.getId() == null)
    	{
    		isNew = true;
    		transactionVO.setRemainingAmount(transactionVO.getTransactionAmount());
    	}
    	
    	Transaction transaction = getTransactionDao().transactionVOToEntity(transactionVO);
    	transaction = getTransactionDao().createOrUpdate(transaction);
    	
    	/**
    	 * First find the unpaid contributions for the member's transaction
    	 */
    	Collection<LoanPayment> payments = getLoanPayments(transactionVO, LoanType.INTERESTFREE); // interest free loans first#
    	payments.addAll(getLoanPayments(transactionVO, LoanType.STANDARD));    	
    	
    	if(transaction.getId() != null && isNew)
    	{
    		InstanceMemberVO member = transactionVO.getInstanceMember();
    		BigDecimal balance = member.getBalance();
    		balance = balance.subtract(transactionVO.getTransactionAmount());
    		member.setBalance(balance);
    		getInstanceMemberDao().update(getInstanceMemberDao().instanceMemberVOToEntity(member));
    		
    		BigDecimal instanceBalance = transactionVO.getMotsheloInstance().getCumulativeBalance();
			instanceBalance = instanceBalance.add(transactionVO.getTransactionAmount());
			transactionVO.getMotsheloInstance().setCumulativeBalance(instanceBalance);
			getMotsheloInstanceDao().update(getMotsheloInstanceDao().motsheloInstanceVOToEntity(transactionVO.getMotsheloInstance()));
    	}
    	
    	return getTransactionDao().toTransactionVO(transaction);
    }

    /**
     * @see com.systemsjr.motshelo.transaction.service.TransactionService#removeTransaction(TransactionVO)
     */
    @Override
    protected  boolean handleRemoveTransaction(TransactionVO transactionVO)
        throws Exception
    {
    	if(transactionVO.getId() != null)
    	{
    		getTransactionDao().remove(transactionVO.getId());
    		return true;
    	}
    	
    	return false;
    }

    /**
     * @see com.systemsjr.motshelo.transaction.service.TransactionService#getAllTransactions()
     */
    @Override
    protected  Collection<TransactionVO> handleGetAllTransactions()
        throws Exception
    {
    	Collection<Transaction> transactions = getTransactionDao().loadAll();
    	return getTransactionDao().toTransactionVOCollection(transactions);
    }

    /**
     * @see com.systemsjr.motshelo.transaction.service.TransactionService#getAllTransactionsArray()
     */
    @Override
    protected  TransactionVO[] handleGetAllTransactionsArray()
        throws Exception
    {
    	Collection<Transaction> transactions = getTransactionDao().loadAll();
    	return getTransactionDao().toTransactionVOArray(transactions);
    }

    /**
     * @see com.systemsjr.motshelo.transaction.service.TransactionService#searchTransactions(TransactionSearchCriteria)
     */
    @Override
    protected  Collection<TransactionVO> handleSearchTransactions(TransactionSearchCriteria searchCriteria)
        throws Exception
    {
    	Collection<Transaction> transactions = getTransactionDao().findByCriteria(searchCriteria);
    	return getTransactionDao().toTransactionVOCollection(transactions);
    }

    /**
     * @see com.systemsjr.motshelo.transaction.service.TransactionService#searchTransactionsArray(TransactionSearchCriteria)
     */
    @Override
    protected  TransactionVO[] handleSearchTransactionsArray(TransactionSearchCriteria searchCriteria)
        throws Exception
    {
    	Collection<Transaction> transactions = getTransactionDao().findByCriteria(searchCriteria);
    	return getTransactionDao().toTransactionVOArray(transactions);
    }

}