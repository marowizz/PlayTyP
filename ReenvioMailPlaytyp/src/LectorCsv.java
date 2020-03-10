import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LectorCsv {

	public static final String SEPARATOR=";";
	public static final String QUOTE="\"";
	
	
	
	 
	public void leerCsv (File csv, String pass,String mailFrom,String host, int port, File html, File asustoMail) throws IOException {
		BufferedReader br = null;
		SenderMail em = new SenderMail();
		try {
			//Se lee csv por cada linea
	         br =new BufferedReader(new FileReader(csv));
	         String line = br.readLine();
	         
	         while (line!=null) {
	        	//Se agrega en arreglo campos con el separador
	            String [] campos = line.split(SEPARATOR);
	            //System.out.println(Arrays.toString(campos));
	            String correoReceptor = campos[0];
	            String nombreReceptor = campos[1];
	            //System.out.println(correoReceptor);
	            //System.out.println(nombreReceptor);
	            em.sendEmail(pass, mailFrom, host, port,correoReceptor,nombreReceptor, html, asustoMail);
	            line = br.readLine();
	         }
	         
	      } catch (Exception e) {
	    	  System.out.println(e.getMessage());
	      } finally {
	         if (null!=br) {
	            br.close();
	         }
	}
}
}
