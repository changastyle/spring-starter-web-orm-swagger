package com.vd.emkt.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.*;
import javax.persistence.*;

@Entity @Table(name = "plantillas")
@Data
@Builder
@AllArgsConstructor
public class Plantilla implements Comparable<Plantilla>
{
    //ATRIBUTOS:
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String texto;
    private boolean activo;
    
    @ManyToOne(cascade = CascadeType.MERGE) @JoinColumn(name = "fkInstalacion") @JsonIgnore
    private Operador instalacion;
    
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "plantilla")
    private List<Archivo> adjuntosList;
    @ManyToOne() @JoinColumn(name = "fkGrupoPlantilla") @JsonIgnore
    private GrupoPlantilla grupoPlantilla;
    
    
    //CONTRUCTOR VACIO:
    public Plantilla() 
    {
        adjuntosList = new ArrayList<>();
    }
    
    //CONTRUCTOR PARAMETROS SIN LISTAS:
    public Plantilla(String titulo,String texto,boolean activo)
    {
        this.titulo = titulo;
        this.texto = texto;
        this.activo = activo;
        this.adjuntosList = new ArrayList<>();
    }
    
    //CONTRUCTOR PARAMETROS CON LISTAS:
    public Plantilla(String titulo,String texto,List<Archivo> adjuntosList)
    {
        this.titulo = titulo;
        this.texto = texto;
        this.adjuntosList = new ArrayList<>();
    }


    //<editor-fold desc="GETTERS Y SETTERS:">
    public int getId() 
    {
        return id;
    }
    public String getTitulo() 
    {
        return titulo;
    }
    public String getTexto() 
    {
        return texto;
    }
    public List<Archivo> getAdjuntosList() 
    {
        return adjuntosList;
    }

    //SET
    public void setId( int id ) 
    {
        this.id = id;
    }
    public void setTitulo( String titulo ) 
    {
        this.titulo = titulo;
    }
    public void setTexto( String texto ) 
    {
        this.texto = texto;
    }
    public void setAdjuntosList( List<Archivo> adjuntosList ) 
    {
        this.adjuntosList = adjuntosList;
        for(Archivo archivoLoop : adjuntosList)
        {
            archivoLoop.setPlantilla(this);
        }
    }
   public boolean addArchivo(Archivo archivo)
   {
       boolean agregue = false;
       
       archivo.setPlantilla(this);
       this.adjuntosList.add(archivo);
    
       return agregue;
   }

    public Operador getInstalacion()
    {
        return instalacion;
    }

    public void setInstalacion(Operador instalacion)
    {
        this.instalacion = instalacion;
    }

    public boolean getActivo()
    {
        return activo;
    }

    public void setActivo(boolean activo)
    {
        this.activo = activo;
    }

    public GrupoPlantilla getGrupoPlantilla()
    {
        return grupoPlantilla;
    }
    
   
    //</editor-fold>
    
    //@Override
    public String toString()
    {
        String str = "{";
        str += "id:" + id + ", ";
        str += "titulo:" + titulo + ", ";
        str += "texto:" + texto + ", ";
        str += "activo:" + activo + ", ";
        
        if( adjuntosList != null) 
        {
            str += "adjuntosList:" + adjuntosList.size() + ", ";
        }
        
        str += "}";
        
        return str;
    }

    
 
        
    
    //DYN:

    
    public int compareTo(Plantilla otro)
    {
        return this.titulo.compareTo(otro.texto);
    }

    public void setGrupoPlantilla(GrupoPlantilla grupo)
    {
        this.grupoPlantilla = grupo;
    }
    
}
