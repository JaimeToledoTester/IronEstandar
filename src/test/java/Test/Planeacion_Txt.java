package Test;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import Adicional.Data_Provider;
import POM.Base;
import POM.CPlanTxt;

public class Planeacion_Txt {
	Base b=Base.getInstance();
	CPlanTxt cplan=CPlanTxt.getInstance();	
	Base base;
    String Page;

	@Test(dataProvider = "UsersandTxt", dataProviderClass = Data_Provider.class)
	public void CargaPlan(String empresa,String usuario, String contrasena, String rutatxt, String url){
	try {
    cplan.CargarTxt(usuario, contrasena, url, rutatxt,empresa);	
	Page= new Object(){}.getClass().getEnclosingMethod().getName();
	}catch(Exception e) {System.out.println("Error en Test Cargar Planeaci�n, Metodo CargaPlan: "+e);}
	}

	@AfterMethod
	public void ResultadoPlanTxt(ITestResult result) {
		b.resultest(result,Page);	
	}
	
	@AfterTest
	public void testend() throws Exception {		
		cplan.extent.flush(); 		
		cplan.driver.quit();
	}

	@AfterClass
	public void afterClass() throws Exception {
 		cplan.driver.quit();
	}
}
