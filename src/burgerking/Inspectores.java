package burgerking;

/**
 *
 * @author Nicolas Guinzio & Ariel Risoluto.
 */
public class Inspectores extends Persona {

    public Inspectores(String user, String password) {
        setUser(user);
        setPassword(password);
    }

    @Override
    public boolean trabajar(Sistema sistema) {
        EntradaSalida.mostrarTexto("Bienvenido Inspector: " + getUser() + "\n");

        int op;

        if (EntradaSalida.siNo("Desea realizar alguna consulta? si/no")) {

            while ((op = EntradaSalida.menuInspector()) != 0) {
                switch (op) {

                    case 1:
                        EntradaSalida.mostrarTexto("\tlista ADMINITRADORES\n");
                        sistema.listaPorRoles("burgerking.Administrador");
                        EntradaSalida.mostrarTexto("\tlista GERENTES\n");
                        sistema.listaPorRoles("burgerking.Gerentes");
                        EntradaSalida.mostrarTexto("\tlista COCINEROS\n");
                        sistema.listaPorRoles("burgerking.Cocineros");
                        EntradaSalida.mostrarTexto("\tlista VENDEDORES\n");
                        sistema.listaPorRoles("burgerking.Vendedor");
                        EntradaSalida.mostrarTexto("\tlista INSPECTORES\n");
                        sistema.listaPorRoles("burgerking.Inspectores");
                        break;
                    case 2:
                        EntradaSalida.mostrarTexto("TOTAL DE PEDIDOS GENERADOS: " + (sistema.getCombo().size()));
                        if (EntradaSalida.siNo("Desea ver cada uno de los TICKETS? si/no")) {
                            for (int i = 0; i < sistema.getCombo().size(); i++) {
                                EntradaSalida.ticket(sistema.getCombo().get(i));
                            }
                        }
                        break;
                    case 3:
                        int entregados = 0;
                        for (int i = 0; i < sistema.getCombo().size(); i++) {
                            if (sistema.getCombo().get(i).getEstado().equals("LISTO")) {
                                entregados++;
                            }
                        }
                        EntradaSalida.mostrarTexto("TOTAL DE PEDIDOS LISTOS PARA ENTREGA: " + entregados);
                        if (EntradaSalida.siNo("Desea ver cada uno de los TICKETS? si/no")) {
                            for (int i = 0; i < sistema.getCombo().size(); i++) {
                                if (sistema.getCombo().get(i).getEstado().equals("LISTO")) {
                                    EntradaSalida.ticket(sistema.getCombo().get(i));
                                }
                            }
                        }
                        break;
                    case 4:
                        int cancelados = 0;
                        for (int i = 0; i < sistema.getCombo().size(); i++) {
                            if (sistema.getCombo().get(i).getEstado().equals("CANCELADO")) {
                                cancelados++;
                            }
                        }
                        EntradaSalida.mostrarTexto("TOTAL DE PEDIDOS CANCELADOS: " + cancelados);
                        if (EntradaSalida.siNo("Desea ver cada uno de los TICKETS? si/no")) {
                            for (int i = 0; i < sistema.getCombo().size(); i++) {
                                if (sistema.getCombo().get(i).getEstado().equals("CANCELADO")) {
                                    EntradaSalida.ticket(sistema.getCombo().get(i));
                                }
                            }
                        }
                        break;
                    case 5:
                        double recaudado = 0;
                        for (int i = 0; i < sistema.getCombo().size(); i++) {
                            recaudado += sistema.getCombo().get(i).getPrecio();
                        }
                        EntradaSalida.mostrarTexto("TOTAL DE LO RECAUDADO DE LAS VENTAS EFECTUADAS: " + recaudado);
                        break;
                }
            }
        }
        return false;
    }

}
