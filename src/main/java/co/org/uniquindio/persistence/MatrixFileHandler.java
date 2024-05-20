package co.org.uniquindio.persistence;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Esta clase proporciona métodos para guardar y cargar matrices en archivos XML utilizando JAXB.
 * Es una clase de utilidad que no debe ser instanciada.
 *  @autor Marlon Stiven Espinosa Joaqui
 *  @autor Juan Esteban Quintero Rodriguez
 *  @autor Jesus Santiago Ramon Ramos
 */
public class MatrixFileHandler {

    private static final String DEFAULT_DIRECTORY = "src/main/resources/matrices";

    /**
     * Constructor privado para prevenir la instanciación de la clase de utilidad.
     */
    private MatrixFileHandler() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * Guarda una matriz en un archivo XML.
     * @param matrix La matriz a guardar.
     * @param filename El nombre del archivo donde se guardará la matriz.
     * @throws Exception Si ocurre un error durante el proceso de guardado.
     */
    public static void saveMatrix(double[][] matrix, String filename) throws Exception {
        filename = filename.endsWith(".xml") ? filename : filename + ".xml";
        Path path = Paths.get(DEFAULT_DIRECTORY);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(MatrixWrapper.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        MatrixWrapper wrapper = new MatrixWrapper(matrix);
        marshaller.marshal(wrapper, new File(path.resolve(filename).toUri()));
    }

    /**
     * Carga una matriz desde un archivo XML.
     * @param filename El nombre del archivo desde donde se cargará la matriz.
     * @return La matriz cargada.
     * @throws Exception Si ocurre un error durante el proceso de carga.
     */
    public static double[][] loadMatrix(String filename) throws Exception {
        filename = filename.endsWith(".xml") ? filename : filename + ".xml";
        Path path = Paths.get(DEFAULT_DIRECTORY, filename);

        JAXBContext jaxbContext = JAXBContext.newInstance(MatrixWrapper.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        MatrixWrapper wrapper = (MatrixWrapper) unmarshaller.unmarshal(new File(path.toUri()));

        return wrapper.getData();
    }
}
