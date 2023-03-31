/**
 * 
 */
package com.integrador.proyecto.voguel.clases;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JFrame;

import static java.util.Arrays.stream;


/**
 * @author PABLO
 *
 */
public class AproximacionDeVoguel {
	
	private int[] demanda;
	private int[] oferta;
	private int[][] costos;
	private int nFilas;
	private int nColumnas;
	private boolean[] filaTerminada;
	private boolean[] columnaTerminada;
	private int[][] resultado;
	static ExecutorService es = Executors.newFixedThreadPool(2);
	//private static Scanner sc = new Scanner(System.in);
	
	
	public AproximacionDeVoguel(int[] demanda, int[] oferta, int[][] costos) {
		this.demanda = demanda;
		this.oferta = oferta;
		this.costos = costos;
		this.nFilas = oferta.length;
		this.nColumnas = demanda.length;
		filaTerminada = new boolean[nFilas];
		columnaTerminada = new boolean[nColumnas];
		resultado = new int[nFilas][nColumnas];
		
		System.out.println(nFilas + "     |     " + nColumnas);
	}
	
	public void solucion() throws Exception{
		
		
		
		int ofertaRestante = stream(oferta).sum();
		int costoTotal = 0;
		
		System.out.println("Oferta inicial: " + ofertaRestante);
		//sc.nextLine();
		
		while(ofertaRestante > 0) {
			int[] cell = siguienteCelda();
			int r = cell[0];
			int c = cell[1];
			
			int cantidad = Math.min(demanda[c], oferta[r]);
			
			System.out.println( "cantidad: " + cantidad);
			
			demanda[c] -= cantidad;
			
			if(demanda[c] == 0) {
				columnaTerminada[c] = true;
			}
			
			oferta[r] -= cantidad;
			
			if(oferta[r] == 0) {
				filaTerminada[r] = true;
			}
			
			resultado[r][c] = cantidad;
			
			
			ofertaRestante -= cantidad;
			
			System.out.println("oferta restante: " + ofertaRestante);
			
			costoTotal += cantidad * costos[r][c];
			
			System.out.println("costo total: " + costoTotal + "\n\n");
			
			//sc.nextLine();
		}
		
		System.out.println(costoTotal);
		
		com.integrador.proyecto.voguel.guiforms.FormVentanaResultados form = new com.integrador.proyecto.voguel.guiforms.FormVentanaResultados(resultado,costoTotal);
		form.setVisible(true);
		form.setLocationRelativeTo(null);
		form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//es.shutdown();
	}
	
	public int sum(int[] array) {
		int suma = 0;
		
		for(int i = 0; i<array.length; i++) {
			suma += array[i];
		}
		
		return suma;
	}
	
	public int[] siguienteCelda() throws Exception{
		
		Future<int[]> f1 = es.submit( () -> penalizacionMaxima(nFilas, nColumnas, true) );
		Future<int[]> f2 = es.submit( () -> penalizacionMaxima(nColumnas, nFilas, false) );
		
		int[] res1 = f1.get();
		int[] res2 = f2.get();
		
		if(res1[3] == res2[3]) {
			return res1[2] < res2[2] ? res1 : res2;
		}
		
		return (res1[3] > res2[3]) ? res2 : res1;
	}
	
	public int[] diferencia(int j, int len, boolean esFila) {
		int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
		int minP = -1;
		
		for(int i = 0; i < len; i++) {
			if(esFila ? columnaTerminada[i] : filaTerminada[i]) {
				continue;
			}
			int c = esFila ? costos[j][i] : costos[i][j];
			if(c < min1) {
				min2 = min1;
				min1 = c;
				minP = i;
			}else if(c < min2) {
				min2 = c;
			}
		}
		return new int[] {min2 - min1, min1, minP};
	}
	
	public int[] penalizacionMaxima(int len1, int len2, boolean esFila) {
		int md = Integer.MIN_VALUE;
		int pc = -1, pm = -1, mc = -1;
		
		for(int i = 0; i<len1;i++) {
			if(esFila ? filaTerminada[i] : columnaTerminada[i]) {
				continue;
			}
			int[] res = diferencia(i,len2,esFila);
			if(res[0] > md) {
				md = res[0];
				pm = i;
				mc = res[1];
				pc = res[2];
			}
		}
		
		return esFila ? new int[] {pm,pc,mc,md} : new int[] {pc,pm,mc,md};
	}
	
}
