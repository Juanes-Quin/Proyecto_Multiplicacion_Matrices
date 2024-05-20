package co.org.uniquindio.algoritmos;

/**
 * Esta clase contiene métodos para multiplicar matrices utilizando el desenrollado de bucles con un factor de dos.
 * Este enfoque optimiza el rendimiento al reducir la sobrecarga del control de bucles.
 *  @autor Marlon Stiven Espinosa Joaqui
 *  @autor Juan Esteban Quintero Rodriguez
 *  @autor Jesus Santiago Ramon Ramos
 */
public class NaivLoopUnrollingTwo {

    /**
     * Este método realiza la multiplicación de dos matrices utilizando el desenrollado de bucles con un factor de dos.
     * @param matrizA La primera matriz a multiplicar.
     * @param matrizB La segunda matriz a multiplicar.
     * @param matrizRes La matriz resultado de la multiplicación.
     * @param N La dimensión de las filas de la matriz A.
     * @param P La dimensión de las columnas de la matriz A y filas de la matriz B.
     * @param M La dimensión de las columnas de la matriz B.
     */
    public static void algNaivLoopUnrollingTwo(double[][] matrizA, double[][] matrizB, double[][] matrizRes, int N, int P, int M) {
        int i, j, k;
        double aux;
        if (P % 2 == 0) {
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0.0;
                    for (k = 0; k < P; k += 2) {
                        aux += matrizA[i][k] * matrizB[k][j] + matrizA[i][k + 1] * matrizB[k + 1][j];
                    }
                    matrizRes[i][j] = aux;
                }
            }
        } else {
            int PP = P - 1;
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0.0;
                    for (k = 0; k < PP; k += 2) {
                        aux += matrizA[i][k] * matrizB[k][j] + matrizA[i][k + 1] * matrizB[k + 1][j];
                    }
                    matrizRes[i][j] = aux + matrizA[i][PP] * matrizB[PP][j];
                }
            }
        }
    }

    /**
     * Este método es un envoltorio para el método algNaivLoopUnrollingTwo.
     * Toma dos matrices y las multiplica utilizando el algoritmo de desenrollado de bucles.
     * @param matrizA La primera matriz a multiplicar.
     * @param matrizB La segunda matriz a multiplicar.
     */
    public static void multiply(double[][] matrizA, double[][] matrizB) {
        int N = matrizA.length;
        int P = matrizB.length;
        int M = matrizB[0].length;
        double[][] matrizRes = new double[N][M];
        algNaivLoopUnrollingTwo(matrizA, matrizB, matrizRes, N, P, M);
    }
}
