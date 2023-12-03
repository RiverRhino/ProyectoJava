package controlador;

public class validacionNombre{
    public static void validar(String nombre) throws exNombre{
        if(nombre.matches(".*\\d.*")){
            throw new exNombre(nombre);
        }
    }
    
    public static void validarEspacioBlanco(String nombre) throws exNombreBlanco{
        if(nombre.isBlank()){
            throw new exNombreBlanco(nombre);
        }
    }
}
