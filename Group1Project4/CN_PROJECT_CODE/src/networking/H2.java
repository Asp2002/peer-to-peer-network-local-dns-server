package networking;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class H2 {

	public static void main(String[] args) {
		try {
			
			
			System.out.println("Hello from H2 Machine...");
            DatagramSocket clientSocket = new DatagramSocket(2447);
            System.out.println("Enter request data send to H1: ");
            Scanner scn=new Scanner(System.in);
            String sctr=scn.nextLine();
            InetAddress h1Address = InetAddress.getLocalHost();

            // Request data and time from H1
            byte[] sendData = sctr.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, h1Address, 2446);

            clientSocket.send(sendPacket);

            // Receive response from H1
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            clientSocket.receive(receivePacket);

            String dateTime = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("H2 received DateTime from H1: " + dateTime);

            clientSocket.close();
            scn.close();
        } catch (Exception e) {
           System.out.println(e.getLocalizedMessage());
        }
	}

}
