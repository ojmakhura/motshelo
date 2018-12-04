// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::motshelo::com.systemsjr.motshelo::transaction::service::TransactionService
 * STEREOTYPE:  Service
 */
package com.systemsjr.motshelo.transaction.service;

import com.systemsjr.motshelo.transaction.vo.TransactionSearchCriteria;
import com.systemsjr.motshelo.transaction.vo.TransactionVO;
import java.util.Collection;
import org.springframework.stereotype.Service;

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
        // TODO implement protected  TransactionVO handleFindById(Long id)
        throw new UnsupportedOperationException("com.systemsjr.motshelo.transaction.service.TransactionService.handleFindById(Long id) Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.transaction.service.TransactionService#saveTransaction(TransactionVO)
     */
    @Override
    protected  TransactionVO handleSaveTransaction(TransactionVO transactionVO)
        throws Exception
    {
        // TODO implement protected  TransactionVO handleSaveTransaction(TransactionVO transactionVO)
        throw new UnsupportedOperationException("com.systemsjr.motshelo.transaction.service.TransactionService.handleSaveTransaction(TransactionVO transactionVO) Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.transaction.service.TransactionService#removeTransaction(TransactionVO)
     */
    @Override
    protected  boolean handleRemoveTransaction(TransactionVO transactionVO)
        throws Exception
    {
        // TODO implement protected  boolean handleRemoveTransaction(TransactionVO transactionVO)
        throw new UnsupportedOperationException("com.systemsjr.motshelo.transaction.service.TransactionService.handleRemoveTransaction(TransactionVO transactionVO) Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.transaction.service.TransactionService#getAllTransactions()
     */
    @Override
    protected  Collection handleGetAllTransactions()
        throws Exception
    {
        // TODO implement protected  Collection handleGetAllTransactions()
        throw new UnsupportedOperationException("com.systemsjr.motshelo.transaction.service.TransactionService.handleGetAllTransactions() Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.transaction.service.TransactionService#getAllTransactionsArray()
     */
    @Override
    protected  TransactionVO[] handleGetAllTransactionsArray()
        throws Exception
    {
        // TODO implement protected  TransactionVO[] handleGetAllTransactionsArray()
        throw new UnsupportedOperationException("com.systemsjr.motshelo.transaction.service.TransactionService.handleGetAllTransactionsArray() Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.transaction.service.TransactionService#searchTransactions(TransactionSearchCriteria)
     */
    @Override
    protected  Collection handleSearchTransactions(TransactionSearchCriteria searchCriteria)
        throws Exception
    {
        // TODO implement protected  Collection handleSearchTransactions(TransactionSearchCriteria searchCriteria)
        throw new UnsupportedOperationException("com.systemsjr.motshelo.transaction.service.TransactionService.handleSearchTransactions(TransactionSearchCriteria searchCriteria) Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.transaction.service.TransactionService#searchTransactionsArray(TransactionSearchCriteria)
     */
    @Override
    protected  TransactionVO[] handleSearchTransactionsArray(TransactionSearchCriteria searchCriteria)
        throws Exception
    {
        // TODO implement protected  TransactionVO[] handleSearchTransactionsArray(TransactionSearchCriteria searchCriteria)
        throw new UnsupportedOperationException("com.systemsjr.motshelo.transaction.service.TransactionService.handleSearchTransactionsArray(TransactionSearchCriteria searchCriteria) Not implemented!");
    }

}