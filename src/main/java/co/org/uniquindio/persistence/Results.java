package co.org.uniquindio.persistence;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Esta clase actúa como un contenedor para una lista de resultados, permitiendo su serialización y deserialización utilizando JAXB.
 *  @autor Marlon Stiven Espinosa Joaqui
 *  @autor Juan Esteban Quintero Rodriguez
 *  @autor Jesus Santiago Ramon Ramos
 */
@XmlRootElement(name = "results") // Definir la raíz del elemento XML
public class Results {
    private List<ResultData> results;

    /**
     * Obtiene la lista de resultados.
     * @return La lista de resultados.
     */
    @XmlElement(name = "result")
    public List<ResultData> getResults() {
        return results;
    }

    /**
     * Establece la lista de resultados.
     * @param results La lista de resultados.
     */
    public void setResults(List<ResultData> results) {
        this.results = results;
    }
}
