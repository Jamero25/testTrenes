package com.test.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Ruta {

	private Map<String, Map<String, Integer>> rutaMap;

	public void generarRuta(String[] rutas) throws Exception {
		rutaMap = new HashMap<String, Map<String, Integer>>();

		for (String ruta : rutas) {
			String ciudadInicio = String.valueOf(ruta.charAt(0));
			String ciudadDestino = String.valueOf(ruta.charAt(1));
			Integer distancia = Integer.valueOf(ruta.substring(2, 3));
			if (rutaMap.containsKey(ciudadInicio)) {
				// System.out.println("Ya esta registrado el inicio");
				if (!rutaMap.get(ciudadInicio).containsKey(ciudadDestino)) {
					// No existe el destino, lo creamos
					HashMap<String, Integer> destino = new HashMap<String, Integer>();
					destino.put(String.valueOf(ruta.charAt(1)), distancia);
					// rutaMap.put(String.valueOf(ruta.charAt(0)), destino);
					rutaMap.get(ciudadInicio).put(ciudadDestino, distancia);
				} else {
					// Ya existe el destino, no se pueden repetir, lanzamos exception
					throw new Exception("La ruta " + ruta.charAt(0) + ruta.charAt(1)
							+ " YA EXISTE, POR FAVOR VERIFIQUE LA INFORMACION INDICADA");
				}
			} else {
				// No existe el inicio creado, lo creamos
				HashMap<String, Integer> destino = new HashMap<String, Integer>();
				destino.put(String.valueOf(ruta.charAt(1)), distancia);
				rutaMap.put(String.valueOf(ruta.charAt(0)), destino);
			}
		}

		//generarRespuestas();

	}

	public void generarRespuestas() {
		System.out.println("==========RESULTADOS==========");
		// RESPUESTA 1
		System.out.println("1.- The distance of the route A-B-C.");
		Integer distancia = calcularDistancia("A-B-C");
		String respuesta1 = "";
		if (distancia < 0) {
			respuesta1 = "NO SUH ROUTE";
		} else {
			respuesta1 = String.valueOf(distancia);
		}

		System.out.println("RESPUESTA ---> " + respuesta1);

		// RESPUESTA 2

		System.out.println("2.- The distance of the route A-D.");
		distancia = calcularDistancia("A-D");
		String respuesta2 = "";
		if (distancia < 0) {
			respuesta2 = "NO SUH ROUTE";
		} else {
			respuesta2 = String.valueOf(distancia);
		}

		System.out.println("RESPUESTA ---> " + respuesta2);

		// RESPUESTA 3

		System.out.println("3.- The distance of the route A-D-C.");
		distancia = calcularDistancia("A-D-C");
		String respuesta3 = "";
		if (distancia < 0) {
			respuesta3 = "NO SUH ROUTE";
		} else {
			respuesta3 = String.valueOf(distancia);
		}

		System.out.println("RESPUESTA ---> " + respuesta3);

		// RESPUESTA 4
		System.out.println("4. The distance of the route A-E-B-C-D.");

		distancia = calcularDistancia("A-E-B-C-D");
		String respuesta4 = "";
		if (distancia < 0) {
			respuesta4 = "NO SUH ROUTE";
		} else {
			respuesta4 = String.valueOf(distancia);
		}

		System.out.println("RESPUESTA ---> " + respuesta4);

		// RESPUESTA 5

		System.out.println("5. The distance of the route A-E-D.");

		distancia = calcularDistancia("A-E-D");
		String respuesta5 = "";
		if (distancia < 0) {
			respuesta5 = "NO SUH ROUTE";
		} else {
			respuesta5 = String.valueOf(distancia);
		}

		System.out.println("RESPUESTA ---> " + respuesta5);

		// RESPUESTA 6

		System.out.println("6. The number of trips starting at C and ending at C with a maximum of 3 stops.");
		int valor = ContarRutaConParadaMaxOLimite("C", "C", 3, false);
		System.out.println("Respuesta --->" + valor);

		// RESPUESTA 7
		System.out.println("7. The number of trips starting at A and ending at C with exactly 4 stops.");
		valor = ContarRutaConParadaMaxOLimite("A", "C", 4, true);
		System.out.println("Respuesta --->" + valor);
	}

	public Integer calcularDistancia(String ruta) {

		int distancia = 0;

		String[] valoresList = ruta.split("-");

		for (int i = 0; i < valoresList.length - 1; i++) {
			if (contieneNodo(valoresList[i]) && contieneDestino(valoresList[i], valoresList[i + 1])) {
				distancia += rutaMap.get(valoresList[i]).get(valoresList[i + 1]);
			} else {
				distancia = -1;
				break;
			}
		}

		return distancia;
	}

	private boolean contieneNodo(String nodo) {
		return rutaMap.containsKey(nodo);
	}

	private boolean contieneDestino(String inicio, String destino) {
		return rutaMap.get(inicio).containsKey(destino);
	}

	// funciones para calcular número de caminos
	public int ContarRutaConParadaMaxOLimite(String inicio, String fin, int cantidadParada, boolean limiteExacto) {
		List<Object[]> paths = obtenerRutaEntrePuntos(inicio, fin);
		int count = 0;
		if (paths == null || paths.size() <= 0) {
			// No hay ruta exacta, se envía 0
			return count;
		}

		for (Object[] path : paths) {
			if (!limiteExacto) {
				if (path.length <= cantidadParada + 1) {
					count++;
				}
			} else {
				if (path.length == cantidadParada + 1) {
					count++;
				}
			}

		}
		return count;
	}

	public List<Object[]> obtenerRutaEntrePuntos(String inicio, String fin) {
		// Creamos una pila para manejar los resultados
		Stack<String> pila = new Stack<>();
		List<Object[]> rutas = new LinkedList<>();
		Set<String> recorrido = new HashSet<>();
		recorrerVector(inicio, inicio, fin, "", pila, rutas, recorrido);
		for (Object[] path : rutas) {
			mostrarRutasPantalla(path);
		}
		return rutas;
	}

	public void recorrerVector(String inicio, String index, String fin, String prev, Stack<String> pila,
			List<Object[]> rutas, Set<String> recorrido) {
		pila.push(index);
		if (index.equals(fin) && !prev.equals("")) {
			rutas.add(pila.toArray());
			pila.pop();
		} else {
			Map<String, Integer> edgeMap = rutaMap.get(index);
			if (null != edgeMap && edgeMap.size() > 0) {
				for (Map.Entry<String, Integer> entry : edgeMap.entrySet()) {
					if (!pila.contains(entry.getKey()) || !recorrido.contains(entry.getKey())) {
						recorrerVector(inicio, entry.getKey(), fin, index, pila, rutas, recorrido);
					}
				}
				recorrido.add(pila.pop());
			}
		}
	}

	private void mostrarRutasPantalla(Object[] path) {
		StringBuilder sb = new StringBuilder();
		for (Object o : path) {
			sb.append(o);
		}
		System.out.println(sb);
	}

}
