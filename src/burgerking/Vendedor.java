package burgerking;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Nicolas Guinzio & Ariel Risoluto.
 */
public class Vendedor extends Persona{

    public Vendedor(String user, String password) {
        setUser(user);
        setPassword(password);
    }

    @Override
    public boolean trabajar(Sistema sistema) {
        SistemaPedidos sp = new SistemaPedidos();
        EntradaSalida.mostrarTexto("Bienvenido Vendedor: "+getUser()+"\n");
        sp.tomarPedido(sistema);
        return false;
    }

}
