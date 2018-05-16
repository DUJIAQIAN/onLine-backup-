package cacRead;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class cacRead {
	
	public String readCAC() throws IOException {
	URL url = new URL("https://investir.lesechos.fr/cours/indice-cac-40,xpar,px1,fr0003500008,isin.html");
	URLConnection urlConn = url.openConnection();
	InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
	BufferedReader buff = new BufferedReader(inStream);
	String price="not found";
	String line = buff.readLine();
	while(line!=null) {
		if(line.contains("var cours")) {
			int target = line.indexOf("var cours ='");
			int cours = line.indexOf(".", target);
			int start = cours;
			while(line.charAt(start)!='\'') {
				start--;
			}
			price = line.substring(start+1, cours+2);
		}
		line = buff.readLine();
	}
	return price;
	

}
}
