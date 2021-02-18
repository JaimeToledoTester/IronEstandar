package Test;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityModelProvider;
import Adicional.CapturarPantalla;
import Adicional.Parametros;
import Adicional.Reporte;
import POM.CEventos;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class Eventos { 
	
	CapturarPantalla c = new CapturarPantalla();// objeto para otra clase
	Reporte rep = Reporte.getInstance();
	Parametros param = Parametros.getInstance();	
	ExtentTest test;
	ExtentReports extent;
    WebDriver driver;
	CEventos ceventos;
    By toast=By.className("toast-message"); 
    By ass= By.xpath("//*[@class='toast-message'] or //div[@id='qr-document-container']");
    By qr=By.id("qr-document-container"); 
	MediaEntityModelProvider evidencia;
    String Page;
    
	@BeforeClass
    public void beforeClass() {	
			 ceventos = new CEventos(driver);
			 driver = ceventos.chrome();
			 extent=rep.addreporte();
    } 	
	
  @Test (dataProvider = "Eventos", dataProviderClass=DataProvider.class)
  public void eventos(String empresa,String name,String type,String certificate,String credential,String transline,String driver_name,
		  String trailer_status,String validate_trailer_status,String phone,String validate_phone,String boxes,String validate_boxes,
		  String notes,String validate_notes,String confirmation,String validate_confirmation,String custodian,String validate_custodian,
		  String echo_trailer,String validate_echo_trailer,String echo_tract,String validate_echo_tract,String weight,String validate_weight,
		  String pallets,String validate_pallets,String seal_1,String validate_seal_1,String seal_2,String validate_seal_2,
		  String seal_3,String validate_seal_3,String shipment,String validate_shipment,String trailer_plate,String validate_trailers_plates,
		  String tract_plate,String validate_tracts_plates,String usuario,String contraseña,String url) {
  try { 
     test=extent.createTest("CP: Evento "+name+" Empresa: "+empresa);
	 ceventos.hacer_evento(extent,test,empresa, name, type, certificate, credential, transline, driver_name, trailer_status, 
			  validate_trailer_status, phone, validate_phone, boxes, validate_boxes, notes, validate_notes, confirmation, validate_confirmation,
			  custodian, validate_custodian, echo_trailer, validate_echo_trailer, echo_tract, validate_echo_tract, weight, validate_weight, 
			  pallets, validate_pallets, seal_1, validate_seal_1, seal_2, validate_seal_2, seal_3, validate_seal_3, shipment, validate_shipment, 
			  trailer_plate, validate_trailers_plates, tract_plate, validate_tracts_plates, usuario, contraseña, url); 
    }catch(Exception e) {System.out.println("Error en Test Eventos");}
  
    WebDriverWait wait=new WebDriverWait(driver,5);
    wait.until(ExpectedConditions.presenceOfElementLocated(toast));
    String message=driver.findElement(toast).getText();
      Assert.assertEquals(message,"Registro exitoso");
    }
  @AfterMethod
 	public void ResultadoEventos(ITestResult result) throws Exception {
	  ceventos.resultest(result,test,driver,Page);
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
