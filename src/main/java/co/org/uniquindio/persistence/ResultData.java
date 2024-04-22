package co.org.uniquindio.persistence;

public class ResultData {
    private int size;
    private String algorithm;
    private String language;
    private long executionTime;

    // Constructor
    public ResultData(int size, String algorithm, String language, long executionTime) {
        this.size = size;
        this.algorithm = algorithm;
        this.language = language;
        this.executionTime = executionTime;
    }

    // Getters y Setters
    public int getSize() {
        return size;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getLanguage() {
        return language;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }
}
