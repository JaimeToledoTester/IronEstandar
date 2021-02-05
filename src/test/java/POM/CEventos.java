package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class CEventos extends Base {

	ExtentReports extent;
    ExtentTest test;
    //declarando busquedas
    By User=By.id("User");
    By Pass=By.id("Password");
    By Loggin=By.xpath("//input[@value='Iniciar Sesión']");
    By Modulo= By.className("zmdi-truck");
    By PorTxt= By.id("1");
    By SelecTxt= By.id("uploadFileName");
    By CargaTxt=By.id("file_upload_button");
    By GuardarForm= By.id("shipment_card_button_save");
    By GuardarList=By.xpath("//button[@class='confirm btn btn-success']");
    By Certificado=By.id("cardautentifier_certified_tab");
    By SinCertificado=By.id("cardautentifier_uncertified_tab");
    By Credencial=By.id("credential");
    By GuarCred=By.id("cardautentifier_certified_button");
    By GuardSinCred=By.id("cardautentifier_uncertified_button");
    By Conductor=By.id("uncertified_driver_name");
    By Lt=By.id("uncertified_transline_name");
    By Viaje=By.id("shipment");
    By TS=By.id("trailer_status");
    By phon=By.id("phone_number");
    By box=By.id("box");
    By nots=By.id("note");
    By conf=By.id("confirmation");
    By cust=By.id("guard_name");
    By TP =By.id("trailer_plate");
    By TE=By.id("trailer_eco");
    By TRP= By.id("truck_plate");
    By TRE=By.id("truck_eco");
    By ps=By.id("weight");
    By pale=By.id("platform");
    By S1=By.id("seal1");
    By S2=By.id("seal2");
    By S3=By.id("seal3");
    
    //declarando variables
    String usuario,contraseña,url,ruta,empresa,name,type,certificate,credential,transline,driver_name,
    trailer_status,validate_trailer_status,phone,validate_phone,boxes,validate_boxes,notes,validate_notes,confirmation,validate_confirmation,
    custodian,validate_custodian,echo_trailer,validate_echo_trailer,echo_tract,validate_echo_tract,weight,validate_weight,pallets,validate_pallets,
    seal_1,validate_seal_1,seal_2,validate_seal_2,seal_3,validate_seal_3,shipment,validate_shipment,trailer_plate,validate_trailers_plates,
    tract_plate,validate_tracts_plates;
	
     
	public CEventos (WebDriver driver) {
		 super(driver);
	}
	
 
	public void hacer_evento(ExtentReports extent,ExtentTest test,String empresa,String name,String type,String certificate,
			  String credential,String transline,String driver_name,String trailer_status,String validate_trailer_status,
			  String phone,String validate_phone,String boxes,String validate_boxes,String notes,String validate_notes,
			  String confirmation,String validate_confirmation,String custodian,String validate_custodian,String echo_trailer,
			  String validate_echo_trailer,String echo_tract,String validate_echo_tract,String weight,String validate_weight,
			  String pallets,String validate_pallets,String seal_1,String validate_seal_1,String seal_2,String validate_seal_2,
			  String seal_3,String validate_seal_3,String shipment,String validate_shipment,String trailer_plate,
			  String validate_trailers_plates,String tract_plate,String validate_tracts_plates,String usuario,String contraseña,String url) {
		   try { 
		   this.usuario=usuario;
		   this.contraseña=contraseña;
		   this.url=url;
		   this.extent=extent; 
		   this.test=test;
		   this.empresa=empresa;
		   this.name=name;
		   this.type=type;
		   this.certificate=certificate;
		   this.credential=credential;
		   this.transline=transline;
		   this.driver_name=driver_name;
		   this.trailer_status=trailer_status;
		   this.validate_trailer_status=validate_trailer_status;
		   this.phone=phone;
		   this.validate_phone=validate_phone;
		   this.boxes=boxes;
		   this.validate_boxes=validate_boxes;
		   this.notes=notes;
		   this.validate_notes=validate_notes;
		   this.confirmation=confirmation;
		   this.validate_confirmation=validate_confirmation;
		   this.custodian=custodian;
		   this.validate_custodian=validate_custodian;
		   this.echo_trailer=echo_trailer;
		   this.validate_echo_trailer=validate_echo_trailer;
		   this.echo_tract=echo_tract;
		   this.validate_echo_tract=validate_echo_tract;
		   this.weight=weight;
		   this.validate_weight=validate_weight;
		   this.pallets=pallets;
		   this.validate_pallets=validate_pallets;
		   this.seal_1=seal_1;
		   this.validate_seal_1=validate_seal_1;
		   this.seal_2=seal_2;
		   this.validate_seal_2=validate_seal_2;
		   this.seal_3=seal_3;
		   this.validate_seal_3=validate_seal_3;
		   this.shipment=shipment;
		   this.validate_shipment=validate_shipment;
		   this.trailer_plate=trailer_plate;
		   this.validate_trailers_plates=validate_trailers_plates;
		   this.tract_plate=tract_plate;
		   this.validate_tracts_plates=validate_tracts_plates;
		   login();
		   buscarevento();
		   mtipoevento();
		   }catch(Exception e) {System.out.println("Error en CEventos, Metodo hacer_evento"+e);}
	}
	
	public void login() {
		try {
		   go(url);
		   wait(User);
		   write(usuario,User); 
		   write(contraseña,Pass);
		   submit(Loggin);
		   Thread.sleep(2000);
		   wait(Modulo); 
		   clic(Modulo);
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
		}catch(Exception e) {System.out.println("Error en Clase CEventos, Metodo: login "+e);}
	}
	public void buscarevento() {
		try {
		waitimplic();
	    wait(By.xpath("//a[contains(text(),'"+name+"')]"));
	    clic(By.xpath("//a[contains(text(),'"+name+"')]"));
	    ExtentTest cevent = test.createNode("Evento a realizar: "+name);
	    Boolean ispresent = isPresent(By.xpath("//a[text()='"+name +"']"));
	    if (ispresent==false) {
        	cevent.fail("No se encontro el evento:"+name);
    	} else {
    		cevent.pass("Se encontro el evento: " + name);
    	}
		}catch(Exception e) {System.out.println("Error en Clase CEventos, Metodo: buscarevento: "+e);}
	}
	
	public void mtipoevento() {
		try {	
		ExtentTest ctipoevento = test.createNode("Tipo de evento");
		 if (type.isEmpty()) {
	     	ctipoevento.fail("No se encontro el tipo de evento:"+type);
	 	} else {
	 		ctipoevento.pass("El tipo de evento es: " + type);
	 	}
	  switch (type)//que tipo de evento es
	       {
	           case "Formulario"://si el tipo de evento es formu
	               try
	               {
	           	    ExtentTest ccertificado = test.createNode("Certificacion de evento");
	        	    if (certificate.isEmpty()) {
	        		 ccertificado.fail("No se configuro una forma de realizar el registro correctamente(certificación): "+certificate);
	         	    } else {
	         		ccertificado.pass("El eventos se registro con certificacion?: "+certificate );
	         	    }
	        	    waitimplic();
	                mcertificado();
	           		mcamposevento();
	           		wait(GuardarForm);
                    submit(GuardarForm);
	               }
	               catch (Exception i) {  System.out.println("Error en Evento: "+name+" de tipo "+type+",con el viaje: \"+shipment+\" Error: \n" + i); }
	               break;

	           case "Listado":
	               try {
	               test.log(Status.INFO, "Tipo de Evento: "+type);
	               wait(By.xpath("//button[@class='btn btn-danger' and contains(@onclick,'CONFIRM') and contains(@onclick,'"+shipment+"')]"));
	               clic(By.xpath("//button[@class='btn btn-danger' and contains(@onclick,'CONFIRM') and contains(@onclick,'"+shipment+"')]"));               
	               waitimplic();
	               clic(GuardarList);
	               } catch (Exception i) {  System.out.println("Error en Evento: "+name+" de tipo "+type+",con el viaje: \"+shipment+\" Error: \n" + i); }
	               break;
	           case "Listado-Formulario":
	        	   try {
	        	   waitimplic();
	        	   test.log(Status.INFO, "Tipo de Evento: "+type);
	        	   wait(By.xpath("//button[@class='btn btn-danger' and contains(@onclick,'FORM') and contains(@onclick,'"+shipment+"')]"));
	               clic(By.xpath("//button[@class='btn btn-danger' and contains(@onclick,'FORM') and contains(@onclick,'"+shipment+"')]"));               
	               ExtentTest ccertificado = test.createNode("Certificacion de evento");
	       	       if (certificate.isEmpty()) {
	       		   ccertificado.fail("No se configuro una forma de realizar el registro correctamente(certificación): "+certificate);
	        	   } else {
	        	     ccertificado.pass("El eventos se registro con certificacion? :"+certificate );
	        	   }
	               mcertificado();
	               mcamposevento();
	          	   wait(GuardarForm);
	          	   submit(GuardarForm);
	        	   }catch(Exception er) {System.out.println("Error en Evento: "+name+", de tipo "+type+", con el viaje: "+shipment+" Error: \n" + er);}
	               break;
	            default:
	        	   System.out.println("error en tipo de evento: NO SE ECNOTNRO UNA FUNCION PARA ESE TIPO DE EVENTO ");
	               break;
	       }         
		}catch (Exception e) {System.out.println("ERROR EN MTIPO DE EVENTO: "+e);}
	}
	
	public void mcertificado() {
	    try
	    {
			 Thread.sleep(1000);
			 waitimplic();
	        switch (certificate)//si el evento es con credencial o no
	        {
	            case "true"://si el evento es con certificado
	    			Thread.sleep(2000);
//	            	waitimplic();	            	
	            	ExtentTest ccredencial = test.createNode("Datos de Certificado");
	        		if (credential.isEmpty()) {
	        		ccredencial.fail("No se configuro una credencial para el evento: "+credential);
	        	 	} else {
	        	 	ccredencial.pass("El evento se registro con la credencial: "+credential);
	        	 	}
	        		wait(Certificado);
	                clic(Certificado);
	                write(credential,Credencial);
	                clic(GuarCred);
	                break;

	            case "false"://si el evento no es certificado
                    waitimplic();
                    ExtentTest csincert = test.createNode("Evento realizado sin certificacion");
	    		    if (driver_name.isEmpty()|transline.isEmpty()) {
	    		    	csincert.fail("No se configuraron correctamente los datos para realizar el evento sin certificación: "+credential);
	    	 	    } else {
	    	 	    	csincert.pass("El eventos se registro con los datos: "+"Operador: "+driver_name+" Linea de Transportes: "+transline);
	    	 	    }
	        		wait(SinCertificado);
	                clic(SinCertificado);
	                write(driver_name,Conductor);
	                write(transline,Lt);
	                wait(By.id("ui-id-1"));
	                WebElement autocompletado =findElement(By.id("ui-id-1"));
	                autocompletado.findElement(By.xpath("//a[contains(text(),'" +transline+ "')]"));
	                autocompletado.click();
	                clic(GuardSinCred);
	                break;
	                default:
//	                	System.out.println("Certificado: Disabled");
	                	waitimplic();
	            	break;           
	        }
	    }
	    catch (Exception i) { System.out.println("Error en MCertificación: "+i); }
	}

	 

	public void mcamposevento()
	{
	    try {
			Thread.sleep(1000);
//	    	-----------------------------------Numero de viaje    	
		if (validate_shipment.contains("true")) {
			ExtentTest cshipment = test.createNode("Campo Shipment");
			Boolean ispresent1=isEnabled(Viaje);
			if (ispresent1!=false) {
			cshipment.pass("Numero de viaje: "+shipment);
			clear(Viaje);
			write(shipment,Viaje);
			}else {cshipment.fail("No se encontro el campo en el evento.");}	
		}
//		-----------------------------------ESTADO REMOLQUE
		if (validate_trailer_status.contains("true")) {
			ExtentTest ctrastat = test.createNode("Campo sstatus de Remolque"+trailer_status);
			Boolean ispresent2= isEnabled(TS);
			if (ispresent2!=false) {
			ctrastat.pass("El estatus de el remolque es: "+trailer_status);
			Select er = new Select(findElement(TS));
			er.selectByVisibleText(trailer_status);
		}else {ctrastat.fail("No se encontro el campo en el evento.");}	
		}
//		-----------------------------------TELEFONO
		if (validate_phone.contains("true")) {
			ExtentTest cphone = test.createNode("Campo Telefono");
			Boolean ispresent3= isEnabled(phon);
			if (ispresent3!=false) {
			cphone.pass("El Telefono capturado es: "+phone);
			write(phone,phon);
			}else {cphone.fail("No se encontro el campo en el evento.");}	
		}
//		-----------------------------------CAJAS
		if (validate_boxes.contains("true")) {
			ExtentTest cbox = test.createNode("Campo Cajas");
			Boolean ispresent4= isEnabled(box);
			if (ispresent4!=false) {
			cbox.pass("Cajas: "+boxes);
			write(boxes,box);
			}else {cbox.fail("No se encontro el campo en el evento.");}	
		}
//		-----------------------------------NOTAS
		if (validate_notes.contains("true")) {
			ExtentTest cnotes = test.createNode("Campo Notas");
			Boolean ispresent5=isEnabled(nots);
			if (ispresent5!=false) {
			cnotes.pass("Notas: "+notes);
			write(notes,nots);
			}else {cnotes.fail("No se encontro el campo en el evento.");}	
		}
//		-----------------------------------CONFIRMACIÓN
		if (validate_confirmation.contains("true")) {
			ExtentTest cconfirmation = test.createNode("Campo Confirmacion");
			Boolean ispresent6= isEnabled(conf);
			if (ispresent6!=false) {
			cconfirmation.pass("Confirmacion: "+confirmation);
			write(confirmation,conf);
			}else {cconfirmation.fail("No se encontro el campo en el evento.");}
		}
//		-----------------------------------CUSTODIO
		if (validate_custodian.contains("true")) {
			ExtentTest ccustodio = test.createNode("Campo Custodio");
			Boolean ispresent7 = isEnabled(cust);
			if (ispresent7!=false) {
			ccustodio.pass("Custodio: "+custodian);
			write(custodian,cust);
			}else {ccustodio.fail("No se encontro el campo en el evento.");}
		}
//		-----------------------------------PLACA REMOLQUE
		if (validate_trailers_plates.contains("true")) {
			ExtentTest cplacaremo = test.createNode("Campo Placa Remolque");
			Boolean ispresent8 = isEnabled(TP);
			if (ispresent8!=false) {
			cplacaremo.pass("Placa Remolque: "+trailer_plate);
			clear(TP);
			write(trailer_plate,TP);
			}else {cplacaremo.fail("No se encontro el campo en el evento.");}	
		}
//		-----------------------------------ECO REMOLQUE	
		if (validate_echo_trailer.contains("true")) {
			ExtentTest cecoremolque = test.createNode("Campo Eco Remolque");
			Boolean ispresent9 = isEnabled(TE);
			if (ispresent9!=false) {
			cecoremolque.pass("Eco Remolque: "+echo_trailer);	
			clear(TE);
			write(echo_trailer,TE);
			}else {cecoremolque.fail("No se encontro el campo en el evento.");}	
		} 
//		-----------------------------------PLACA TRACTO
		if (validate_tracts_plates.contains("true")) {
			ExtentTest cplacatracto = test.createNode("Campo Placa Tracto");
			Boolean ispresent10 = isEnabled(TRP);
			if (ispresent10!=false) {
				cplacatracto.pass("Placa Tracto: "+tract_plate);
				clear(TRP);
				write(tract_plate,TRP);
			}else {cplacatracto.fail("No se encontro el campo en el evento.");}	
		}
//		-----------------------------------ECO TRACTO
		if (validate_echo_tract.contains("true")) {
			Boolean ispresent11 = isEnabled(TRE);
	        ExtentTest cecho_tract= test.createNode("Campo Eco Tracto");
			if (ispresent11!=false) {
			cecho_tract.pass("Eco Tracto: "+echo_tract);
			clear(TRE);
			write(echo_tract,TRE);
			}else {cecho_tract.fail("No se encontro el campo en el evento.");}	
		}
//		-----------------------------------PESO
		if (validate_weight.contains("true")) {
			Boolean ispresent12 = isEnabled(ps);
			ExtentTest cweight= test.createNode("Campo Peso");
			if (ispresent12!=false) {
			cweight.pass("Peso: "+weight);	
			clear(ps);
			write(weight,ps);
			}else {cweight.fail("No se encontro el campo en el evento.");}	
		}
//		-----------------------------------PALLETS
		if (validate_pallets.contains("true")) {
			Boolean ispresent13 = isEnabled(pale);
			ExtentTest cpallets = test.createNode("Campo Tarimas");
			if (ispresent13!=false) {
			cpallets.pass("Tarimas: "+pallets);
			clear(pale);
			write(pallets,pale);
			}else {cpallets.fail("No se encontro el campo en el evento.");}	
		}
//		-----------------------------------SELLO1
		if (validate_seal_1.contains("true")) {
			Boolean ispresent14 = isEnabled(S1);
			ExtentTest cseal1 = test.createNode("Campo Sello 1");
			if (ispresent14!=false) {
			cseal1.pass("Sello 1: "+seal_1);
			clear(S1);
			write(seal_1,S1);
			}else {cseal1.fail("No se encontro el campo en el evento.");}	
		}
//		-----------------------------------SELLO2
		if (validate_seal_2.contains("true")) {
			Boolean ispresent15 = isEnabled(S2);
			ExtentTest cseal2 = test.createNode("Campo Sello 2");
			if (ispresent15!=false) {
			cseal2.pass("Sello 2: "+seal_2);
			clear(S2);
			write(seal_2,S2);
			}else {cseal2.fail("No se encontro el campo en el evento.");}	
		}
//		-----------------------------------SELLO3
		if (validate_seal_3.contains("true")) {
			ExtentTest cseal3 = test.createNode("Campo Sello 3");
			Boolean ispresent16=isEnabled(S3);
			if (ispresent16!=false) {
			cseal3.pass("Sello 3: "+seal_3);
			clear(S3);
			write(seal_3,S3);
			}else {cseal3.fail("No se encontro el campo en el evento.");}	
		}
	} catch (Exception i) {
		System.out.println("Error en MCamposevento: "+ i);
	}
	}
	
	
}
