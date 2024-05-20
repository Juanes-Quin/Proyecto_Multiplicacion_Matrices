package co.org.uniquindio.persistence;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase proporciona métodos para gestionar y combinar resultados de diferentes lenguajes de programación.
 *  @autor Marlon Stiven Espinosa Joaqui
 *  @autor Juan Esteban Quintero Rodriguez
 *  @autor Jesus Santiago Ramon Ramos
 */
public class ResultsManager {

    private static final String JAVA_RESULTS_PATH = "src/main/resources/results/java_results.xml";
    private static final String PYTHON_RESULTS_PATH = "src/main/resources/results/python_results.xml";

    /**
     * Constructor privado para prevenir la instanciación de la clase de utilidad.
     */
    private ResultsManager() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * Obtiene una lista combinada de resultados de los archivos XML de resultados de Java y Python.
     * @return La lista combinada de resultados.
     * @throws Exception Si ocurre un error durante el proceso de lectura.
     */
    public static List<ResultData> getCombinedResults() throws Exception {
        List<ResultData> javaResults = readResults(JAVA_RESULTS_PATH);
        List<ResultData> pythonResults = readResults(PYTHON_RESULTS_PATH);

        List<ResultData> combinedResults = new ArrayList<>(javaResults);
        combinedResults.addAll(pythonResults);
        return combinedResults;
    }

    /**
     * Lee los resultados desde un archivo XML especificado.
     * @param filePath El path del archivo XML.
     * @return La lista de resultados leídos desde el archivo.
     * @throws Exception Si ocurre un error durante el proceso de lectura.
     */
    private static List<ResultData> readResults(String filePath) throws Exception {
        return ResultFileHandler.loadResults(filePath);
    }
}
