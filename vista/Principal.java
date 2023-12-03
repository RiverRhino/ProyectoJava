package vista;

import controlador.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import modelo.Inventario;
import static java.lang.Integer.parseInt;

public class Principal extends JFrame implements ActionListener {

    ArrayList<Inventario> arrayInventario = new ArrayList<>();
    mtArchivo<Inventario> archivoInventario = new mtArchivo("Inventario.ser");
    Inventario inv;
    mtArchivo mtArchivo;

//  PANELES Y BOTONES PRINCIPALES
    JPanel p0, pADMI, pCLIE;
    JButton admi, clie, btnArchivo;
    JTextField txtArchivo;
    JLabel nomArchivo;

    //  PANELES Y BOTONES PARA ADMINISTRADOR 
    JPanel panP, panA, panM, panE;
    JButton cerrarA, bA, bM, bE;

    //  COMPONENTES PARA "AGREGAR"
    JLabel tituloA, codigoA, nom, pre, can;
    JTextField ccod, cnom, cpre, ccan;
    JButton aceptar;

    //  COMPONENTES PARA "MODIFICAR"
    JLabel tituloM, codigoM, precioM, cantidadM;
    JTextField cCodigoM, cPrecioM, cCantidadM;
    JButton modificar;

    //  COMPONENTES PARA "ELIMINAR"
    JLabel tituloE, codigoE;
    JTextField cCodigo;
    JButton eliminar;

    JFrame frame;
//  COMPONENTES PARA EL PANEL DEL CLIENTE
    JTextArea area;
    JScrollPane scroll;
    JButton mostrar, comprar, cerrarC;
    JLabel codi, cant;
    JTextField cCodi, cCant;

    int codigoBarras = 0;
    float precio = 0;
    String nombre = "";
    int existencia = 0;

    public Principal() {
        setTitle("Proyecto Final");
        setSize(900, 600);
        setLayout(null);
        this.setDefaultCloseOperation(3);
        inicio();
    }

