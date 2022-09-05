package com.vd.emkt.controllers;
import com.google.gson.Gson;
import java.util.*;
import com.vd.emkt.modelo.*;
import com.vd.emkt.repo.AccionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/acciones")
public class wsAccion
{
    @Autowired
    AccionRepo accionRepo;

    @GetMapping(value = "/")
    public List<AccionBarraLateral> findAccions()
    {
        List<AccionBarraLateral> accionsList = new ArrayList<AccionBarraLateral>();

        accionsList = accionRepo.findAll();

        return accionsList;
    }
    @PostMapping(value = "/")
    public AccionBarraLateral save(@RequestBody AccionBarraLateral accionBarraLateral)
    {
        return accionRepo.save(accionBarraLateral);
    }
}