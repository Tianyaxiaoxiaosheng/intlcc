package com.jony.intlcc.util;


import java.io.IOException;
import java.net.*;

/**
 * Created by jony on 1/16/18.
 */
public class InUdpUtil {

    private int serverPort = 0; //本地端口
    private DatagramSocket localSocket; //本地socket
    private boolean isReceive = true; //udp 接收标志


    private InUdpUtil() {

    }

    //单类
    private static InUdpUtil sharedInUdpUtil = new InUdpUtil();

    public static InUdpUtil getInstance() {
        return sharedInUdpUtil;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * udp receive
     */
    public void startReceive() {

        //socket创建
        if (this.serverPort != 0){
            try {

                localSocket = new DatagramSocket(this.serverPort);
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("In Udp server port is 0.");
        }


        //接收准备
        byte[] recvBuf = new byte[1024];
        final DatagramPacket recePacket = new DatagramPacket(recvBuf, recvBuf.length);

        if (localSocket == null) {
            System.out.println("In Udp localSocket is null");
            return;
        }

        new Thread() {

            @Override
            public void run() {

                System.out.println("In Udp receive started !");

                try {

                    System.out.println("In UDP ip:" + InetAddress.getLocalHost().getHostAddress() + "\tport:" + localSocket.getLocalPort());

                    //测试消息计数
//                    int sendNum = 300;

                    while (isReceive) {
                        localSocket.receive(recePacket);
                        String recvString = new String(recePacket.getData(), 0, recePacket.getLength());
                        System.out.println("In Udp client receive:" + recvString);


                        new InUdpMPThread(recvString).start();

                        //测试消息处理
//                        MessageProcessThread messageProcessThread = new MessageProcessThread(SocketType.UDP, recvString);
//                        messageProcessThread.start();
                        if (recvString.indexOf("test") == 0){
                            String subStr = recvString.substring(4);
                            int sendNum = Integer.parseInt(subStr);
                            for (int i = 0; i < sendNum; i++){
                                new InUdpMPThread("status message " + i).start();
                            }
                        }




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
        if (this.localSocket != null){
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
