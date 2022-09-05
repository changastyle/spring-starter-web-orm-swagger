package com.vd.emkt.auxiliar;

import javax.persistence.Transient;

public class ClaveValor 
{
    @Transient
    private int idRequerimientoTransient;
    private String clave;
    private String valor;

    public ClaveValor() {
    }

    public ClaveValor(int idRequerimientoTransient, String clave, String valor) {
        this.idRequerimientoTransient = idRequerimientoTransient;
        this.clave = clave;
        this.valor = valor;
    }

    

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getIdRequerimientoTransient() {
        return idRequerimientoTransient;
    }

    public void setIdRequerimientoTransient(int idRequerimientoTransient) {
        this.idRequerimientoTransient = idRequerimientoTransient;
    }
    
    
    @Override
    public String toString() {
        return "ClaveValor{" + "clave=" + clave + ", valor=" + valor + '}';
    }
    
    
}
