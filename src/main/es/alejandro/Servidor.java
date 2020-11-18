package main.es.alejandro;

import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class Servidor {
	
	public static void main(String[] args) {
		MarcoServidor mimarco = new MarcoServidor();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	
}


class MarcoServidor extends JFrame implements Runnable{
	public MarcoServidor() {
		setBounds(1200,300,280,350);
		JPanel milamina = new JPanel();
		milamina.setLayout(new BorderLayout());
		areatexto = new JTextArea();
		milamina.add(areatexto, BorderLayout.CENTER);
		add(milamina);
		setVisible(true);
		Thread hilo = new Thread(this);
		hilo.start();
	}
	private JTextArea areatexto;
	
	@Override
	public void run() {
		//System.out.println("Estoy a la escucha");	
		try {
			ServerSocket servidor = new ServerSocket(9999); // Abrimos una conexión a través del puerto, en este caso 9999
			while(true) {
				Socket socket = servidor.accept(); //Acceptamos todas las conexiones
				DataInputStream flujoEntrada = new DataInputStream(socket.getInputStream()); //Leemos lo que viene en el flujo de datos
				String mensajeTexto = flujoEntrada.readUTF(); //Guardamos el flujo de datos del cliente
				areatexto.append("\n" + mensajeTexto); //Imprimimos el texto del cliente en el area de texto
				socket.close(); //cerramos la conexión
				//servidor.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
	