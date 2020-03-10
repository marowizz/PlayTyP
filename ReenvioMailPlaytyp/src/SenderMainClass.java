import java.io.File;
import java.io.IOException;

public class SenderMainClass {
	static String pass = "PasoPaso111";
	static String mailTo = "mcasteletti@azurian.com";
	static String mailFrom = "noticias@playtyp.cl";
	static String host = "mail.playtyp.cl";
	static int port = 587;
	static String asustoMail = null;
	
	static LectorCsv lc = new LectorCsv();
	
	public static void main( String[] args ) throws IOException {
		
    	if (args.length == 3) {
    		File fileCsv = new File(args[0]);
    		File filehtml = new File(args[1]);
    		File asustoMail = new File(args[2]);
    		
    		System.out.println("Datos ingresados corrrectamente");
    		System.out.println("Ejecutanto proceso");
    		System.out.println("*******************");
    		lc.leerCsv(fileCsv,pass,mailFrom,host, port, filehtml, asustoMail);
    		
    	} else {
    		System.out.println("Ingrese argumentos");
    	}	
	}
	
}
