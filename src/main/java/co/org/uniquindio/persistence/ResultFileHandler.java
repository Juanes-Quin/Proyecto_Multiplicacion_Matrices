package persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ResultFileHandler {

    private static final String LANGUAGE = "python";
    private static final String DEFAULT_DIRECTORY = "resources/results";
    private static final String FILE_NAME = LANGUAGE + "_results.csv";
    private static final Path filePath = Paths.get(DEFAULT_DIRECTORY, FILE_NAME);
    private static boolean initialized = false;

    /**
     * Initializes the result file and writes the header if the file does not already exist.
     */
    public static void initializeResultFile() throws IOException {
        if (!initialized) {
            if (Files.notExists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            String header = "Size,Algorithm,Language,ExecutionTime\n";
            Files.write(filePath, header.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            initialized = true;
        }
    }

    /**
     * Save a result line to the results file.
     *
     * @param size The size of the matrix.
     * @param algorithm The name of the algorithm.
     * @param executionTime The execution time of the algorithm.
     */
    public static void saveResult(int size, String algorithm, long executionTime) throws IOException {
        initializeResultFile();
        String resultLine = String.format("%d,%s,%s,%d %n", size, algorithm, LANGUAGE, executionTime);
        Files.write(filePath, resultLine.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
    }
}

