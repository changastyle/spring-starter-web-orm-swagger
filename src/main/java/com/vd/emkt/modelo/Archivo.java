package com.vd.emkt.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vd.emkt.Emkt3;
import com.vd.emkt.controllers.MasterController;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.util.*;
import javax.persistence.*;

@Entity @Table(name = "archivos")
@Data
@Builder
@AllArgsConstructor
public class Archivo implements Comparable<Archivo>
{
    //ATRIBUTOS:
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String url;
    @ManyToOne() @JoinColumn(name = "fkPlantilla") @JsonIgnore
    private Plantilla plantilla;
    
    
    //CONTRUCTOR VACIO:
    public Archivo() 
    {
    }
    
    //CONTRUCTOR PARAMETROS SIN LISTAS:
    public Archivo(String titulo,String url,Plantilla plantilla)
    {
        this.titulo = titulo;
        this.url = url;
        this.plantilla = plantilla;
    }


    //@Override
    public String toString()
    {
        String str = "{";
        str += "id:" + id + ", ";
        str += "titulo:" + titulo + ", ";
        str += "url:" + url + ", ";
        str += "plantilla:" + plantilla + ", ";
        
        str += "}";
        
        return str;
    }

 
        
    
    //DYN:
    public int compareTo(Archivo otro)
    {
        return 1;
    }

    public String getFullURL()
    {
        String fullUrl = Emkt3.getURLFS() + "" + url;

        return fullUrl;
    }
    public String getFullURL2()
    {
        String fullUrl = Emkt3.getURLFS() +"" + url;

        return fullUrl;
    }
    public String getFullURLFS()
    {
        String fullUrl = null;
        
        if(url != null)
        {
            fullUrl = Emkt3.getURLFS() + File.separator + url;
        }
        
        return fullUrl;
    }
    public boolean getSoyPDF()
    {
        boolean soy = false;
        
        if(url != null)
        {
            if(url.toLowerCase().endsWith("pdf"))
            {
                soy = true;
            }
        }
        
        return soy;
    }
    public boolean getSoyXLS()
    {
        boolean soy = false;
        
        if(url != null)
        {
            if(url.toLowerCase().endsWith("xls")||url.toLowerCase().endsWith("xlsx") || url.toLowerCase().endsWith("csv"))
            {
                soy = true;
            }
        }
        
        return soy;
    }
    public boolean getSoyDoc()
    {
        boolean soy = false;
        
        if(url != null)
        {
            if(url.toLowerCase().endsWith("doc")||url.toLowerCase().endsWith("docx") || url.toLowerCase().endsWith("txt"))
            {
                soy = true;
            }
        }
        
        return soy;
    }
}
