package com.jony.intlcc.util;

import com.jony.intlcc.domain.*;
import net.sf.json.JSONObject;

/**
 * Created by jony on 3/27/18.
 */
public class MessageProcessUtil {

    private static ClientTcpUtil sharedClientTcpUtil = ClientTcpUtil.getInstance();
    private static UdpUtil sharedUdpUtil = UdpUtil.getInstance();

    static int count = 0;


    /**
     * TCP Message code and send
     */
    static void codeTCPMessageAndSend(TCPMessageType type, String content){

//        TCPMessage socketMessage = new TCPMessage(TCPMessageType.STATE, content);
//        JSONObject jsonObject = JSONObject.fromObject(socketMessage);
        JSONObject jsonObject = JSONObject.fromObject(new TCPMessage(type, content));

        boolean isSuccess = sharedClientTcpUtil.sendByTcp(jsonObject.toString());
        System.out.println("tcp send "+isSuccess);
    }

    /**
     * Register Reply Message code and send
     */
    static void codeRegRepMessageAndSend(RegReplyMessage regReplyMessage){

        JSONObject jsonObject = JSONObject.fromObject(regReplyMessage);

        codeTCPMessageAndSend(TCPMessageType.REGISTER_REPLY, jsonObject.toString());
    }


    /**
     * None Message Processing
     */
    static void noneMessageProcessing(String content){
        System.out.println("NONE MESSAGE : "+content);
    }


    /**
     * Control Message Processing
     */
    static void controlMessageProcessing(String content){

//        System.out.println(content);

        JSONObject jsonObject = JSONObject.fromObject(content);

        Object object = JSONObject.toBean(jsonObject, ControlMessage.class);

        if (object instanceof ControlMessage){

            ControlMessage controlMessage = (ControlMessage) object;

            //仅仅是发送结果
            boolean isSuccess = sharedUdpUtil.send(controlMessage.getContent()
                    ,controlMessage.getRcuIp()
                    ,controlMessage.getRcuPort());

            System.out.println("udp send "+isSuccess);
        }

    }


    /**
     * Heart Beat Message Processing
     */
    static void HeartBeatMessageProcessing(String content){

        System.out.println((count++)+" : "+content);

        sharedClientTcpUtil.clientSocket.hbValue = content;
    }

    /**
     * Register Message Processing
     */
    static void registerMessageProcessing(String content){

        JSONObject jsonObject = JSONObject.fromObject(content);
        Object object = JSONObject.toBean(jsonObject, RegisterMessage.class);

        if (object instanceof RegisterMessage){

            RegisterMessage registerMessage = (RegisterMessage) object;
            System.out.println("Register Message : "+registerMessage.toString());
        }
    }
}
