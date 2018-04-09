package com.jony.intlcc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by jony on 3/12/18.
 */
public class TcpSocketThread extends Thread {

    private Socket socket = null;

    private BufferedReader in = null;
    private OutputStream out = null;



    public TcpSocketThread(Socket socket) {
        this.socket = socket;

        try {

            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = this.socket.getOutputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        try {

            String message = null;
//            只有遇到"/r"、"/n"、"/r/n"才会返回
            System.out.println("TCP Client received message。。。。");
            while ((message = in.readLine()) != null){

                System.out.println("TCP Server Message:" + message);
                new TCPMessageProcessThread(message).start();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean sendMessage(String str) {

        try {
            str = str+"\n";
            out.write(str.getBytes());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return false;
    }
}
