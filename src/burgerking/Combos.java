/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package burgerking;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Nicolas Guinzio & Ariel Risoluto.
 */
public class Combos implements Serializable{

    private int nroPedido;
    private LocalDate fecha;
    private String tipoCombo;
    private String bebidaYpapas;
    private Double precio;
    private String estado;

    public Combos(int nroPedido, String tipoCombo, String bebidaYpapas, Double precio, String estado) {
        this.fecha = LocalDate.now();
        this.nroPedido = nroPedido;
        this.tipoCombo = tipoCombo;
        this.bebidaYpapas = bebidaYpapas;
        this.precio = precio;
        this.estado = estado;
    }

    public LocalDate getFecha() {
        return fecha;
    }


    
    public int getNroPedido() {
        return nroPedido;
    }

    public String getTipoCombo() {
        return tipoCombo;
    }

    public String getBebidaYpapas() {
        return bebidaYpapas;
    }

    public Double getPrecio() {
        return precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setNroPedido(int nroPedido) {
        this.nroPedido = nroPedido;
    }

    public void setTipoCombo(String tipoCombo) {
        this.tipoCombo = tipoCombo;
    }

    public void setBebidaYpapas(String bebidaYpapas) {
        this.bebidaYpapas = bebidaYpapas;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
