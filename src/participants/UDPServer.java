package participants;

import java.net.*;
import java.io.*;

public class UDPServer {

    public static void main(String args[]) {
        DatagramSocket socket = null;
        try {
            int serverPort = 49152;
            socket = new DatagramSocket(serverPort);
            byte[] buffer = new byte[1000]; // buffer encapsulará mensajes
            while (true) {
                System.out.println("Waiting for messages...");
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);  // bloqueante

                DatagramPacket reply = new
                        DatagramPacket(request.getData(),
                        request.getLength(),
                        request.getAddress(),
                        request.getPort());

                System.out.println("Server received a request from " + request.getAddress());
                socket.send(reply);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (socket != null)
                socket.close();
        }
    }
}
