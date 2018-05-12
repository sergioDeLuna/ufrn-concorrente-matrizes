package br.ufrn.concorrente.main;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que tem como objetivo ler as matrizes dos arquivos de entrada
 * @author sergioluna
 *
 */
public class Leitura {
	
	/**
	 * Lista de lista de inteiros para representar as linhas
	 */
	private List<List<Integer>> linhas = new ArrayList<>();
	
	/**
	 * Variável para armazenar a altura
	 */
    private int altura = 0;
    
    /**
     * Variável para armazenar a largura
     */
    private int largura = 0;

    /**
     * Recebe uma string com os parâmetros de altura e largura
     * sendo A é altura e L largura.
     * @param dimensao string com parâmetros
     * @return 
     */
    public Leitura alturaElargura(final String dimensao) {
        String[] dimensoes = dimensao.split(" ");
        if (dimensoes.length == 2) {
            novaAltura(dimensoes[0]);
            novaLargura(dimensoes[1]);
        }
        return this;
    }
    
    /**
     * Método para retornar a altura
     * @return
     */
    public int getaltura() {
        return altura;
    }
    
    /**
     * Método para retornar a largura
     * @return
     */
    public int getlargura() {
        return largura;
    }
    
    /**
     * Método retorna a nova altura
     * @param novaAltura
     * @return
     */
    protected int novaAltura(final String novaAltura) {
        this.altura = Integer.parseInt(novaAltura);
        return this.altura;
    }
    
    /**
     * Método retorna a nova largura
     * @param novalargura
     * @return
     */
    protected int novaLargura(final String novaLargura) {
        this.largura = Integer.parseInt(novaLargura);
        return this.largura;
    }
     
    /**
     * Método para ler o elemento
     * @param elemento
     * @return
     */
    protected int lerElemento(final String elemento) {
        return Integer.parseInt(elemento);
    }
    
    /**
     * Método para ler a linha
     * @param linhastring
     * @return
     */
    public List<Integer> lerLinha(final String linhastring) {
        ArrayList<Integer> linha = new ArrayList<>();

        String[] elementos = linhastring.split(" ");
        for (String elemento : elementos) {
            int valor = lerElemento(elemento);
            linha.add(valor);
        }

        linhas.add(linha);
        return linha;
    }

    /**
     * Constrói a Matrix a partir dos dados armazenados na classe, fornecidos
     * pelos outros métodos
     * @return Matriz
     */
    public Matriz construir() {
            return new Matriz(linhas);
    }
}
