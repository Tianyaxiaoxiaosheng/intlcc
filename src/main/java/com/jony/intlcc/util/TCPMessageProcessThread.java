package com.jony.intlcc.util;

import com.jony.intlcc.domain.TCPMessage;
import net.sf.json.JSONObject;

/**
 * Created by jony on 3/27/18.
 */
public class TCPMessageProcessThread extends Thread{

    private String message = null;

    public TCPMessageProcessThread(String message) {
        this.message = message;
    }

    @Override
    public void run() {

//        message = message.substring(0,message.length()-1);

        JSONObject jsonObject = JSONObject.fromObject(message);

        TCPMessage tcpMessage = (TCPMessage) JSONObject.toBean(jsonObject, TCPMessage.class);

        if (tcpMessage != null) {
            switch (tcpMessage.getType()) {

                case CONTROL:
                    MessageProcessUtil.controlMessageProcessing(tcpMessage.getContent());
                    break;

                case REGISTER:
                    MessageProcessUtil.registerMessageProcessing(tcpMessage.getContent());
                    break;

                case HEART_BEAT:
                    MessageProcessUtil.HeartBeatMessageProcessing(tcpMessage.getContent());
                    break;

                case NONE:
                    MessageProcessUtil.noneMessageProcessing(tcpMessage.getContent());
                    break;

                case STATE: //server
                    break;

                case REGISTER_REPLY: //server
                    break;

                default:
                    System.out.println("TCP Message Type Error !");
            }
        }
    }


}
