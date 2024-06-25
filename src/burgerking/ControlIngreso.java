/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package burgerking;

import java.io.IOException;

/**
 * 
 * @author Nicolas Guinzio & Ariel Risoluto.
 */
public class ControlIngreso {

    Sistema sistema = new Sistema();
    
    private String usuario;
    private String contraseña;
    private boolean validar;

    public void ingresar() {

        try {
            sistema = sistema.deSerializar("base_empleados.txt");
            System.out.println("Bienvenido al sistema de BURGER KING");
            validar = EntradaSalida.siNo("Para ingresar al sistma -si- para salir -no- ");

        } catch (Exception ex) {
            EntradaSalida.mostrarTexto("\tARRANQUE DE SISTEMA\n\n");
            do {
                usuario = EntradaSalida.leerString("ingrese un administrador\nUsuario:");
                contraseña = EntradaSalida.leerString("Contraseña:"); 
            }while (sistema.validarIngreso(usuario, contraseña));
            
            try {
                        sistema.getEmpleado().add(new Administrador(usuario, contraseña));
                        sistema.serializar("base_empleados.txt");
                        EntradaSalida.mostrarTexto("El administrador fue ingresado correctamente por favor reincie el sistema");
                    } catch (IOException e) {
                    }
        }

        while (validar) {
            usuario = EntradaSalida.leerString("Usuraio: ");
            contraseña = EntradaSalida.leerString("Contraseña: ");

            if(!sistema.validarIngreso(usuario, contraseña)){
                Persona p = sistema.buscarUsuario(usuario+":"+contraseña);
                if(p == null){
                    EntradaSalida.mostrarTexto("\nUsuario NO encontrado\n");
                }else{
                    validar = p.trabajar(sistema);
                }
            }else{
                EntradaSalida.mostrarTexto("\n***usuario no encontrado***\n");
            }
        }
    }
}
