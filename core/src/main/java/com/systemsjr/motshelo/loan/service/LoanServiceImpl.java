// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::motshelo::com.systemsjr.motshelo::loan::service::LoanService
 * STEREOTYPE:  Service
 */
package com.systemsjr.motshelo.loan.service;

import com.systemsjr.motshelo.loan.vo.LoanSearchCriteria;
import com.systemsjr.motshelo.loan.vo.LoanVO;
import com.systemsjr.motshelo.member.vo.MemberVO;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * @see com.systemsjr.motshelo.loan.service.LoanService
 */
@Service("com.systemsjr.motshelo.loan.service.LoanService")
public class LoanServiceImpl
    extends LoanServiceBase
{

    /**
     * @see com.systemsjr.motshelo.loan.service.LoanService#findById(Long)
     */
    @Override
    protected  MemberVO handleFindById(Long id)
        throws Exception
    {
        // TODO implement protected  MemberVO handleFindById(Long id)
        throw new UnsupportedOperationException("com.systemsjr.motshelo.loan.service.LoanService.handleFindById(Long id) Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.loan.service.LoanService#saveLoan(LoanVO)
     */
    @Override
    protected  LoanVO handleSaveLoan(LoanVO loanVO)
        throws Exception
    {
        // TODO implement protected  LoanVO handleSaveLoan(LoanVO loanVO)
        throw new UnsupportedOperationException("com.systemsjr.motshelo.loan.service.LoanService.handleSaveLoan(LoanVO loanVO) Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.loan.service.LoanService#removeLoan(LoanVO)
     */
    @Override
    protected  boolean handleRemoveLoan(LoanVO loanVO)
        throws Exception
    {
        // TODO implement protected  boolean handleRemoveLoan(LoanVO loanVO)
        throw new UnsupportedOperationException("com.systemsjr.motshelo.loan.service.LoanService.handleRemoveLoan(LoanVO loanVO) Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.loan.service.LoanService#getAllLoans()
     */
    @Override
    protected  Collection handleGetAllLoans()
        throws Exception
    {
        // TODO implement protected  Collection handleGetAllLoans()
        throw new UnsupportedOperationException("com.systemsjr.motshelo.loan.service.LoanService.handleGetAllLoans() Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.loan.service.LoanService#getAllLoansArray()
     */
    @Override
    protected  LoanVO[] handleGetAllLoansArray()
        throws Exception
    {
        // TODO implement protected  LoanVO[] handleGetAllLoansArray()
        throw new UnsupportedOperationException("com.systemsjr.motshelo.loan.service.LoanService.handleGetAllLoansArray() Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.loan.service.LoanService#searchLoans(LoanSearchCriteria)
     */
    @Override
    protected  Collection handleSearchLoans(LoanSearchCriteria searchCriteria)
        throws Exception
    {
        // TODO implement protected  Collection handleSearchLoans(LoanSearchCriteria searchCriteria)
        throw new UnsupportedOperationException("com.systemsjr.motshelo.loan.service.LoanService.handleSearchLoans(LoanSearchCriteria searchCriteria) Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.loan.service.LoanService#searchLoansArray(LoanSearchCriteria)
     */
    @Override
    protected  LoanVO[] handleSearchLoansArray(LoanSearchCriteria searchCriteria)
        throws Exception
    {
        // TODO implement protected  LoanVO[] handleSearchLoansArray(LoanSearchCriteria searchCriteria)
        throw new UnsupportedOperationException("com.systemsjr.motshelo.loan.service.LoanService.handleSearchLoansArray(LoanSearchCriteria searchCriteria) Not implemented!");
    }

}