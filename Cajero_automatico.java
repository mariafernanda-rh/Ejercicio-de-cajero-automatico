package com.mycompany.ejerciciodecajero;
  

import javax.swing.JOptionPane;
import java.util.Random;

public class Cajero_automatico {
    private int Saldo = 7000000, SaldoCN = 20000000, retiroD = 2100000;
    private boolean continuar = true;

    public Cajero_automatico() {
    }

    public int getSaldo() {
        return Saldo;
    }

    public void setSaldo(int Saldo) {
        this.Saldo = Saldo;
    }

    public int getSaldoCN() {
        return SaldoCN;
    }

    public void setSaldoCN(int SaldoCN) {
        this.SaldoCN = SaldoCN;
    }

    public int getRetiroD() {
        return retiroD;
    }

    public void setRetiroD(int retiroD) {
        this.retiroD = retiroD;
    }

    public void Cajero_automatico() {
        while (continuar) {
            try {
                StringBuilder menu = new StringBuilder("MENU CAJERO AUTOMATICO \n\n");
                menu.append("seleciones una opcion del 1 al 4 asi \n")
                        .append("1. consultar saldo \n")
                        .append("2. consignar dinero \n")
                        .append("3. retirar dinero \n")
                        .append("4. salir \n");
                String opcion = JOptionPane.showInputDialog(null, menu, "Cajero Automatico", JOptionPane.QUESTION_MESSAGE);
                if (opcion == null) {
                    if (confirmarSalida()) {
                        continuar = false;
                    }
                    continue;
                }
                int opc = Integer.parseInt(opcion);
                switch (opc) {
                    case 1 -> consulta_saldo();
                    case 2 -> consignar_dinero();
                    case 3 -> retirarDinero();
                    default -> throw new AssertionError();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error", "Debes ingresar un numero del 1 al 4", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean confirmarSalida() {
        int confirmar = JOptionPane.showConfirmDialog(null, "¿Estas seguro que deseas salir?", "confirmar Salida", JOptionPane.YES_NO_OPTION);
        return confirmar == JOptionPane.YES_OPTION;
    }

    public String id_validacion() {
        Random id = new Random();
        int numero = id.nextInt(9000) + 1000;
        return "ID de la operacion # " + numero + "\n";
    }

    public void consulta_saldo() {
        String validacion = id_validacion();
        StringBuilder mensaje1 = new StringBuilder("CONSULTAR SALDO \n");
        mensaje1.append(validacion)
                .append("Saldo Actual: $")
                .append(String.format("%,d", Saldo));
        JOptionPane.showInternalMessageDialog(null, mensaje1, "Consultar Saldo", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showInternalMessageDialog(null, mensaje1, "Su saldo actual es: $", JOptionPane.INFORMATION_MESSAGE);
    }

    public void consignar_dinero() {
        String consigna = id_validacion();
        StringBuilder mensaje2 = new StringBuilder("CONSIGNAR DINERO \n");
        mensaje2.append(consigna)
                .append(String.format("%,d", Saldo));
        int D = 0;
        Saldo = D - Saldo;

        JOptionPane.showInputDialog(null, "Billetes de 10mil en adelante ", "¡Solo se aceptan billetes!", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showInternalMessageDialog(null, mensaje2, "Consignar dinero", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void consignarDinero() {

        JOptionPane.showMessageDialog(null, "NO INGRESAR MONEDAS", "ADVERTENCIA",
                JOptionPane.WARNING_MESSAGE);
        String consigna = JOptionPane.showInputDialog("Monto a consignar:");
        if (consigna == null) {

        }
        try {
            int monto = Integer.parseInt(consigna);
            if (monto % 10000 == 0) {
                Saldo += monto;
                JOptionPane.showMessageDialog(null, "Consignacion exitosa \n su nuevo saldo es: "
                        + Saldo);
            } else {
                JOptionPane.showInternalMessageDialog(null, "Monto invalido",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero valido");
        }
    }

    public void retirarDinero() {
        String saldoRet = JOptionPane.showInputDialog("Monto a retirar:");
        if (saldoRet == null);
        try {
            int retiro = Integer.parseInt(saldoRet);
            if (retiro <= 0) {
                JOptionPane.showMessageDialog(null, "Monto invalido");
            } else if (retiro > retiroD) {
                JOptionPane.showMessageDialog(null, "Excede el limite diario de retiro: " + retiroD);
            } else if (retiro > Saldo) {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente");
            } else if (retiro % 10000 == 0 && retiro > 9000) {

                Saldo -= retiro;
                JOptionPane.showMessageDialog(null,
                        "Retiro exitoso \n Nuevo saldo: " + Saldo);
            } else {
                JOptionPane.showMessageDialog(null, "Monto invalido");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero valido", "ERROR",
                    JOptionPane.ERROR_MESSAGE);

        }

    }
  
}

    