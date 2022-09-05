package com.vd.emkt.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/email")
public class EmailCtl
{
    @GetMapping("/")
    @CrossOrigin
    public List<String> list()
    {
        List<String> arr = new ArrayList<>();
        return arr;
    }
}
