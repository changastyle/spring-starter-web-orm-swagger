package com.vd.emkt.controllers;

import com.google.gson.Gson;
import java.util.*;

import com.vd.emkt.modelo.Persona;
import com.vd.emkt.modelo.RelReqGrupo;
import com.vd.emkt.modelo.Valor;
import com.vd.emkt.util.dao.DAOEclipse;
import org.springframework.web.bind.annotation.*;

@RestController
public class wsValor
{
    @RequestMapping(value = "findValors")
    public static List<Valor> findValors()
    {
        List<Valor> valorsList = new ArrayList<Valor>();
        
        String jpql = "SELECT v FROM Valor v";
        valorsList = DAOEclipse.findAllByJPQL(jpql);
        
        Collections.sort(valorsList);
        
        return valorsList;
    }
    
    @RequestMapping(value = "guardarValor")
    public static boolean guardarValor(@RequestParam(value = "strValor" , defaultValue = "") String strValor)
    {
        boolean guarde = false;
        boolean modoEdit = false;
        
        Valor valorDB = null;
        if(strValor != null)
        {
            try
            {
                Valor valorRecibido = new Gson().fromJson(strValor, Valor.class);
                
                if(valorRecibido != null)
                {
                    // MODO EDIT:
                    if(valorRecibido.getId() != -1)
                    {
                        valorDB = (Valor) DAOEclipse.get(Valor.class, valorRecibido.getId());
                        
                        if(valorDB != null)
                        {
                            // 0 - ACTUALIZO VALORES DEL OBJ.DB CON LOS DEL OBJ.RECIBIDOS:
                            valorDB.setRelGrupo(valorRecibido.getRelGrupo());
                            valorDB.setPersona(valorRecibido.getPersona());
                            valorDB.setVal(valorRecibido.getVal());
                            guarde = DAOEclipse.update(valorDB);
                        }
                    }
                    else
                    {
                        // 3 - MODO ADD:
                        
                        guarde = DAOEclipse.update(valorRecibido);
                        
                    }
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
        
        return guarde;
    }
    
    @RequestMapping(value = "getValor")
    public static Valor getValor(@RequestParam(value = "idValor" , defaultValue = "-1") int idValor)
    {
        Valor valorDB = null;
        
        if(idValor != -1)
        {
            valorDB = (Valor) DAOEclipse.get(Valor.class, idValor);
        }
        
        return valorDB;
    }
    @RequestMapping(value = "getValorPorFKRelYFKPersona")
    public static Valor getValorPorFKRelYFKPersona
    (
        @RequestParam(value = "fkRel" , defaultValue = "-1") int fkRel,
        @RequestParam(value = "fkPersona" , defaultValue = "-1") int fkPersona
    )
    {
        Valor valorDB = null;
        
        String jpql = "SELECT v FROM Valor v WHERE v.relGrupo.id = " + fkRel + " AND v.persona.id = " + fkPersona + "";
        
        if(fkRel != -1 && fkPersona != -1)
        {
            valorDB = (Valor) DAOEclipse.getByJPQL(jpql);
        }
        
        return valorDB;
    }
    
    private static List<Valor> arrValores;
    private static long timestampUltimaActualizacionArrValores = 0;
    @RequestMapping(value = "getValorPorFKRelYFKPersonaFast")
    public static Valor getValorPorFKRelYFKPersonaFast
    (
        @RequestParam(value = "fkRel" , defaultValue = "-1") int fkRel,
        @RequestParam(value = "fkPersona" , defaultValue = "-1") int fkPersona
    )
    {
        Valor valorDB = null;
        
        long ahora = new Date().getTime();
        long distancia = (ahora - timestampUltimaActualizacionArrValores);
        System.out.println("DISTANCIA:" + distancia );
        if( arrValores == null || distancia > 2500)
        {
            arrValores = wsValor.findValors();
            
            timestampUltimaActualizacionArrValores = new Date().getTime();
        }
        
        if(arrValores != null)
        {
            for(Valor valorLoop : arrValores)
            {
                RelReqGrupo relLoop = valorLoop.getRelGrupo();
                Persona personaLoop = valorLoop.getPersona();
                if(relLoop != null && personaLoop != null)
                {
                    if(relLoop.getId() == fkRel && personaLoop.getId() == fkPersona)
                    {
                        valorDB = valorLoop;
                    }
                }
            }
        }
        return valorDB;
    }
    @RequestMapping(value = "getValorEmpty")
    public Valor getValorEmpty()
    {
       Valor valorEmpty = new Valor();
       valorEmpty.setId(-1);
       return valorEmpty;
    }
}
