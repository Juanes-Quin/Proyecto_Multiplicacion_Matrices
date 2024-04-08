/**
 * Esta clase implementa el algoritmo original de Winograd para la multiplicación de matrices.
 * El algoritmo de Winograd reduce la cantidad de operaciones requeridas para la multiplicación de matrices
 * mediante el uso de operaciones adicionales de preprocesamiento y posprocesamiento.
 *  @author Marlon Stiven Espinosa Joaqui
 *  @author Juan Esteban Quintero Rodriguez
 *  @author Jesus Santiago Ramon Ramos
 */
public class WinogradOriginal {
    /**
     * Este método implementa el algoritmo original de Winograd para la multiplicación de matrices.
     * @param matrizA La matriz A.
     * @param matrizB La matriz B.
     * @param matrizRes La matriz resultante.
     * @param N El número de filas de la matriz A y el número de columnas de la matriz resultante.
     * @param P El número de columnas de la matriz A y el número de filas de la matriz B.
     * @param M El número de filas de la matriz B y el número de filas de la matriz resultante.
     */
    public void algWinogradOriginal(double [][] matrizA, double [][] matrizB, double [][] matrizRes, int N, int P, int M ) {
        int i, j, k;
        double aux;
        int upsilon = P % 2;
        int gamma = P - upsilon;
        double[] y = new double[M];
        double[] z = new double[N];
        for (i = 0; i < N; i++) {
            aux = 0.0;
            for (j = 0; j < gamma; j += 2) {
                aux += matrizA[i][j] * matrizA[i][j + 1];
            }
            y[i] = aux;
        }
        for (i = 0; i < N; i++) {
            aux = 0.0;
            for (j = 0; j < gamma; j += 2) {
                aux += matrizB[j][i] * matrizB[j + 1][i];
            }
            z[i] = aux;
        }
        if (upsilon == 1) {
            int PP = P - 1;
            for (i = 0; i < M; i++) {
                for (k = 0; k < N; k++) {
                    aux = 0.0;
                    for (j = 0; j < gamma; j += 2) {
                        aux += (matrizA[i][j] + matrizB[j + 1][k])
                                * (matrizA[i][j + 1] + matrizB[j][k]);
                    }
                    matrizRes[i][k] = aux - y[i] - z[k] + matrizA[i][PP] * matrizB[PP][k];
                }
            }
        } else {
            for (i = 0; i < M; i++) {
                for (k = 0; k < N; k++) {
                    aux = 0.0;
                    for (j = 0; j < gamma; j += 2) {
                        aux += (matrizA[i][j] + matrizB[j + 1][k])
                                * (matrizA[i][j + 1] + matrizB[j][k]);
                    }
                    matrizRes[i][k] = aux - y[i] - z[k];
                }
            }
        }
        // Liberar el espacio de la memoria
        y = null;
        z = null;
    }
}
