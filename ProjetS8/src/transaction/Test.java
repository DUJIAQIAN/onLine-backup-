package transaction;

import java.util.ArrayList;
import java.util.List;

import DAO.jee.DAO;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<historiqueTransaction> histList = DAO.getTransaction("subrebost","kevin");
		for (int i = 0; i < histList.size(); i++) {
			System.out.println(histList.get(i).getNum() + " " + histList.get(i).getOperation() +
					" " + histList.get(i).getMotif() + " " + histList.get(i).getMontant() + " " + histList.get(i).getDate());
		}
	}

}
