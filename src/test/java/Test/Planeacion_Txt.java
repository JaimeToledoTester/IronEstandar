package Test;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
<<<<<<< HEAD
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
=======
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
	public void CargaPlan(String empresa,String usuario, String contraseña, String rutatxt, String url){
>>>>>>> JaimeMejoras
	try {
    cplan.CargarTxt(usuario, contraseña, url, rutatxt,empresa);	
	Page= new Object(){}.getClass().getEnclosingMethod().getName();
	}catch(Exception e) {System.out.println("Error en Test Cargar Planeación, Metodo CargaPlan: "+e);}
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
