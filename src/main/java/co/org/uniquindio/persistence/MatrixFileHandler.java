package persistence;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MatrixFileHandler {
    // Define la ruta de directorio predeterminada dentro de la clase
    private static final String DEFAULT_DIRECTORY = "resources/matrices";

    public static void saveMatrix(double[][] matrix, String filename) throws IOException {
        // Usa el directorio predeterminado para guardar las matrices
        final Path path = Paths.get(DEFAULT_DIRECTORY);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DEFAULT_DIRECTORY + "/" + filename))) {
            for (double[] ints : matrix) {
                for (int j = 0; j < ints.length; j++) {
                    if (j > 0) writer.write(",");
                    writer.write(Double.toString(ints[j]));
                }
                writer.newLine();
            }
        }
    }

    public static double[][] loadMatrix(String filename) throws IOException {
        java.nio.file.Path path = Paths.get(DEFAULT_DIRECTORY, filename);
        double[][] matrix = null;
        java.util.List<String> lines = Files.readAllLines(path);

        if (!lines.isEmpty()) {
            int n = lines.size();
            matrix = new double[n][n];

            for (int i = 0; i < n; i++) {
                String[] values = lines.get(i).split(",");
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Double.parseDouble(values[j]);
                }
            }
        }

        return matrix;
    }
}
