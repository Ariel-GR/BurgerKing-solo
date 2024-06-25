/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package burgerking;

/**
 * 
 * @author Nicolas Guinzio & Ariel Risoluto.
 */
public class Administrador extends Persona {

    public Administrador(String user, String password) {
        setUser(user);
        setPassword(password);
    }
    
    @Override
    public boolean trabajar(Sistema sistema) {
        EntradaSalida.mostrarTexto("Bienvenido Administrador: "+getUser()+"\n");
        
        return sistema.altaUsuario();
    }
}
