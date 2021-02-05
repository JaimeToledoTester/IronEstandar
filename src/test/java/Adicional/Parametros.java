package Adicional;

import java.util.ArrayList;

public class Parametros {

	private static Parametros param;// para la instancia

	// info de navegación
	public String Chrome = "./src/test/resources/ChromeDriver/chromedriver.exe";
//-----------------------------------------módulos a validar
	public String mloggin,mplanning,mevents;	
//---------------------------------------para mail
	public String msend="",muser,mpass,mfrom,maliasfrom,maliasto,msubject,mmessage,mfilename,mfiledescription;
	public ArrayList<String>mto = new ArrayList<String>();
//------------------------------------------------Viajes
//	public ArrayList<String>mshipment= new ArrayList<String>();
//------------------------------------------------Eventos
//	public ArrayList<String[]> meventos = new ArrayList<String[]>();
	
	
	
	
	
	
	
	private Parametros() {

	}

	public static Parametros getInstance()// si no existe una instancia creala
	{
		if (param == null) {
			param = new Parametros();
		}
		return param;
	}
}
