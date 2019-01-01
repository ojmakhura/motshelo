// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::motshelo::com.systemsjr.motshelo::instance::period::service::InstancePeriodService
 * STEREOTYPE:  Service
 */
package com.systemsjr.motshelo.instance.period.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.systemsjr.motshelo.instance.period.InstancePeriod;
import com.systemsjr.motshelo.instance.period.vo.InstancePeriodSearchCriteria;
import com.systemsjr.motshelo.instance.period.vo.InstancePeriodVO;

/**
 * @see com.systemsjr.motshelo.instance.period.service.InstancePeriodService
 */
@Service("com.systemsjr.motshelo.instance.period.service.InstancePeriodService")
public class InstancePeriodServiceImpl
    extends InstancePeriodServiceBase
{

    /**
     * @see com.systemsjr.motshelo.instance.period.service.InstancePeriodService#findById(Long)
     */
    @Override
    protected  InstancePeriodVO handleFindById(Long id)
        throws Exception
    {
    	if(id != null)
    	{
    		return getInstancePeriodDao().toInstancePeriodVO(getInstancePeriodDao().load(id));
    	}
    	
    	return null;
    }

    /**
     * @see com.systemsjr.motshelo.instance.period.service.InstancePeriodService#saveInstancePeriod(InstancePeriodVO)
     */
    @Override
    protected  InstancePeriodVO handleSaveInstancePeriod(InstancePeriodVO instancePeriodVO)
        throws Exception
    {
    	InstancePeriod instancePeriod = getInstancePeriodDao().instancePeriodVOToEntity(instancePeriodVO);
    	instancePeriod.setMotsheloInstance(getMotsheloInstanceDao().load(instancePeriod.getMotsheloInstance().getId()));
    	
    	SimpleDateFormat f1 = new SimpleDateFormat("MMM");
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(instancePeriodVO.getLoanByDate());
    	instancePeriod.setPeriodName(f1.format(instancePeriodVO.getLoanByDate()) + " " + cal.get(Calendar.YEAR));
    	
    	instancePeriod = getInstancePeriodDao().createOrUpdate(instancePeriod);
    	return getInstancePeriodDao().toInstancePeriodVO(instancePeriod);
    }

    /**
     * @see com.systemsjr.motshelo.instance.period.service.InstancePeriodService#removeInstancePeriod(InstancePeriodVO)
     */
    @Override
    protected  boolean handleRemoveInstancePeriod(InstancePeriodVO instancePeriodVO)
        throws Exception
    {
    	if(instancePeriodVO.getId() != null)
    	{
    		getInstancePeriodDao().remove(instancePeriodVO.getId());
    		return true;
    	}
    	return false;
    }

    /**
     * @see com.systemsjr.motshelo.instance.period.service.InstancePeriodService#getAllInstancePeriods()
     */
    @Override
    protected  Collection<InstancePeriodVO> handleGetAllInstancePeriods()
        throws Exception
    {
    	Collection<InstancePeriod> instancePeriods = getInstancePeriodDao().loadAll();
    	return getInstancePeriodDao().toInstancePeriodVOCollection(instancePeriods);
    }

    /**
     * @see com.systemsjr.motshelo.instance.period.service.InstancePeriodService#getAllInstancePeriodsArray()
     */
    @Override
    protected  InstancePeriodVO[] handleGetAllInstancePeriodsArray()
        throws Exception
    {
    	Collection<InstancePeriod> instancePeriods = getInstancePeriodDao().loadAll();
    	return getInstancePeriodDao().toInstancePeriodVOArray(instancePeriods);
    }

    /**
     * @see com.systemsjr.motshelo.instance.period.service.InstancePeriodService#searchInstancePeriods(InstancePeriodSearchCriteria)
     */
    @Override
    protected  Collection<InstancePeriodVO> handleSearchInstancePeriods(InstancePeriodSearchCriteria searchCriteria)
        throws Exception
    {
    	Collection<InstancePeriod> instancePeriods = getInstancePeriodDao().findByCriteria(searchCriteria);
    	return getInstancePeriodDao().toInstancePeriodVOCollection(instancePeriods);
    }

    /**
     * @see com.systemsjr.motshelo.instance.period.service.InstancePeriodService#searchInstancePeriodsArray(InstancePeriodSearchCriteria)
     */
    @Override
    protected  InstancePeriodVO[] handleSearchInstancePeriodsArray(InstancePeriodSearchCriteria searchCriteria)
        throws Exception
    {
    	Collection<InstancePeriod> instancePeriods = getInstancePeriodDao().findByCriteria(searchCriteria);
    	return getInstancePeriodDao().toInstancePeriodVOArray(instancePeriods);
    }

}