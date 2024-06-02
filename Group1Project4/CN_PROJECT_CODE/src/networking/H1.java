package networking;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
public class H1 {	
	public static void main(String[] args) {
		try {
			System.out.println("hello from H1 Machine....");
			
			
            DatagramSocket clientSocket = new DatagramSocket(2446);
            
            
            InetAddress dsAddress = InetAddress.getLocalHost();  //getByName("234.678") // ds ip address
            // Request data and time from DS
            System.out.println("Enter data to sent to DS: ");
            Scanner scn=new Scanner(System.in);
            String sctr=scn.nextLine();
            
            
            byte[] sendData = sctr.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, dsAddress, 2445);
            clientSocket.send(sendPacket);
            
            
            // Receive response from DS  
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            
            
            String dateTime = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("H1 received DateTime from DS: " + dateTime);
            
            
            //receive this data to H2
           // InetAddress h2Address = InetAddress.getByName("192.168.160.131");
            // Request data and time from H1
            	
            	
            byte[] receiveDataH2 = new byte[1024];
            DatagramPacket receivePacketH2 = new DatagramPacket(receiveDataH2, receiveDataH2.length);
            clientSocket.receive(receivePacketH2);
            
            InetAddress h2Address=receivePacketH2.getAddress();
        	int H2port=receivePacketH2.getPort(); // 0-1023 reserved .... 
        	
        	
            String strH2 = new String(receivePacketH2.getData(), 0, receivePacketH2.getLength());
            if(strH2.equals("Request DateTime From H1")) {
                 byte[] sendDataH2 = dateTime.getBytes();
            DatagramPacket sendPacketH2 = new DatagramPacket(sendDataH2, sendDataH2.length, h2Address, H2port);
            clientSocket.send(sendPacketH2);
            scn.close();
            clientSocket.close();
            }
            else {
            	System.out.println("Please give us corecct input: to access time:");
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
	}
}