package com.example.makbl.chatapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by MAkbl on 22.01.2017.
 */

public class YeniUye extends Activity {

    private EditText kulladi,sifre;
    BufferedReader in=null;
    PrintWriter out=null;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yeniuye);
        kulladi=(EditText)findViewById(R.id.kulladi);
        sifre=(EditText)findViewById(R.id.sifre);

        in=Veritabani.getIn();
        out=Veritabani.getOut();

    }
    protected void kaydol(View view){
        String user=kulladi.getText().toString();
        String pass=sifre.getText().toString();
        if(user.equals("")||pass.equals("")){
            Toast.makeText(getApplicationContext(),"Boş parametre bırakılamaz!",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            out.println("yenikayit");
            out.println(user);
            out.println(pass);
            String gelen=in.readLine();
            Toast.makeText(getApplicationContext(),gelen,Toast.LENGTH_SHORT).show();


        }catch (IOException e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
