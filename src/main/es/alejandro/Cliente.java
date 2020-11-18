package main.es.alejandro;

import java.net.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Cliente {
	public static void main(String[] args) {
		MarcoCliente mimarco = new MarcoCliente();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
}

class MarcoCliente extends JFrame {
	public MarcoCliente() {
		setBounds(600,300,280,350);
		LaminaMarcoCliente milamina = new LaminaMarcoCliente();
		add(milamina);
		setVisible(true);	
	}
}

class LaminaMarcoCliente extends JPanel {
	private JTextField campo1;
	private JButton miboton;
	
	public LaminaMarcoCliente() {
		JLabel texto = new JLabel("Cliente");
	
		add(texto);
		campo1 = new JTextField(20);
		add(campo1);
		miboton = new JButton("Enviar");
		EnviarTexto mievento = new EnviarTexto();
		miboton.addActionListener(mievento);
		add(miboton);

	}
	
	private class EnviarTexto implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				Socket socket = new Socket("localhost", 9999); //Se construye el socket
				DataOutputStream flujoSalida = new DataOutputStream(socket.getOutputStream()); //Se crea el flujo
				flujoSalida.writeUTF(campo1.getText()); //Mete dentro del flujo lo que escribamos
				flujoSalida.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			//System.out.println(campo1.getText());
		}
		
	}
}
