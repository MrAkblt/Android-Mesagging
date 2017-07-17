package com.example.makbl.chatapp;

import android.app.Activity;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Mesajlasma extends AppCompatActivity {

    EditText et1;
    ListView mesajlistesi;
    PrintWriter out;
    BufferedReader in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mesajlasma);
        et1=(EditText) findViewById(R.id.et1);
        mesajlistesi=(ListView)findViewById(R.id.mesajlistesi);
        //* server 'a localhost ve 7755 portu üzerinden başlantı sağlanıyor *//
        in=Veritabani.getIn();
        out=Veritabani.getOut();

    }
    protected void mesajGetir(View view){
        out.println("mesajgetir");
        String deger;
        ArrayAdapter<String> veriAdaptoru;
        List<String> liste=new ArrayList<>();
        try{
            while (!(deger=in.readLine()).equals("bitti")){
                liste.add(deger);
            }
            veriAdaptoru=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,liste);
            mesajlistesi.setAdapter(veriAdaptoru);
        }catch (IOException e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }

    }
    protected void mesajAt(View view){
        try{
            //* Gönderilecek sayının girişi yapılıyor *//

            if(!(et1.getText().toString().equals(""))) {
                String mesaj = et1.getText().toString();
                out.println("mesaj");
                out.println(mesaj);
                Toast.makeText(getApplicationContext(), "Server'dan gelen sonuç = " + in.readLine(), Toast.LENGTH_LONG).show();
                et1.setText("");
            }else{
                Toast.makeText(getApplicationContext(), "Boş Mesaj Gönderilemez ", Toast.LENGTH_LONG).show();
            }

        }
        catch (IOException ex){
            Toast.makeText(getApplicationContext(),""+ex.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    protected void onStop(){
        super.onStop();
    }

    protected void onPause(){
        super.onPause();
    }

}
