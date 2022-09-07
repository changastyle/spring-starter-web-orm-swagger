package com.vd.emkt.controllers;

import com.vd.emkt.modelo.Operador;
import com.vd.emkt.repo.OperadorDAO;
import com.vd.emkt.validations.ValidationOperador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "api/operador")
public class OperadorCtl
{
    @Autowired
    OperadorDAO operadorDAO;
    @Autowired
    MasterController masterController;

    @GetMapping(value = "/")
    @CrossOrigin
    public List<Operador> findOperadors()
    {
        return operadorDAO.findActives();
    }

    @PostMapping(value = "/logearse")
    @CrossOrigin
    public Operador logearse
    (
            @RequestParam(value = "email") String email,
            @RequestParam(value = "pass") String pass ,
            HttpServletRequest request
    )
    {
        Operador operadorDB = empty();

        if(ValidationOperador.validarEmail(email))
        {
            if(ValidationOperador.validarPass(pass))
            {
                // 1 - BUSCO EN DB POR EMAIL Y PASS:
                operadorDB = operadorDAO.checkEmailAndPass(email, pass);

                // 2 - LOGEO EN VARIABLE DE SESION:
                MasterController.sesionarUsuario(request,operadorDB);
            }
        }
        return operadorDB;
    }
    @GetMapping(value = "/logeado")
    @CrossOrigin
    public Operador comprobarLogeado(HttpServletRequest request)
    {
        return masterController.dameUsuarioLogeadoFromDB(request);
    }

    @PostMapping(value = "/nuevo")
    @CrossOrigin
    public Operador nuevo(@RequestBody Operador operador)
    {
        operador.setId(-1);
        operador.setPassword("cambiame");
        operador.setActive(true);

        // DEFAULT IMAGE:
        if(operador.getImg() == null || operador.getImg().equalsIgnoreCase("string"))
        {
            operador.setImg("default.jpg");
        }

        return operadorDAO.save(operador);
    }
    @PostMapping(value = "/changePassword")
    @CrossOrigin
    public boolean changePassword(@RequestParam(value = "newPassword") String newPassword , HttpServletRequest request)
    {
        boolean ok = false;

        Operador operadorDB = masterController.dameUsuarioLogeadoFromDB(request);

        if(operadorDB != null)
        {
            operadorDB.setPassword(newPassword);
            if (operadorDAO.save(operadorDB) != null)
            {
                ok = true;
            }
        }

        return ok;
    }
    @GetMapping(value = "/exit")
    @CrossOrigin
    public Operador exit(HttpServletRequest request)
    {
        Operador operadorRTA = null;

        if(masterController.exit(request))
        {
            operadorRTA = masterController.getOperadorEmpty();
        }
        else
        {
            operadorRTA = comprobarLogeado(request);
        }
        return operadorRTA;
    }

    @GetMapping(value = "/empty")
    @CrossOrigin
    public Operador empty()
    {
        return masterController.getOperadorEmpty();
    }
}