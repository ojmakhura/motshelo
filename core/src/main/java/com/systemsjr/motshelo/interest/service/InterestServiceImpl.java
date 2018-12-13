// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::motshelo::com.systemsjr.motshelo::interest::service::InterestService
 * STEREOTYPE:  Service
 */
package com.systemsjr.motshelo.interest.service;

import com.systemsjr.motshelo.interest.Interest;
import com.systemsjr.motshelo.interest.vo.InterestSearchCritirea;
import com.systemsjr.motshelo.interest.vo.InterestVO;
import com.systemsjr.motshelo.loan.vo.LoanVO;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * @see com.systemsjr.motshelo.interest.service.InterestService
 */
@Service("com.systemsjr.motshelo.interest.service.InterestService")
public class InterestServiceImpl
    extends InterestServiceBase
{

    /**
     * @see com.systemsjr.motshelo.interest.service.InterestService#findById(Long)
     */
    @Override
    protected  InterestVO handleFindById(Long id)
        throws Exception
    {
        if(id != null)
        {
        	return getInterestDao().toInterestVO(getInterestDao().load(id));
        }
        
        return null;
    }

    /**
     * @see com.systemsjr.motshelo.interest.service.InterestService#saveInterest(InterestVO)
     */
    @Override
    protected  LoanVO handleSaveInterest(InterestVO interestVO)
        throws Exception
    {
        
    	Interest interest = getInterestDao().createOrUpdate(getInterestDao().interestVOToEntity(interestVO));
    	
    	return null;
    }

    /**
     * @see com.systemsjr.motshelo.interest.service.InterestService#removeInterest(InterestVO)
     */
    @Override
    protected  boolean handleRemoveInterest(InterestVO interestVO)
        throws Exception
    {
    	if(interestVO.getId() != null)
    	{
    		getInterestDao().remove(interestVO.getId());
    		return true;
    	}
    	
    	return false;
    }

    /**
     * @see com.systemsjr.motshelo.interest.service.InterestService#getAllInterests()
     */
    @Override
    protected  Collection handleGetAllInterests()
        throws Exception
    {
        return getInterestDao().toInterestVOCollection(getInterestDao().loadAll());
    }

    /**
     * @see com.systemsjr.motshelo.interest.service.InterestService#getAllInterestsArray()
     */
    @Override
    protected  LoanVO[] handleGetAllInterestsArray()
        throws Exception
    {
        //return getInterestDao().toInterestVOArray(getInterestDao().loadAll());
    	return null;
    }

    /**
     * @see com.systemsjr.motshelo.interest.service.InterestService#searchInterests(InterestSearchCritirea)
     */
    @Override
    protected  Collection handleSearchInterests(InterestSearchCritirea searchCriteria)
        throws Exception
    {
    	//return getInterestDao().toInterestVOCollection(getInterestDao().)
        // TODO implement protected  Collection handleSearchInterests(InterestSearchCritirea searchCriteria)
        throw new UnsupportedOperationException("com.systemsjr.motshelo.interest.service.InterestService.handleSearchInterests(InterestSearchCritirea searchCriteria) Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.interest.service.InterestService#searchInterestsArray(InterestSearchCritirea)
     */
    @Override
    protected  LoanVO[] handleSearchInterestsArray(InterestSearchCritirea searchCriteria)
        throws Exception
    {
        // TODO implement protected  LoanVO[] handleSearchInterestsArray(InterestSearchCritirea searchCriteria)
        throw new UnsupportedOperationException("com.systemsjr.motshelo.interest.service.InterestService.handleSearchInterestsArray(InterestSearchCritirea searchCriteria) Not implemented!");
    }

}