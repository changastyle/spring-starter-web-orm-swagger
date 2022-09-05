package com.vd.emkt.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/pulga")
public class PulgaCtl
{
    @GetMapping("/")
    @CrossOrigin
    public List<String> list()
    {
        List<String> arr = new ArrayList<>();
        arr.add("pulga");
        return arr;
    }


    @PostMapping("crearNuevoPerro")
    public boolean crearNuevoPerro
    (
            @RequestParam(value = "nombre") String nombre,
            @RequestParam(value = "raza") String raza,
            @RequestParam(value = "edad") int edad
    )
    {
        return true;
    }
}
