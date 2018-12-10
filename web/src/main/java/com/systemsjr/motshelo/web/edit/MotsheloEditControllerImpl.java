// license-header java merge-point
// Generated by andromda-jsf cartridge (controllers\ControllerImpl.java.vsl)
package com.systemsjr.motshelo.web.edit;

import com.systemsjr.motshelo.vo.MotsheloVO;
import java.util.Arrays;
import java.util.Date;

import javax.faces.context.FacesContext;

/**
 * @see com.systemsjr.motshelo.web.edit.MotsheloEditController
 */
public class MotsheloEditControllerImpl
    extends MotsheloEditController
{
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = -7273825904286601925L;

    /**
     * @see com.systemsjr.motshelo.web.edit.MotsheloEditController#enterInitialiseEditScreen(MotsheloVO motsheloVO)
     */
    @Override
    public void enterInitialiseEditScreen(EnterInitialiseEditScreenForm form)
    {
    	MotsheloVO motsheloVO = (MotsheloVO) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("motsheloVO");
    	
    	getEditMotsheloSaveForm().setMotsheloVO(motsheloVO);
    }

    /**
     * @see com.systemsjr.motshelo.web.edit.MotsheloEditController#doInitialiseEditScreen(MotsheloVO motsheloVO)
     */
    @Override
    public void doInitialiseEditScreen(DoInitialiseEditScreenForm form)
    {
        
        /*if(motsheloVO != null && motsheloVO.getId() != null)
        {
        	
        }*/
        
    }

    /**
     * @see com.systemsjr.motshelo.web.edit.MotsheloEditController#exitInitialiseEditScreen(MotsheloVO motsheloVO)
     */
    @Override
    public void exitInitialiseEditScreen(ExitInitialiseEditScreenForm form)
    {
        
    }

    /**
     * @see com.systemsjr.motshelo.web.edit.MotsheloEditController#enterNewMotshelo()
     */
    @Override
    public void enterNewMotshelo()
    {
    }

    /**
     * @see com.systemsjr.motshelo.web.edit.MotsheloEditController#doNewMotshelo()
     */
    @Override
    public void doNewMotshelo()
    {
    	MotsheloVO motsheloVO = new MotsheloVO();
    	getEditMotsheloSaveForm().setMotsheloVO(motsheloVO);
    }

    /**
     * @see com.systemsjr.motshelo.web.edit.MotsheloEditController#exitNewMotshelo()
     */
    @Override
    public void exitNewMotshelo()
    {
    }

    /**
     * @see com.systemsjr.motshelo.web.edit.MotsheloEditController#enterSaveMotshelo()
     */
    @Override
    public void enterSaveMotshelo()
    {
    }

    /**
     * @see com.systemsjr.motshelo.web.edit.MotsheloEditController#doSaveMotshelo()
     */
    @Override
    public void doSaveMotshelo()
    {
    	MotsheloVO motsheloVO = getEditMotsheloSaveForm().getMotsheloVO();
    	
    	//TODO: do the actual saving
    	getMotsheloService().saveMotshelo(motsheloVO);
    	getEditMotsheloSaveForm().setMotsheloVO(motsheloVO);
    }

    /**
     * @see com.systemsjr.motshelo.web.edit.MotsheloEditController#exitSaveMotshelo()
     */
    @Override
    public void exitSaveMotshelo()
    {
    	FacesContext.getCurrentInstance().getExternalContext().getFlash().put("studentVO", getEditMotsheloSaveForm().getMotsheloVO());
    }

    /**
     * @see com.systemsjr.motshelo.web.edit.MotsheloEditController#enterUpdateMotshelo()
     */
    @Override
    public void enterUpdateMotshelo()
    {
    }

    /**
     * @see com.systemsjr.motshelo.web.edit.MotsheloEditController#doUpdateMotshelo()
     */
    @Override
    public void doUpdateMotshelo()
    {
    }

    /**
     * @see com.systemsjr.motshelo.web.edit.MotsheloEditController#exitUpdateMotshelo()
     */
    @Override
    public void exitUpdateMotshelo()
    {
    }

}