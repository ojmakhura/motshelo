// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::motshelo::com.systemsjr.motshelo::transaction::service::TransactionService
 * STEREOTYPE:  Service
 */
package com.systemsjr.motshelo.transaction.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

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

    /**
     * @see com.systemsjr.motshelo.transaction.service.TransactionService#saveTransaction(TransactionVO)
     */
    @Override
    protected  TransactionVO handleSaveTransaction(TransactionVO transactionVO)
        throws Exception
    {
    	Transaction transaction = getTransactionDao().transactionVOToEntity(transactionVO);
    	transaction = getTransactionDao().createOrUpdate(transaction);
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