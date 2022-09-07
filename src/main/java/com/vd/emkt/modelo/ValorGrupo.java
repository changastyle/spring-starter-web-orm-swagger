package com.vd.emkt.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity @Table(name = "valorgrupo")
public class ValorGrupo 
{
    //ATRIBUTOS:
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombreValor;
    private String valor;
    private String tipoValor;
    
    public ValorGrupo()
    {
    }

    public ValorGrupo(String nombreValor, String valor, String tipoValor) 
    {
        this.nombreValor = nombreValor;
        this.valor = valor;
        this.tipoValor = tipoValor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreValor() {
        return nombreValor;
    }

    public void setNombreValor(String nombreValor) {
        this.nombreValor = nombreValor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipoValor() {
        return tipoValor;
    }

    public void setTipoValor(String tipoValor) {
        this.tipoValor = tipoValor;
    }

    
}
