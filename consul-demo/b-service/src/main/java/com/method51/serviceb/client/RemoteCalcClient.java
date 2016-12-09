package com.method51.serviceb.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "a-service")
public interface RemoteCalcClient {

    @RequestMapping(method = RequestMethod.GET, value = "/add?a={a}&b={b}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String add(@PathVariable("a") Integer a, @PathVariable("b") Integer b);

}
