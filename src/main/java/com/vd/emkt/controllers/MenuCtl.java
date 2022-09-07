package com.vd.emkt.controllers;
import java.util.*;
import com.vd.emkt.modelo.*;
import com.vd.emkt.repo.MenuDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/menus")
public class MenuCtl
{
    @Autowired
    MenuDAO accionDAO;

    @GetMapping(value = "/")
    public List<Menu> findAccions()
    {
        List<Menu> accionsList = new ArrayList<Menu>();

        accionsList = accionDAO.findActives();

        return accionsList;
    }
    @PostMapping(value = "/")
    public Menu save(@RequestBody Menu accionBarraLateral)
    {
        return accionDAO.save(accionBarraLateral);
    }
}