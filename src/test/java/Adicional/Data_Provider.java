package Adicional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

public class Data_Provider {

	static LeerJson js = LeerJson.getInstance();

	@DataProvider(name = "Usuarios")
	public static Iterator<Object[]> getUsers() throws FileNotFoundException, IOException, ParseException {
		ArrayList<Object[]> list = js.arregloUsuarios();
//		System.out.println("===============iterador: " + list.iterator());
		return list.iterator();
	}

	@DataProvider(name = "UsersandTxt")
	public static Iterator<Object[]> getUsersandtxtplan() throws FileNotFoundException, IOException, ParseException {
		ArrayList<Object[]> UandT = js.UsersandTxt();
		return UandT.iterator();
	}
	
	@DataProvider(name = "Eventos")
	public static Iterator<Object[]> getEvents() throws FileNotFoundException, IOException, ParseException  {
		List<Object[]> evens =js.validar_eventos().next();
		return evens.iterator();
	}
   
}
