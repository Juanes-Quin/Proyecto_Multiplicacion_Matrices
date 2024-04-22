package co.org.uniquindio.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class ResultsManager {
    private static final String JAVA_RESULTS_PATH = "resources/results/java_results.csv";
    private static final String PYTHON_RESULTS_PATH = "resources/results/python_results.csv";

    public static List<ResultData> getCombinedResults() throws IOException {
        List<ResultData> javaResults = readResults(JAVA_RESULTS_PATH, "Java");
        List<ResultData> pythonResults = readResults(PYTHON_RESULTS_PATH, "Python");

        List<ResultData> combinedResults = new ArrayList<>(javaResults);
        combinedResults.addAll(pythonResults);
        return combinedResults;
    }

    private static List<ResultData> readResults(String filePath, String language) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            return reader.lines()
                    .skip(1)  // Skip header
                    .map(line -> line.split(","))
                    .map(tokens -> new ResultData(
                            Integer.parseInt(tokens[0]),
                            tokens[1],
                            language,
                            Long.parseLong(tokens[3])
                    ))
                    .toList();
        }
    }
}
