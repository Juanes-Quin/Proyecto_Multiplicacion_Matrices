package co.org.uniquindio.persistence;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "matrix")
public class MatrixWrapper {
    private double[][] data;

    public MatrixWrapper() { }

    public MatrixWrapper(double[][] data) {
        this.data = data;
    }

    @XmlElement(name = "row")
    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }
}

