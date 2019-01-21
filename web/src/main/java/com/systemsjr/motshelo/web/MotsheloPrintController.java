package com.systemsjr.motshelo.web;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;


@ManagedBean
@ViewScoped
public class MotsheloPrintController {
	@PostConstruct
	public void init() {
		
	}
	
	//public void exportPdf(Map<String, Object> params, String jasperPath, List<?> dataSource, String fileName) throws JRException, IOException {
		
		/*String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
		File file = new File(relativeWebPath);
		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSource, false);
		JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, source);
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=" + fileName);
		ServletOutputStream stream = response.getOutputStream();
		JasperExportManager.exportReportToPdfFile(print, fileName);
		FacesContext.getCurrentInstance().responseComplete();*/
	//}
}
