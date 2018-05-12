package br.ufrn.concorrente.main;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma Matriz com algumas operações principais 
 * tais como gets e sets, métodos para a atualização de elementos
 * @author sergioluna
 *
 */
public class Matriz {
	
	/**
	 * Lista de lista de inteiros que representam
	 * os elementos de toda a Matriz
	 */
	private List<List<Integer>> matriz;
	
    /**
     * Construtor da Matriz fornecendo uma matriz
     * @param matriz de elementos 
     */
    public Matriz(List<List<Integer>> matriz) {
        this.matriz = matriz;
    }
    
	/**
	 * Construtor da Matriz fornecendo o tamanho e inicializando com valores
	 * iguais a 0
	 * @param tamanhoMatriz
	 */
	public Matriz(int tamanhoMatriz){
		matriz = new ArrayList<>();
		for (int i = 0; i < tamanhoMatriz; i++) {
            List<Integer> linhaMatriz = new ArrayList<>();
            for (int j = 0; j < tamanhoMatriz; j++) {
            	linhaMatriz.add(0);
            }
            matriz.add(linhaMatriz);
        }
		
	}
	
	/**
     * Retorna o tamanho da matriz
     * @return
     */
    public int getTamanho() {
        return matriz.size();
    }

    /**
     * Retorna um elemento da matriz
     * @param coluna 
     * @param linha 
     * @return o elemento que está na posição (linha, coluna)
     */
    public int getElemento(int linha, int coluna) {
        return matriz.get(linha).get(coluna);
    }

    /**
     * Seta um elemento da matriz
     * @param linha 
     * @param coluna
     * @param valor novo do elemento 
     */
    public void setElemento(int linha, int coluna, int valor) {
        matriz.get(linha).set(coluna, valor);
    }

    /**
     * Retorna uma linha da matriz
     * @param linha 
     * @return retorna uma lista com os elementos da linha solicitada
     */
    public List<Integer> getLinhaMatriz(int linha) {
        return matriz.get(linha);
    }
    
    /**
     * Retorna uma coluna da matriz
     * @param coluna 
     * @return retorna uma lista com os elementos da coluna solicitada
     */
    public List<Integer> getColunaMatriz(int coluna) {
        ArrayList<Integer> colunaMatriz = new ArrayList<>();

        for (List<Integer> linha : matriz) {
        	colunaMatriz.add(linha.get(coluna));
        }
        return colunaMatriz;
    }
    
    /**
     * Retorna uma matriz imprimível como uma String
     * @return uma String representando o estado atual da matriz
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (List<Integer> linhaMatriz : matriz) {
            for (Integer elemento : linhaMatriz) {
                builder.append(elemento);
                builder.append(" ");
            }
            builder.setCharAt(builder.length() - 1, '\n');
        }
        return builder.toString();
    }
}
