import persistence.MatrixFileHandler;
import persistence.ResultData;
import persistence.ResultFileHandler;
import persistence.ResultsManager;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {

        int minDigits = 6;
        int[] sizes = {8, 16, 32, 64, 128, 256, 512, 1024};

        // Probar cada algoritmo con cada tamaño de matriz
        for (int size : sizes) {
            // Generar matrices
            double[][] matrixA = matrixGenerator(size, minDigits);
            double[][] matrixB = matrixGenerator(size, minDigits);

            // Guardar matrices
            saveMatrix(matrixA, "matrix_A_" + size + "x" + size + ".csv");
            saveMatrix(matrixB, "matrix_B_" + size + "x" + size + ".csv");

            // Cargar las matrices para el tamaño actual
            matrixA = loadMatrix("matrix_A_" + size + "x" + size + ".csv");
            matrixB = loadMatrix("matrix_B_" + size + "x" + size + ".csv");

            // Ejecutar y mostrar resultados del algoritmo NaivOnArray
            long startTime = System.nanoTime();
            NaivOnArray naiveOnArray = new NaivOnArray();
            naiveOnArray.algNaivOnArray(matrixA, matrixB,new double[size][size],size, size, size);
            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;

            // Guardar el resultado en el archivo CSV
            saveResult(size, "NaivOnArray", executionTime);

            // Ejecutar y mostrar resultados del algoritmo NaivLoopUnrollingTwo
            startTime = System.nanoTime();
            NaivLoopUnrollingTwo naivLoopUnrollingTwo = new NaivLoopUnrollingTwo();
            naivLoopUnrollingTwo.algNaivLoopUnrollingTwo(matrixA, matrixB,new double[size][size],size, size, size);
            endTime = System.nanoTime();
            executionTime = endTime - startTime;

            saveResult(size, "NaivLoopUnrollingTwo", executionTime);

            // Ejecutar y mostrar resultados del algoritmo NaivLoopUnrollingFour
            startTime = System.nanoTime();
            NaivLoopUnrollingFour naivLoopUnrollingFour = new NaivLoopUnrollingFour();
            naivLoopUnrollingFour.algNaivLoopUnrollingFour(matrixA, matrixB,new double[size][size],size, size, size);
            endTime = System.nanoTime();
            executionTime = endTime - startTime;

            saveResult(size, "NaivLoopUnrollingFour", executionTime);

            // Ejecutar y mostrar resultados del algoritmo WinogradOriginal
            startTime = System.nanoTime();
            WinogradOriginal winogradOriginal = new WinogradOriginal();
            winogradOriginal.algWinogradOriginal(matrixA, matrixB,new double[size][size],size, size, size);
            endTime = System.nanoTime();
            executionTime = endTime - startTime;

            saveResult(size, "WinogradOriginal", executionTime);

            // Ejecutar y mostrar resultados del algoritmo WinogradScaled
            startTime = System.nanoTime();
            WinogradScaled winogradScaled = new WinogradScaled();
            winogradScaled.algWinogradScaled(matrixA, matrixB,new double[size][size],size, size, size);
            endTime = System.nanoTime();
            executionTime = endTime - startTime;

            saveResult(size, "WinogradScaled", executionTime);

            // Ejecutar y mostrar resultados del algoritmo StrassenNaiv
            startTime = System.nanoTime();
            StrassenNaiv strassenNaiv = new StrassenNaiv();
            strassenNaiv.algStrassenNaiv(matrixA, matrixB,new double[size][size],size, size, size);
            endTime = System.nanoTime();
            executionTime = endTime - startTime;

            saveResult(size, "StrassenNaiv", executionTime);

            // Ejecutar y mostrar resultados del algoritmo StrassenWinograd
            startTime = System.nanoTime();
            StrassenWinograd strassenWinograd = new StrassenWinograd();
            strassenWinograd.algStrassenWinograd(matrixA, matrixB,new double[size][size],size, size, size);
            endTime = System.nanoTime();
            executionTime = endTime - startTime;

            saveResult(size, "StrassenWinograd", executionTime);

            // Ejecutar y mostrar resultados del algoritmo III 3 SequentialBlock
            startTime = System.nanoTime();
            III_3_Sequential_Block iii_3_sequential_block = new III_3_Sequential_Block();
            iii_3_sequential_block.alg_III_3_SequentialBlock(matrixA, matrixB, size, size);
            endTime = System.nanoTime();
            executionTime = endTime - startTime;

            saveResult(size, "III_3_Sequential_Block", executionTime);

            // Ejecutar y mostrar resultados del algoritmo III 4 ParallelBlock
            startTime = System.nanoTime();
            III_4_Parallel_Block iii_4_parallel_block = new III_4_Parallel_Block();
            iii_4_parallel_block.alg_III_4_Parallel_Block(matrixA, matrixB, size, size);
            endTime = System.nanoTime();
            executionTime = endTime - startTime;

            saveResult(size, "III_4_Parallel_Block", executionTime);

            // Ejecutar y mostrar resultados del algoritmo III 5 Enhanced Parallel Block
            //startTime = System.nanoTime();
            //III_5_Enhanced_Parallel_Block iii_5_enhanced_parallel_block = new III_5_Enhanced_Parallel_Block();
            //iii_5_enhanced_parallel_block.alg_III_5_Enhanced_Parallel_Block(matrixA, matrixB, size, size);
            //endTime = System.nanoTime();
            //executionTime = endTime - startTime;

            //saveResult(size, "III_5_Enhanced_Parallel_Block", executionTime);

            // Ejecutar y mostrar resultados del algoritmo IV 3 SequentialBlock
            startTime = System.nanoTime();
            IV_3_Sequential_Block iv_3_sequential_block = new IV_3_Sequential_Block();
            iv_3_sequential_block.alg_IV_3_Sequential_Block(matrixA, matrixB, size, size);
            endTime = System.nanoTime();
            executionTime = endTime - startTime;

            saveResult(size, "IV_3_Sequential_Block", executionTime);

            // Ejecutar y mostrar resultados del algoritmo IV 4 ParallelBlock
            startTime = System.nanoTime();
            IV_4_Parallel_Block iv_4_parallel_block = new IV_4_Parallel_Block();
            iv_4_parallel_block.alg_IV_4_Parallel_Block(matrixA, matrixB, size, size);
            endTime = System.nanoTime();
            executionTime = endTime - startTime;

            saveResult(size, "IV_4_Parallel_Block", executionTime);

            // Ejecutar y mostrar resultados del algoritmo IV 5 Enhanced Parallel Block
            //startTime = System.nanoTime();
            //IV_5_Enhanced_Parallel_Block iv_5_enhanced_parallel_block = new IV_5_Enhanced_Parallel_Block();
            //iv_5_enhanced_parallel_block.alg_IV_5_Enhanced_Parallel_Block(matrixA, matrixB, size, size);
            //endTime = System.nanoTime();
            //executionTime = endTime - startTime;

            //saveResult(size, "IV_5_Enhanced_Parallel_Block", executionTime);

            // Ejecutar y mostrar resultados del algoritmo V 3 SequentialBlock
            startTime = System.nanoTime();
            V_3_Sequential_Block v_3_sequential_block = new V_3_Sequential_Block();
            v_3_sequential_block.alg_V_3_Sequential_Block(matrixA, matrixB, size, size);
            endTime = System.nanoTime();
            executionTime = endTime - startTime;

            saveResult(size, "V_3_Sequential_Block", executionTime);

            // Ejecutar y mostrar resultados del algoritmo V 4 ParallelBlock
            startTime = System.nanoTime();
            V_4_Parallel_Block v_4_parallel_block = new V_4_Parallel_Block();
            v_4_parallel_block.alg_V_4_ParallelBlockTres(matrixA, matrixB, size, size);
            endTime = System.nanoTime();
            executionTime = endTime - startTime;

            saveResult(size, "V_4_Parallel_Block", executionTime);

        }

        // Cargar los resultados combinados
        List<ResultData> results = loadCombinedResults();

    }

    private static void saveResult(int size, String algorithm, long executionTime) throws IOException {
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

    public static void saveMatrix(double[][] matrix, String filename) throws IOException {
        MatrixFileHandler.saveMatrix(matrix, filename);
    }

    public static double[][] loadMatrix(String filename) throws IOException {
        return MatrixFileHandler.loadMatrix(filename);
    }

    public static List<ResultData> loadCombinedResults() throws IOException {
        return ResultsManager.getCombinedResults();
    }

    public static void displayChart(List<ResultData> results) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Añadir datos de Java y Python al dataset
        for (ResultData data : results) {
            dataset.addValue(data.getExecutionTime(), data.getLanguage(), data.getAlgorithm() + " " + data.getSize());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Comparación de Tiempos de Ejecución",
                "Algoritmo y Tamaño",
                "Tiempo (Nanosegundos)",
                dataset,
                PlotOrientation.VERTICAL,
                true,   // include legend
                true,
                false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        JFrame frame = new JFrame();
        frame.setContentPane(chartPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}