/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package burgerking;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicolas Guinzio & Ariel Risoluto.
 */
public class SistemaPedidos implements Serializable {

    public void tomarPedido(Sistema sistema) {

        int op;

        while ((op = EntradaSalida.menuCombo()) != 0) {

            switch (op) {
                case 1:
                    crearPedido("SIMPLE", sistema);
                    break;
                case 2:
                    crearPedido("DOBLE", sistema);
                    break;
                case 3:
                    crearPedido("TRIPLE", sistema);
                    break;
            }
            if (op > 0 && op < 4) {
                Combos ultimaComanda = sistema.getCombo().getLast();
                EntradaSalida.ticket(ultimaComanda);
            }
        }
    }

    private void crearPedido(String tipoDeCombo, Sistema sistema) {
        int numPedido = sistema.getCombo().size() + 1;
        String h = tipoDeCombo;
        String tamanio = "REGULAR";
        String e = "preparando";
        double precio = 1.00;

        EntradaSalida.mostrarTexto("\tTipo de combo " + tipoDeCombo);
        switch (EntradaSalida.menuTamanio("\nTAMANIO DEL COMBO\n")) {
            case 2:
                precio = 2.00;
                tamanio = "MEDIANO";
                break;
            case 3:
                precio = 3.00;
                tamanio = "GRANDE";
                break;
        }

        sistema.getCombo().add(new Combos(numPedido, h, tamanio, precio, e));
        try {
            sistema.serializar("base_empleados.txt");

        } catch (IOException ex) {
            Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void estadoPedidos(Sistema sistema) {

        EntradaSalida.mostrarTexto("\t\tCOMANDAS PARA COCINAR\n");

        consultarEstado("preparando", sistema);

        while (EntradaSalida.siNo("para ACTULIZAR el estado de una comanda ingrese si/no")) {
            int nro = EntradaSalida.leerNro("Ingrese el NRO DE COMANDA para actualizar su estado: ");
            if (nro < sistema.getCombo().size() && nro >= 0) {
                if (EntradaSalida.siNo("Desa ENTREGAR la comanda nro " + nro + "? si/no")) {
                    sistema.getCombo().get(nro).setEstado("LISTO");
                } else if (EntradaSalida.siNo("Desa CANCELAR la comanda nro " + nro + "? si/no")) {
                    sistema.getCombo().get(nro).setEstado("CANCELADO");
                } else {
                    EntradaSalida.mostrarTexto("\t\t***no se realizaron cambios***");
                }
            } else {
                EntradaSalida.mostrarTexto("no se encontro el nro de comanda ingresado");
            }
            try {
                sistema.serializar("base_empleados.txt");

            } catch (IOException ex) {
                Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        EntradaSalida.mostrarTexto("\t\tCOMANDAS LISTA PARA ENTRAGAR\n");
        consultarEstado("LISTO", sistema);

        EntradaSalida.mostrarTexto("\t\tCOMANDAS CANCELADAS\n");
        consultarEstado("CANCELADO", sistema);

    }

    private void consultarEstado(String estado, Sistema sistema) {
        for (int i = 0; i < sistema.getCombo().size(); i++) {

            if (sistema.getCombo().get(i).getEstado().equals(estado)) {
                EntradaSalida.mostrarTexto("\tCOMANDA: " + (i) + "\n");
                EntradaSalida.ticket(sistema.getCombo().get(i));
                EntradaSalida.mostrarTexto("\n");
            }
        }
    }

}
