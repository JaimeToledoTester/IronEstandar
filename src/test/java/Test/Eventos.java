package Test;

import org.testng.annotations.Test;
import com.aventstack.extentreports.MediaEntityModelProvider;
import Adicional.Data_Provider;
import POM.Base;
import POM.CEventos;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class Eventos { 
	
	Base b=Base.getInstance();
	CEventos ceven=CEventos.getInstance();	
	MediaEntityModelProvider evidencia;
    String Page;
    
  @Test (dataProvider = "Eventos", dataProviderClass = Data_Provider.class)
  public void eventos(String empresa,String name,String type,String certificate,String credential,String transline,String driver_name,
		  String trailer_status,String validate_trailer_status,String phone,String validate_phone,String boxes,String validate_boxes,
		  String notes,String validate_notes,String confirmation,String validate_confirmation,String custodian,String validate_custodian,
		  String echo_trailer,String validate_echo_trailer,String echo_tract,String validate_echo_tract,String weight,String validate_weight,
		  String pallets,String validate_pallets,String seal_1,String validate_seal_1,String seal_2,String validate_seal_2,
		  String seal_3,String validate_seal_3,String shipment,String validate_shipment,String trailer_plate,String validate_trailers_plates,
		  String tract_plate,String validate_tracts_plates,String usuario,String contraseña,String url) {
  try { 
     ceven.hacer_evento(empresa, name, type, certificate, credential, transline, driver_name, trailer_status, 
			  validate_trailer_status, phone, validate_phone, boxes, validate_boxes, notes, validate_notes, confirmation, validate_confirmation,
			  custodian, validate_custodian, echo_trailer, validate_echo_trailer, echo_tract, validate_echo_tract, weight, validate_weight, 
			  pallets, validate_pallets, seal_1, validate_seal_1, seal_2, validate_seal_2, seal_3, validate_seal_3, shipment, validate_shipment, 
			  trailer_plate, validate_trailers_plates, tract_plate, validate_tracts_plates, usuario, contraseña, url); 
    }catch(Exception e) {System.out.println("Error en Test Eventos");}
 
    }
  
    @AfterMethod
 	public void ResultadoEventos(ITestResult result) throws Exception {
	  b.resultest(result,Page);
 	}
   

 	@AfterTest
 	public void testend() throws Exception {		
 		ceven.extent.flush();
 		ceven.driver.quit();
 	}

 	@AfterClass
 	public void afterClass() throws Exception {
 		ceven.driver.quit();
 	}
 	

}
