package burgerking;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicolas Guinzio & Ariel Risoluto.
 */
public class Gerentes extends Persona {

    public Gerentes(String user, String password) {
        setUser(user);
        setPassword(password);
    }

    @Override
    public boolean trabajar(Sistema sistema) {
        String solicitud;
        String usuario;
        int opcion;
        EntradaSalida.mostrarTexto("Bienvenido Gerente: "+getUser()+"\n");
        do {
            switch (opcion = EntradaSalida.menuGerentes()) {
                case 1:
                    EntradaSalida.mostrarTexto("\tlista VENDEDORES\n");
                    sistema.listaPorRoles("burgerking.Vendedor");
                    break;
                case 2:
                    EntradaSalida.mostrarTexto("\tlista COCINEROS\n");
                    sistema.listaPorRoles("burgerking.Cocineros");
                    break;
                case 3:
                    solicitud = EntradaSalida.leerString("Ingrese el nombre del usaurio");
                    usuario = solicitud;
                    try {
                        solicitud = sistema.buscarPorRol(solicitud);
                        if (solicitud == null) {
                            EntradaSalida.mostrarTexto("\n***No es posible cambiar el rol del usuario " + usuario + " ya que no existe***\n");
                        } else {
                            if (solicitud.equals("Administrador") || solicitud.equals("Gerente") || solicitud.equals("Inspector")) {
                                EntradaSalida.mostrarTexto("\n***No es posible cambiar el rol del " + usuario + " ya que es un" + solicitud+"***\n");
                            } else {
                                sistema.modificarRol(usuario, solicitud);
                            }
                        }

                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Gerentes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 4:
                    if (!sistema.altaUsuario()) {
                        EntradaSalida.mostrarTexto("\n\tCargando sistema.....");
                    }
                    break;
            }
        } while (opcion != 0);

        return false;
    }
}
