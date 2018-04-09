package com.jony.intlcc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jony on 3/12/18.
 */
public class TcpSocketClient {


    private String host;
    private int port;

    private Socket socket = null; //socket

    private BufferedReader in = null;
    private OutputStream out = null;

    private boolean isReceive = true; //是否循环接收消息

    private Timer timer = null; //心跳值监测定时器

    public String hbValue = null; //心跳值


    public TcpSocketClient(String host, int port) {
        this.host = host;
        this.port = port;

        createdConnect();
    }


    private void createdConnect() {

        try {

            this.socket = new Socket(this.host, this.port);

            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = this.socket.getOutputStream();

            this.isReceive = true;
            startedReceive();

            monitor(); //监测心跳值

        } catch (IOException e) {
            e.printStackTrace();

            System.out.println("Created TCP Client Socket failed");

            reConnect();
        }

    }

    private void startedReceive(){

        new Thread(new Runnable() {
            public void run() {

                try {

                    String message = null;
//            只有遇到"/r"、"/n"、"/r/n"才会返回
                    System.out.println("TCP Client run。。。。");
                    while (isReceive && (message = in.readLine()) != null){

                        System.out.println("TCP Server Message:" + message);
                        new TCPMessageProcessThread(message).start();

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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

    private void destory(){

        try {

            //初始化参数
            //一旦网络中断，首先关闭心跳监听，否则会重复重连
            if(this.timer != null){
                this.timer.cancel();
                this.timer = null;
            }

            this.isReceive = false;

            if (this.socket != null && this.socket.isConnected()) //如果Socket在输入输出流后关闭，则会卡在输入输出关闭处
                this.socket.close();

            if (this.in != null)
                this.in.close();

            if (this.out != null)
                this.out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void monitor(){

        this.timer = new Timer(); //初始化

        long initInterval = 30 * 1000;
        long checkInterval = 30 * 1000;

        final long dropValue = 30*1000;


        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (hbValue != null){
                    long hbValueLong = Long.parseLong(hbValue);
                    long currentTime = new Date().getTime();

                    long diffValue = currentTime - hbValueLong;

//                    System.out.println("diffValue :"+diffValue);

                    if (diffValue > dropValue){

                        System.out.println("TCP Client Drop.");

                        destory(); //销毁相关

                        reConnect(); //重连

                    }
                }
            }
        }, initInterval, checkInterval);
    }


    /**
     * reConnect
     */
    private void reConnect(){

        System.out.println("Reconnect after 30 second.");

        long delayTime = 30 * 1000;


        //延迟时间重连
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                System.out.println("Reconnect.....");
                createdConnect();
            }
        }, delayTime);

    }
}
