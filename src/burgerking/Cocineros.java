package burgerking;

/**
 * 
 * @author Nicolas Guinzio & Ariel Risoluto.
 */
public class Cocineros extends Persona {

    public Cocineros(String user, String password) {
        setUser(user);
        setPassword(password);
    }

    @Override
    public boolean trabajar(Sistema sistema) {
        SistemaPedidos sp = new SistemaPedidos();
        EntradaSalida.mostrarTexto("Bienvenido Cocinero: "+getUser()+"\n");
        
        if(EntradaSalida.siNo("Desea revisar el estado de las comandas? si/no")){
            sp.estadoPedidos(sistema);
        }
        
        
        return false;
    }
    
    
}
