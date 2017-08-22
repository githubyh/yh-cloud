package com.yh.server;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;


@RestController
public class ComputeController {
    private final Logger logger = Logger.getLogger(getClass());
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return r;
    }

    @GetMapping("/dc")
    public String dc() {
        String services = "Services: " + client.getServices();
        System.out.println(services);
        return services;
    }

    @RequestMapping(value= "hello",method = RequestMethod.GET)
    public String hello(){
        logger.info("hello world2" );
        return "hello world2";
    }

}