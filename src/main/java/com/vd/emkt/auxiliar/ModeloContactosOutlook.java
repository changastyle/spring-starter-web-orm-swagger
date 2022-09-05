package com.vd.emkt.auxiliar;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class ModeloContactosOutlook
{
    public String nombre;
    public String apellido;
    public String email;
    public String tel;
    public String alias;
    

    public ModeloContactosOutlook()
    {
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getApellido()
    {
        return apellido;
    }

    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getTel()
    {
        return tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public String getAlias()
    {
        return alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    @Override
    public String toString()
    {
        return "ModeloContactosOutlook{" + "nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", tel=" + tel + ", alias=" + alias + '}';
    }
    
    

    
    
    public String toJSON()
    {
        return new Gson().toJson(this);
    }
        
    
    //DYN:

    public List<String> dameEmailsDinamicos()
    {
        List<String> arrEmails = new ArrayList<String>();
        
        String strAcumulador = "";
        for(int i = 0 ; i < email.length() ;i ++)
        {
            char actual = email.charAt(i);
            
            int largo = email.length();
            if(actual == ';' || (i == largo-1) )
            {
                if((i == largo-1))
                {
                    strAcumulador += actual;
                }
                arrEmails.add(strAcumulador);
                strAcumulador = "";
            }
            else
            {
                strAcumulador += actual;
            }
            
        }
        
        return arrEmails;
    }
    
    
    
}
