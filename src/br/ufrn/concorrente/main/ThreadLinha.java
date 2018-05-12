package br.ufrn.concorrente.main;

/**
 * Classe que representa a thread criada por linha 
 * @author sergioluna
 *
 */
public class ThreadLinha extends Thread{
	    /**
	     * Referencia a linha da thread
	     */
	    private final int linha;
	    
	    /**
	     * Variável para armazenar a matriz A
	     */
        private final Matriz matrizA;
        
	    /**
	     * Variável para armazenar a matriz B
	     */
	    private final Matriz matrizB;
	    
	    /**
	     * Variável para armazenar a matriz resultante
	     */
	    private final Matriz resultante;
	    
	    /**
	     * Construtor padrão para a Thread Linha
	     * @param nome
	     * @param matrizA
	     * @param matrizB
	     * @param resultante
	     * @param linha
	     */
	    public ThreadLinha(String nome, Matriz matrizA, Matriz matrizB, Matriz resultante, int linha) {
	        super(nome);
	        this.matrizA = matrizA;
	        this.matrizB = matrizB;
	        this.resultante = resultante;
	        this.linha = linha;
	    }
	    
	    /**
	     * Método invocado a cada start da thread
	     */
	    @Override
	    public void run() {
	        Calcula.calculaLinhaMatriz(matrizA, matrizB, linha, resultante);
	    }

}
