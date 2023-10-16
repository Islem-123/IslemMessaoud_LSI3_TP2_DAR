package Ex2;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[]args) {
		try {
			// Réservation du port 
			ServerSocket serverSocket = new ServerSocket(1234);
			Socket socket = serverSocket.accept();
			
			System.out.println("Client connecté");
			
			// Utlisation du ObjectInputStream pour pouvoir lire l'objet reçus du client
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			
			// Conversion du type Object vers Operation
			Operation op = (Operation) ois.readObject();
			
			
			int result1 = op.getOp1();
			//etudier les cas des opérations
			switch (op.getOperation()) {
            case '+': 
            	op.setResult1(result1 + op.getOp2());
                break;
            case '-':
            	op.setResult1(result1 - op.getOp2());
                break;
            case '*':
            	op.setResult1(result1 * op.getOp2());
                break;
            case '/':
                if (op.getOp2() == 0) {
                    throw new ArithmeticException("Division par zéro");
                }
                op.setResult1(result1 / op.getOp2());
                break;
            default:
                throw new IllegalArgumentException("Opérateur non valide");
        }
			
		
		
		// envoyer l'objet vers le client après modification de la propriétés 'Result1'
		OutputStream os = socket.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(op);
		
		// Libération des ressources
		serverSocket.close();
		socket.close();
		
	} catch (IOException e) {
		e.printStackTrace();
	
} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}}






