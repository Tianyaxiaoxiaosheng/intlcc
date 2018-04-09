package com.jony.intlcc.dao.impl;

import com.jony.intlcc.util.ClientTcpUtil;
import com.jony.intlcc.util.UdpUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by jony on 3/26/18.
 */
public class ServletContextListenerImpl implements ServletContextListener{


    private static final int LOCALPORT = 6666;
//    private static final String SERVERHOST = "47.97.192.24";
    private static final String SERVERHOST = "192.168.0.149";
    private ClientTcpUtil sharedClientTcpUtil = null;


    /**
     *初始化一个链接到云服务器的tcp客户端
     */
    private void initializationTcpClient(){

        sharedClientTcpUtil = ClientTcpUtil.getInstance();

        sharedClientTcpUtil.createTcpClient(SERVERHOST, LOCALPORT);

    }

    /**
     * 初始化udp 服务器
     */
    private void initlizationUdpServer(){
        System.out.println("UDP Socket Initialization");
        //启动udp接收
        UdpUtil sharedUdpUtil = UdpUtil.getInstance();
        //开始接收消息
        sharedUdpUtil.startReceive();
    }




    public void contextInitialized(ServletContextEvent servletContextEvent) {

        initializationTcpClient();
        initlizationUdpServer();

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
