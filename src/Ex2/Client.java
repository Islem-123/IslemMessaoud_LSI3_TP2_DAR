package Ex2;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		try {
			// Connexion au serveur
			Socket socket = new Socket("localhost", 1234);
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.print("Donner le premier operateur : ");
			int op1 = scanner.nextInt();
			
			System.out.print("\nDonner l'operation : ");
			String operation;
			
			do {
				operation = scanner.nextLine();
			}
			while(!(operation.equals("*")) && !(operation.equals("+")) && !(operation.equals("-")) && !(operation.equals("/")));
	
			System.out.print("\nDonner le deuxieme operateur : ");
			int op2 = scanner.nextInt();
			
			scanner.close();
			
			
			// Création d'une instance de Operation à partir de l'utilisateur
			Operation op = new Operation(op1, op2, operation.charAt(0));

			
			// Utilisation de ObjectOutputStream pour pouvoir convertir un objet (Serializable) à un fichier binaire
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(op);
			
			// Utlisation du ObjectInputStream pour pourvoir lire l'objet 
			InputStream is = socket.getInputStream();
			ObjectInputStream oiss = new ObjectInputStream(is);
			
			op = (Operation) oiss.readObject();
			
			System.out.println("\n" + op.getOp1() + " " + op.getOperation() + " " + op.getOp2() + " = " + op.getResult1());
			
			// Deconnexion et libération des ressources
			socket.close();
	
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
