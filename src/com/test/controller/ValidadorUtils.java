package com.test.controller;

public class ValidadorUtils {

	public String[] leerCadenaIngresada(String in) throws IllegalArgumentException, IllegalArgumentException {

		String[] parts = in.toUpperCase().split("\\s+|,\\s*");
		for (String route : parts)
			validarEntrada(route);
		return parts;
	}

	public static boolean validarEntrada(String data) {
		if (data.length() == 3 && esLetra(data.charAt(0)) && esLetra(data.charAt(1)) && esNumero(data.charAt(2))) {
			return true;
		} else {
			throw new IllegalArgumentException("Nodo '" + data + "' Invalido");

		}

	}

	private static boolean esLetra(char s) {
		if (Character.isLetter(s))
			return true;
		throw new IllegalArgumentException("El nodo '" + s + "' debe ser una letra [a-z]");
	}

	private static boolean esNumero(char i) {
		if (Character.isDigit(i))
			return true;
		throw new IllegalArgumentException("El tercer campo deberia ser numérico '" + i + "'");
	}
}
