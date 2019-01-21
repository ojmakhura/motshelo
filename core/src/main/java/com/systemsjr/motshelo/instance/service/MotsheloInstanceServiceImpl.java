// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::motshelo::com.systemsjr.motshelo::instance::service::MotsheloInstanceService
 * STEREOTYPE:  Service
 */
package com.systemsjr.motshelo.instance.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.systemsjr.motshelo.instance.MotsheloInstance;
import com.systemsjr.motshelo.instance.vo.MotsheloInstanceSearchCriteria;
import com.systemsjr.motshelo.instance.vo.MotsheloInstanceVO;
import com.systemsjr.motshelo.interest.vo.InterestVO;
import com.systemsjr.motshelo.loan.LoanType;
import com.systemsjr.motshelo.loan.vo.LoanVO;
import com.systemsjr.motshelo.transaction.vo.TransactionVO;

/**
 * @see com.systemsjr.motshelo.instance.service.MotsheloInstanceService
 */
@Service("com.systemsjr.motshelo.instance.service.MotsheloInstanceService")
public class MotsheloInstanceServiceImpl
    extends MotsheloInstanceServiceBase
{

    /**
     * @see com.systemsjr.motshelo.instance.service.MotsheloInstanceService#findById(Long)
     */
    @Override
    protected  MotsheloInstanceVO handleFindById(Long id)
        throws Exception
    {
    	
    	if(id != null)
    	{
    		return getMotsheloInstanceDao().toMotsheloInstanceVO(getMotsheloInstanceDao().load(id));
    	}
    	
    	return null;
    }

    /**
     * @see com.systemsjr.motshelo.instance.service.MotsheloInstanceService#saveMotsheloInstance(MotsheloInstanceVO)
     */
    @Override
    protected  MotsheloInstanceVO handleSaveMotsheloInstance(MotsheloInstanceVO motsheloInstanceVO)
        throws Exception
    {
    	if(motsheloInstanceVO.getId() == null)
    	{
    		motsheloInstanceVO.setBalance(motsheloInstanceVO.getStartingBalance());
    		motsheloInstanceVO.setCummulativeBalance(motsheloInstanceVO.getStartingBalance());
    	}
    	
    	MotsheloInstance instance = getMotsheloInstanceDao().motsheloInstanceVOToEntity(motsheloInstanceVO);
    	instance.setMotshelo(getMotsheloDao().load(motsheloInstanceVO.getMotshelo().getId()));
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(motsheloInstanceVO.getCloseDate());
    	instance.setInstanceName(instance.getMotshelo().getName() + " " + cal.get(Calendar.YEAR));  
    	
    	instance = getMotsheloInstanceDao().createOrUpdate(instance);
    	return getMotsheloInstanceDao().toMotsheloInstanceVO(instance);
    }

    /**
     * @see com.systemsjr.motshelo.instance.service.MotsheloInstanceService#removeMotsheloInstance(MotsheloInstanceVO)
     */
    @Override
    protected  boolean handleRemoveMotsheloInstance(MotsheloInstanceVO motsheloInstanceVO)
        throws Exception
    {
    	
    	if(motsheloInstanceVO.getId() != null)
    	{
    		getMotsheloInstanceDao().remove(motsheloInstanceVO.getId());
    		return true;
    	}
    	
    	return false;
    }

    /**
     * @see com.systemsjr.motshelo.instance.service.MotsheloInstanceService#getAllMotsheloInstances()
     */
    @Override
    protected  Collection<MotsheloInstanceVO> handleGetAllMotsheloInstances()
        throws Exception
    {
    	Collection<MotsheloInstance> instances = getMotsheloInstanceDao().loadAll();
    	Collection<MotsheloInstanceVO> vos = new ArrayList<MotsheloInstanceVO>();
    	
    	for(MotsheloInstance instance : instances)
    	{
    		vos.add(getMotsheloInstanceDao().toMotsheloInstanceVO(instance));
    	}
    	
    	return vos;
    }

    /**
     * @see com.systemsjr.motshelo.instance.service.MotsheloInstanceService#getAllMotsheloInstancesArray()
     */
    @Override
    protected  MotsheloInstanceVO[] handleGetAllMotsheloInstancesArray()
        throws Exception
    {
    	Collection<MotsheloInstance> instances = getMotsheloInstanceDao().loadAll();
		MotsheloInstanceVO[] vos = new MotsheloInstanceVO[instances.size()];
    	
    	int i = 0;    	
    	for(MotsheloInstance instance : instances)
    	{
    		vos[i] = getMotsheloInstanceDao().toMotsheloInstanceVO(instance);
    		i++;
    	}
    	
    	return vos;
    }

    /**
     * @see com.systemsjr.motshelo.instance.service.MotsheloInstanceService#searchMetshelo(MotsheloInstanceSearchCriteria)
     */
    @Override
    protected  Collection<MotsheloInstanceVO> handleSearchMetsheloInstances(MotsheloInstanceSearchCriteria searchCriteria)
        throws Exception
    {
    	Collection<MotsheloInstance> instances = getMotsheloInstanceDao().findByCriteria(searchCriteria);
    	Collection<MotsheloInstanceVO> vos = new ArrayList<MotsheloInstanceVO>();
    	
    	for(MotsheloInstance instance : instances)
    	{
    		vos.add(getMotsheloInstanceDao().toMotsheloInstanceVO(instance));
    	}
    	
    	return vos;
    }

    /**
     * @see com.systemsjr.motshelo.instance.service.MotsheloInstanceService#searchMotsheloInstanceArray(MotsheloInstanceSearchCriteria)
     */
    @Override
    protected  MotsheloInstanceVO[] handleSearchMotsheloInstanceArray(MotsheloInstanceSearchCriteria searchCriteria)
        throws Exception
    {
    	Collection<MotsheloInstance> instances = getMotsheloInstanceDao().findByCriteria(searchCriteria);
    	MotsheloInstanceVO[] vos = new MotsheloInstanceVO[instances.size()];
    	
    	int i = 0;    	
    	for(MotsheloInstance instance : instances)
    	{
    		vos[i] = getMotsheloInstanceDao().toMotsheloInstanceVO(instance);
    		i++;
    	}
    	
    	return vos;
    }

	@Override
	protected MotsheloInstanceVO handleUpdateMotsheloInstance(MotsheloInstanceVO motsheloInstanceVO) throws Exception {
		
		MotsheloInstance instance = getMotsheloInstanceDao().updateMotsheloInstance(getMotsheloInstanceDao().load(motsheloInstanceVO.getId()));		
		return getMotsheloInstanceDao().getBasicMotsheloInstanceVO(instance);
	}

	@Override
	protected MotsheloInstanceVO handleCreateNextMotsheloInstance(MotsheloInstanceVO currentMotsheloInstanceVO)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}