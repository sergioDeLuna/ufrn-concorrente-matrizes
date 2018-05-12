package br.ufrn.concorrente.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Classe que contém as estatísticas
 * @author sergioluna
 *
 */
public class Estatistica {
	
	/**
	 * Coleção de dados
	 */
    private Collection<Double> dados = new ArrayList<>();
    
    /**
     * Variável para armazenar o valor máximo dentre a coleção
     */
    private Double maximo = Double.MIN_VALUE;
    
    /**
     * Variável para armazenar o valor mínimo dentre a coleção
     */
    private Double minimo = Double.MAX_VALUE;

    /**
     * Adiciona um dado para a população de dados
     * @param dado 
     */
    public void adicionaDado(final Double dado) {
        maximo = Double.max(dado, maximo);
        minimo = Double.min(dado, minimo);
        this.dados.add(dado);
    }

    /**
     * @return a coleção de dados 
     */
    public Collection<Double> getDados() {
        return dados;
    }

    /**
     * @return o maior valor da coleção
     */
    public Double getMaximo() {
        return maximo;
    }

    /**
     * @return o menor valor da coleção
     */
    public Double getMinimo() {
        return minimo;
    }

    /**
     * @return a média dos dados da coleção
     */
    public Double calculaMedia() {
    	return dados.stream().mapToDouble(p -> p).average().getAsDouble();
    }

    /**
     * @return o desvio padrão dos dados da coleção
     */
    public Double calculaDesvioPadrao() {
        Double desvio = 0.0;
        Double media = calculaMedia();

        for (Double elemento : dados) {
            desvio += Math.pow(elemento - media, 2);
        }

        desvio /= dados.size();

        return Math.sqrt(desvio);
    }

    /**
     * @return as estatisticas do arquivo referente
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Dados Estatísticos: \n")
                .append(dataToString())
                .append('\n')
                .append("Tempo Máximo: ")
                .append(maximo)
                .append('\n')
                .append("Tempo Mínimo: ")
                .append(minimo)
                .append('\n')
                .append("Média: ")
                .append(calculaMedia())
                .append('\n')
                .append("Desvio Padrão: ")
                .append(calculaDesvioPadrao());

        return builder.toString();
    }

    /**
     * @return os dados armazenados de forma legível
     */
    protected String dataToString() {
        StringBuilder builder = new StringBuilder();

        Iterator<Double> iterator = dados.iterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next());

            if (iterator.hasNext()) {
                builder.append("; ");
            }
        }
        return builder.toString();
    }

}
