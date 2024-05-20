package co.org.uniquindio.persistence;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;

/**
 * Esta clase actúa como un contenedor para matrices, permitiendo su serialización y deserialización utilizando JAXB.
 *  @autor Marlon Stiven Espinosa Joaqui
 *  @autor Juan Esteban Quintero Rodriguez
 *  @autor Jesus Santiago Ramon Ramos
 */
@XmlRootElement(name = "matrix")
public class MatrixWrapper {
    private double[][] data;

    /**
     * Constructor sin parámetros necesario para la deserialización con JAXB.
     */
    public MatrixWrapper() { }

    /**
     * Constructor que inicializa el contenedor con la matriz dada.
     * @param data La matriz a ser contenida.
     */
    public MatrixWrapper(double[][] data) {
        this.data = data;
    }

    /**
     * Obtiene la matriz contenida.
     * @return La matriz contenida.
     */
    @XmlElement(name = "row")
    public double[][] getData() {
        return data;
    }

    /**
     * Establece la matriz contenida.
     * @param data La matriz a ser contenida.
     */
    public void setData(double[][] data) {
        this.data = data;
    }
}
