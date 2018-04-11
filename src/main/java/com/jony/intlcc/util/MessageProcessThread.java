package com.jony.intlcc.util;

import com.jony.intlcc.domain.Message;
import net.sf.json.JSONObject;

/**
 * Created by jony on 3/27/18.
 */
public class MessageProcessThread extends Thread{

    private String message = null;

    public MessageProcessThread(String message) {
        this.message = message;
    }

    @Override
    public void run() {

//        message = message.substring(0,message.length()-1);

        JSONObject jsonObject = JSONObject.fromObject(message);

        Message message = (Message) JSONObject.toBean(jsonObject, Message.class);

        if (message != null) {
            switch (message.getType()) {

                case CONTROL:
                    MessageProcessUtil.controlMessageProcessing(message.getContent());
                    break;

                case REGISTER:
                    MessageProcessUtil.registerMessageProcessing(message.getContent());
                    break;

                case HEART_BEAT:
                    MessageProcessUtil.HeartBeatMessageProcessing(message.getContent());
                    break;

                case NONE:
                    MessageProcessUtil.noneMessageProcessing(message.getContent());
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
