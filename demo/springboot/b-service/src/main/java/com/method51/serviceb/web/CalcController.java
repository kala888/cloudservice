package com.method51.serviceb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.method51.serviceb.client.RemoteCalcClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class CalcController {

    @Autowired
    private RemoteCalcClient remoteCalcClient;



    @RequestMapping(value = "/remote/add", method = RequestMethod.GET)
    public String warpedAdd(@RequestParam Integer a, @RequestParam Integer b) {
        return add(a, b);
    }



    @RequestMapping(value = "/plus1and2", method = RequestMethod.GET)
    public String plus1and2() {
        return add(1, 2);
    }
    
    @HystrixCommand(fallbackMethod = "fallBackCall")
    public String add(Integer a,Integer b){
        return remoteCalcClient.add(a, b); 
    }



    public String fallBackCall() {
        return "FAILED SERVICE A CALL! - FALLING BACK";
    }
}