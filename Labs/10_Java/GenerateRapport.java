/**
 * 
 */
package com.plb.jasper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

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
		
		//
		if ( args.length ==0 ) {
			System.out.println("Usage : run <jrxml> [<country_parameter> <locale>]");
			System.exit(0);
		}
		String file = args[0];
		String country = "France";
		String langu
		if ( args.length > 1 ) {
			country = args[1];
		}
		if ( args.length > 2 ) {
			language = args[2];

	/////////////////
	// A compléter //
	/////////////////


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
