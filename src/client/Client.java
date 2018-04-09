package client;

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author ENG-TORKY
 */
public class Client {

    public static void main(String[] args) {
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket();
            System.out.println("please enter your message:");
            byte[] message = new Scanner(System.in).nextLine().getBytes();
            System.out.println("please enter the internet address:");
            InetAddress aHost = InetAddress.getByName(new Scanner(System.in).nextLine());
            int serverPort = 6789;

            DatagramPacket request = new DatagramPacket(message, message.length, aHost, serverPort);
            aSocket.send(request);

            byte[] buffer = new byte[1000];
            DatagramPacket replay = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(replay);

            System.out.println("replay: " + new String(replay.getData()));
        } catch (SocketException ex) {
            System.out.println(ex.toString());
        } catch (UnknownHostException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            if (aSocket != null) {
                aSocket.close();
            }
        }
    }
}
