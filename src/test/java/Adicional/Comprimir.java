package Adicional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JFileChooser;

public class Comprimir {
	public void agregarCarpeta(String ruta, String carpeta, ZipOutputStream zip) throws Exception {
		File directorio = new File(carpeta);
		for (String nombreArchivo : directorio.list()) {
			if (ruta.equals("")) {
				agregarArchivo(directorio.getName(), carpeta + "/" + nombreArchivo, zip);
			} else {
				agregarArchivo(ruta + "/" + directorio.getName(), carpeta + "/" + nombreArchivo, zip);
			}
		}
	}

	public void agregarArchivo(String ruta, String directorio, ZipOutputStream zip) throws Exception {
		File archivo = new File(directorio);
		if (archivo.isDirectory()) {
			agregarCarpeta(ruta, directorio, zip);
		} else {
			byte[] buffer = new byte[4096];
			int leido;
			FileInputStream entrada = new FileInputStream(archivo);
			zip.putNextEntry(new ZipEntry(ruta + "/" + archivo.getName()));
			while ((leido = entrada.read(buffer)) > 0) {
				zip.write(buffer, 0, leido);
			}
			entrada.close();
		}
	}

	public void comprimir(String archivo, String archivoZIP) throws Exception {
		ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(archivoZIP));
		agregarCarpeta("", archivo, zip);
		zip.flush();
		zip.close();
	}

	public void comprimira() throws Exception {
		Comprimir comp = new Comprimir();
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Seleccione la Carpeta a Comprimir:"); 
		jfc.showOpenDialog(jfc);
		File archivoSeleccionado = jfc.getSelectedFile();
		String rutacarpeta = archivoSeleccionado.getParent();
		String nuevaruta = rutacarpeta.replaceAll("\\\\", "\\\\\\\\");
		System.out.println("CARPETA SELECCIONADA -> " + nuevaruta);
		String destino = nuevaruta + ".zip";
		System.out.println("DESTINO -> " + destino);
		System.out.println("Comprimiendo...");
		comp.comprimir(nuevaruta, destino);
		System.out.println("Archivo ZIP creado correctamente!");
	}

	private static Comprimir comp;

	private Comprimir() {

	}

	public static Comprimir getInstance()// si no existe una instancia creala
	{
		if (comp == null) {
			comp = new Comprimir();
		}
		return comp;
	}
}
