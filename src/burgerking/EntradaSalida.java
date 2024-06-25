/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package burgerking;

import java.util.Scanner;

/**
 *
 * @author Nicolas Guinzio & Ariel Risoluto.
 */
public class EntradaSalida {

    public static String leerString(String texto) {

        Scanner scan = new Scanner(System.in);
        System.out.println(texto);
        String lectura = scan.nextLine();
        lectura = lectura.toLowerCase();

        return lectura;
    }
    
    public static int leerNro(String texto) {

        Scanner scan = new Scanner(System.in);
        System.out.println(texto);
        int nro = scan.nextInt();

        return nro;
    }

    public static boolean siNo(String texto) {
        String i = leerString(texto);
        while (!i.equals("si") && !i.equals("no")) {
            i = leerString("***Por favor ingreses solo si o no***");
        }
        return i.equals("si");
    }

    public static void mostrarTexto(String texto) {
        System.out.println(texto);
    }
    
    public static void mostrarTexto(int texto) {
        System.out.println(texto);
    }
    
    public static void mostrarTexto(double texto) {
        System.out.println(texto);
    }
    
    public static int menuAlta() {

        return EntradaSalida.leerNro(
                "\nALTA DE NUEVO USUARIO\n"
                + "1 - Ingresar Vendedor\n"
                + "2 - Ingresar Cocinero\n"
                + "3 - Ingresar Gerente\n"
                + "4 - Ingresar Inpector\n"
                + "5 - Finalizar Carga");
    }

    public static int menuGerentes() {

        return EntradaSalida.leerNro(
                "\tSISTEMA PARA GERENETES\n"
                + "1 - lista de VENDEDORES\n"
                + "2 - lista de COCINEROS\n"
                + "3 - Cambiar rol de usuario\n"
                + "4 - Dar de alta un Usuario\n"
                + "0 - Salir");
    }
    
    public static int menuCombo(){
        return EntradaSalida.leerNro(
                "\tCOMBOS DISPONIBLES\n"
                + "1 - SIMPLE\n"
                + "2 - DOBLE\n"
                + "3 - TRIPLE\n"
                + "0 - Salir");
    }
    
    public static int menuTamanio(String texto){
        mostrarTexto(texto);
        return EntradaSalida.leerNro(
                "\tCOMBOS DISPONIBLES\n"
                + "1 - REGULAR\n"
                + "2 - MEDIANO\n"
                + "3 - GRANDE\n"        
                + "0 - Salir");
    }
    
    public static void ticket(Combos comanda){
    
        mostrarTexto("====TICKET===="
                    +"\nCOMBO: "+ comanda.getTipoCombo()
                    +"\nBEBIDA Y PAPAS: "+comanda.getBebidaYpapas()
                    +"\nESTADO: "+comanda.getEstado());
        mostrarTexto("====PRECIO====");
        mostrarTexto(comanda.getPrecio());
        mostrarTexto("==NRO.PEDIDO==");
        mostrarTexto(comanda.getNroPedido());
        mostrarTexto("==============");

    }
    
    public static int menuInspector() {

        return EntradaSalida.leerNro(
                "\tSISTEMA PARA INSPECTORES\n"
                + "1 - Consultar lista de TODOS LOS EMPLEADOS\n"
                + "2 - Consultar lista de PEDIDOS GENERADOS\n"
                + "3 - Consultar lista de PEDIDOS LISTO PARA SER ENTREGADOS\n"
                + "4 - Consultar lista de PEDIDOS CANCELADOS\n"
                + "5 - Consultar el TOTAL DE LO RECAUDADO\n"          
                + "0 - Salir");
    }

}
