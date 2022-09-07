package com.vd.emkt.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.*;
import javax.persistence.*;

@Entity @Table(name = "relsreqgrupo")
@Data
@Builder
@AllArgsConstructor
public class RelReqGrupo implements Comparable<RelReqGrupo>
{
    //ATRIBUTOS:
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.MERGE) @JoinColumn(name = "fkGrupo") @JsonIgnore
    private Grupo grupo;
    @OneToOne() @JoinColumn(name = "fkRequerimiento")
    private Requerimiento requerimiento;
    
//    @OneToMany(mappedBy = "rel") @JsonIgnore
//    private List<Valor> arrValores;
    private boolean activo;
    
    
    //CONTRUCTOR VACIO:
    public RelReqGrupo() 
    {
//        arrValores = new ArrayList<>();
    }
    
    //CONTRUCTOR PARAMETROS SIN LISTAS:
    public RelReqGrupo(Grupo grupo,Requerimiento requerimiento,boolean activo)
    {
        this.grupo = grupo;
        this.requerimiento = requerimiento;
        this.activo = activo;
    }


    //@Override
    public String toString()
    {
        String str = "{";
        str += "id:" + id + ", ";
        str += "grupo:" + grupo + ", ";
        str += "requerimiento:" + requerimiento + ", ";
        str += "activo:" + activo + ", ";
        
        str += "}";
        
        return str;
    }

 
        
    
    //DYN:

    
    public int compareTo(RelReqGrupo otro)
    {
        return 1;
    }
    
}
