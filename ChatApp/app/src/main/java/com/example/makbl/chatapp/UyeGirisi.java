package com.example.makbl.chatapp;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class UyeGirisi extends AppCompatActivity {

    EditText kulladi,sifre;
    PrintWriter out=null;
    BufferedReader in=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uyegirisi);
        kulladi=(EditText) findViewById(R.id.kulladi);
        sifre=(EditText)findViewById(R.id.sifre);

        //Server ile soket bağlantısı kuruluyor..
        new Veritabani(this);
        out=Veritabani.getOut();
        in=Veritabani.getIn();
    }

    protected void onStop(){
        super.onStop();
    }

    protected void onPause(){
        super.onPause();
    }

    protected void yenikayit(View view){
        Intent intent=new Intent(this,YeniUye.class);
        startActivity(intent);
    }
    protected void giris(View view){
        try{
            out.println("giris");
            out.println(kulladi.getText().toString());
            out.println(sifre.getText().toString());

            if(in.readLine().equals("true")){
                Toast.makeText(this,"Giriş Başarılı",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,Mesajlasma.class);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Geçersiz ID, Şifre",Toast.LENGTH_SHORT).show();
            }
        }catch (IOException e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }

}
