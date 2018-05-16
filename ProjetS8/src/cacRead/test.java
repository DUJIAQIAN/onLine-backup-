package cacRead;
import java.util.*;
import java.io.*;
import java.security.*;
import DAO.jee.*;
import inscription.Client;
public class test {
	
	
	    public static void main(String args[]) throws Exception {
	        // 读入账号口令
	        /*String name = "lbwleon.info";
	        String passwd = "lbwleon.info";
	        // 生成盐
	        Random rand = new Random();
	        byte[] salt = new byte[12];
	        rand.nextBytes(salt);
	        // 计算消息摘要
	        MessageDigest m = MessageDigest.getInstance("MD5");
	        m.update(salt);
	        m.update(passwd.getBytes("UTF8"));
	        byte s[] = m.digest();
	        String result = "";
	        for (int i = 0; i < s.length; i++) {
	            result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00)
	                    .substring(6);
	        }
	        // 保存账号、盐和消息摘要
	        System.out.println(result);*/
	   
	    	   Client client = new Client();
	    	   String pwd = "0987";
	    	   java.sql.Date date = java.sql.Date.valueOf("1994-04-03");
	    	   client.setSexe("F");
				client.setNom("luo");
				client.setPrenom("wandong");
				client.setNationalite("chinese");
				client.setDateNaissance(date);
				client.setEmail("ddjq524@gmail.com");
				client.setTelephone("0782141780");
				client.setAdresse("rouen");
				client.setPwd(pwd);
				DAO.InsererClient(client);
				
				int id = DAO.getIdClient(client.getNom(), client.getPrenom());
				DAO.insererCptCourant(id); 
				
				System.out.println(client.getDateNaissance());
				System.out.println(client.getPwd());
	    }
	}

