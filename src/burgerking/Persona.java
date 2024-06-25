package burgerking;

/**
 * 
 * @author Nicolas Guinzio & Ariel Risoluto.
 */

import java.io.Serializable;

public abstract class Persona implements Serializable{

    private String user;
    private String password;

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public abstract boolean trabajar(Sistema sistema);
    
    public boolean encontrarCredenciales(String credenciales){
        return credenciales.equals(user+":"+password);
    }
}