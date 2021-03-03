package Test;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import Adicional.*;
import POM.Base;
import POM.CLogin;

public class Login {
	Base b=Base.getInstance();
	CLogin clog=CLogin.getInstance();	
	String Page;	 
	 
	@Test(dataProvider = "Usuarios", dataProviderClass = Data_Provider.class)
	public void login(String empresa,String usuario,String contrasena, String url){
	try {	 
	clog.login(usuario, contrasena, url, empresa);
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
	}


}
