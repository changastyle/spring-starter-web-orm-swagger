package com.vd.emkt.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import javax.persistence.*;

@Entity @Table(name = "relsvalorgrupo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelValorGrupo implements Comparable<RelValorGrupo>
{
    //ATRIBUTOS:
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean activo;
    @ManyToOne() @JoinColumn(name = "fkGrupo") @JsonIgnore
    private Grupo grupo;
    @OneToOne() @JoinColumn(name = "fkValorGrupo")
    private ValorGrupo valorGrupo;
    
    

    //CONTRUCTOR PARAMETROS SIN LISTAS:
    public RelValorGrupo(boolean activo,Grupo grupo,ValorGrupo valorGrupo)
    {
        this.activo = activo;
        this.grupo = grupo;
        this.valorGrupo = valorGrupo;
    }



    //@Override
    public String toString()
    {
        String str = "{";
        str += "id:" + id + ", ";
        str += "activo:" + activo + ", ";
        str += "grupo:" + grupo + ", ";
        str += "valorGrupo:" + valorGrupo + ", ";
        
        str += "}";
        
        return str;
    }

    //DYN:
    public int compareTo(RelValorGrupo otro)
    {
        return 1;
    }
    
}
