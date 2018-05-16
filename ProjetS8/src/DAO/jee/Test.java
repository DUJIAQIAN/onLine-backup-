package DAO.jee;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import Cpt.InsererCpt;
import inscription.Client;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int cli =  InsererCpt.randomNumeroCompte(1001, 5555, 6557);
		System.out.println(cli);
	}
}
