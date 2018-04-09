package com.jony.intlcc.dao.impl;

import com.jony.intlcc.dao.TCPClientListener;

/**
 * Created by jony on 2018/4/9.
 */
public class TCPClientListenerImpl implements TCPClientListener{

    public void drop() {

        System.out.println("TCP Client Drop.");

    }
}
