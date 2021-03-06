// license-header java merge-point
// Generated by andromda-jsf cartridge (controllers\ControllerImpl.java.vsl)
package com.systemsjr.motshelo.web.details;

import javax.faces.context.FacesContext;

import com.systemsjr.motshelo.vo.MotsheloVO;

/**
 * @see com.systemsjr.motshelo.web.details.MotsheloDetailsController
 */
public class MotsheloDetailsControllerImpl
    extends MotsheloDetailsController
{
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = -3221153127722761029L;

    /**
     * @see com.systemsjr.motshelo.web.details.MotsheloDetailsController#doInitialiseDetailsScreen(MotsheloVO motsheloVO)
     */
    @Override
    public void doInitialiseDetailsScreen(DoInitialiseDetailsScreenForm form)
    {
    	MotsheloVO motsheloVO = (MotsheloVO) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("motsheloVO");
    	form.setMotsheloVO(motsheloVO);
    	getMotsheloDetailsForm().setMotsheloVO(motsheloVO);
    }

	@Override
	public void doMotsheloEdit(DoMotsheloEditForm form) throws Throwable {
		
		getMotsheloEditForm().setMotsheloVO(getMotsheloDetailsForm().getMotsheloVO());
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("motsheloVO", getMotsheloEditForm().getMotsheloVO());
	}
}