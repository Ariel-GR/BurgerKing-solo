/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package burgerking;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicolas Guinzio & Ariel Risoluto.
 */
public class Sistema implements Serializable {

    private ArrayList<Persona> empleado;
    private ArrayList<Combos> combo;

    

    public Sistema() {
        empleado = new ArrayList<>();
        combo = new ArrayList<>();
    }

    public ArrayList<Persona> getEmpleado() {
        return empleado;
    }

    public void setEmpleado(ArrayList<Persona> empleado) {
        this.empleado = empleado;
    }
    
    public ArrayList<Combos> getCombo() {
        return combo;
    }

    public void setCombo(ArrayList<Combos> combo) {
        this.combo = combo;
    }

    public Sistema deSerializar(String a) throws IOException, ClassNotFoundException {
        FileInputStream f = new FileInputStream(a);
        ObjectInputStream o = new ObjectInputStream(f);
        Sistema s = (Sistema) o.readObject();
        o.close();
        f.close();
        return s;
    }

    public void serializar(String a) throws IOException {
        FileOutputStream f = new FileOutputStream(a);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();
    }

    public Persona buscarUsuario(String credenciales) {

        for (Persona p : empleado) {
            if (p.encontrarCredenciales(credenciales)) {
                EntradaSalida.mostrarTexto("\n---Usuario Encontrado---\n");
                return p;
            }
        }
        return null;
    }

    public static boolean validarIngreso(String user, String pass) {
        boolean validar = (user.equals("") || pass.equals(""));

        if (validar) {
            EntradaSalida.mostrarTexto("\n***el usuario y/o la contraseña no pueden ser nulos***\n"
                    + "Por favor ingrese nuevamente\n");
        }

        return validar;
    }

    public void listaPorRoles(String referencia) {

        for (int i = 0; i < empleado.size(); i++) {

            try {
                if (empleado.get(i).getClass() == Class.forName(referencia)) {
                    EntradaSalida.mostrarTexto("\t\t" + empleado.get(i).getUser());
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EntradaSalida.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        EntradaSalida.mostrarTexto("\n");
    }

    public String buscarPorRol(String usuario) throws ClassNotFoundException {

        String rol = null;
        
        for (int i = 0; i < empleado.size(); i++) {

            if (empleado.get(i).getUser().equals(usuario)) {
                if (empleado.get(i).getClass() == Class.forName("burgerking.Administrador")) {
                    rol = "Administrador";
                } else if (empleado.get(i).getClass() == Class.forName("burgerking.Cocineros")) {
                    rol = "Cocinero";
                } else if (empleado.get(i).getClass() == Class.forName("burgerking.Vendedor")) {
                    rol = "Vendedor";
                } else if (empleado.get(i).getClass() == Class.forName("burgerking.Gerentes")) {
                    rol = "Gerente";
                } else if (empleado.get(i).getClass() == Class.forName("burgerking.Inspectores")) {
                    rol = "Inspector";
                }
            }
        }
        return rol;
    }

    public void modificarRol(String user, String rol) {

        String pass = null;
        int index = 0;

        for (int i = 0; i < empleado.size(); i++) {
            if (empleado.get(i).getUser().equals(user)) {
                pass = empleado.get(i).getPassword();
                index = i;
            }
        }

        switch (rol) {
            case "Vendedor":
                if (EntradaSalida.siNo("Roles disponibles: \n\t+Cocinero\nPara efectuar el cambio ingrese -si- caso contrario -no-:")) {
                    getEmpleado().set(index, new Cocineros(user, pass));
                    try {
                        serializar("base_empleados.txt");
                    } catch (IOException e) {
                        EntradaSalida.mostrarTexto("error grabar archivo");
                    }
                    EntradaSalida.mostrarTexto("\n---MODIFICACION DE ROL REALIZADA---");
                }
                break;
            case "Cocinero":
                if (EntradaSalida.siNo("Roles disponibles: \n\t+Vendedor\nPara efectuar el cambio ingrese -si- caso contrario -no-:")) {
                    getEmpleado().set(index, new Vendedor(user, pass));
                    try {
                        serializar("base_empleados.txt");
                    } catch (IOException e) {
                        EntradaSalida.mostrarTexto("error grabar archivo");
                    }
                    EntradaSalida.mostrarTexto("\n---MODIFICACION DE ROL REALIZADA---");
                }
                break;
        }
    }

    public boolean altaUsuario() {
        int opcion;
        boolean seguir = false;

        while ((opcion = EntradaSalida.menuAlta())!= 5){
            String usuarioNuevo = EntradaSalida.leerString("Usuario:");
            String contraseñaNueva = EntradaSalida.leerString("Contraseña:");
            if (!validarIngreso(usuarioNuevo, contraseñaNueva)) {
                Persona p = buscarUsuario(usuarioNuevo + ":" + contraseñaNueva);
                if (p == null) {
                    switch (opcion) {
                        case 1:
                            getEmpleado().add(new Vendedor(usuarioNuevo, contraseñaNueva));
                            EntradaSalida.mostrarTexto("---ingreso correcto del VENDEDOR: " + usuarioNuevo + "---\n");
                            break;
                        case 2:
                            getEmpleado().add(new Cocineros(usuarioNuevo, contraseñaNueva));
                            EntradaSalida.mostrarTexto("---ingreso correcto del COCINERO: " + usuarioNuevo + "---\n");
                            break;
                        case 3:
                            getEmpleado().add(new Gerentes(usuarioNuevo, contraseñaNueva));
                            EntradaSalida.mostrarTexto("---ingreso correcto del GERENTE: " + usuarioNuevo + "---\n");
                            break;
                        case 4:
                            getEmpleado().add(new Inspectores(usuarioNuevo, contraseñaNueva));
                            EntradaSalida.mostrarTexto("---ingreso correcto del INSPECTOR: " + usuarioNuevo + "---\n");
                            break;
                    }
                    try {
                        serializar("base_empleados.txt");
                    } catch (Exception e) {
                        EntradaSalida.mostrarTexto("error grabar archivo");
                    }

                } else {
                    EntradaSalida.mostrarTexto("\n***No se puede dar de alta, el USUARIO: " + usuarioNuevo + " ya fue ingresado***\n");
                }
            }
        }

        return seguir;
    }
     
}
