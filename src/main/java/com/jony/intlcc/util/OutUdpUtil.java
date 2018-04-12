package com.jony.intlcc.util;

import java.io.IOException;
import java.net.*;

/**
 * Created by jony on 2018/4/11.
 */
public class OutUdpUtil {

    private  int localPort = 0; //本地端口
    private DatagramSocket localSocket; //本地socket
    private boolean isReceive = true; //udp 接收标志

    private InetSocketAddress desAddress = null;

    private OutUdpUtil() {


    }

    //单类
    private static OutUdpUtil sharedOutUdpUtil = new OutUdpUtil();

    public static OutUdpUtil getInstance() {
        return sharedOutUdpUtil;
    }


    public void setDesAddress(InetSocketAddress desAddress) {
        this.desAddress = desAddress;
    }

    public void setLocalPort(int localPort) {
        this.localPort = localPort;
    }

    /**
     * udp receive
     */
    public void startReceive() {


        //socket 创建
        if (this.localPort != 0){

            try {

                localSocket = new DatagramSocket(this.localPort);
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Out Udp server port is 0.");
        }

        //接收准备
        byte[] recvBuf = new byte[1024];
        final DatagramPacket recePacket = new DatagramPacket(recvBuf, recvBuf.length);

        if (localSocket == null) {
            System.out.println("Out Udp localSocket is null");
            return;
        }

        new Thread(new Runnable() {
            public void run() {

                System.out.println("Out Udp receive started !");

                //发送测试
//                send("hello");

                try {

                    System.out.println("Out UDP ip:" + InetAddress.getLocalHost().getHostAddress() + "\tport:" + localSocket.getLocalPort());

                    while (isReceive){
                        localSocket.receive(recePacket);
                        String recvString = new String(recePacket.getData(), 0, recePacket.getLength());
                        System.out.println("Out Udp receive:" + recvString);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    localSocket.close();
                }

            }
        }).start();

    }


    private void stopReceive() {

        this.isReceive = false;
    }

    /**
     * @param sendStr       send string
     * @return Whether to transmit successfully
     */
    public boolean send(String sendStr) {

        boolean isSuccess = false;
        //检查地址合法性
       if ((this.desAddress != null) && (this.localSocket != null)){

           byte[] sendBuf = sendStr.getBytes();
           DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, this.desAddress);

           try {

               this.localSocket.send(packet);
               isSuccess = true;
           } catch (IOException e) {

               e.printStackTrace();
           }
       }


       System.out.println("Out UDP send "+isSuccess);
        return isSuccess;

    }

}
