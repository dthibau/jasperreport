package org.formation.jasper;

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

public class ComputeTime extends JRDefaultScriptlet {

	long reportInitTime = 0;

	@Override
	public void beforeReportInit() throws JRScriptletException {
		reportInitTime = System.currentTimeMillis();		

	}

	@Override
	public void beforePageInit() throws JRScriptletException {
		super.beforePageInit();
		setVariableValue("LAST_FIRST_LETTER", getVariableValue("FIRST_LETTER"));
		System.out.println(getVariableValue("FIRST_LETTER"));
			
	}

	public Long getComputeTime() {
		long now = System.currentTimeMillis();
		return new Long(now - reportInitTime);
	}

}
