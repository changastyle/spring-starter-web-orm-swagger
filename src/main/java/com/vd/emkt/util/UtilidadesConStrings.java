package com.vd.emkt.util;

public class UtilidadesConStrings 
{
    public static String dameStringEntre(String cadena, String inicio, String fin)
    {
        boolean paseInicio = false;
        boolean llegueAlFin = false;
        String respuesta = "";
        String strAcumulador = "";
        
        for(int i = 0 ; i < cadena.length() ; i++)
        {
            char caracterActual = cadena.charAt(i);
            
            strAcumulador += "" + caracterActual;
            
            if(!paseInicio && strAcumulador.endsWith(inicio))
            {
                paseInicio = true;
            }
            if(paseInicio && strAcumulador.endsWith(fin))
            {
                llegueAlFin = true;
            }
            
            if(paseInicio && !llegueAlFin)
            {
                respuesta += caracterActual;
            }
        }
        return respuesta;
    }
    public static String dameStringEntre(String cadena, String simbolo, boolean antesDelSimbolo)
    {
        String salida = "";
        
        int posicionSimbolo = cadena.lastIndexOf(simbolo);

        if(posicionSimbolo > 0)
        {
            if(antesDelSimbolo)
            {
                salida = cadena.substring(0,posicionSimbolo);
            }
            else
            {
                salida = cadena.substring(posicionSimbolo + 1 ,cadena.length());
            }
        }
        
        return salida;
    }
    
}