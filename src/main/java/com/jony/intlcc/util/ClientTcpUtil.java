package com.jony.intlcc.util;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by jony on 3/26/18.
 */
public class ClientTcpUtil {

    private Socket clientSocket = null;
    private TcpSocketThread tcpSocketThread = null; //socket process thread

    private ClientTcpUtil() {
    }

    private static ClientTcpUtil sharedClientTcpUtil = new ClientTcpUtil();

    public static ClientTcpUtil getInstance(){
        return sharedClientTcpUtil;
    }

    /**
     * Created tcp client
     */
    public boolean createClientTcp(String host, int port){

        try {
            this.clientSocket = new Socket(host, port);
            this.tcpSocketThread = new TcpSocketThread(this.clientSocket);
            this.tcpSocketThread.start();

//            sendByTcp("TCP Client connect Success");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Drop TCP Connect
     */
    public boolean dropTcpConnect(){
        if (this.clientSocket != null){
            try {
                this.clientSocket.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     * Sending a message
     */
    public boolean sendByTcp(String str){

        boolean isSuccess = false;

        if (this.tcpSocketThread != null){

            isSuccess = this.tcpSocketThread.sendMessage(str);
        }else {
            System.out.println("TCP Socket Thread is null");
        }

        return isSuccess;
    }
}
