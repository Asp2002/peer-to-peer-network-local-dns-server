package networking;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class DS {
	public static void main(String[] args) {
		try {
			System.out.println("hello From DS1");
            DatagramSocket serverSocket = new DatagramSocket(2445);
            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                serverSocket.receive(receivePacket);
                
                
                String str=new String(receivePacket.getData(),0,receivePacket.getLength());
                //sending to H1
                
                
                InetAddress clientAddress = receivePacket.getAddress();  
                int clientPort = receivePacket.getPort();
                
                
                // Get current date and time
              //  System.out.println(str);
                if(str.equals("Request DateTime")) {
                String dateTime = java.util.Calendar.getInstance().getTime().toString();
                byte[] sendData = dateTime.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
                System.out.println("Packet Sent to H1");
                }
                else {
                	System.out.println("error:");
                }
            }
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
}
}
