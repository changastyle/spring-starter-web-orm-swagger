package com.vd.emkt.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import javax.persistence.*;


@Entity @Table(name = "operadores")
@Data
@Builder
@AllArgsConstructor
public class Operador implements Comparable<Operador>
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String apellido;
    @JsonIgnore
    private String dni;
    private String email;
    @JsonIgnore
    private String emailsCopia;
    @JsonIgnore
    private String alias;
    @JsonIgnore
    private String htmlFirma;
    @JsonIgnore
    private String password;
    private String img;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "instalacion") @JsonIgnore
    private List<Grupo> gruposList;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "instalacion") @JsonIgnore
    private List<Persona> personasList;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "instalacion") @JsonIgnore
    private List<Plantilla> plantillasList;

    private boolean active;


    //CONTRUCTOR VACIO:
    public Operador()
    {
        gruposList = new ArrayList<>();
        personasList = new ArrayList<>();
    }


    public void setGruposList( List<Grupo> gruposList )
    {
        this.gruposList = gruposList;
        for(Grupo grupoLoop : gruposList)
        {
            grupoLoop.setInstalacion(this);
        }
    }

    public boolean addGrupo(Grupo grupo)
    {
        boolean agregue = false;

        grupo.setInstalacion(this);
        this.gruposList.add(grupo);

        return agregue;
    }
    public void setPersonasList( List<Persona> personasList )
    {
        this.personasList = personasList;
        for(Persona personaLoop : personasList)
        {
            personaLoop.setInstalacion(this);
        }
    }
    public boolean addPersona(Persona persona)
    {
        boolean agregue = false;

        persona.setInstalacion(this);
        this.personasList.add(persona);

        return agregue;
    }


    //@Override
    public String toString()
    {
        String str = "{";
        str += "id:" + id + ", ";
        str += "nombre:" + nombre + ", ";
        str += "apellido:" + apellido + ", ";
        str += "dni:" + dni + ", ";
        str += "email:" + email + ", ";
        str += "alias:" + alias + ", ";
        str += "img:" + img + ", ";

//        if( gruposList != null)
//        {
//            str += "gruposList:" + gruposList.size() + ", ";
//        }
//
//        if( personasList != null)
//        {
//            str += "personasList:" + personasList.size() + ", ";
//        }

        str += "}";

        return str;
    }




    //DYN:


    public int compareTo(Operador otro)
    {
        return 1;
    }

    public String toJson()
    {
        String json = "{";
        json += "\"id\":" + id + ",";
        json += "\"nombre\":\"" + nombre + "\",";
        json += "\"apellido\":\"" + apellido + "\",";
//        json += "\"dni\":\"" + dni + "\",";
        json += "\"email\":\"" + email + "\",";
        json += "\"img\":\"" + img + "\"";
        json += "}";
        return json;
    }
    public String toJson2()
    {
        return new Gson().toJson(this);
    }


    public List<String> dameArraydeMailsCOPIA()
    {
        List<String> emailsList = new ArrayList<>();
        String emailPrincipal = "";

        if(emailsCopia != null)
        {
            String acumulador = "";
            for(int i = 0 ; i < emailsCopia.length() ; i ++)
            {
                char c = emailsCopia.charAt(i);

                if(c == ';' || c == ',' || (i == emailsCopia.length() - 1))
                {
                    if(i == emailsCopia.length()-1)
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
}
