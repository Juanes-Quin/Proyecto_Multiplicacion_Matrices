package co.org.uniquindio.algoritmos;

/**
 * Esta clase contiene métodos para multiplicar matrices utilizando el algoritmo original de Winograd.
 * Este enfoque optimiza la multiplicación de matrices al reducir la cantidad de operaciones multiplicativas necesarias.
 *  @autor Marlon Stiven Espinosa Joaqui
 *  @autor Juan Esteban Quintero Rodriguez
 *  @autor Jesus Santiago Ramon Ramos
 */
public class WinogradOriginal {

    /**
     * Este método realiza la multiplicación de dos matrices utilizando el algoritmo original de Winograd.
     * @param matrizA La primera matriz a multiplicar.
     * @param matrizB La segunda matriz a multiplicar.
     * @param matrizRes La matriz resultado de la multiplicación.
     * @param N La dimensión de las filas de la matriz A.
     * @param P La dimensión de las columnas de la matriz A y filas de la matriz B.
     * @param M La dimensión de las columnas de la matriz B.
     */
    public static void algWinogradOriginal(double[][] matrizA, double[][] matrizB, double[][] matrizRes, int N, int P, int M) {
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

    /**
     * Este método es un envoltorio para el método algWinogradOriginal.
     * Toma dos matrices y las multiplica utilizando el algoritmo original de Winograd.
     * @param matrizA La primera matriz a multiplicar.
     * @param matrizB La segunda matriz a multiplicar.
     */
    public static void multiply(double[][] matrizA, double[][] matrizB) {
        int N = matrizA.length;
        int P = matrizB.length;
        int M = matrizB[0].length;
        double[][] matrizRes = new double[N][M];
        algWinogradOriginal(matrizA, matrizB, matrizRes, N, P, M);
    }
}
