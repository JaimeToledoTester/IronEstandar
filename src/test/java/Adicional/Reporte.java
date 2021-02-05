package Adicional;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.*;

public class Reporte {

//////---------------------------------Ruta Reporte
	private static ExtentHtmlReporter reporte = new ExtentHtmlReporter("./Reporte/Resultados.html");
	private static ExtentReports extent = new ExtentReports();// crear el "el reporte"

	private Reporte() {
		extent.attachReporter(reporte);
		reporte.config().setCSS("css-string");
		reporte.config().setDocumentTitle("RECO");
		reporte.config().setEncoding("utf-8");
		reporte.config().setJS("js-string");
		reporte.config().setProtocol(Protocol.HTTPS);
		reporte.config().setReportName("Iron Estandar");
		reporte.config().setTheme(Theme.STANDARD);
		reporte.config().setTimeStampFormat("dd MM yyyy HH:mm:ss");
	}
	
	public ExtentReports addreporte() {
		return extent;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static Reporte rep;// para la instancia

	public static Reporte getInstance()// si no existe una instancia creala
	{
		if (rep == null) {
			rep = new Reporte();
		}
		return rep;
	}

}
