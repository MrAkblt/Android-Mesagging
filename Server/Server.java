import java.io.*;
import java.net.*;
public class Server extends Thread{
    public Server(){
    }
    public static void main(String[] args){
        ServerSocket serverSocket = null;
        int serverPort=7755;
 
        try {
            //*Server 7755 portundan Client'ı dinliyor *//
            serverSocket = new ServerSocket(serverPort);
        } catch (Exception e) {
             System.out.println("Port Hatasi!");
        }
        System.out.println("SERVER BAGLANTi iCiN HAZiR...");
        //Veritabanı bağlantısı kuruluyor..
        new Veritabani();
        
        //Yeni gelen istemciler için soket kabulü ve yeni thread..
        Socket clientSocket = null;
        try{
            while(true){
                clientSocket = serverSocket.accept();
                (new ClientListener(clientSocket)).start();
            }
        }catch(IOException e){}

    }
}