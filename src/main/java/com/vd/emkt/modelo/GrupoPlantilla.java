package com.vd.emkt.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.*;
import javax.persistence.*;

@Entity @Table(name = "grupoplantilla")
@Data
@Builder
@AllArgsConstructor
public class GrupoPlantilla implements Comparable<GrupoPlantilla>
{
    //ATRIBUTOS:
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "grupoPlantilla")
    private List<Plantilla> arrPlantillas;
    @ManyToOne() @JoinColumn(name = "fkInstalacion") @JsonIgnore
    private Operador instalacion;
    private boolean activo;
    private boolean esDefault;
    private int orden;
    
    @Transient
    public List<Plantilla> arrPlantilasEnlazadas;
    @Transient
    public List<Plantilla> arrPlantilasNoEnlazadas;
    
    
    //CONTRUCTOR VACIO:
    public GrupoPlantilla() 
    {
        arrPlantillas = new ArrayList<>();
        arrPlantilasEnlazadas = new ArrayList<>();
        arrPlantilasNoEnlazadas = new ArrayList<>();
        arrPlantillas = new ArrayList<>();
    }
    
    //CONTRUCTOR PARAMETROS SIN LISTAS:

    public GrupoPlantilla(String titulo, Operador instalacion, boolean activo, boolean esDefault, int orden)
    {
        this.titulo = titulo;
        this.instalacion = instalacion;
        this.activo = activo;
        this.esDefault = esDefault;
        this.orden = orden;
        arrPlantillas = new ArrayList<>();
        arrPlantilasEnlazadas = new ArrayList<>();
        arrPlantilasNoEnlazadas = new ArrayList<>();
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
    public List<Plantilla> getArrPlantillas() 
    {
        return arrPlantillas;
    }
    public Operador getInstalacion() 
    {
        return instalacion;
    }
    public boolean getActivo() 
    {
        return activo;
    }
    public int getOrden() 
    {
        return orden;
    }

    public boolean isEsDefault()
    {
        return esDefault;
    }

    public void setEsDefault(boolean esDefault)
    {
        this.esDefault = esDefault;
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
    public void setArrPlantillas( List<Plantilla> arrPlantillas ) 
    {
        this.arrPlantillas = arrPlantillas;
        for(Plantilla plantillaLoop : arrPlantillas)
        {
            plantillaLoop.setGrupoPlantilla(this);
        }
    }
   public boolean addPlantilla(Plantilla plantilla)
   {
       boolean agregue = false;
       
       plantilla.setGrupoPlantilla(this);
       this.arrPlantillas.add(plantilla);
    
       return agregue;
   }
    public void setInstalacion( Operador instalacion ) 
    {
        this.instalacion = instalacion;
    }
    public void setActivo( boolean activo ) 
    {
        this.activo = activo;
    }
    public void setOrden( int orden ) 
    {
        this.orden = orden;
    }
    //</editor-fold>
    
    //@Override
    public String toString()
    {
        String str = "{\n";
        str += "id:" + id + ", \n";
        str += "titulo:" + titulo + ", \n";
        
        if( arrPlantillas != null) 
        {
            str += "arrPlantillas:" + arrPlantillas.size() + ", \n";
        }
        str += "instalacion:" + instalacion + ", \n";
        str += "activo:" + activo + ", \n";
        str += "orden:" + orden + ", \n";
        
        str += "}\n";
        
        return str;
    }

    public String toJSON()
    {
        String json = "{";
        json += "id:" + id + ",";
        json += "titulo:" + titulo + ",";
        
        if( arrPlantillas != null) 
        {
            json += "arrPlantillas:" + arrPlantillas.size() + ",";
        }
        
        if( instalacion != null) 
        {
            json += "fkInstalacion:" + instalacion.getId() + ",";
        }
        json += "activo:" + activo + ",";
        json += "orden:" + orden + ",";
        
        json += "}";
        
        return json;
    }

 
        
    
    //DYN:

    
    public int compareTo(GrupoPlantilla otro)
    {
        return 1;
    }
    
    
}
