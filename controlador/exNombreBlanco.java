package controlador;

public class exNombreBlanco extends Exception{
    String nombre;
    
    public exNombreBlanco(String nombre){
        this.nombre=nombre;
    }
}
