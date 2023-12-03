package controlador;

public class validacionCompra{
    public static void validarCompra(int inventario) throws exCompra{
        if(inventario < 0){
            throw new exCompra(inventario);
        }
    }
    
}
