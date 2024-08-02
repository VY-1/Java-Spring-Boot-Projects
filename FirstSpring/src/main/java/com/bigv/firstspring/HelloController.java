package com.bigv.firstspring;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {


    @GetMapping("/hello")
    public HelloResponse hello() {
        return new HelloResponse("Hello, World!");
    }

    @GetMapping("/hello/{name}")
    public HelloResponse helloVariable(@PathVariable String name) {
        return new HelloResponse("Hello, "+ name +"!");
    }

    @GetMapping("/hello/param")
    public HelloResponse helloParam(@PathParam("name") String name) {
        return new HelloResponse("Hello, "+ name +"!");
    }
    @PostMapping("/hello")
    public HelloResponse helloPost(@RequestBody String name){
        return new HelloResponse("Hello " + name + "!");
    }
}
