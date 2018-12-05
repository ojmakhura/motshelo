// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::motshelo::com.systemsjr.motshelo::service::MotsheloService
 * STEREOTYPE:  Service
 */
package com.systemsjr.motshelo.service;

import com.systemsjr.motshelo.Motshelo;
import com.systemsjr.motshelo.vo.MotsheloSearchCriteria;
import com.systemsjr.motshelo.vo.MotsheloVO;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * @see com.systemsjr.motshelo.service.MotsheloService
 */
@Service("com.systemsjr.motshelo.service.MotsheloService")
public class MotsheloServiceImpl
    extends MotsheloServiceBase
{

    /**
     * @see com.systemsjr.motshelo.service.MotsheloService#findById(Long)
     */
    @Override
    protected  MotsheloVO handleFindById(Long id)
        throws Exception
    {
        if(id != null)
        {
        	return getMotsheloDao().toMotsheloVO(getMotsheloDao().load(id));
        } else
        {
        	return null;
        }
    }

    /**
     * @see com.systemsjr.motshelo.service.MotsheloService#saveMotshelo(MotsheloVO)
     */
    @Override
    protected  MotsheloVO handleSaveMotshelo(MotsheloVO motsheloVO)
        throws Exception
    {
    	Motshelo motshelo = getMotsheloDao().motsheloVOToEntity(motsheloVO);
    	if(motsheloVO.getId() != null)
    	{
    		
    	}
    	
    	return getMotsheloDao().toMotsheloVO(motshelo);
    }

    /**
     * @see com.systemsjr.motshelo.service.MotsheloService#removeMotshelo(MotsheloVO)
     */
    @Override
    protected  boolean handleRemoveMotshelo(MotsheloVO motsheloVO)
        throws Exception
    {
        // TODO implement protected  boolean handleRemoveMotshelo(MotsheloVO motsheloVO)
        throw new UnsupportedOperationException("com.systemsjr.motshelo.service.MotsheloService.handleRemoveMotshelo(MotsheloVO motsheloVO) Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.service.MotsheloService#getAllMetshelo()
     */
    @Override
    protected  Collection handleGetAllMetshelo()
        throws Exception
    {
        // TODO implement protected  Collection handleGetAllMetshelo()
        throw new UnsupportedOperationException("com.systemsjr.motshelo.service.MotsheloService.handleGetAllMetshelo() Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.service.MotsheloService#getAllMetsheloArray()
     */
    @Override
    protected  MotsheloVO[] handleGetAllMetsheloArray()
        throws Exception
    {
        // TODO implement protected  MotsheloVO[] handleGetAllMetsheloArray()
        throw new UnsupportedOperationException("com.systemsjr.motshelo.service.MotsheloService.handleGetAllMetsheloArray() Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.service.MotsheloService#searchMetshelo(MotsheloSearchCriteria)
     */
    @Override
    protected  Collection handleSearchMetshelo(MotsheloSearchCriteria searchCriteria)
        throws Exception
    {
        // TODO implement protected  Collection handleSearchMetshelo(MotsheloSearchCriteria searchCriteria)
        throw new UnsupportedOperationException("com.systemsjr.motshelo.service.MotsheloService.handleSearchMetshelo(MotsheloSearchCriteria searchCriteria) Not implemented!");
    }

    /**
     * @see com.systemsjr.motshelo.service.MotsheloService#searchMetsheloArray(MotsheloSearchCriteria)
     */
    @Override
    protected  MotsheloVO[] handleSearchMetsheloArray(MotsheloSearchCriteria searchCriteria)
        throws Exception
    {
        // TODO implement protected  MotsheloVO[] handleSearchMetsheloArray(MotsheloSearchCriteria searchCriteria)
        throw new UnsupportedOperationException("com.systemsjr.motshelo.service.MotsheloService.handleSearchMetsheloArray(MotsheloSearchCriteria searchCriteria) Not implemented!");
    }

}