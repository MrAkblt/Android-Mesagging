package com.example.makbl.chatapp;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Created by MAkbl on 21.01.2017.
 */

public class Veritabani {
    private static String ServerIP="192.168.2.9";
    private static int ServerPort=7755;
    private Socket socket = null;
    private Context context;
    private static PrintWriter out;
    private static BufferedReader in;
    public Veritabani(Context c){
        context=c;
        try {
            socket = new Socket(ServerIP, ServerPort);
            Toast.makeText(context,"Baglanti Kuruldu",Toast.LENGTH_LONG).show();
        }catch (Exception e) {
            Toast.makeText(context,"Port Hatasi:"+e.toString(),Toast.LENGTH_LONG).show();
        }

        try{
            out=new PrintWriter(socket.getOutputStream(),true);
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch (IOException e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    public static PrintWriter getOut(){
        return out;
    }
    public static BufferedReader getIn(){
        return in;
    }
}
