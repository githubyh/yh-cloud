package com.yh.metrics.controller;

import com.codahale.metrics.annotation.Metered;
import com.ryantenney.metrics.annotation.Counted;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

//http://blog.csdn.net/king_is_everyone/article/details/53261354


@RestController("helloworld")
public class HelloWorldController {

    @Metered(name = "sayHello_m")
    @Counted(  name = "sayHello_c",monotonic = true)
    @RequestMapping("/")
    public String sayHello() {

        List list = Arrays.asList(2,3,4,56);

        list.forEach( val  -> System.out.println(val));
        return "Hello,World!";
    }

    public static void main(String[] args){
        List list = Arrays.asList(2,3,4,56);

        list.forEach(val -> System.out.println(val));
    }
}