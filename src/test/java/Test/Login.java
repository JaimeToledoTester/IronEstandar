package Test;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
<<<<<<< HEAD
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.*;
import org.testng.*;
=======
>>>>>>> JaimeMejoras
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import Adicional.*;
import POM.Base;
import POM.CLogin;

public class Login {
<<<<<<< HEAD
	CapturarPantalla c = new CapturarPantalla();// objeto para otra clase
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
	
	@Test(dataProvider = "Usuarios", dataProviderClass = DataProvider.class)
	public void Loginm(String empresa,String usuario,String contraseña, String url){
	try {	
    test=extent.createTest("CP: Validar Login de "+empresa);
	clogin.login(extent,test,usuario, contraseña, url);
	clogin.wait(asser);
	assertTrue(clogin.isDisplayed(asser));
=======
	Base b=Base.getInstance();
	CLogin clog=CLogin.getInstance();	
	String Page;	 
	 
	@Test(dataProvider = "Usuarios", dataProviderClass = Data_Provider.class)
	public void login(String empresa,String usuario,String contraseña, String url){
	try {	 
	clog.login(usuario, contraseña, url, empresa);
>>>>>>> JaimeMejoras
	Page= new Object(){}.getClass().getEnclosingMethod().getName();
	}catch(Exception e) {System.out.println("Error en TestLogin, Metodo login "+e);}
	}
 
	@AfterMethod
	public void ResultadoLoggin(ITestResult result) {
		b.resultest(result,Page);	
	}

	
	@AfterTest
	public void testend() throws Exception {		
		clog.extent.flush();
 		clog.driver.quit();
	}

	@AfterClass
	public void afterClass() throws Exception {
 		clog.driver.quit();
	}

}
