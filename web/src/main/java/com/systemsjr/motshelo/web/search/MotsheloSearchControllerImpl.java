// license-header java merge-point
// Generated by andromda-jsf cartridge (controllers\ControllerImpl.java.vsl)
package com.systemsjr.motshelo.web.search;

import java.util.Collection;

import javax.faces.context.FacesContext;

import com.systemsjr.motshelo.vo.MotsheloSearchCriteria;
import com.systemsjr.motshelo.vo.MotsheloVO;

/**
 * @see com.systemsjr.motshelo.web.search.MotsheloSearchController
 */
public class MotsheloSearchControllerImpl
    extends MotsheloSearchController
{
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = -1974807143693285405L;

    /**
     * @see com.systemsjr.motshelo.web.search.MotsheloSearchController#initialiseSearchScreen(MotsheloSearchCriteria searchCriteria)
     */
    @Override
    public void doInitialiseSearchScreen(DoInitialiseSearchScreenForm form)
    {
        MotsheloSearchCriteria searchCriteria = new MotsheloSearchCriteria();
        form.setSearchCriteria(searchCriteria);
    }

    /**
     * @see com.systemsjr.motshelo.web.search.MotsheloSearchController#doMetsheloSearch()
     */
    @Override
    public void doMetsheloSearch()
    {
    	Collection<MotsheloVO> metshelo = getMotsheloService().searchMetshelo(getSearchMetsheloSearchForm().getSearchCriteria());
    	getSearchMetsheloSearchForm().setMotshelos(metshelo);
    }


    /**
     * @see com.systemsjr.motshelo.web.search.MotsheloSearchController#doEditMotshelo(java.lang.Long id, MotsheloVO motsheloVO)
     */
    @Override
    public void doEditMotshelo(DoEditMotsheloForm form)
    {
    	getSearchMetsheloForm().setId(form.getId());
    	try {
			form.setMotsheloVO(getSelectedMotshelo());
		} catch (Throwable e) {
			e.printStackTrace();
		}    	
    }


    /**
     * @see com.systemsjr.motshelo.web.search.MotsheloSearchController#doMotsheloDetails(java.lang.Long id, MotsheloVO motsheloVO)
     */
    @Override
    public void doMotsheloDetails(DoMotsheloDetailsForm form)
    {    	
    	getSearchMetsheloForm().setId(form.getId());
    	try {
			form.setMotsheloVO(getSelectedMotshelo());
		} catch (Throwable e) {
			e.printStackTrace();
		} 
    	
    }

	@Override
	protected MotsheloVO getSelectedMotshelo() throws Throwable {
		MotsheloVO motsheloVO = getMotsheloService().findById(getSearchMetsheloForm().getId());
    	FacesContext.getCurrentInstance().getExternalContext().getFlash().put("motsheloVO", motsheloVO);
    	return motsheloVO;
	}

}