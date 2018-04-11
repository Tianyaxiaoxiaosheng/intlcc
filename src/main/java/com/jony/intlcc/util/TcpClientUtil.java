package com.jony.intlcc.util;

/**
 * Created by jony on 3/26/18.
 */
public class TcpClientUtil {

    public TcpSocketClient clientSocket = null;


    private TcpClientUtil() {
    }

    private static TcpClientUtil sharedClientTcpUtil = new TcpClientUtil();

    public static TcpClientUtil getInstance(){
        return sharedClientTcpUtil;
    }

    /**
     * Created one tcp client
     */
    public void createTcpClient(String host, int port){

       clientSocket = new TcpSocketClient(host, port);
    }


    /**
     * Sending a message
     */
    public boolean sendByTcp(String str){

        boolean isSuccess = false;

        if (this.clientSocket != null){

            isSuccess = this.clientSocket.sendMessage(str);
        }else {
            System.out.println("Client Socket is null");
        }

        return isSuccess;
    }


}
