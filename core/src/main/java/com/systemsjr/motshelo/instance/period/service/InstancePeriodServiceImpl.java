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
import java.util.Date;

import org.springframework.stereotype.Service;

import com.systemsjr.motshelo.instance.MotsheloInstance;
import com.systemsjr.motshelo.instance.period.InstancePeriod;
import com.systemsjr.motshelo.instance.period.vo.InstancePeriodSearchCriteria;
import com.systemsjr.motshelo.instance.period.vo.InstancePeriodVO;
import com.systemsjr.motshelo.instance.vo.MotsheloInstanceVO;

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
    	
    	InstancePeriod next = instancePeriod.getNextPeriod();
    	if(next == null || next.getId() == null)
    	{
    		instancePeriod.setNextPeriod(null);
    	} else {
    		instancePeriod.setNextPeriod(getInstancePeriodDao().load(next.getId()));
    	}
    	
    	InstancePeriod prev = instancePeriod.getPreviousPeriod();
    	if(prev == null || prev.getId() == null)
    	{
    		instancePeriod.setPreviousPeriod(null);
    	} else {
    		instancePeriod.setPreviousPeriod(getInstancePeriodDao().load(prev.getId()));
    	}
    	
    	SimpleDateFormat f1 = new SimpleDateFormat("MMM");
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(instancePeriodVO.getLoanByDate());
    	instancePeriod.setPeriodName(f1.format(instancePeriodVO.getLoanByDate()) + " " + cal.get(Calendar.YEAR));
    	
    	instancePeriod = getInstancePeriodDao().createOrUpdate(instancePeriod);/*
    	
    	InstancePeriod period = InstancePeriod.Factory.newInstance();
    	period.setId(instancePeriod.getId());
    	period.setEndDate(instancePeriod.getEndDate());
    	period.setLoanByDate(instancePeriod.getLoanByDate());
    	period.setMotsheloInstance(instancePeriod.getMotsheloInstance());
    	period.setPeriodName(instancePeriod.getPeriodName());
    	period.setStartDate(instancePeriod.getStartDate());
    	period.setStatus(instancePeriod.getStatus());
    	
    	if(next != null && (next.getPreviousPeriod() == null || next.getPreviousPeriod().getId() != instancePeriod.getId()))
    	{
    		next.setPreviousPeriod(period);
    		getInstancePeriodDao().update(next);
    	}
    	
    	if(prev != null && (prev.getNextPeriod() == null || prev.getNextPeriod().getId() != instancePeriod.getId()))
    	{
    		//InstancePeriod period = getInstancePeriodDao().load(instancePeriod.getId());
    		prev.setNextPeriod(period);
    		getInstancePeriodDao().update(prev);
    	}*/
    	
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

	@Override
	protected Collection<InstancePeriodVO> handleSearchPreviousActivePeriods(Date currentDate, MotsheloInstanceVO motsheloInstanceVO) throws Exception {
		Collection<InstancePeriod> instancePeriods = getInstancePeriodDao().getPreviousActivePeriods(currentDate, getMotsheloInstanceDao().motsheloInstanceVOToEntity(motsheloInstanceVO));
    	return getInstancePeriodDao().toInstancePeriodVOCollection(instancePeriods);
	}

	@Override
	protected Collection<InstancePeriodVO> handleSearchPreviousPeriods(Date currentDate, MotsheloInstanceVO motsheloInstanceVO) throws Exception {
		Collection<InstancePeriod> instancePeriods = getInstancePeriodDao().getPreviousPeriods(currentDate, getMotsheloInstanceDao().motsheloInstanceVOToEntity(motsheloInstanceVO));
    	return getInstancePeriodDao().toInstancePeriodVOCollection(instancePeriods);
	}

	@Override
	protected Collection<InstancePeriodVO> handleSearchUpcomingPeriods(Date currentDate, MotsheloInstanceVO motsheloInstanceVO) throws Exception {
		Collection<InstancePeriod> instancePeriods = getInstancePeriodDao().getUpcomingPeriods(currentDate, getMotsheloInstanceDao().motsheloInstanceVOToEntity(motsheloInstanceVO));
    	return getInstancePeriodDao().toInstancePeriodVOCollection(instancePeriods);
	}

	@Override
	protected InstancePeriodVO handleGetCurrentPeriod(MotsheloInstanceVO motsheloInstanceVO) throws Exception {
		InstancePeriod instancePeriod = getInstancePeriodDao().getCurrentPeriod(getMotsheloInstanceDao().motsheloInstanceVOToEntity(motsheloInstanceVO));
    	return instancePeriod != null ? getInstancePeriodDao().toInstancePeriodVO(instancePeriod) : null;
	}

}