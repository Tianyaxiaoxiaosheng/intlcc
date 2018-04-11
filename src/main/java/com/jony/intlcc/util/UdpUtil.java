package com.jony.intlcc.util;


import java.io.IOException;
import java.net.*;

/**
 * Created by jony on 1/16/18.
 */
public class UdpUtil {

    private TcpClientUtil sharedClientTcpUtil = TcpClientUtil.getInstance();

    private static final int INPORT = 5000; //本地端口
    private DatagramSocket localSocket; //本地socket
    private boolean isReceive = true; //udp 接收标志


    private UdpUtil() {

        //localSocket init
        try {

            localSocket = new DatagramSocket(INPORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }

//        startReceive();
    }

    //单类
    private static UdpUtil sharedUdpUtil = new UdpUtil();

    public static UdpUtil getInstance() {
        return sharedUdpUtil;
    }


    /**
     * udp receive
     */
    public void startReceive() {

        byte[] recvBuf = new byte[1024];
        final DatagramPacket recePacket = new DatagramPacket(recvBuf, recvBuf.length);

        if (localSocket == null) {
            System.out.println("localSocket is null");
            return;
        }

        new Thread() {

            @Override
            public void run() {

                System.out.println("Udp receive started !");

                try {

                    System.out.println("UDP ip:" + InetAddress.getLocalHost().getHostAddress() + "\tport:" + localSocket.getLocalPort());

                    while (isReceive) {
                        localSocket.receive(recePacket);
                        String recvString = new String(recePacket.getData(), 0, recePacket.getLength());
                        System.out.println("udp client receive:" + recvString);

                        //测试消息处理
//                        MessageProcessThread messageProcessThread = new MessageProcessThread(SocketType.UDP, recvString);
//                        messageProcessThread.start();
                        new UDPMessageProcessThread(recvString).start();

                        //测试tcp发送
//                        boolean isSuccess = sharedClientTcpUtil.sendByTcp(recvString);
//                        System.out.println("tcp send "+isSuccess);
                    }

                } catch (IOException e) {
//                    System.out.println("udp receive error");
                    e.printStackTrace();
                } finally {
                    localSocket.close();
                }

            }
        }.start();
    }


    private void stopReceive() {

        this.isReceive = false;
    }


    /**
     * @param sendStr       send string
     * @param rcuIp target ip address
     * @param rcuPort target  port
     * @return Whether to transmit successfully
     */
    public boolean send(String sendStr, String rcuIp, int rcuPort) {

        //if 检查ip和端口的合法性
        if (true){
            try {
                InetAddress address = InetAddress.getByName(rcuIp);
                int port = rcuPort;
                byte[] sendBuf = sendStr.getBytes();

                DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, address, port);
                localSocket.send(packet);
                return true;
            } catch (UnknownHostException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }


        return false;
    }
}
