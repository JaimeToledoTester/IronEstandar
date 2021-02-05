package Adicional;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class LeerJson {

	FileChooser fc = FileChooser.getInstance();
	Parametros p = Parametros.getInstance();

	JSONObject todo = null;

	public void leerjson() throws FileNotFoundException, IOException, ParseException {
		try {
		String ruta = fc.traerarchivo("Seleccione el Archivo JSON con la Configuración: ");
		final BufferedReader json =new BufferedReader(new InputStreamReader(new FileInputStream(ruta), "UTF-8"));
		JSONParser parser = new JSONParser();
		Object obj=parser.parse(json);
		todo = (JSONObject) obj;
		// return todo;
		}catch(Exception e) {System.out.println("Error al leer json"+e);}
	}  

	// se trae todos los usuarios y contraseñas
	public ArrayList<Object[]> arregloUsuarios() throws FileNotFoundException, IOException, ParseException {
		JSONArray data = (JSONArray) todo.get("companies");
		ArrayList<Object[]> usuarios = new ArrayList<Object[]>();
		for (int i = 0; i < data.size(); i++) {
			JSONObject ind = (JSONObject) data.get(i);// leer cada usuario
			Object a[] = { ind.get("company").toString(),ind.get("user").toString(), ind.get("password").toString(), ind.get("url").toString() };
			usuarios.add(a);
		}
		return usuarios;
	}

	// se trae todos los usuarios,contraseñas y txt planeación
	public ArrayList<Object[]> UsersandTxt() throws FileNotFoundException, IOException, ParseException {
		JSONArray data = (JSONArray) todo.get("companies");
		ArrayList<Object[]> UandT = new ArrayList<Object[]>();
		for (int i = 0; i < data.size(); i++) {
			JSONObject uandt = (JSONObject) data.get(i);
			Object a[] = {uandt.get("company").toString(), uandt.get("user").toString(), uandt.get("password").toString(),
					uandt.get("txtplanning").toString(), uandt.get("url").toString() };
			UandT.add(a);
		}
		return UandT;
	}

	// se trae datos para el envío de correos
	public void dataemail() throws FileNotFoundException, IOException, ParseException {
		try {
			JSONArray data = (JSONArray) todo.get("mail");
			for (int i = 0; i < data.size(); i++) {
				JSONObject dm = (JSONObject) data.get(i);
				p.msend = dm.get("send").toString();
				p.muser = dm.get("user").toString();
				p.mpass = dm.get("password").toString();
				p.mfrom = dm.get("from").toString();
				p.maliasfrom = dm.get("aliasfrom").toString();
				JSONArray destinatarios = (JSONArray) dm.get("to");
				for (int j = 0; j < destinatarios.size(); j++) {
					String a = destinatarios.get(j).toString();
//					System.out.println("destinatario: " + j + " = " + a);
					p.mto.add(a);
				}
				p.msubject = dm.get("subject").toString();
				p.mmessage = dm.get("message").toString();
				p.mfilename = dm.get("filename").toString();
				p.mfiledescription = dm.get("filedescription").toString();
			}
		} catch (Exception e) {
			System.out.println("=============================ERROR EN METODO DATA de EMAIL: " + e.getStackTrace());
		}
	}

	// SABER MÓDULOS QUE SE VALIDARAN
	public void modulos() throws FileNotFoundException, IOException, ParseException {
		try {
			JSONArray data = (JSONArray) todo.get("modules");
			for (int i = 0; i < data.size(); i++) {
				JSONObject dm = (JSONObject) data.get(i);
				p.mloggin = dm.get("loggin").toString();
				p.mplanning = dm.get("planning").toString();
				p.mevents = dm.get("events").toString();
			}
		} catch (Exception e) {
			System.out.println("=============================ERROR EN METODO DATA de EMAIL: " + e.getStackTrace());
		}
	}

	public Iterator<ArrayList<Object[]>> validar_eventos() {
		ArrayList<Object[]> evento = new ArrayList<Object[]>();
		ArrayList<ArrayList<Object[]>> eventos = new ArrayList<ArrayList<Object[]>>();
		try {
			JSONArray data = (JSONArray) todo.get("companies");// traer los datos de companies
			for (int xx = 0; xx < data.size(); xx++) {// recorrer los datos dentro de cada companies
				JSONObject uspassurl = (JSONObject) data.get(xx);// se trae todos los datos dentro de cada companies
				Object company = uspassurl.get("company");
				Object vuser = uspassurl.get("user");
				Object vpass = uspassurl.get("password");
				Object vurl = uspassurl.get("url");
				JSONArray veventos = (JSONArray) uspassurl.get("events");
				//-----------------------sacar viajes-----------------------------------------
				JSONArray vshipments = (JSONArray) uspassurl.get("shipments");
				JSONObject todo_shipm= (JSONObject) vshipments.get(0);
				JSONArray shipments= (JSONArray) todo_shipm.get("data");
				//-----------------------placas remolque-----------------------------------------
				JSONArray vtrailers_plates = (JSONArray) uspassurl.get("trailers_plates");
				JSONObject todo_placrem= (JSONObject) vtrailers_plates.get(0);
				JSONArray trailers_plates= (JSONArray) todo_placrem.get("data");
				//-----------------------placas tracto--------------------------------------------
				JSONArray vtracts_plates = (JSONArray) uspassurl.get("tracts_plates");
				JSONObject todo_platrac= (JSONObject) vtracts_plates.get(0);
				JSONArray tracts_plates= (JSONArray) todo_platrac.get("data");
				// -----------------------------------------------------------------------RECORRE
				// EVENTOS--------------------------------------------------------------
				for (int i = 0; i < veventos.size(); i++) {
//					System.out.println("**************************LEYENDO EVENTOS*****************************");
					JSONObject vevento = (JSONObject) veventos.get(i);// -------------------leer cada evento
					// ---------------------------------------------------------------------viaje-placas-------------------------------------------------------------------
					// se compara si hay la misma cantidad de viajes que de placas
					if (shipments.size() == trailers_plates.size() && shipments.size() == tracts_plates.size()) {
						// si si se recorre viaje
						for (int k = 0; k < shipments.size(); k++) {
							evento.add(addobject1(company, vevento,shipments, trailers_plates, tracts_plates, k, k,
									k, vuser, vpass, vurl));
						}
					} else if (shipments.size() >= trailers_plates.size()) {// si hay mas placas que viajes

						for (int k = 0; k < shipments.size(); k++) {// cuantos viajes

							if (shipments.size() == trailers_plates.size()
									&& trailers_plates.size() > tracts_plates.size()) {
								for (int m = 0; m < tracts_plates.size(); m++) {
									evento.add(addobject1(company, vevento, shipments, trailers_plates,
											tracts_plates, k, k, m, vuser, vpass, vurl));
								}
							} else
								for (int l = 0; l < trailers_plates.size(); l++) {// cuantas placas remolque
									// comienzan escenarios
									if (shipments.size() > trailers_plates.size()
											&& trailers_plates.size() == tracts_plates.size()) {
										evento.add(addobject1(company, vevento, shipments, trailers_plates,
												tracts_plates, k, l, l, vuser, vpass, vurl));
									} else if (shipments.size() > trailers_plates.size()
											&& shipments.size() == tracts_plates.size()
											&& trailers_plates.size() < tracts_plates.size()) {
										evento.add(addobject1(company, vevento,shipments, trailers_plates,
												tracts_plates, k, l, k, vuser, vpass, vurl));
									} else {
										System.out.println("Operación no soportada, revisa los datos dinamicos1");
									}
								}
						}
					} else {
						System.out.println("Operación no soportada, revisa los datos dinamicos2");
					}
					// -----------------------------------------------------------------------FIN
					// RECORRIDO
					// DINAMICOS--------------------------------------------------------------

					eventos.add(evento);
					// -----------------------------------------------------------------------FIN
					// RECORRIDO
					// EVENTOS--------------------------------------------------------------
				}
			}
		} catch (Exception e) {
			System.out
					.println("=============================ERROR EN METODO EVENTOS POR COMPANY: " + e);
		}
		return eventos.iterator();
	}


	public Object[] addobject1(Object company, JSONObject vevento, JSONArray shipments, JSONArray trailers_plates,
			JSONArray tracts_plates, int a, int b, int c, Object vuser, Object vpass, Object vurl){	
		Object addeventt1[]=null;
		try {
		// ---------------------------------------------trailer_status--------------------------
		JSONArray atrailer = (JSONArray) vevento.get("trailer_status");
		JSONObject btrailer = (JSONObject) atrailer.get(0);
		Object trailer_status = btrailer.get("data");
		Object vtrailer = btrailer.get("validate");
		// --------------------------------------------telefono---------------------------
		JSONArray aphone = (JSONArray) vevento.get("phone");
		JSONObject bphone = (JSONObject) aphone.get(0);
		Object phone = bphone.get("data");
		Object vphone = bphone.get("validate");
		// --------------------------------------------cajas---------------------------
		JSONArray aboxes = (JSONArray) vevento.get("boxes");
		JSONObject bboxes = (JSONObject) aboxes.get(0);
		Object boxes = bboxes.get("data");
		Object vboxes = bboxes.get("validate");
		// --------------------------------------------notas---------------------------
		JSONArray anotes = (JSONArray) vevento.get("notes");
		JSONObject bnotes = (JSONObject) anotes.get(0);
		Object notes = bnotes.get("data");
		Object vnotes = bnotes.get("validate");
		// --------------------------------------------confirmación---------------------------
		JSONArray aconfirmation = (JSONArray) vevento.get("confirmation");
		JSONObject bconfirmation = (JSONObject) aconfirmation.get(0);
		Object confirmation = bconfirmation.get("data");
		Object vconfirmation = bconfirmation.get("validate");
		// --------------------------------------------custodio---------------------------
		JSONArray acustodian = (JSONArray) vevento.get("custodian");
		JSONObject bcustodian = (JSONObject) acustodian.get(0);
		Object custodian = bcustodian.get("data");
		Object vcustodian = bcustodian.get("validate");
		// --------------------------------------------eco_remolque---------------------------
		JSONArray aecho_trailer = (JSONArray) vevento.get("echo_trailer");
		JSONObject becho_trailer = (JSONObject) aecho_trailer.get(0);
		Object echo_trailer = becho_trailer.get("data");
		Object vecho_trailer = becho_trailer.get("validate");
		// --------------------------------------------eco_trailer---------------------------
		JSONArray aecho_tract = (JSONArray) vevento.get("echo_tract");
		JSONObject becho_tract = (JSONObject) aecho_tract.get(0);
		Object echo_tract = becho_tract.get("data");
		Object vecho_tract= becho_tract.get("validate");
		// --------------------------------------------peso---------------------------
		JSONArray aweight = (JSONArray) vevento.get("weight");
		JSONObject bweight = (JSONObject) aweight.get(0);
		Object weight = bweight.get("data");
		Object vweight = bweight.get("validate");
		// --------------------------------------------tarimas---------------------------
		JSONArray apallets = (JSONArray) vevento.get("pallets");
		JSONObject bpallets = (JSONObject) apallets.get(0);
		Object pallets = bpallets.get("data");
		Object vpallets = bpallets.get("validate");
		// --------------------------------------------sello1---------------------------
		JSONArray aseal_1 = (JSONArray) vevento.get("seal_1");
		JSONObject bseal_1 = (JSONObject) aseal_1.get(0);
		Object seal_1 = bseal_1.get("data");
		Object vseal_1 = bseal_1.get("validate");
		// --------------------------------------------sello2---------------------------
		JSONArray aseal_2 = (JSONArray) vevento.get("seal_2");
		JSONObject bseal_2 = (JSONObject) aseal_2.get(0);
		Object seal_2 = bseal_2.get("data");
		Object vseal_2 = bseal_2.get("validate");
		// --------------------------------------------sello3---------------------------
		JSONArray aseal_3 = (JSONArray) vevento.get("seal_3");
		JSONObject bseal_3 = (JSONObject) aseal_3.get(0);
		Object seal_3 = bseal_3.get("data");
		Object vseal_3 = bseal_3.get("validate");
		//------------------------------------------validar viaje----------------------
		JSONArray ship = (JSONArray) vevento.get("shipment");
		JSONObject ship2 = (JSONObject) ship.get(0);
		Object validate_shipments= ship2.get("validate");
		//------------------------------------------validar placa remo----------------------
		JSONArray placar1 = (JSONArray) vevento.get("trailer_plate");
		JSONObject placar2 = (JSONObject) placar1.get(0);
		Object validate_trailers_plates= placar2.get("validate");
		//------------------------------------------validar viaje----------------------
	    JSONArray placat1 = (JSONArray) vevento.get("tract_plate");
		JSONObject placat2 = (JSONObject) placat1.get(0);
		Object validate_tracts_plates= placat2.get("validate");
		Object addeventt[]= {company.toString(),vevento.get("name").toString(), vevento.get("type").toString(),
				vevento.get("certificate").toString(), vevento.get("credential").toString(),
				vevento.get("transline").toString(), vevento.get("driver_name").toString(), trailer_status.toString(),
				vtrailer.toString(), phone.toString(), vphone.toString(),boxes.toString(),vboxes.toString(),
				notes.toString(),vnotes.toString(), confirmation.toString(),vconfirmation.toString(),
				custodian.toString(),vcustodian.toString(), echo_trailer.toString(),vecho_trailer.toString(),
				echo_tract.toString(),vecho_tract.toString(), weight.toString(),vweight.toString(),
				pallets.toString(),vpallets.toString(),seal_1.toString(),vseal_1.toString(),seal_2.toString(),vseal_2.toString(),
				seal_3.toString(),vseal_3.toString(), shipments.get(a).toString(),validate_shipments.toString(), trailers_plates.get(b).toString(),validate_trailers_plates.toString(),
				tracts_plates.get(c).toString(),validate_tracts_plates.toString(), vuser, vpass, vurl};
		addeventt1=addeventt;
		}catch (Exception err) {System.out.println("error en metodo addobject1"+err);};
     return addeventt1;
	}
 
	private static LeerJson json;

	private LeerJson() {
	}

	public static LeerJson getInstance()// si no existe una instancia creala
	{
		if (json == null) {
			json = new LeerJson();
		}
		return json;
	}

}
