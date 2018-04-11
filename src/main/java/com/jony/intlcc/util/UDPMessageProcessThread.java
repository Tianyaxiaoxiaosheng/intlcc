package com.jony.intlcc.util;

import com.jony.intlcc.domain.MessageType;

/**
 * Created by jony on 3/27/18.
 */
public class UDPMessageProcessThread extends Thread{

    private String message;

    public UDPMessageProcessThread(String message) {
        this.message = message;
    }

    @Override
    public void run() {

        if (message != null){
            MessageProcessUtil.codeMessageAndSend(MessageType.STATE, this.message);
        }
    }
}
