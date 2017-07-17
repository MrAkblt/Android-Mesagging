import java.io.*;
import java.sql.*;
public class Veritabani{ 
    private static Connection conn=null;
    private static Statement st=null;
    public Veritabani(){
        String surucu = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/";
        String veritabani="dbUyeler";
        try{
            Class.forName(surucu);
        }catch(ClassNotFoundException e){
            System.out.println("Mysql Driverda sorun var");
        }
        
        try{
            conn=DriverManager.getConnection(url+veritabani,"root","");
        }catch(SQLException e){
            System.out.println("Baglanti saglanamadi");
            e.printStackTrace();
            return;
        }
    }
    
    public static String uyeEkle(String kulladi,String sifre){
        String str="Kayıt Başarılı";
        String sorgu="INSERT INTO Uyeler(kulladi,sifre) VALUES('"+kulladi+"','"+sifre+"');";
        try{
            st=conn.createStatement();
            st.executeUpdate(sorgu);
            st.close();
            
        }catch(SQLException e){
            System.out.println(e.toString());
            str="Geçersiz Kullanıcı Adı";
        }
        return str;
    }
    public static boolean uyeKontrol(String kulladi,String sifre){
        String sorgu="SELECT * FROM Uyeler WHERE kulladi='"+kulladi+"';";
        try{
            st=conn.createStatement();
            ResultSet rs=st.executeQuery(sorgu);
            rs.next();
            if(sifre.equals(rs.getString("sifre"))){return true;}
            st.close();
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return false;
    }

    public static String mesajAt(String kulladi,String mesaj){
        String str="Mesaj Gönderildi";
        String sorgu="INSERT INTO Mesajlar(kulladi,mesaj) VALUES('"+kulladi+"','"+mesaj+"');";
        try{
            st=conn.createStatement();
            st.executeUpdate(sorgu);
            st.close();
            
        }catch(SQLException e){
            System.out.println(e.toString());
            str="Mesaj Gönderilemedi";
        }
        return str;
    }

    public static void mesajGetir(PrintWriter out){
        String sorgu="SELECT * FROM Mesajlar;";
        try{
            st=conn.createStatement();
            ResultSet rs=st.executeQuery(sorgu);
            while(rs.next()){
                String str=rs.getString("kulladi")+": "+rs.getString("mesaj");
                out.println(str);
            }
            out.println("bitti");
            st.close();
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        
    }
}