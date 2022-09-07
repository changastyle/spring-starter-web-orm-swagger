package com.vd.emkt.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vd.emkt.Emkt3;
import com.vd.emkt.controllers.MasterController;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import javax.persistence.*;

@Entity @Table(name = "grupos")
@Data
@Builder
@AllArgsConstructor
public class Grupo implements Comparable<Grupo>
{
    //ATRIBUTOS:
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String foto;
    @Transient
    private String fotoAux;
    private String nombre;
    @Transient
    private boolean selected;
    @ManyToOne(cascade = CascadeType.MERGE) @JoinColumn(name = "fkInstalacion") @JsonIgnore
    private Operador instalacion;
    
    @OneToMany(mappedBy = "grupo") @JsonIgnore
    private List<RelPersonaGrupo> relPersonasList;
    
    @OneToMany(mappedBy = "grupo")
    List<RelReqGrupo> arrRequerimientos;
    
    @OneToMany(mappedBy = "grupo")
    private List<RelValorGrupo> arrRelsValGrup;
    boolean activo;

    //CONTRUCTOR VACIO:
    public Grupo() 
    {
        relPersonasList = new ArrayList<>();
        arrRequerimientos = new ArrayList<>();
        arrRelsValGrup = new ArrayList<>();
    }
    
    //CONTRUCTOR PARAMETROS SIN LISTAS:
    public Grupo(String foto,String nombre, boolean activo)
    {
        this.foto = foto;
        this.nombre = nombre;
        relPersonasList = new ArrayList<>();
        arrRequerimientos = new ArrayList<>();
        arrRelsValGrup = new ArrayList<>();
    }


    //<editor-fold desc="GETTERS Y SETTERS:">
    public int getId() 
    {
        return id;
    }
    public String getFoto() 
    {
        foto = Emkt3.getURLVisualizacionWEB() +  foto;

//            foto = config.getUrlVisualizacionIconos() + ""+  foto;

        return foto;
    }
    public String getNombre() 
    {
        return nombre;
    }

    public String getFotoAux()
    {
        if(fotoAux == null)
        {
            if(foto.contains("/"))
            {
                int posUltSlash = foto.lastIndexOf("/") + 1 ;
                fotoAux = foto.substring(posUltSlash , foto.length());
            }
            else
            {
                fotoAux = "groups.png";
            }
        }
        return fotoAux;
    }
    


    //@Override
    public String toString()
    {
        String str = "{";
        str += "id:" + id + ", ";
        str += "foto:" + foto + ", ";
        str += "nombre:" + nombre + ", ";
        
//        int contador = 1;
//        for(RelPersonaGrupo relPersonaLoop : relPersonasList)
//        {
//            str += "RELPERSONA[" + contador + "]:" + relPersonasList.get(contador).toString() + ", \n";
//        }
        
        str += "}";
        
        return str;
    }

 
        
    
    //DYN:

    
    public int compareTo(Grupo otro)
    {
        return 1;
    }
    
    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }
     
    public int getCalcularRelsActivas()
    {
        int contador = 0;
        
        if(relPersonasList != null)
        {
            for(RelPersonaGrupo relLoop : relPersonasList)
            {
                if(relLoop.isActivo())
                {
                    contador++;
                }
            }
        }
        
        Collections.sort(relPersonasList);
        
        return contador;
    }
    
    public List<Valor> getCalcularArrValores()
    {
        List<Valor> arrValores = new ArrayList<Valor>();
        
        for(RelReqGrupo relReqLoop : arrRequerimientos)
        {
            if(relReqLoop != null)
            {
                arrValores.add(new Valor(relReqLoop, null, ""));
            }
            
//            Collections.sort(arrValores);
//            arrValores.addAll(arrValores);
        }
        
        return arrValores;
    }

    public boolean calcularSiPersonaPerteneceAlGrupoActivamente(Persona personaRecibida) 
    {
        boolean ok = false;
        
        for(RelPersonaGrupo relLoop : getRelPersonasList())
        {
            if(relLoop.getPersona().getId() == personaRecibida.getId())
            {
                if(relLoop.isActivo())
                {
                    ok = true;
                }
            }
        }
        
        return ok;
    }
    
    public boolean dameValorDeGrupoBooleanByNombre(String nombre)
    {
        boolean rta = false;
        
        String strRta = dameValorDeGrupoStrByNombre(nombre);
        
        if(strRta != null)
        {
            if( strRta.length() > 0)
            {
                if(strRta.equalsIgnoreCase("1"))
                {
                    rta = true;
                }
            }
        }
        
        return rta;
    }
    public String dameValorDeGrupoStrByNombre(String nombre)
    {
        String rta = null; 
        
        ValorGrupo valorGrupo = dameValorDeGrupoByNombre(nombre);
        
        if(valorGrupo != null)
        {
           rta =  valorGrupo.getValor();
        }
        
        return rta;
    }
    public ValorGrupo dameValorDeGrupoByNombre(String nombre)
    {
        ValorGrupo valorGrupoDB = null;
        
        if(arrRelsValGrup != null)
        {
            for(RelValorGrupo relLoop : arrRelsValGrup)
            {
                if(relLoop != null)
                {
                    ValorGrupo valorGrupoLoop = relLoop.getValorGrupo();
                    
                    if(relLoop.isActivo())
                    {
                        if(valorGrupoLoop != null)
                        {

                            if(valorGrupoLoop.getNombreValor().toLowerCase().contains(nombre.toLowerCase()))
                            {
                                valorGrupoDB = valorGrupoLoop;
                                break;
                            }
                        }
                    }
                }
            }
        }
        
        return valorGrupoDB;
    }

    public List<ValorGrupo> getArrValoresGrupo()
    {
        List<ValorGrupo> arrValorGrupo = new ArrayList<ValorGrupo>();
        
        if(arrRelsValGrup != null)
        {
            for(RelValorGrupo relLoop : arrRelsValGrup)
            {
                if(relLoop != null)
                {
                    if(relLoop.isActivo())
                    {
                        ValorGrupo valorGrupoLoop = relLoop.getValorGrupo();
                        arrValorGrupo.add(valorGrupoLoop);
                    }
                }
            }
        }
        return arrValorGrupo;            
    }
}
