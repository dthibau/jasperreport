/**
 * 
 */
package org.formation.jasper;

import java.awt.Dimension;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;



/**
 * @author David
 *
 */
public class GenerateRapport {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Class.forName("org.hsqldb.jdbcDriver"); //Or any other driver
		}
		catch(Exception x){
			System.out.println("Unable to load the driver class!");
		}
//		ResourceBundle res = ResourceBundle.getBundle("labels");
		if ( args.length ==0 ) {
			System.out.println("Usage : run <jrxml> [<country_parameter> <locale>]");
			System.exit(0);
		}
		String file = args[0];
		String country = "France";
		String language = "FR";
		if ( args.length > 1 ) {
			country = args[1];
		}
		if ( args.length > 2 ) {
			language = args[2];
		}
		
		GenerateRapport me = new GenerateRapport();
		
		JasperPrint jasperPrint = me.generate(file, country, language);
		
		JFrame frame = new JFrame("Jasper report");

        JRViewer viewer = new JRViewer(jasperPrint);
        
        frame.add(viewer);
        frame.setSize(new Dimension(500, 400));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

	}
	
	public JasperPrint generate(String fileName, String country, String language)  {
		try {
			File file = new File(
					getClass().getClassLoader().getResource(fileName).getFile()
				);
			JasperDesign design = JRXmlLoader.load(file);
			
			JasperReport jasperReport = JasperCompileManager.compileReport(design);
			
			JasperPrint jasperPrint = null;
			if ( country == null ) {
				jasperPrint =JasperFillManager.fillReport(jasperReport, null,getConnection());
			} else {
				Map<String,Object> parametersMap = new HashMap<String,Object>();
				parametersMap.put("SHIPCOUNTRY",country);
				parametersMap.put("REPORT_LOCALE",new Locale(language));
				jasperPrint =JasperFillManager.fillReport(jasperReport, parametersMap,getConnection());
			}
			
			JasperExportManager.exportReportToPdfFile(jasperPrint, fileName +".pdf");
			
			return jasperPrint;
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		 return null;
	}
	
	private static Connection getConnection() {
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost","sa",null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

}
