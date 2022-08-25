package com.test.pruebas;

import com.test.controller.ValidadorUtils;
import com.test.services.Ruta;

import org.junit.Assert;
import org.junit.jupiter.api.Test;



public class PruebasEjercicio {

	@Test
	public void test_leer_cadena() throws Exception {
		ValidadorUtils validadorUtils = new ValidadorUtils();
		String[] path = validadorUtils.leerCadenaIngresada("AD5, AB4");
		Ruta ruta = new Ruta();
		ruta.generarRuta(path);
		
	}
	
	@Test
	public void test1() throws Exception {
		ValidadorUtils validadorUtils = new ValidadorUtils();
		String[] path = validadorUtils.leerCadenaIngresada("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		Ruta ruta = new Ruta();
		ruta.generarRuta(path);
		int distancia = ruta.calcularDistancia("A-B-C");
		Assert.assertEquals(9, distancia);
	}
	
	@Test
	public void test2() throws Exception {
		ValidadorUtils validadorUtils = new ValidadorUtils();
		String[] path = validadorUtils.leerCadenaIngresada("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		Ruta ruta = new Ruta();
		ruta.generarRuta(path);
		int distancia = ruta.calcularDistancia("A-D");
		Assert.assertEquals(5, distancia);
	}
	
	@Test
	public void test3() throws Exception {
		ValidadorUtils validadorUtils = new ValidadorUtils();
		String[] path = validadorUtils.leerCadenaIngresada("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		Ruta ruta = new Ruta();
		ruta.generarRuta(path);
		int distancia = ruta.calcularDistancia("A-D-C");
		Assert.assertEquals(13, distancia);
	}
	
	@Test
	public void test6() throws Exception {
		ValidadorUtils validadorUtils = new ValidadorUtils();
		String[] path = validadorUtils.leerCadenaIngresada("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		Ruta ruta = new Ruta();
		ruta.generarRuta(path);
		int valor = ruta.ContarRutaConParadaMaxOLimite("C", "C", 3, false);
		Assert.assertEquals(2, valor);
	}
}
