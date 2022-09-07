package com.vd.emkt.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vd.emkt.controllers.wsValor;
import com.vd.emkt.util.auxiliar.ClaveValor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.*;
import javax.persistence.*;

@Entity @Table(name = "relpersonagrupo")
@Data
@Builder
@AllArgsConstructor
public class RelPersonaGrupo implements Comparable<RelPersonaGrupo>
{
    //ATRIBUTOS:
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean activo;
    @OneToOne() @JoinColumn(name = "fkGrupo") @JsonIgnore
    private Grupo grupo;
    @OneToOne() @JoinColumn(name = "fkPersona") 
    private Persona persona;
    
    @Transient
    private String nombreGrupoTransient;
    @Transient
    private List<ClaveValor> arrClaveValorTransient;
    
    
    //CONTRUCTOR VACIO:
    public RelPersonaGrupo() 
    {
    }
    
    //CONTRUCTOR PARAMETROS SIN LISTAS:
    public RelPersonaGrupo(boolean activo,Grupo grupo,Persona persona)
    {
        this.activo = activo;
        this.grupo = grupo;
        this.persona = persona;
    }



    //@Override
    public String toString()
    {
        String str = "{";
        str += "id:" + id + ", ";
        str += "activo:" + activo + ", ";
//        str += "grupo:" + grupo + ", ";
        str += "persona:" + persona + ", ";
        
        List<ClaveValor> arrClaveValor = getArrClaveValorTransientUnico();
        if(arrClaveValor != null)
        {
            str += "arrClaveValorTransient:" + arrClaveValor.size() + ", ";
        }
        
        str += "}";
        
        return str;
    }

 
        
    
    //DYN:

    
    public int compareTo(RelPersonaGrupo otro)
    {
        return this.persona.getApellido().compareTo(otro.persona.getApellido());
    }
    public String getNombreGrupoTransient()
    {
        String nombreGrupo = null;
        if(grupo != null)
        {
            nombreGrupo = grupo.getNombre();
        }
        
        return nombreGrupo;
    }
    
    public List<ClaveValor> dameArrClaveValorDelHTML()
    {
        return arrClaveValorTransient;
    }
    public List<ClaveValor> getArrClaveValorTransientUnico()
    {
        if(arrClaveValorTransient == null)
        {
            arrClaveValorTransient = getArrClaveValorTransient();
        }

        return arrClaveValorTransient;
    }
    public List<ClaveValor> getArrClaveValorTransient()
    {
        // 1 - POR CADA RELACION PERSONA EN UN GRUPO, BUSCO LOS VALORES QUE NECESITA EL GRUPO
        // 2 -  SI NO TIENE VALOR LO INICIALIZO EN 0:
        List<ClaveValor> arrClavesValores = new ArrayList<ClaveValor>();
        
        // 3  - QUE VALORES NECESITO PARA EL GRUPO (EN BASE A LAS RELACIONES REQ DEL GRUPO)??:
        for(RelReqGrupo relReqLoop : grupo.getArrRequerimientos())
        {
            // 4 - SI LA RELACION CON EL REQUERIMIENTO ESTA ACTIVA:
            if(relReqLoop.isActivo() && relReqLoop.getGrupo().calcularSiPersonaPerteneceAlGrupoActivamente(persona))
            {
                // 5 - TENGO EL REQUERIMIENTO:
                Requerimiento requerimiento = relReqLoop.getRequerimiento();
                if(requerimiento != null)
                {
                    String nombreRequerimiento = requerimiento.getNombre();
                    int fkRequerimiento = requerimiento.getId();
                    
                    int fkRel = relReqLoop.getId();
                    boolean loTengo = false;

                    // 6 - BUSCO EL VALOR QUE TENGO EN DB PARA ESA RELACION Y ESA PERSONA:
                    if(persona != null)
                    {
                        Valor valorDB = wsValor.getValorPorFKRelYFKPersonaFast(fkRel, persona.getId());
                        
                        // 7 - SI EXISTE UN VALOR , GENERO UNA CLAVE VALOR CON EL REQUERIMIENTO Y EL VALOR:
                        if(valorDB != null)
                        {
                            loTengo = true;
                            arrClavesValores.add(new ClaveValor(fkRequerimiento, nombreRequerimiento, valorDB.getVal()));
                        }
                    }


                    // 99 - SI NO TENGO UN REQUERIMIENTO QUE EL GRUPO NECESITA, LO CREO CON VALOR 0:
                    if(!loTengo)
                    {
                        arrClavesValores.add(new ClaveValor(fkRequerimiento, nombreRequerimiento, "0"));
                    }
                }
            }
        }
        
        
        return arrClavesValores;
    }
}
