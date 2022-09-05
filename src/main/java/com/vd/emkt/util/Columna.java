package com.vd.emkt.util;

import java.util.ArrayList;
import java.util.List;

public class Columna
{
    private List<String> arrEncabezados;
    private List<String> arrValores;

    public Columna()
    {
        arrEncabezados = new ArrayList<>();
        arrValores = new ArrayList<>();
    }

    public Columna(List<String> arrEncabezados, List<String> arrValores)
    {
        this.arrEncabezados = arrEncabezados;
        this.arrValores = arrValores;
    }

    public List<String> getArrEncabezados()
    {
        return arrEncabezados;
    }

    public void setArrEncabezados(List<String> arrEncabezados)
    {
        this.arrEncabezados = arrEncabezados;
    }

    public List<String> getArrValores()
    {
        return arrValores;
    }

    public void setArrValores(List<String> arrValores)
    {
        this.arrValores = arrValores;
    }

    @Override
    public String toString()
    {
        return "Columna{" + "arrEncabezados=" + arrEncabezados + ", arrValores=" + arrValores + '}';
    }
    
    


    
}
