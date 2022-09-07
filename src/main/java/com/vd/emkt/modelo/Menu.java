package com.vd.emkt.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Table(name = "menus")
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class Menu implements Comparable<Menu>
{
    //ATRIBUTOS:
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String enlace;
    private String icono;
    private String nombre;
    private int orden;
    private String urlBG;
    private int bgposx;
    private int bgposy;
    private boolean mostrarMenu;
    private boolean active;
    private boolean requiereAdmin;


    
    //@Override
    public String toString()
    {
        String str = "{";
        str += "id:" + id + ", ";
        str += "enlace:" + enlace + ", ";
        str += "icono:" + icono + ", ";
        str += "nombre:" + nombre + ", ";
        str += "requiereAdmin:" + requiereAdmin + ", ";
        str += "orden:" + orden + ", ";
        
        str += "}";
        
        return str;
    }

 
        
    
    //DYN:
    public String getTerminacion() 
    {
        int posUltimoSlash = enlace.lastIndexOf("/") + 1 ;
        
        String terminacion = "";
        
        if(posUltimoSlash != -1)
        {
            terminacion = enlace.substring(posUltimoSlash , enlace.length());
        }
        return terminacion;
    }
    public String getFullURLBG()
    {
        String urlFull = "";
//        Configuracion config = MasterController.dameConfigMaster();
//        if(config != null)
//        {
//            urlFull = config.getUrlVisualizacion()+ "/" + urlBG;
//        }
        
        return urlFull;
    }
    public int compareTo(Menu otro)
    {
        if(this.orden > otro.getOrden())
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
    
    
}