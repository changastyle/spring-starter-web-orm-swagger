package com.vd.emkt.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vd.emkt.controllers.MasterController;

import java.util.*;
import javax.persistence.*;

@Entity @Table(name = "personas")
public class Persona implements Comparable<Persona>
{
    //ATRIBUTOS:
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String apellido;
    private String email;
    private String foto;
    private String nombre;
    private String tel;
    private String alias;
    @Transient
    private String fotoAux;
    @Transient
    private boolean selected;
    @OneToMany(mappedBy = "persona",cascade = CascadeType.ALL) @JsonIgnore
    private List<RelPersonaGrupo> relGruposList;
    
    @ManyToOne(cascade = CascadeType.MERGE) @JoinColumn(name = "fkInstalacion") @JsonIgnore
    private Operador instalacion;
    
    //CONTRUCTOR VACIO:
    public Persona() 
    {
        relGruposList = new ArrayList<>();
    }
    
    //CONTRUCTOR PARAMETROS SIN LISTAS:
    
    public Persona(String apellido, String email, String foto, String nombre, String tel, String alias, Operador instalacion)
    {
        this.apellido = apellido;
        this.email = email;
        this.foto = foto;
        this.nombre = nombre;
        this.tel = tel;
        this.alias = alias;
        this.instalacion = instalacion;
        relGruposList = new ArrayList<>();
    }


    //<editor-fold desc="GETTERS Y SETTERS:">
    public int getId() 
    {
        return id;
    }
    public String getApellido() 
    {
        return apellido;
    }
    public String getEmail() 
    {
        return email;
    }
    public String getFoto() 
    {
            if(foto.equalsIgnoreCase("null"))
            {
                foto = "default.png";
            }
//          TODO:  foto = config.getUrlVisualizacion() + "/" + foto;

        return foto;
    }
    public String getNombre() 
    {
        return nombre;
    }

    public String getFotoAux()
    {
        return fotoAux;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    public String getAlias()
    {
        return alias;
    }

    

    
    
    //SET
    public void setId( int id ) 
    {
        this.id = id;
    }
    public void setApellido( String apellido ) 
    {
        this.apellido = apellido;
    }
    public void setEmail( String email ) 
    {
        this.email = email;
    }
    public void setFoto( String foto ) 
    {
        this.foto = foto;
    }
    public void setNombre( String nombre ) 
    {
        this.nombre = nombre;
    }

    public void setFotoAux(String fotoAux)
    {
        this.fotoAux = fotoAux;
    }

    public List<RelPersonaGrupo> getRelGruposList()
    {
        return relGruposList;
    }

    public void setRelGruposList(List<RelPersonaGrupo> relGruposList)
    {
        this.relGruposList = relGruposList;
    }

    public Operador getInstalacion()
    {
        return instalacion;
    }

    public void setInstalacion(Operador instalacion)
    {
        this.instalacion = instalacion;
    }

    public String getTel()
    {
        return tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }
    public void setAlias(String alias)
    {
        this.alias = alias;
    }
    
    
    
    
    public void addRel(RelPersonaGrupo rel)
    {
//        if(relGruposList != null)
//        {
//            boolean laTengo = false;
//            for(RelPersonaGrupo relLoop : relGruposList)
//            {
//                if(relLoop.getGrupo().getId() == rel.getGrupo().getId() && relLoop.getPersona().getId() == rel.getPersona().getId())
//                {
//                    relLoop.setActivo(true);
//                    laTengo = true;
//                }
//            }
//            
//            if(!laTengo)
//            {
//                relGruposList.add(rel);
//            }
//        }

        rel.setPersona(this);
        relGruposList.add(rel);
    }
    //</editor-fold>
    
    //@Override
    public String toString()
    {
        String str = "{";
        str += "id:" + id + ", ";
        str += "apellido:" + apellido + ", ";
        str += "email:" + email + ", ";
        str += "foto:" + foto + ", ";
        str += "nombre:" + nombre + ", ";
        
        str += "}";
        
        return str;
    }

 
        
    
    //DYN:

    
    public int compareTo(Persona otro)
    {
        int orden = 0;

        orden = this.apellido.compareTo(otro.apellido);
        
        return orden;
    }

    public void limpiarTablaRelaciones()
    {
        
    }
    public void desabilitarTodasLasRelaciones()
    {
        if(relGruposList != null)
        {
            for(RelPersonaGrupo relLoop : relGruposList)
            {
                relLoop.setActivo(false);
            }
        }
    }
    public String dameEmailPrincipal()
    {
        String emailPrincipal = null;
        
        List<String> emailsList = dameArraydeMails();
        
        if(emailsList != null)
        {
            if(emailsList.size() > 0  )
            {
                emailPrincipal = emailsList.get(0);
            }
        }
        
        return emailPrincipal;
    }
    public List<String> dameArraydeMails()
    {
        List<String> emailsList = new ArrayList<>();
        String emailPrincipal = "";
        
        if(email != null)
        {
            String acumulador = "";
            for(int i = 0 ; i < email.length() ; i ++)
            {
                char c = email.charAt(i);
                
                if(c == ';' || c == ',' || (i == email.length() - 1))
                {
                    if(i == email.length()-1)
                    {
                        acumulador += c;
                    }
                    emailsList.add(acumulador);
                    acumulador = "" ;
                }
                else
                {
                    acumulador += c;
                }
            }
        }
        
        return emailsList;
    }
    public List<String> dameArraydeMailsCC()
    {
        List<String> emailsList = dameArraydeMails();
        List<String> emailsListReal = new ArrayList<String>();
        
        if(emailsList != null)
        {
            if(emailsList.size() > 1)
            {
                emailsListReal = emailsList.subList(1, emailsList.size());
            }
        }
        
        return emailsListReal;
    }
    public String getArrayEmailsBonito()
    {
        String salida = "";
        List<String> arrEmails = dameArraydeMails();
        
        for(String emailLoop : arrEmails)
        {
            salida += "" + emailLoop + "<br>";
        }
        
        return salida;
    }
}