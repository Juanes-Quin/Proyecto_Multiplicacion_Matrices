package co.org.uniquindio.persistence;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;

/**
 * Esta clase representa los datos del resultado de la ejecución de un algoritmo, incluyendo el tamaño de la matriz,
 * el nombre del algoritmo, el lenguaje utilizado y el tiempo de ejecución.
 *  @autor Marlon Stiven Espinosa Joaqui
 *  @autor Juan Esteban Quintero Rodriguez
 *  @autor Jesus Santiago Ramon Ramos
 */
@XmlAccessorType(XmlAccessType.FIELD) // Para que JAXB sepa que debe acceder a los campos directamente
public class ResultData {

    @XmlElement(required = true)
    private int size;

    @XmlElement(required = true)
    private String algorithm;

    @XmlElement(required = true)
    private String language;

    @XmlElement(required = true)
    private long executionTime;

    /**
     * Constructor sin argumentos necesario para la deserialización con JAXB.
     */
    public ResultData() {
    }

    /**
     * Constructor que inicializa todos los campos de la clase.
     * @param size El tamaño de la matriz.
     * @param algorithm El nombre del algoritmo utilizado.
     * @param language El lenguaje de programación utilizado.
     * @param executionTime El tiempo de ejecución del algoritmo.
     */
    public ResultData(int size, String algorithm, String language, long executionTime) {
        this.size = size;
        this.algorithm = algorithm;
        this.language = language;
        this.executionTime = executionTime;
    }

    /**
     * Obtiene el tamaño de la matriz.
     * @return El tamaño de la matriz.
     */
    public int getSize() {
        return size;
    }

    /**
     * Establece el tamaño de la matriz.
     * @param size El tamaño de la matriz.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Obtiene el nombre del algoritmo utilizado.
     * @return El nombre del algoritmo.
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * Establece el nombre del algoritmo utilizado.
     * @param algorithm El nombre del algoritmo.
     */
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Obtiene el lenguaje de programación utilizado.
     * @return El lenguaje de programación.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Establece el lenguaje de programación utilizado.
     * @param language El lenguaje de programación.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Obtiene el tiempo de ejecución del algoritmo.
     * @return El tiempo de ejecución en milisegundos.
     */
    public long getExecutionTime() {
        return executionTime;
    }

    /**
     * Establece el tiempo de ejecución del algoritmo.
     * @param executionTime El tiempo de ejecución en milisegundos.
     */
    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }
}
