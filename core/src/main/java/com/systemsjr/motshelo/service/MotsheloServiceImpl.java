// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::motshelo::com.systemsjr.motshelo::service::MotsheloService
 * STEREOTYPE:  Service
 */
package com.systemsjr.motshelo.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.systemsjr.motshelo.Motshelo;
import com.systemsjr.motshelo.member.vo.MemberVO;
import com.systemsjr.motshelo.vo.MotsheloSearchCriteria;
import com.systemsjr.motshelo.vo.MotsheloVO;

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
    	
    	motshelo = getMotsheloDao().createOrUpdate(motshelo);
    	    	
    	return getMotsheloDao().toMotsheloVO(motshelo);
    }

    /**
     * @see com.systemsjr.motshelo.service.MotsheloService#removeMotshelo(MotsheloVO)
     */
    @Override
    protected  boolean handleRemoveMotshelo(MotsheloVO motsheloVO)
        throws Exception
    {
        if(motsheloVO.getId() != null)
        {
        	getMotsheloDao().remove(motsheloVO.getId());
        	return true;
        }
        
        return false;
    }

    /**
     * @see com.systemsjr.motshelo.service.MotsheloService#getAllMetshelo()
     */
    @Override
    protected  Collection handleGetAllMetshelo()
        throws Exception
    {
    	return getMotsheloDao().toMotsheloVOCollection(getMotsheloDao().loadAll());
    }

    /**
     * @see com.systemsjr.motshelo.service.MotsheloService#getAllMetsheloArray()
     */
    @Override
    protected  MotsheloVO[] handleGetAllMetsheloArray()
        throws Exception
    {
    	 
    	return getMotsheloDao().toMotsheloVOArray(getMotsheloDao().loadAll());
    }

    /**
     * @return 
     * @see com.systemsjr.motshelo.service.MotsheloService#searchMetshelo(MotsheloSearchCriteria)
     */
	@Override
    protected  Collection<MotsheloVO> handleSearchMetshelo(MotsheloSearchCriteria searchCriteria)
        throws Exception
    {
    	Collection<Motshelo> metshelo = getMotsheloDao().findByCriteria(searchCriteria);
    	return getMotsheloDao().toMotsheloVOCollection(metshelo);
    }

    /**
     * @see com.systemsjr.motshelo.service.MotsheloService#searchMetsheloArray(MotsheloSearchCriteria)
     */
    @Override
    protected  MotsheloVO[] handleSearchMetsheloArray(MotsheloSearchCriteria searchCriteria)
        throws Exception
    {
    	Collection<Motshelo> metshelo = getMotsheloDao().findByCriteria(searchCriteria);
    	return getMotsheloDao().toMotsheloVOArray(metshelo);
    }

	@Override
	protected MotsheloVO handleUpdateMotshelo(MotsheloVO motsheloVO) throws Exception {
		
		Collection<MemberVO> memberVOs = motsheloVO.getMembers();
		for(MemberVO memberVO : memberVOs)
		{
			//getMemberDao().up
			getMemberService().updateMemberBalance(memberVO);
		}
		
		return null;
	}

}