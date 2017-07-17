import java.io.*;
import java.net.*;
 
public class ClientListener extends Thread {
    Socket clientSocket=null;
    String kulladi="",sifre="";
    BufferedReader in;
    PrintWriter out;
    String clientGelen;
    public ClientListener(Socket clientSocket){
        this.clientSocket=clientSocket;
    }
    public void run(){
        
        int sayi;
         
        try{
                //* Client'a veri gönderimi için kullandığımız PrintWriter nesnesi oluşturulur *//
                out = new PrintWriter(clientSocket.getOutputStream(), true);
            
                //* Client'dan gelen verileri tutan BufferedReader nesnesi oluşturulur *//
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                
                while((clientGelen =  in.readLine()) != null) {
                    if(clientGelen.equals("yenikayit")){
                        yenikayit(); 
                    }
                    else if(clientGelen.equals("giris")){ 
                        giris();
                    }
                    else if(clientGelen.equals("mesaj")){
                        mesajAt();
                    }else if(clientGelen.equals("mesajgetir")){
                        mesajGetir();
                    }else
                        System.out.println("Geçersiz istek");
                    
                }
                System.out.println(kulladi+" odadan ayrildi");
                out.close();
                in.close();
                clientSocket.close();
            
        }catch(IOException e){System.out.println(e.toString());}
            
     }
     public void yenikayit(){
         String ykulladi="",ysifre="";
         try{
            ykulladi=in.readLine();
            ysifre=in.readLine();
            String sonuc=Veritabani.uyeEkle(ykulladi,ysifre);
            out.println(sonuc);
         }catch(IOException e){
             System.out.println(e.toString());
         }
         
     }
     public void giris(){
         if(!kulladi.equals("")){
             System.out.println(kulladi+" odadan ayrildi");
         }
         try{
            kulladi=in.readLine();
            sifre=in.readLine();
            Boolean bool=Veritabani.uyeKontrol(kulladi,sifre);
            if(bool){
                System.out.println(kulladi+" odaya baglandi");
                out.println("true");
            }
            else{
                out.println("false");
            }     
         }catch(IOException e){
             System.out.println(e.toString());
         }
     }
     public void mesajAt(){
         try{
            String mesaj=in.readLine();
            String str=Veritabani.mesajAt(kulladi,mesaj);
            out.println(str);
         }catch(IOException e){
             System.out.println(e.toString());
         }
     }
     public void mesajGetir(){
         Veritabani.mesajGetir(out);
     }
}