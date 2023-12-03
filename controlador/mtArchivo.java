package controlador;

import java.io.*;

import java.util.ArrayList;

public class mtArchivo<A>{
    File archivo;
    String nArchivo;
    
    public mtArchivo(){
        nArchivo="archivo.ser";
    }

    public mtArchivo(String nArchivo){
        this.nArchivo=nArchivo;
    }
    
    public boolean nuevArchivo(){
        boolean resultado = false;
        
        try{
            archivo =new File(nArchivo);
            resultado = archivo.createNewFile();
        }catch(IOException e){
        }
        return resultado;
    }
    
    
    public boolean actualizarR(ArrayList<A>informacion){
        boolean resultado = false;
        FileOutputStream fileOut = null;
        ObjectOutputStream salida = null;
        
        try{
            fileOut = new FileOutputStream(nArchivo);
            salida = new ObjectOutputStream(fileOut);
            salida.writeObject(informacion);
            resultado = true;
        }catch(IOException e){
            
        }finally{
            try{
                if(fileOut != null){
                    fileOut.close();
                    fileOut=null;
                }
                
                if(salida != null){
                    salida.close();
                }
            }catch(IOException ex){
                
            }
        }
        return resultado;
    }
   
    public ArrayList<A> leerRegistros(){
        ArrayList<A> informacion = new ArrayList();
        FileInputStream fileInp = null;
        ObjectInputStream entrada = null;
        
        try{
            fileInp = new FileInputStream(nArchivo);
            entrada = new ObjectInputStream(fileInp);
            informacion=(ArrayList<A>)entrada.readObject();
        }catch(FileNotFoundException e){
            
        }catch(EOFException e){
            
        }catch(IOException e){
            
        }catch(Exception e){
            
        }finally{
            try{
                if(fileInp != null){
                    fileInp.close();
                    fileInp = null;
                }
                
                if(entrada!=null){
                    entrada.close();
                }
            }catch(IOException e){
                
            }
        }
        return informacion;
    }
    
}
