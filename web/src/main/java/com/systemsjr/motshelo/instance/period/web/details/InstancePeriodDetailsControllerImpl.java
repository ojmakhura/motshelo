// license-header java merge-point
// Generated by andromda-jsf cartridge (controllers\ControllerImpl.java.vsl)
package com.systemsjr.motshelo.instance.period.web.details;

import com.systemsjr.motshelo.instance.period.vo.InstancePeriodVO;
import java.util.Date;

/**
 * @see com.systemsjr.motshelo.instance.period.web.details.InstancePeriodDetailsController
 */
public class InstancePeriodDetailsControllerImpl
    extends InstancePeriodDetailsController
{
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = -5717107477669974309L;

    /**
     * @see com.systemsjr.motshelo.instance.period.web.details.InstancePeriodDetailsController#exitInitialiseDetailsScreen(InstancePeriodVO instancePeriodVO)
     */
    @Override
    public void exitInitialiseDetailsScreen(ExitInitialiseDetailsScreenForm form)
    {
        // populating value with dummy instance
        InstancePeriodVO instancePeriodVO = new InstancePeriodVO();
        instancePeriodVO.setStartDate(new Date());
        instancePeriodVO.setEndDate(new Date());
        instancePeriodVO.setLoanByDate(new Date());
        instancePeriodVO.setMotsheloInstance(null);
        form.setInstancePeriodVO(instancePeriodVO);
    }

    /**
     * @see com.systemsjr.motshelo.instance.period.web.details.InstancePeriodDetailsController#doInitialiseDetailsScreen(InstancePeriodVO instancePeriodVO)
     */
    @Override
    public void doInitialiseDetailsScreen(DoInitialiseDetailsScreenForm form)
    {
        // populating value with dummy instance
        InstancePeriodVO instancePeriodVO = new InstancePeriodVO();
        instancePeriodVO.setStartDate(new Date());
        instancePeriodVO.setEndDate(new Date());
        instancePeriodVO.setLoanByDate(new Date());
        instancePeriodVO.setMotsheloInstance(null);
        form.setInstancePeriodVO(instancePeriodVO);
    }

    /**
     * @see com.systemsjr.motshelo.instance.period.web.details.InstancePeriodDetailsController#enterInitialiseDetailsScreen(InstancePeriodVO instancePeriodVO)
     */
    @Override
    public void enterInitialiseDetailsScreen(EnterInitialiseDetailsScreenForm form)
    {
        // populating value with dummy instance
        InstancePeriodVO instancePeriodVO = new InstancePeriodVO();
        instancePeriodVO.setStartDate(new Date());
        instancePeriodVO.setEndDate(new Date());
        instancePeriodVO.setLoanByDate(new Date());
        instancePeriodVO.setMotsheloInstance(null);
        form.setInstancePeriodVO(instancePeriodVO);
    }

	@Override
	public void enterInstancePeriodEdit(EnterInstancePeriodEditForm form) throws Throwable {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doInstancePeriodEdit(DoInstancePeriodEditForm form) throws Throwable {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitInstancePeriodEdit(ExitInstancePeriodEditForm form) throws Throwable {
		// TODO Auto-generated method stub
		
	}

}