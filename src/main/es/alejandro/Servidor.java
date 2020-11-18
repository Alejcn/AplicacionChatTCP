package main.es.alejandro;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Servidor {
	static int PUERTO = 5000;
	ServerSocket sc;
	Socket so;
	DataOutputStream salida;
	DataInputStream entrada;
	String mensajeRecibido;

	public void initServidor() {

		Scanner teclado = new Scanner(System.in);
		try {
			sc = new ServerSocket(PUERTO);
			so = new Socket();

			System.out.println("Servidor: Esperando conexión...");
			so = sc.accept();
			System.out.println("Servidor: Se ha conectado un cliente...");
			entrada = new DataInputStream(so.getInputStream());
			salida = new DataOutputStream(so.getOutputStream());
			String msn = "";
			while (!msn.equals("exit")) {

				mensajeRecibido = entrada.readUTF();
				System.out.println("Servidor: Me ha enviado el Cliente: " + mensajeRecibido);
				System.out.println("Servidor: Escriba un mensaje para enviar al Cliente");
				msn = teclado.nextLine();
				salida.writeUTF("" + msn);
			}
			System.out.println("Servidor: Cerrando conexion: " + mensajeRecibido);
			sc.close();
			teclado.close();
		} catch (Exception e) {
			System.out.println("Servidor: Exception: " + e);
		}
	}

	public static void main(String[] args) {
		Servidor o = new Servidor();
		o.initServidor();
	}
}
