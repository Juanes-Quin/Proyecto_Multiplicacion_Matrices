package co.org.uniquindio.views;

import co.org.uniquindio.persistence.ResultData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Esta clase proporciona una interfaz gráfica para visualizar los resultados de la ejecución de algoritmos.
 * Utiliza JFreeChart para generar gráficos de barras comparativos.
 *  @autor Marlon Stiven Espinosa Joaqui
 *  @autor Juan Esteban Quintero Rodriguez
 *  @autor Jesus Santiago Ramon Ramos
 */
public class ResultsViewer extends JFrame {
    private int currentMatrixSize; // Tamaño actual de la matriz visualizada
    private String currentLanguage = "Ambos"; // Lenguaje actual visualizado
    private Map<Integer, List<ResultData>> groupedResults;
    private ChartPanel chartPanel;

    /**
     * Constructor que inicializa la vista de resultados con una lista de resultados.
     * @param results La lista de resultados a visualizar.
     */
    public ResultsViewer(List<ResultData> results) {
        super("Resultados de Algoritmos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        groupedResults = results.stream().collect(Collectors.groupingBy(ResultData::getSize));

        List<Integer> sortedSizes = new ArrayList<>(groupedResults.keySet());
        Collections.sort(sortedSizes); // Asegurar que los tamaños estén ordenados
        currentMatrixSize = sortedSizes.isEmpty() ? -1 : sortedSizes.get(0); // Inicializar con el menor tamaño disponible

        JComboBox<Integer> sizeSelector = new JComboBox<>(sortedSizes.toArray(new Integer[0]));
        sizeSelector.addActionListener(e -> {
            Integer selectedSize = (Integer) sizeSelector.getSelectedItem();
            if (selectedSize != null) {
                currentMatrixSize = selectedSize;
                updateChart();
            }
        });

        JComboBox<String> languageSelector = new JComboBox<>(new String[]{"Ambos", "Java", "Python"});
        languageSelector.addActionListener(e -> {
            String selectedLanguage = (String) languageSelector.getSelectedItem();
            if (selectedLanguage != null) {
                currentLanguage = selectedLanguage;
                updateChart();
            }
        });

        chartPanel = new ChartPanel(null);
        add(chartPanel, BorderLayout.CENTER);

        JPanel controlsPanel = new JPanel();
        controlsPanel.add(new JLabel("Seleccione el tamaño de la matriz:"));
        controlsPanel.add(sizeSelector);
        controlsPanel.add(new JLabel("Seleccione el lenguaje:"));
        controlsPanel.add(languageSelector);

        add(controlsPanel, BorderLayout.NORTH);

        updateChart(); // Cargar el gráfico inicial
    }

    /**
     * Actualiza el gráfico de barras basado en el tamaño de la matriz y el lenguaje seleccionados.
     */
    private void updateChart() {
        List<ResultData> resultsForSize = groupedResults.getOrDefault(currentMatrixSize, Collections.emptyList());
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Asegurarse de que los resultados se procesan en orden por algoritmo y lenguaje
        resultsForSize.stream()
                .collect(Collectors.groupingBy(ResultData::getAlgorithm))
                .forEach((algorithm, resultsByAlg) -> {
                    Map<String, Double> languageTimes = resultsByAlg.stream()
                            .collect(Collectors.groupingBy(ResultData::getLanguage,
                                    Collectors.averagingDouble(ResultData::getExecutionTime)));

                    if (currentLanguage.equals("Ambos") || currentLanguage.equals("Java")) {
                        dataset.addValue(languageTimes.getOrDefault("java", 0.0) / 1_000_000.0, "Java", algorithm);
                    }
                    if (currentLanguage.equals("Ambos") || currentLanguage.equals("Python")) {
                        dataset.addValue(languageTimes.getOrDefault("python", 0.0) / 1_000_000.0, "Python", algorithm);
                    }
                });

        JFreeChart chart = ChartFactory.createBarChart(
                "Comparación de Tiempos de Ejecución - Tamaño " + currentMatrixSize,
                "Algoritmo",
                "Tiempo (Milisegundos)",
                dataset,
                PlotOrientation.VERTICAL,
                true,  // include legend
                true,
                false);

        configureChart(chart);
        configureChartColors(chart, dataset);
        chartPanel.setChart(chart);
    }

    /**
     * Configura la apariencia del gráfico, incluyendo el ancho de las barras y la inclinación de las etiquetas.
     * @param chart El gráfico a configurar.
     */
    private void configureChart(JFreeChart chart) {
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // Ajustar el ancho máximo de la barra para que no sea demasiado delgado
        renderer.setMaximumBarWidth(0.20);  // Ajusta esto según lo delgado que quieras que sea cada barra

        // Establecer el margen entre las barras dentro de cada categoría a 0 para que estén pegadas
        renderer.setItemMargin(0.0);  // Esto eliminará el espacio entre las barras Java y Python dentro de cada algoritmo

        // Ajustar el margen entre grupos de barras para separar más los grupos
        plot.getDomainAxis().setCategoryMargin(0.30);  // Incrementa el espacio entre grupos de categorías

        // Ajustar la inclinación de los nombres de los algoritmos
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        chartPanel.setChart(chart);
    }

    /**
     * Configura los colores del gráfico, asignando rojo para Java y azul para Python.
     * @param chart El gráfico a configurar.
     * @param dataset El conjunto de datos del gráfico.
     */
    private void configureChartColors(JFreeChart chart, DefaultCategoryDataset dataset) {
        CategoryItemRenderer renderer = chart.getCategoryPlot().getRenderer();

        int javaIndex = dataset.getRowIndex("Java");
        if (javaIndex >= 0) {
            renderer.setSeriesPaint(javaIndex, Color.RED);
        }

        int pythonIndex = dataset.getRowIndex("Python");
        if (pythonIndex >= 0) {
            renderer.setSeriesPaint(pythonIndex, Color.BLUE);
        }
    }
}
