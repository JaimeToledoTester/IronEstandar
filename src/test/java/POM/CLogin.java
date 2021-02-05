package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class CLogin extends Base {
		
    By User=By.id("User");
    By Pass=By.id("Password");
    By Loggin=By.xpath("//input[@value='Iniciar Sesión']");
    String usuario,contraseña,url;
    ExtentReports extent;
    ExtentTest test;
    WebDriver driver;

    
   public CLogin(WebDriver driver) {
	   super(driver);
	   this.driver=driver;

   }
  
   public void login(ExtentReports extent,ExtentTest test,String usuario,String contraseña,String url)   {
   this.usuario=usuario;
   this.contraseña=contraseña;
   this.url=url;
   this.extent=extent;
   this.test=test;
   try {
   go(url);
   wait(User);
   write(usuario,User);  
   write(contraseña,Pass);
   submit(Loggin);
   Thread.sleep(3000);
   waitimplic();
   createreport(); 
   }catch(Exception e) {System.out.println("Error en CLogin, Metodo Login"+e);}
   }
   
   public void createreport() {
	   try {   
		    test.log(Status.INFO, "Resultado de Prueba: ");// información
		  	ExtentTest curl = test.createNode("Url de Prueba"); // son escenarios o pasos
		  	if (url.isEmpty()) {
		  		Assert.fail("No se encontro una URL de Pruebas");
		  	} else {
		  		curl.pass("Url de Ambiente de Pruebas: " + url);
		  	}
		  	ExtentTest cusr = test.createNode("Registro de Usuario"); // son escenarios o pasos
			if (usuario.isEmpty()) {
				Assert.fail("El Campo usuario esta vacio.");
			} else { 
				cusr.pass("Se registro el usuario: " + usuario);
			} 
			ExtentTest cpass = test.createNode("Registro de Contrasena");
			if (contraseña.isEmpty()) {
				Assert.fail("El Campo Contrasena esta vacio.");
			} else {
				cpass.pass("Se registro contrasena: " + contraseña);
			}
		   
		    }catch (Exception e) {System.out.println("Error en: createreportLoggin Error:\n"+e);}
   }
   
   
}
