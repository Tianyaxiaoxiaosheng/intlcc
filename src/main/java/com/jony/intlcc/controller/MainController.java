package com.jony.intlcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.logging.Logger;

/**
 * Created by jony on 3/19/18.
 */

@Controller
public class MainController {

    private static Logger logger = Logger.getLogger(String.valueOf(MainController.class));

    @RequestMapping(value = "/tcp_client.do", method = RequestMethod.GET)
    public String tcp_client(){

        logger.info("123456");
        return "tcp_client";
    }
}
