package co.org.uniquindio;

import co.org.uniquindio.algoritmos.*;
import co.org.uniquindio.persistence.*;
import co.org.uniquindio.views.ResultsViewer;
import javax.swing.*;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

/**
 * Clase principal para ejecutar los algoritmos de multiplicación de matrices, guardar los resultados y visualizarlos.
 *  @autor Marlon Stiven Espinosa Joaqui
 *  @autor Juan Esteban Quintero Rodriguez
 *  @autor Jesus Santiago Ramon Ramos
 */
public class Main {
    public static void main(String[] args) throws Exception {

        int minDigits = 7;
        int[] sizes = {32, 64, 128, 256, 512, 1024, 2048, 4096};

        // Probar cada algoritmo con cada tamaño de matriz
//        for (int size : sizes) {
////            // Generar matrices
////            double[][] matrixA = matrixGenerator(size, minDigits);
////            double[][] matrixB = matrixGenerator(size, minDigits);
////
////            // Guardar matrices
////            saveMatrix(matrixA, "matrix_A_" + size + "x" + size);
////            saveMatrix(matrixB, "matrix_B_" + size + "x" + size);
//
//            processAlgorithm(size, "NaivOnArray", NaivOnArray::multiply);
//            processAlgorithm(size, "NaivLoopUnrollingTwo", NaivLoopUnrollingTwo::multiply);
//            processAlgorithm(size, "NaivLoopUnrollingFour", NaivLoopUnrollingFour::multiply);
//            processAlgorithm(size, "WinogradOriginal", WinogradOriginal::multiply);
//            processAlgorithm(size, "WinogradScaled", WinogradScaled::multiply);
//            processAlgorithm(size, "StrassenNaiv", StrassenNaiv::multiply);
//            processAlgorithm(size, "StrassenWinograd", StrassenWinograd::multiply);
//            processAlgorithm(size, "III_3_Sequential_Block", III_3_Sequential_Block::multiply);
//            processAlgorithm(size, "III_4_Parallel_Block", III_4_Parallel_Block::multiply);
//            processAlgorithm(size, "IV_3_Sequential_Block", IV_3_Sequential_Block::multiply);
//            processAlgorithm(size, "IV_4_Parallel_Block", IV_4_Parallel_Block::multiply);
//            processAlgorithm(size, "V_3_Sequential_Block", V_3_Sequential_Block::multiply);
//            processAlgorithm(size, "V_4_Parallel_Block", V_4_Parallel_Block::multiply);
//            processAlgorithm(size, "III_5_Enhanced_Parallel_Block", III_5_Enhanced_Parallel_Block::multiply);
//            processAlgorithm(size, "IV_5_Enhanced_Parallel_Block", IV_5_Enhanced_Parallel_Block::multiply);
//
//        }

        // Cargar los resultados combinados
        List<ResultData> results = loadCombinedResults();

        // Mostrar el gráfico con los resultados
        displayChart(results);

    }

    /**
     * Guarda un resultado en el archivo de resultados.
     *
     * @param size El tamaño de la matriz.
     * @param algorithm El nombre del algoritmo utilizado.
     * @param executionTime El tiempo de ejecución del algoritmo.
     * @throws Exception Si ocurre un error durante el proceso de guardado.
     */
    private static void saveResult(int size, String algorithm, long executionTime) throws Exception {
        ResultFileHandler.saveResult(size, algorithm, executionTime);
    }

    /**
     * Genera una matriz con valores aleatorios.
     *
     * @param n El tamaño de la matriz.
     * @param minDigits El número mínimo de dígitos para los valores de la matriz.
     * @return La matriz generada.
     */
    public static double[][] matrixGenerator(int n, int minDigits) {
        Random rand = new Random();
        double[][] matrix = new double[n][n];
        int minVal = (int) Math.pow(10, (minDigits - 1));
        int maxVal = (int) Math.pow(10, minDigits) - 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = 1 + rand.nextInt(minVal, maxVal);
            }
        }

        return matrix;
    }

    /**
     * Procesa un algoritmo de multiplicación de matrices y guarda el resultado.
     *
     * @param size El tamaño de la matriz.
     * @param algorithmName El nombre del algoritmo.
     * @param algorithmExecutor La función que ejecuta el algoritmo.
     * @throws Exception Si ocurre un error durante el proceso.
     */
    private static void processAlgorithm(int size, String algorithmName, BiConsumer<double[][], double[][]> algorithmExecutor) throws Exception {
        // Cargar las matrices para el tamaño actual
        double[][] matrixA = loadMatrix("matrix_A_" + size + "x" + size);
        double[][] matrixB = loadMatrix("matrix_B_" + size + "x" + size);

        // Ejecutar algoritmo
        long startTime = System.nanoTime();
        algorithmExecutor.accept(matrixA, matrixB);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        // Guardar el resultado
        saveResult(size, algorithmName, executionTime);
    }

    /**
     * Guarda una matriz en un archivo.
     *
     * @param matrix La matriz a guardar.
     * @param filename El nombre del archivo.
     * @throws Exception Si ocurre un error durante el proceso de guardado.
     */
    public static void saveMatrix(double[][] matrix, String filename) throws Exception {
        MatrixFileHandler.saveMatrix(matrix, filename);
    }

    /**
     * Carga una matriz desde un archivo.
     *
     * @param filename El nombre del archivo.
     * @return La matriz cargada.
     * @throws Exception Si ocurre un error durante el proceso de carga.
     */
    public static double[][] loadMatrix(String filename) throws Exception {
        return MatrixFileHandler.loadMatrix(filename);
    }

    /**
     * Carga los resultados combinados de los archivos de resultados de diferentes lenguajes.
     *
     * @return La lista de resultados combinados.
     * @throws Exception Si ocurre un error durante el proceso de carga.
     */
    public static List<ResultData> loadCombinedResults() throws Exception {
        return ResultsManager.getCombinedResults();
    }

    /**
     * Muestra el gráfico con los resultados.
     *
     * @param results La lista de resultados a visualizar.
     */
    public static void displayChart(List<ResultData> results) {
        SwingUtilities.invokeLater(() -> {
            ResultsViewer viewer = new ResultsViewer(results);
            viewer.setVisible(true);
        });
    }
}
