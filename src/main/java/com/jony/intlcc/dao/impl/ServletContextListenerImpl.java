package com.jony.intlcc.dao.impl;

import com.jony.intlcc.util.OutUdpUtil;
import com.jony.intlcc.util.TcpClientUtil;
import com.jony.intlcc.util.InUdpUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by jony on 3/26/18.
 */
public class ServletContextListenerImpl implements ServletContextListener{


    private static final int LOCALPORT = 6666;
    private static final String SERVERHOST = "47.97.192.24";
//    private static final String SERVERHOST = "192.168.0.149";

    private TcpClientUtil sharedClientTcpUtil = null;

    private static final int OUTUDP_DESPORT = 7000;

    private static final int OUTUDP_SERVERPORT = 7000;

    private static final int INUDP_SERVERPORT = 5000;

    /**
     *初始化一个链接到云服务器的tcp客户端
     */
    private void initializationTcpClient(){

        sharedClientTcpUtil = TcpClientUtil.getInstance();

        sharedClientTcpUtil.createTcpClient(SERVERHOST, LOCALPORT);

    }

    /**
     * 初始化udp 服务器
     */
    private void initlizationUdpServer(){
        System.out.println("UDP Socket Initialization");
        //启动udp接收
        InUdpUtil sharedInUdpUtil = InUdpUtil.getInstance();
        sharedInUdpUtil.setServerPort(INUDP_SERVERPORT);
        //开始接收消息
        sharedInUdpUtil.startReceive();

        //外网udp
        OutUdpUtil sharedOutUdpUtil = OutUdpUtil.getInstance();
        sharedOutUdpUtil.setServerPort(OUTUDP_SERVERPORT);
        sharedOutUdpUtil.setDesIp(SERVERHOST);
        sharedOutUdpUtil.setDesPort(OUTUDP_DESPORT);
        sharedOutUdpUtil.startReceive();
    }




    public void contextInitialized(ServletContextEvent servletContextEvent) {

        initializationTcpClient();
        initlizationUdpServer();

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
