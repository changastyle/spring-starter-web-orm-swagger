package com.vd.emkt.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.*;
import javax.persistence.*;

@Entity @Table(name = "valoresreqgrupo")
@Data
@Builder
@AllArgsConstructor
public class Valor implements Comparable<Valor>
{
    //ATRIBUTOS:
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne() @JoinColumn(name = "fkRelGrupo") @JsonIgnore
    private RelReqGrupo relGrupo;
    @OneToOne() @JoinColumn(name = "fkPersona")
    private Persona persona;
    private String val;
    
    
    //CONTRUCTOR VACIO:
    public Valor() 
    {
    }
    
    //CONTRUCTOR PARAMETROS SIN LISTAS:
    public Valor(RelReqGrupo relGrupo,Persona persona,String val)
    {
        this.relGrupo = relGrupo;
        this.persona = persona;
        this.val = val;
    }


    //<editor-fold desc="GETTERS Y SETTERS:">
    public int getId() 
    {
        return id;
    }

    public RelReqGrupo getRelGrupo() {
        return relGrupo;
    }
    
    public Persona getPersona() 
    {
        return persona;
    }
    public String getVal() 
    {
        return val;
    }

    //SET
    public void setId( int id ) 
    {
        this.id = id;
    }

    public void setRelGrupo(RelReqGrupo relGrupo) {
        this.relGrupo = relGrupo;
    }
    
    public void setPersona( Persona persona ) 
    {
        this.persona = persona;
    }
    public void setVal( String val ) 
    {
        this.val = val;
    }
    //</editor-fold>
    
    //@Override
    public String toString()
    {
        String str = "{";
        str += "id:" + id + ", ";
        str += "relGrupo:" + relGrupo + ", ";
        str += "persona:" + persona + ", ";
        str += "val:" + val + ", ";
        
        str += "}";
        
        return str;
    }

 
        
    
    //DYN:

    
    public int compareTo(Valor otro)
    {
        return 1;
    }
    
}
