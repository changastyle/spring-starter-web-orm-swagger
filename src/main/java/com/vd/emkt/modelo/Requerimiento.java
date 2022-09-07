package com.vd.emkt.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.*;
import javax.persistence.*;

@Entity @Table(name = "requerimientosdisponibles")
@Data
@Builder
@AllArgsConstructor
public class Requerimiento implements Comparable<Requerimiento>
{
    //ATRIBUTOS:
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String tipoDato;
    private boolean esAdjuntable;
    
    
    //CONTRUCTOR VACIO:
    public Requerimiento() 
    {
    }
    
    //CONTRUCTOR PARAMETROS SIN LISTAS:
    public Requerimiento(String nombre,String tipoDato,boolean esAdjuntable)
    {
        this.nombre = nombre;
        this.tipoDato = tipoDato;
        this.esAdjuntable = esAdjuntable;
    }



    //@Override
    public String toString()
    {
        String str = "{";
        str += "id:" + id + ", ";
        str += "nombre:" + nombre + ", ";
        str += "tipoDato:" + tipoDato + ", ";
        str += "esAdjuntable:" + esAdjuntable + ", ";
        
        str += "}";
        
        return str;
    }

 
        
    
    //DYN:

    
    public int compareTo(Requerimiento otro)
    {
        return 1;
    }
    
}
