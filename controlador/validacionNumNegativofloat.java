package controlador;

public class validacionNumNegativofloat {
    public static void validarNumnegativo(float num) throws exNumfloat{
        if(num < 0){
            throw new exNumfloat(num);
        }
    }
}
