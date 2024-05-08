package co.org.uniquindio;

import co.org.uniquindio.algoritmos.*;
import co.org.uniquindio.persistence.*;
import co.org.uniquindio.views.ResultsViewer;
import javax.swing.*;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class Main {
    public static void main(String[] args) throws Exception {

        int minDigits = 6;
        int[] sizes = {8, 16, 32, 64, 128, 256, 512, 1024};

//        // Probar cada algoritmo con cada tamaño de matriz
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

    private static void saveResult(int size, String algorithm, long executionTime) throws Exception {
        ResultFileHandler.saveResult(size, algorithm, executionTime);
    }

    public static double[][] matrixGenerator(int n,int minDigits){
        Random rand = new Random();
        double[][] matrix = new double[n][n];
        int maxVal = (int) Math.pow(10, minDigits) - 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = 1 + rand.nextInt(maxVal);
            }
        }

        return matrix;
    }

    private static void processAlgorithm(int size, String algorithmName, BiConsumer<double[][], double[][]> algorithmExecutor) throws Exception {
        // Cargar las matrices para el tamaño actual
        double[][] matrixA = loadMatrix("matrix_A_" + size + "x" + size);
        double[][] matrixB = loadMatrix("matrix_B_" + size + "x" + size);

        // Ejecutar algoritmo
        long startTime = System.nanoTime();
        algorithmExecutor.accept(matrixA, matrixB);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        // Guardar el resultado en el archivo CSV
        saveResult(size, algorithmName, executionTime);
    }


    public static void saveMatrix(double[][] matrix, String filename) throws Exception {
        MatrixFileHandler.saveMatrix(matrix, filename);
    }

    public static double[][] loadMatrix(String filename) throws Exception {
        return MatrixFileHandler.loadMatrix(filename);
    }

    public static List<ResultData> loadCombinedResults() throws Exception {
        return ResultsManager.getCombinedResults();
    }

    public static void displayChart(List<ResultData> results) {
        SwingUtilities.invokeLater(() -> {
            ResultsViewer viewer = new ResultsViewer(results);
            viewer.setVisible(true);
        });
    }

}