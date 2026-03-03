package CajeroAutomatico.jsf.uth.hn;

import java.io.Serializable;

public class Cliente {
    private String numeroCuenta;
    private double saldo;
    private String pin;

    public Cliente(String numeroCuenta, double saldo, String pin) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.pin = pin;
    }

    
    public String getNumeroCuenta() {
    	return numeroCuenta;
    	}
    
    public void setNumeroCuenta(String numeroCuenta) {
    	this.numeroCuenta = numeroCuenta;
    	}
    
    public double getSaldo() {
    	return saldo;
    	}
    
    public void setSaldo(double saldo) {
    	this.saldo = saldo; 
    	}
    
    public String getPin() {
    	return pin;
    	}
    
    public void setPin(String pin) {
    	this.pin = pin; }
}
