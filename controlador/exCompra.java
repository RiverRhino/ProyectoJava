
package controlador;

public class exCompra extends Exception {
    int inventario;
    
    public exCompra(int inventario){
        this.inventario=inventario;
    }
}
