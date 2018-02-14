package com.yh.feign;

import com.yh.feign.hystrix.ComputeClientHystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ConsumerController {
    @Autowired
    ComputeClient computeClient;
    @Autowired
    ComputeClientHystrix computeClientHystrix;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {


        return computeClientHystrix.add(10, 20);

    }
}