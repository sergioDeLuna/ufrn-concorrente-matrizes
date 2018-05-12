package br.ufrn.concorrente.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Classe principal do processo, tem como objetivo gerenciar todo o projeto
 * @author sergioluna
 *
 */
public class Main {
	/**
	 * Quantidade de vezes que as matrizes serão construídas
	 * (Reduzir o maior número de tempo)
	 */
    private static final int QUANTIDADE_CONSTRUCOES_MATRIZES = 20;
    
    /**
     * Variável que armazena o tamanho (ordem) da Matriz
     */
    private static int tamanhoMatriz;
    
    /**
     * Algoritmo utilizado para multiplicar as matrizes
     */
    private static String algoritmoMultiplicacao;
    
    /**
     * Variável para armazenar a matriz A
     */
    private static Matriz matrizA;
    
    /**
     * Variável para armazenar a matriz B
     */
    private static Matriz matrizB;
    
    /**
     * Variável para armazenar a matriz C
     */
    private static Matriz matrizC;

    /**
     * Tem como parâmetro a ordem das matrizes e o algoritmo
     * de multiplicação de matrizes a ser utilizado
     * @param ordem e algoritmo
     * @throws IOException 
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
    	// TODO Auto-generated method stub
    	Scanner scanner = new Scanner(System.in);
    	int ordem = scanner.nextInt();
    	String algoritmo = scanner.next();

        tamanhoMatriz = ordem;
        algoritmoMultiplicacao = algoritmo;
        
        matrizA = lerMatriz("A", tamanhoMatriz);
        matrizB = lerMatriz("B", tamanhoMatriz);
        
        if (algoritmoMultiplicacao.equalsIgnoreCase("S")) {
            System.out.println("O algoritmo de multiplicação de matrizes escolhido foi o: Sequencial!");
        } else {
            System.out.println("O algoritmo de multiplicação de matrizes escolhido foi o: Concorrente!");
        }
        
        calculaMatriz(matrizA, matrizB);
        geraEstatistica(matrizA, matrizB);

        System.out.println("\n\nO programa foi finalizado com êxito!\n\nVerifique na pasta 'matrizes-saidas' o seu resultado!");
    }
    
    /**
     * Realiza a leitura do arquivo de matriz a partir do tamanho
     * @param tamanho da matriz (ordem)
     * @return 
     */
    private static Matriz lerMatriz(String nome, int tamanho) throws FileNotFoundException {
        Leitura matrizLeitura = new Leitura();
        Scanner scanner = new Scanner(new FileInputStream("matrizes-entradas/" + fileName(nome, tamanho)));

        if (scanner.hasNextLine()) {
        	matrizLeitura.alturaElargura(scanner.nextLine());
        }

        while (scanner.hasNextLine()) {
        	matrizLeitura.lerLinha(scanner.nextLine());
        }

        scanner.close();
        return matrizLeitura.construir();
    }
    
    /**
     * Retorna o nome do arquivo partindo de uma letra e o tamanho da matriz
     * @param letra
     * @param tamanhoMatriz
     * @return
     */
    private static String fileName(final String letra, final int tamanhoMatriz) {
        return letra + tamanhoMatriz + "x" + tamanhoMatriz + ".txt";
    }
    
    /**
     * Calcula o resultado da multiplicação e escreve no arquivo de saída
     * Cixi.txt, onde i é o tamanho da matriz
     * @param matrizA 
     * @param matriZB 
     * @throws IOException 
     */
    private static void calculaMatriz(Matriz matrizA, Matriz matrizB) throws IOException {
        PrintStream matrizSaida = null; 
 
        File file = new File("matrizes-saidas/" + fileName("C", tamanhoMatriz));
        
        if (file.exists()){
            return;
        } else {
            file.createNewFile();
        }

        Matriz matrizC = Calcula.multiplicacaoSequencial(matrizA, matrizB);

        matrizSaida = new PrintStream(file);
        matrizSaida.println(matrizC);

    }
    
    /**
     * Gera os dados estatísticos para as matrizes e armazena em um arquivo 
     * no formato "Estatisticas-?-Cixi.txt" informando qual o método escolhido
     * @param matrizA 
     * @param matrizB      
     * @throws FileNotFoundException 
     * @throws InterruptedException 
     */
    private static void geraEstatistica(Matriz matrizA, Matriz matrizB) throws FileNotFoundException, InterruptedException {
        Estatistica estatistica = new Estatistica();

        for (int i = 0; i < QUANTIDADE_CONSTRUCOES_MATRIZES; i++) {
            long tempo = calculaTempoExecucaoMultiplicacao(matrizA, matrizB);
            estatistica.adicionaDado(Double.valueOf(tempo));
            System.out.print(".");
        }

        PrintStream estatisticaStream = new PrintStream("matrizes-saidas/" + gerarEstatisticaFileName());
        estatisticaStream.println(estatistica);
        
    }
    
    /**
     * Retorna o nome do arquivo dos dados estatisticos
     * @return
     */
    private static String gerarEstatisticaFileName() {
        return algoritmoMultiplicacao + "-Estatisticas-" + fileName("C", tamanhoMatriz);
    }
    
    /**
     * Calcula o tempo de execução da multiplicação de duas matrizes fornecidas
     * e retorna o tempo de execução em nanossegundos
     * @param matrizA 
     * @param matrizB 
     * @return 
     * @throws InterruptedException 
     */
    private static long calculaTempoExecucaoMultiplicacao(Matriz matrizA, Matriz matrizB) throws InterruptedException {
        long tempoInicio = System.nanoTime();

        if (algoritmoMultiplicacao.equalsIgnoreCase("S")) {
            Calcula.calculaProdutoMatrizes(matrizA, matrizB);
        } else {
            calculoMatrizConcorrencia();
        }
        
        long tempoFinal = System.nanoTime();
        return tempoFinal - tempoInicio;
    }

   /**
    * Método que cálcula o produto de matrize através do método de concorrência
    */
    private static void calculoMatrizConcorrencia() throws InterruptedException {
        matrizC = new Matriz(tamanhoMatriz);
        for (int linha = 0; linha < tamanhoMatriz; linha++) {
            ThreadLinha thread = new ThreadLinha(String.format("Linha: %d", linha), matrizA, matrizB, matrizC, linha);
            thread.start();
            thread.join();
        }
    }

}