    public void inicio() {
        p0 = new JPanel();
        p0.setLayout(null);
        p0.setBackground(Color.white);
        p0.setBounds(0, 0, 900, 600);
        add(p0);

        pADMI = new JPanel();
        pADMI.setLayout(null);
        pADMI.setBounds(0, 0, 900, 600);
        pADMI.setVisible(false);
        add(pADMI);

        pCLIE = new JPanel();
        pCLIE.setLayout(null);
        pCLIE.setBackground(Color.GREEN);
        pCLIE.setBounds(0, 0, 900, 600);
        pCLIE.setVisible(false);
        add(pCLIE);

        admi = new JButton("ADMINISTRADOR");
        admi.setBounds(350, 100, 150, 50);
        p0.add(admi);
        admi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p0.setVisible(false);
                pCLIE.setVisible(false);
                pADMI.setVisible(true);
            }
        });

        panP = new JPanel();
        panP.setLayout(null);
        panP.setBackground(Color.green);
        panP.setBounds(0, 0, 179, 600);
        pADMI.add(panP);

        bA = new JButton("AGREGAR");
        bA.setBackground(Color.WHITE);
        bA.setBounds(40, 130, 100, 60);
        panP.add(bA);
        bA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panM.setVisible(false);
                panE.setVisible(false);
                panA.setVisible(true);
            }
        });

        panA = new JPanel();
        panA.setLayout(null);
        panA.setBackground(Color.white);
        panA.setBounds(180, 0, 705, 600);
        panA.setVisible(false);
        pADMI.add(panA);

        tituloA = new JLabel("Agregar Articulos");
        tituloA.setBounds(265, 10, 170, 40);
        panA.add(tituloA);

        codigoA = new JLabel("Codigo del Articulo:");
        codigoA.setBounds(18, 110, 150, 60);
        panA.add(codigoA);
        nom = new JLabel("Nombre del Articulo:");
        nom.setBounds(18, 170, 150, 60);
        panA.add(nom);
        pre = new JLabel("Precio del Articulo:");
        pre.setBounds(18, 220, 150, 60);
        panA.add(pre);
        can = new JLabel("Cantidad del Articulo:");
        can.setBounds(18, 270, 150, 60);
        panA.add(can);

        ccod = new JTextField();
        ccod.setBounds(180, 120, 150, 35);
        panA.add(ccod);

        cnom = new JTextField();
        cnom.setBounds(180, 180, 150, 35);
        panA.add(cnom);
        cpre = new JTextField();
        cpre.setBounds(180, 235, 150, 35);
        panA.add(cpre);
        ccan = new JTextField();
        ccan.setBounds(180, 290, 150, 35);
        panA.add(ccan);

        aceptar = new JButton("Guardar");
        aceptar.setBounds(340, 350, 80, 30);
        panA.add(aceptar);
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    codigoBarras = parseInt(ccod.getText());
                    validacionNum.validarNumNegativo(codigoBarras);
                    for (int i = 0; i < arrayInventario.size(); i++) {
                        if (arrayInventario.get(i).getCodigo() == (codigoBarras)) {
                            JOptionPane.showMessageDialog(null, "Ingrese otro codigo de barra diferente", "Notificacion", 3);
                            return;
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: Ingresa caracteres numericos para el codigo de barras", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (exNum ex) {
                    JOptionPane.showMessageDialog(frame, "Se ingreso un numero negativo en el codigo de barras", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    nombre = cnom.getText();
                    validacionNombre.validar(nombre);
                    validacionNombre.validarEspacioBlanco(nombre);
                } catch (exNombre ex) {
                    JOptionPane.showMessageDialog(frame, "Error: Se ingreso un numero para el nombre del producto", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (exNombreBlanco ex) {
                    JOptionPane.showMessageDialog(frame, "Error: Se dejo el espacio en blanco en el nombre", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    precio = Float.parseFloat(cpre.getText());
                    validacionNumNegativofloat.validarNumnegativo(precio);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: Ingresa caracteres numericos para el precio", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }catch(exNumfloat ex){
                    JOptionPane.showMessageDialog(frame, "Se ingreso un numero negativo en el precio", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    existencia = parseInt(ccan.getText());
                    validacionNum.validarNumNegativo(existencia);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: Ingresa caracteres numericos para el inventario", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (exNum ex) {
                    JOptionPane.showMessageDialog(frame, "Se ingreso un numero negativo en el inventario", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                inv = new Inventario(codigoBarras, nombre, precio, existencia);
                arrayInventario.add(inv);
                archivoInventario.actualizarR(arrayInventario);
            }

        });

        bM = new JButton("MODIFICAR");
        bM.setBounds(40, 230, 100, 60);
        bM.setBackground(Color.WHITE);
        panP.add(bM);
        bM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panA.setVisible(false);
                panE.setVisible(false);
                panM.setVisible(true);
            }
        });

        panM = new JPanel();
        panM.setLayout(null);
        panM.setBackground(Color.white);
        panM.setBounds(180, 0, 705, 600);
        panM.setVisible(false);
        pADMI.add(panM);

        tituloM = new JLabel("Modificar Articulos");
        tituloM.setBounds(265, 10, 170, 40);
        panM.add(tituloM);

        codigoM = new JLabel("Codigo de Articulo a Modificar:");
        codigoM.setBounds(247, 100, 190, 60);
        panM.add(codigoM);

        precioM = new JLabel("Precio Nuevo:");
        precioM.setBounds(20, 220, 150, 60);
        panM.add(precioM);

        cantidadM = new JLabel("Cantidad Nueva:");
        cantidadM.setBounds(20, 270, 150, 60);
        panM.add(cantidadM);

        cCodigoM = new JTextField();
        cCodigoM.setBounds(260, 150, 150, 35);
        panM.add(cCodigoM);

        cPrecioM = new JTextField();
        cPrecioM.setBounds(130, 230, 150, 35);
        panM.add(cPrecioM);

        cCantidadM = new JTextField();
        cCantidadM.setBounds(130, 280, 150, 35);
        panM.add(cCantidadM);

        modificar = new JButton("Modificar");
        modificar.setBounds(290, 330, 90, 30);
        panM.add(modificar);
        modificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int codigoB = parseInt(cCodigoM.getText());
                boolean cEncontrado = false;
                for (int i = 0; i < arrayInventario.size(); i++) {
                    if (arrayInventario.get(i).getCodigo() == (codigoB)) {
                        float precioN = Float.parseFloat(cPrecioM.getText());
                        int cantidadN = parseInt(cCantidadM.getText());
                        arrayInventario.get(i).setPrecio(precioN);
                        arrayInventario.get(i).setExistencia(cantidadN);
                        archivoInventario.actualizarR(arrayInventario);
                        JOptionPane.showMessageDialog(null, "Modificacion realizada", "Notificacion", 3);
                        cEncontrado = true;
                    }
                }

                if (!cEncontrado) {
                    JOptionPane.showMessageDialog(null, "No se encontro el codigo", "Notificacion", 3);
                }

            }
        });

        bE = new JButton("ELIMINAR");
        bE.setBounds(40, 330, 100, 60);
        bE.setBackground(Color.WHITE);
        panP.add(bE);
        bE.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panA.setVisible(false);
                panM.setVisible(false);
                panE.setVisible(true);
            }
        });

        panE = new JPanel();
        panE.setLayout(null);
        panE.setBackground(Color.white);
        panE.setBounds(180, 0, 705, 600);
        panE.setVisible(false);
        pADMI.add(panE);

        tituloE = new JLabel("Eliminar Articulos");
        tituloE.setBounds(265, 10, 170, 40);
        panE.add(tituloE);

        codigoE = new JLabel("Ingresa el codigo a Eliminar:");
        codigoE.setBounds(250, 100, 190, 60);
        panE.add(codigoE);

        cCodigo = new JTextField();
        cCodigo.setBounds(260, 150, 150, 35);
        panE.add(cCodigo);

        eliminar = new JButton("Eliminar");
        eliminar.setBounds(298, 200, 80, 30);
        panE.add(eliminar);
        eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean encontrado=false;
                try {
                    int codigoB = parseInt(cCodigo.getText());
                    int pos = -1;
                    for (int i = 0; i < arrayInventario.size(); i++) {
                        if (arrayInventario.get(i).getCodigo() == (codigoB)) {
                            pos = i;
                            encontrado=true;
                        } 
                    }
                    if(!encontrado){
                        JOptionPane.showMessageDialog(null, "No se encontro el codigo", "Notificacion", 3);
                    }
                    arrayInventario.remove(pos);
                    archivoInventario.actualizarR(arrayInventario);
                    JOptionPane.showMessageDialog(null, "Eliminacion Exitosa :)", "Eliminar", 1);
               
                } catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "No hay algun dato registrado", "Error", 2);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un caracter entero", "Error", 2);
                }
            }

        });

        cerrarA = new JButton("Cerrar Sesion");
        cerrarA.setBounds(28, 480, 130, 30);
        cerrarA.setBackground(Color.lightGray);
        panP.add(cerrarA);
        cerrarA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pADMI.setVisible(false);
                pCLIE.setVisible(false);
                p0.setVisible(true);
            }
        });

        clie = new JButton("CLIENTE");
        clie.setBounds(350, 200, 150, 50);
        p0.add(clie);
        clie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                p0.setVisible(false);
                pADMI.setVisible(false);
                pCLIE.setVisible(true);
            }
        });

        area = new JTextArea();
        scroll = new JScrollPane();
        scroll.setBounds(1, 20, 460, 525);
        scroll.setViewportView(area);
        pCLIE.add(scroll);

        mostrar = new JButton("Mostrar Articulos");
        mostrar.setBounds(480, 20, 150, 30);
        pCLIE.add(mostrar);
        mostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String leer = "";
                arrayInventario = archivoInventario.leerRegistros();
                for (Inventario inventario : arrayInventario) {
                    leer = leer + "Codigo de barras: " + inventario.getCodigo() + "\n" + "Nombre del producto: " + inventario.getNombre() + "\n" + " Precio: " + inventario.getPrecio() + "\n" + " Inventario: " + inventario.getExistencia() + "\n\n";
                }
                area.setText(leer);
            }
        });

        codi = new JLabel("Codigo del Articulo: ");
        codi.setBounds(480, 100, 170, 40);
        pCLIE.add(codi);

        cCodi = new JTextField();
        cCodi.setBounds(610, 101, 150, 35);
        pCLIE.add(cCodi);

        cant = new JLabel("Cantidad de Articulos: ");
        cant.setBounds(480, 170, 170, 40);
        pCLIE.add(cant);

        cCant = new JTextField();
        cCant.setBounds(610, 171, 150, 35);
        pCLIE.add(cCant);

        comprar = new JButton("Comprar");
        comprar.setBounds(659, 230, 100, 30);
        pCLIE.add(comprar);
        comprar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int codigoBr;
                int cantidad = 0;
                int venta;
                try {
                    codigoBr = parseInt(cCodi.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingresa un codigo de barras valido", "Notificacion", 3);
                    return;
                }
                try {
                    venta = parseInt(cCant.getText());
                    validacionCompra.validarCompra(venta);
                } catch (exCompra ex) {
                    JOptionPane.showMessageDialog(null, "Se ingreso un numero negativo", "Notificacion", 3);
                    return;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingresa un numero entero", "Notificacion", 3);
                    return;
                }
                boolean cEncontrado = false;
                for (int i = 0; i < arrayInventario.size(); i++) {
                    if (arrayInventario.get(i).getCodigo() == (codigoBr)) {
                        cEncontrado = true;
                        int c = arrayInventario.get(i).getExistencia();
                        if (venta > c) {
                            JOptionPane.showMessageDialog(null, "Se ingreso una cantidad mayor al inventario", "Notificacion", 3);
                            return;
                        } else {
                            cantidad = arrayInventario.get(i).getExistencia() - venta;
                            arrayInventario.get(i).setExistencia(cantidad);
                            archivoInventario.actualizarR(arrayInventario);
                        }
                    }
                }

                if (!cEncontrado) {
                    JOptionPane.showMessageDialog(null, "No se encontro el codigo", "Notificacion", 3);
                }
            }
        });
        cerrarC = new JButton("Cerrar Sesion");
        cerrarC.setBounds(740, 510, 130, 30);
        pCLIE.add(cerrarC);
        cerrarC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pADMI.setVisible(false);
                pCLIE.setVisible(false);
                p0.setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public static void main(String[] args) {
        Principal obj = new Principal();
        obj.setVisible(true);
    }
}
