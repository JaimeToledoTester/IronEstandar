package Adicional;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {

	JFileChooser jf = new JFileChooser();

	private FileChooser() {
		
	}

	public String traerarchivo(String proceso) {
		try {
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.json & .zip", "JSON","zip");
		jf.setFileFilter(filtro);
		jf.setDialogTitle(proceso);
		jf.showOpenDialog(jf);	
		}catch(Exception e) {System.out.println("Error en file chooser: "+e);}
		File archivoSeleccionado;
		archivoSeleccionado = jf.getSelectedFile();
		return archivoSeleccionado.toString();
		
		
	}

	
	
	private static FileChooser fc;// para la instancia

	public static FileChooser getInstance()// si no existe una instancia creala
	{
		if (fc == null) {
			fc = new FileChooser();
		}
		return fc;
	}

}
