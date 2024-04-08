/**
 * Esta clase contiene un método para multiplicar dos matrices utilizando un enfoque ingenuo mediante arrays.
 *  @author Marlon Stiven Espinosa Joaqui
 *  @author Juan Esteban Quintero Rodriguez
 *  @author Jesus Santiago Ramon Ramos
 */
public class NaivOnArray {

    /**
     * Este método realiza la multiplicación de dos matrices utilizando un enfoque ingenuo mediante arrays.
     * @param matrizA La primera matriz a multiplicar.
     * @param matrizB La segunda matriz a multiplicar.
     * @param matrizRes La matriz en la que se almacenará el resultado de la multiplicación.
     * @param N El número de filas de la matriz A y el número de filas de la matriz resultado.
     * @param P El número de columnas de la matriz A y el número de filas de la matriz B.
     * @param M El número de columnas de la matriz B y el número de columnas de la matriz resultado.
     */
    public void algNaivOnArray(double[][] matrizA, double[][] matrizB, double[][] matrizRes, int N, int P, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrizRes[i][j] = 0.0;
                for (int k = 0; k < P; k++) {
                    matrizRes[i][j] += matrizA[i][k] * matrizB[k][j];
                }
            }
        }
    }
}
