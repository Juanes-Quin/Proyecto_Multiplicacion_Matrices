package co.org.uniquindio.algoritmos;

/**
 * Esta clase contiene métodos para multiplicar matrices utilizando un enfoque naive (directo) sobre un array.
 * Este enfoque utiliza bucles anidados para realizar la multiplicación de matrices sin optimizaciones adicionales.
 *  @autor Marlon Stiven Espinosa Joaqui
 *  @autor Juan Esteban Quintero Rodriguez
 *  @autor Jesus Santiago Ramon Ramos
 */
public class NaivOnArray {

    /**
     * Este método realiza la multiplicación de dos matrices utilizando un enfoque naive (directo).
     * @param matrizA La primera matriz a multiplicar.
     * @param matrizB La segunda matriz a multiplicar.
     * @param matrizRes La matriz resultado de la multiplicación.
     * @param N La dimensión de las filas de la matriz A.
     * @param P La dimensión de las columnas de la matriz A y filas de la matriz B.
     * @param M La dimensión de las columnas de la matriz B.
     */
    public static void algNaivOnArray(double[][] matrizA, double[][] matrizB, double[][] matrizRes, int N, int P, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrizRes[i][j] = 0.0;
                for (int k = 0; k < P; k++) {
                    matrizRes[i][j] += matrizA[i][k] * matrizB[k][j];
                }
            }
        }
    }

    /**
     * Este método es un envoltorio para el método algNaivOnArray.
     * Toma dos matrices y las multiplica utilizando el algoritmo naive (directo).
     * @param matrizA La primera matriz a multiplicar.
     * @param matrizB La segunda matriz a multiplicar.
     */
    public static void multiply(double[][] matrizA, double[][] matrizB) {
        int N = matrizA.length;
        int P = matrizB.length;
        int M = matrizB[0].length;
        double[][] matrizRes = new double[N][M];
        algNaivOnArray(matrizA, matrizB, matrizRes, N, P, M);
    }
}
