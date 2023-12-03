package modelo;

import java.io.Serializable;

public class Inventario implements Serializable{
    
    private int codigo;
    private String nombre;
    private float precio;
    private int existencia;
    
    public Inventario(int codigo, String nombre, float precio, int existencia){
        this.codigo=codigo;
        this.nombre=nombre;
        this.precio=precio;
        this.existencia=existencia;
    }
    
    //  SETTERS
    public void setCodigo(int codigo){
        this.codigo=codigo;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setPrecio(float precio){
        this.precio=precio;
    }
    public void setExistencia(int existencia){
        this.existencia=existencia;
    }
    
    //  GETTERS
    public int getCodigo(){
        return codigo;
    }
    public String getNombre(){
        return nombre;
    }
    public float getPrecio(){
        return precio;
    }
    public int getExistencia(){
        return existencia;
    }

}
