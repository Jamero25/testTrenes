package com.test.inicio;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.test.controller.ValidadorUtils;
import com.test.services.Ruta;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("##########INICIO###########");
		String respuesta = pregunta("INTRODUZCA CADENA --->");
		System.out.println("LA CADENA INGRESADA ES --->" + respuesta);

		validarRespuesta(respuesta);

	}

	private static String pregunta(String pregunta) {
		String respuesta = "";
		while ("".equals(respuesta)) {
			System.out.println("\n " + pregunta);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				respuesta = br.readLine().trim();
			} catch (Exception e) {

				respuesta = "";
			}

		}
		return respuesta.toUpperCase();

	}

	private static void validarRespuesta(String respuesta) {
		try {
			ValidadorUtils validadorUtils = new ValidadorUtils();
			Ruta ruta = new Ruta();
			String[] path = validadorUtils.leerCadenaIngresada(respuesta);
			if (path != null) {
				ruta.generarRuta(path);
				ruta.generarRespuestas();
			}
		} catch (Exception e) {
			System.out.println("Error --> " + e.getMessage());
			respuesta = pregunta("INTRODUZCA LA CADENA NUEVAMENTE ---> ");
			System.out.println("LA CADENA INGRESADA ES " + respuesta);
			validarRespuesta(respuesta);
		}
	}

}
