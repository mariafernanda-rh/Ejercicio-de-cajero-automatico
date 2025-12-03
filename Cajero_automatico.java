package com.mycompany.ejerciciodecajero;

import javax.swing.JOptionPane;
import java.util.Random;

public class Cajero_automatico {

    private int Saldo = 7000000;
    private final int LIMITE_DIARIO = 2100000;
    private int retiroAcumulado = 0; // 
    private boolean continuar = true;

    public int getSaldo() {
        return Saldo;
    }

    public void setSaldo(int Saldo) {
        this.Saldo = Saldo;
    }

    public int getRetiroAcumulado() {
        return retiroAcumulado;
    }

    public void setRetiroAcumulado(int retiroAcumulado) {
        this.retiroAcumulado = retiroAcumulado;
    }

    public void Cajero_automatico() {
        while (continuar) {
            try {StringBuilder menu = new StringBuilder("MENU CAJERO AUTOMATICO \n");
                menu.append("Seleciona una opcion del 1 al 4 \n")
                        .append("1. consultar saldo \n")
                        .append("2. consignar dinero \n")
                        .append("3. retirar dinero \n")
                        .append("4. salir \n");

                String opcion = JOptionPane.showInputDialog(null, menu, "Cajero Automático", JOptionPane.QUESTION_MESSAGE);

                if (opcion == null) {
                    if (confirmarSalida()) continuar = false;
                    continue;
                }

                int opc = Integer.parseInt(opcion);

                switch (opc) {
                    case 1 -> consultar_saldo();
                    case 2 -> consignar_dinero();
                    case 3 -> retirar_dinero();
                    default ->
                        throw new AssertionError();
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Debes ingresar un número entre 1 y 4",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean confirmarSalida() {
        int confirmar = JOptionPane.showConfirmDialog(null,
                "¿Deseas salir?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION);
        return confirmar == JOptionPane.YES_OPTION;
    }

    public String id_validacion() {
        Random id = new Random();
        int numero = id.nextInt(9000) + 1000;
        return "ID de operación # " + numero + "\n";
    }

    public void consultar_saldo() {
        String mensaje = id_validacion()
                + "Saldo Actual: $" + String.format("%,d", Saldo);
        JOptionPane.showMessageDialog(null, mensaje, "Consultar saldo", JOptionPane.INFORMATION_MESSAGE);
    }

    public void consignar_dinero() {
    String entrada = JOptionPane.showInputDialog("Monto a consignar:");

    if (entrada == null) return;

    try {
        int monto = Integer.parseInt(entrada);

        if (monto <= 0) {
            JOptionPane.showMessageDialog(null, "Monto inválido");
            return;
        }

        if (monto < 10000) {
            JOptionPane.showMessageDialog(null, "El monto mínimo es 10.000");
            return;
        }

        if (monto % 10000 != 0) {
            JOptionPane.showMessageDialog(null, "Solo se aceptan múltiplos de 10.000");
            return;
        }

        Saldo += monto;

        String recibo = id_validacion()
                + "Consignación exitosa.\n"
                + "Monto: $" + String.format("%,d", monto) + "\n"
                + "Nuevo saldo: $" + String.format("%,d", Saldo);

        JOptionPane.showMessageDialog(null, recibo, "Consignación", JOptionPane.INFORMATION_MESSAGE);

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Valor inválido");
    }
}

    

    private void retirar_dinero() {

        String entrada = JOptionPane.showInputDialog(null,
                "Ingrese el monto a retirar:",
                "Retiro",
                JOptionPane.QUESTION_MESSAGE);

        if (entrada == null) return;

        try {
            int retiro = Integer.parseInt(entrada);

            if (retiro <= 0) {
                JOptionPane.showMessageDialog(null, "Monto inválido");
                return;
            }

            if (retiro % 10000 != 0) {
                JOptionPane.showMessageDialog(null, "Solo múltiplos de $10.000");
                return;
            }

            if (retiro > Saldo) {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente");
                return;
            }

            if (retiroAcumulado + retiro > LIMITE_DIARIO) {
                JOptionPane.showMessageDialog(null,
                        "Excede el límite diario de retiro: $"
                                + String.format("%,d", LIMITE_DIARIO));
                return;
            }

            Saldo -= retiro;
            retiroAcumulado += retiro;

              JOptionPane.showMessageDialog(null,
                    "Retiro exitoso \n Nuevo saldo: " + Saldo
                    + "\n Retiro acumulado hoy: " + retiroAcumulado);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Ingrese un número válido",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
