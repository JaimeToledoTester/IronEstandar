package Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.*;
import org.testng.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityModelProvider;
import Adicional.*;
import POM.CLogin;

public class Login {
	Reporte rep = Reporte.getInstance();
	Parametros param = Parametros.getInstance();	
	ExtentTest test;
	ExtentReports extent;
	private WebDriver driver;
	CLogin clogin;
	MediaEntityModelProvider evidencia;
    String Page;
    By asser=By.xpath("//*[contains(@href,'LogOut')]"); 


	@BeforeClass
	public void beforeClass()  {
		clogin = new CLogin(driver);
		driver = clogin.chrome();
		extent=rep.addreporte();
	} 
	
	@Test(dataProvider = "Usuarios", dataProviderClass = Data_Provider.class)
	public void Loginm(String empresa,String usuario,String contraseña, String url){
	try {	
    test=extent.createTest("CP: Validar Login de "+empresa);
	clogin.login(extent,test,usuario, contraseña, url);
	clogin.wait(asser);
	assertTrue(clogin.isDisplayed(asser));
	Page= new Object(){}.getClass().getEnclosingMethod().getName();
	}catch(Exception e) {System.out.println("Error en TestLogin, Metodo Loginm"+e);}
	}
 
	@AfterMethod
	public void ResultadoLoggin(ITestResult result) {
		clogin.resultest(result,test,driver,Page);	
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
