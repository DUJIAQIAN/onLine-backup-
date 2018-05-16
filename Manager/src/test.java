

import java.sql.Date;

import DAO.manager.*;
import cfo.*;
import Inscription.Conseiller;

public class test {
	public static void main(String[] args) throws Exception {
		 Conseiller conseiller = new Conseiller();
         conseiller.setSexe("F");
			conseiller.setNom("FOURNET111");
			conseiller.setPrenom("PAUL");
			conseiller.setNationalite("FRANCE");
			conseiller.setDateNaissance(Date.valueOf("1994-04-03"));
			conseiller.setEmail("dddjq@gmail.com");
			conseiller.setTelephone("0987654321");
			conseiller.setAdresse("qqqqq");
			DAO.InsererConseiller(conseiller);
			
			int idCfo = DAO.getIdConseiller(conseiller.getNom(), conseiller.getPrenom()); 
			System.out.print(idCfo);
				DAO.insererMdp(idCfo);
				
			
		
		/*int max=999;
		int min =111;
	int len = max-min;
	String number = InsererMdp.randomMdp(min, max, len);
	System.out.print(number);
	DAO.insererMdp(32);*/
	}  
}