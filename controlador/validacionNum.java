package controlador;

public class validacionNum {
    public static void validarNumNegativo(int num) throws exNum{
        if(num < 0){
            throw new exNum(num);
        }
    }
}
