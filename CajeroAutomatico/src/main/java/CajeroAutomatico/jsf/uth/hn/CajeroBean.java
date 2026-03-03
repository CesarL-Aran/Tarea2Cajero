package CajeroAutomatico.jsf.uth.hn;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable; // <--- TE FALTABA ESTE
import java.util.ArrayList;
import java.util.List;

@Named(value = "cajeroBean")
@SessionScoped 
public class CajeroBean implements Serializable {
    
    // 1. Atributos Lista de Clientes
    private List<Cliente> clientes;
    private Cliente usuarioActual; // El cliente que "inicia sesión"
    
    // 2. Variables para capturar datos de los .xhtml
    private String cuentaInput;
    private String pinInput;
    private double montoInput;
    private String mensaje;
    private String cuentaBusqueda;
    private String pinBusqueda;

    public CajeroBean() {
        clientes = new ArrayList<>();
        // Agregamos clientes de prueba
        clientes.add(new Cliente("202401", 1500.0, "1234"));
        clientes.add(new Cliente("202402", 500.0, "0000"));
    }
    
    // metodo para verificar en la lista 
    public String login() {
        for (Cliente c : clientes) {
            if (c.getNumeroCuenta().equals(cuentaBusqueda) && c.getPin().equals(pinBusqueda)) {
                this.usuarioActual = c;
                return "index"; // Esto buscará index.xhtml
            }
        }
        this.mensaje = "Credenciales incorrectas";
        return null; // Se queda en login.xhtml
    }
    
    
    // metodo para cerrar el login
    public String logout() {
        this.usuarioActual = null; // Olvidamos al usuario
        this.cuentaBusqueda = "";
        this.pinBusqueda = "";
        return "login?faces-redirect=true"; // Regresamos al inicio
    }
    
    
    //2. metodo para procesar los Depositos
    public String procesarDeposito() {
        if (usuarioActual != null) {
            if (montoInput > 0) {
                // Aquí nace la magia: sumamos al saldo del objeto cliente
                usuarioActual.setSaldo(usuarioActual.getSaldo() + montoInput);
                mensaje = "Depósito exitoso de L. " + montoInput;
                montoInput = 0; 
                return "saldo"; 
            } else {
                mensaje = "Error: El monto debe ser mayor a cero.";
            }
        }
        return null;
    }
    

    // 3. Lógica de Negocio: Retiro
    public String procesarRetiro() {
        if (usuarioActual != null) {
            if (montoInput > 0 && montoInput <= usuarioActual.getSaldo()) {
                usuarioActual.setSaldo(usuarioActual.getSaldo() - montoInput);
                mensaje = "Retiro exitoso de L. " + montoInput;
                return "saldo"; // Navega a la vista de saldo
            } else {
                mensaje = "Error: Saldo insuficiente o monto inválido.";
            }
        }
        return null;
    }

    // --- GETTERS Y SETTERS 
    public Cliente getUsuarioActual() {
    	return usuarioActual;
    	}
    
    public double getMontoInput() {
    	return montoInput;
    	}
    
    public void setMontoInput(double montoInput) {
    	this.montoInput = montoInput; 
    	}

    public String getMensaje() {
    	return mensaje; 
    	}
    
    public void setMensaje(String mensaje) {
    	this.mensaje = mensaje;
    	}
    
    public String getCuentaBusqueda() {
    	return cuentaBusqueda;
    	}
    
    public void setCuentaBusqueda(String cuentaBusqueda) {
    	this.cuentaBusqueda = cuentaBusqueda; 
    	}
    
    public String getPinBusqueda() {
    	return pinBusqueda;
    	}
    
    public void setPinBusqueda(String pinBusqueda){
    	this.pinBusqueda = pinBusqueda;
    }
}