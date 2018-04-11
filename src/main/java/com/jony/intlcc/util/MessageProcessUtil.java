package com.jony.intlcc.util;

import com.jony.intlcc.domain.*;
import net.sf.json.JSONObject;

/**
 * Created by jony on 3/27/18.
 */
public class MessageProcessUtil {

    private static TcpClientUtil sharedClientTcpUtil = TcpClientUtil.getInstance();
    private static InUdpUtil sharedInUdpUtil = InUdpUtil.getInstance();

    private static OutUdpUtil sharedOutUdpUtil = OutUdpUtil.getInstance();

    static int count = 0;


    /**
     * TCP Message code and send
     */
    static void codeMessageAndSend(MessageType type, String content){

//        Message socketMessage = new Message(MessageType.STATE, content);
//        JSONObject jsonObject = JSONObject.fromObject(socketMessage);
        JSONObject jsonObject = JSONObject.fromObject(new Message(type, content));

        boolean isSuccess = false;

        switch (type){
            case STATE:
//                isSuccess = sharedOutUdpUtil.send(jsonObject.toString());
//                break;

            case NONE:
            case REGISTER_REPLY:
                isSuccess = sharedClientTcpUtil.sendByTcp(jsonObject.toString());
                break;

                default:
                    System.out.println("Unknown Message Type.");
        }


        System.out.println("Message send "+isSuccess);
    }

    /**
     * Register Reply Message code and send
     */
    static void codeRegRepMessageAndSend(RegReplyMessage regReplyMessage){

        JSONObject jsonObject = JSONObject.fromObject(regReplyMessage);

        codeMessageAndSend(MessageType.REGISTER_REPLY, jsonObject.toString());
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
            boolean isSuccess = sharedInUdpUtil.send(controlMessage.getContent()
                    ,controlMessage.getRcuIp()
                    ,controlMessage.getRcuPort());

            System.out.println("In udp send "+isSuccess);
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
            System.out.println("Register Message : "+registerMessage.getUser()+"\t"+registerMessage.getPassword());

            //查询验证回复
            RegReplyMessage regReplyMessage = new RegReplyMessage(registerMessage.getUser(), false, "null");
            codeRegRepMessageAndSend(regReplyMessage);
        }
    }
}
