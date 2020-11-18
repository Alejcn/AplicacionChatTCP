package main.es.alejandro;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Cliente {
	static String HOST = "localhost";
	static int PUERTO = 5000;
	Socket sc;
	DataOutputStream salida;
	DataInputStream entrada;
	String mensajeRecibido;

	public void initCliente() {
		Scanner teclado = new Scanner(System.in);
		try {
			sc = new Socket(HOST, PUERTO);
			salida = new DataOutputStream(sc.getOutputStream());
			entrada = new DataInputStream(sc.getInputStream());
			String msn = "";
			while (!msn.equals("exit")) {
				System.out.println("Cliente: Escriba un mensaje para enviar");
				msn = teclado.nextLine();
				salida.writeUTF(msn);
				mensajeRecibido = entrada.readUTF();
				System.out.println("Cliente: He recibido del Servidor: " + mensajeRecibido);
			}
			sc.close();
			teclado.close();
		} catch (Exception e) {
			System.out.println("ERROR : " + e);
		}
	}

	public static void main(String[] args) {
		Cliente o = new Cliente();
		o.initCliente();
	}
}