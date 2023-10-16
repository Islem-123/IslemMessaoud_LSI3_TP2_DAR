package Ex1;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
   public class Client {
		public static void main(String[]args) {
			try {
				Socket client = new Socket("localhost",1234);
				System.out.println("je suis un client connecté");
				int op1,op2;
				String operation;
				InputStream is = client.getInputStream();
				OutputStream os = client.getOutputStream();
				PrintWriter pw =new PrintWriter(os,true);
				Scanner scanner = new Scanner(System.in);
				System.out.println("donner le 1er nombre");
			    op1=scanner.nextInt();
				System.out.println("donner le 2eme nombre");
				op2=scanner.nextInt();
			    do {
			    	System.out.println("donner l'operation (+,-,*,/)");
			    	operation=scanner.nextLine();}
			    while(!operation.equals("+") && !operation.equals("*") &&!operation.equals("/") &&!operation.equals("-"));
			    pw.println(op1);
				pw.println(op2);
				pw.println(operation);
				//lire chaque caractere à partir de client 
				InputStreamReader isr = new InputStreamReader(is);
				//collecteur de caractere 
				BufferedReader br = new BufferedReader(isr);
				System.out.println(op1+" "+operation+" "+op2+" = "+br.readLine());
			}
			catch (Exception e)
			{e.printStackTrace();}}}

