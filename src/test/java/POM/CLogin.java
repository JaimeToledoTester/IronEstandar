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
   
   public void Url() {
	   ExtentTest curl = test.createNode("Url de Prueba"); // son escenarios o pasos
	  	if (url.isEmpty()) {
	  		Assert.fail("No se encontro una URL de Pruebas");
	  	} else {
	 	    go(url);
	 	    User();
	  		curl.pass("Url de Ambiente de Pruebas: " + url);
	  	}   
   }
   public void User() {
	   ExtentTest cusr = test.createNode("Registro de Usuario"); // son escenarios o pasos
		if (usuario.isEmpty()) {
			Assert.fail("El Campo usuario esta vacio.");
		} else { 
			cusr.pass("Se registro el usuario: " + usuario);
			wait(User);
		    write(usuario,User); 	
		    Password();
		} 
 
   }
   
   public void Password() {
	   ExtentTest cpass = test.createNode("Registro de Contrasena");
		if (contraseña.isEmpty()) {
			Assert.fail("El Campo Contrasena esta vacio.");
		} else {
			wait(Pass);
			write(contraseña,Pass);
			cpass.pass("Se registro contrasena: " + contraseña);
		}

   }
  
   public void login(ExtentReports extent,ExtentTest test,String usuario,String contraseña,String url)   {
   this.usuario=usuario;
   this.contraseña=contraseña;
   this.url=url;
   this.extent=extent;
   this.test=test;
   try {
   test.log(Status.INFO, "Resultado de Prueba: ");
   Url();
   submit(Loggin);
   }catch(Exception e) {System.out.println("Error en CLogin, Metodo Login"+e);}
   }
   
   
}
