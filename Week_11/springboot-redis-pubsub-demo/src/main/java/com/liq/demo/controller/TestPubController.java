package com.liq.demo.controller;

import com.liq.demo.service.PubService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description TODO
 * author: liquan
 * date: 2020/12/31 18:51
 * version: 1.0
 */
@RestController
@Log4j2
public class TestPubController {

    @Autowired
    private PubService pubService;

    @RequestMapping(value = "/pushMsg", method = RequestMethod.GET)
    public Object pushMsg() {

        return pubService.pushMsg("order");
    }
}
