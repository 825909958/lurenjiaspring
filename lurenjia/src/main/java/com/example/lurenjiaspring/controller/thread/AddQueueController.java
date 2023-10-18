package com.example.lurenjiaspring.controller.thread;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.lurenjiaspring.LurenjiaspringApplication.addData;

@RestController
public class AddQueueController {
    @RequestMapping("/addQueue")
    public Boolean addQueue(Integer data) {

        return addData(data);
    }
}
