package Test;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityModelProvider;
import Adicional.CapturarPantalla;
import Adicional.Reporte;
import POM.CPlanTxt;

public class Planeacion_Txt {

	CapturarPantalla c = new CapturarPantalla();// objeto para otra clase
	Reporte rep = Reporte.getInstance();	
	ExtentTest test;
	ExtentReports extent;
	private WebDriver driver;
	CPlanTxt cplan;
	MediaEntityModelProvider evidencia;
    String Page;

	@BeforeClass
	public void beforeClass()  {
		cplan = new CPlaneacionTxt(driver);	
		driver=cplan.chrome();
		extent=rep.addreporte();
	} 

	@Test(dataProvider = "UsersandTxt", dataProviderClass = DataProvider.class)
	public void Loginm(String empresa,String usuario, String contraseña, String ruta, String url){
	try {
	test = extent.createTest("CP:Cargar Planeacion de :"+empresa);
    cplan.CargarTxt(extent, test, usuario, contraseña, url, ruta);	
	Page= new Object(){}.getClass().getEnclosingMethod().getName();
	}catch(Exception e) {System.out.println("Error en TestLogin, Metodo Loginm");}
	}

	@AfterMethod
	public void ResultadoPlanTxt(ITestResult result) {
		cplan.resultest(result,test,driver,Page);	
	}
	
	@AfterTest
	public void testend() throws Exception {		
		extent.flush(); 		
		driver.quit();
	}

	@AfterClass
	public void afterClass() throws Exception {
 		driver.quit();
	}
}
