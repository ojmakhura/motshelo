// license-header java merge-point
// Generated by andromda-jsf cartridge (controllers\ControllerImpl.java.vsl)
package com.systemsjr.motshelo.instance.web.edit;

import com.systemsjr.motshelo.instance.vo.MotsheloInstanceVO;
import java.util.Date;

import javax.faces.context.FacesContext;

/**
 * @see com.systemsjr.motshelo.instance.web.edit.MotsheloInstanceEditController
 */
public class MotsheloInstanceEditControllerImpl
    extends MotsheloInstanceEditController
{
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = 8087502174420736204L;

    /**
     * @see com.systemsjr.motshelo.instance.web.edit.MotsheloInstanceEditController#enterSaveMotsheloInstance()
     */
    @Override
    public void enterSaveMotsheloInstance()
    {
    }

    /**
     * @see com.systemsjr.motshelo.instance.web.edit.MotsheloInstanceEditController#doSaveMotsheloInstance()
     */
    @Override
    public void doSaveMotsheloInstance()
    {
    	MotsheloInstanceVO motsheloInstanceVO = getEditMotsheloInstanceSaveForm().getMotsheloInstanceVO();
    	motsheloInstanceVO = getMotsheloInstanceService().saveMotsheloInstance(motsheloInstanceVO);
    	getEditMotsheloInstanceForm().setMotsheloInstanceVO(motsheloInstanceVO);
    	getEditMotsheloInstanceSaveForm().setMotsheloInstanceVO(motsheloInstanceVO);
    }

    /**
     * @see com.systemsjr.motshelo.instance.web.edit.MotsheloInstanceEditController#exitSaveMotsheloInstance()
     */
    @Override
    public void exitSaveMotsheloInstance()
    {
    	FacesContext.getCurrentInstance().getExternalContext().getFlash().put("motsheloInstanceVO", getEditMotsheloInstanceSaveForm().getMotsheloInstanceVO());
    }

    /**
     * @see com.systemsjr.motshelo.instance.web.edit.MotsheloInstanceEditController#exitNewMotsheloInstance()
     */
    @Override
    public void exitNewMotsheloInstance()
    {
    }

    /**
     * @see com.systemsjr.motshelo.instance.web.edit.MotsheloInstanceEditController#doNewMotsheloInstance()
     */
    @Override
    public void doNewMotsheloInstance()
    {
    	MotsheloInstanceVO motsheloInstanceVO = new MotsheloInstanceVO();
    	getEditMotsheloInstanceForm().setMotsheloInstanceVO(motsheloInstanceVO);
    	getEditMotsheloInstanceSaveForm().setMotsheloInstanceVO(motsheloInstanceVO);
    }

    /**
     * @see com.systemsjr.motshelo.instance.web.edit.MotsheloInstanceEditController#enterNewMotsheloInstance()
     */
    @Override
    public void enterNewMotsheloInstance()
    {
    }

    /**
     * @see com.systemsjr.motshelo.instance.web.edit.MotsheloInstanceEditController#exitInitialiseEditScreen(MotsheloInstanceVO motsheloInstanceVO)
     */
    @Override
    public void exitInitialiseEditScreen(ExitInitialiseEditScreenForm form)
    {
    }

    /**
     * @see com.systemsjr.motshelo.instance.web.edit.MotsheloInstanceEditController#doInitialiseEditScreen(MotsheloInstanceVO motsheloInstanceVO)
     */
    @Override
    public void doInitialiseEditScreen(DoInitialiseEditScreenForm form)
    {
    	form.setMotsheloInstanceVO(getEditMotsheloInstanceForm().getMotsheloInstanceVO());
    	getEditMotsheloInstanceSaveForm().setMotsheloInstanceVO(getEditMotsheloInstanceForm().getMotsheloInstanceVO());
    }

    /**
     * @see com.systemsjr.motshelo.instance.web.edit.MotsheloInstanceEditController#enterInitialiseEditScreen(MotsheloInstanceVO motsheloInstanceVO)
     */
    @Override
    public void enterInitialiseEditScreen(EnterInitialiseEditScreenForm form)
    {
    	MotsheloInstanceVO motsheloInstanceVO = (MotsheloInstanceVO) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("motsheloInstanceVO");
    	getEditMotsheloInstanceForm().setMotsheloInstanceVO(motsheloInstanceVO);
    }

}