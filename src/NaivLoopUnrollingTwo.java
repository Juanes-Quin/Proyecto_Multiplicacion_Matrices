/**
 * Esta clase contiene un método para multiplicar dos matrices utilizando un enfoque ingenuo con desenrollado de bucles.
 * Este enfoque intenta mejorar el rendimiento desenrollando bucles para realizar múltiples operaciones en paralelo.
 *  @author Marlon Stiven Espinosa Joaqui
 *  @author Juan Esteban Quintero Rodriguez
 *  @author Jesus Santiago Ramon Ramos
 */
public class NaivLoopUnrollingTwo {
    /**
     * Este método realiza la multiplicación de dos matrices utilizando un enfoque ingenuo con desenrollado de bucles.
     * @param matrizA La primera matriz a multiplicar.
     * @param matrizB La segunda matriz a multiplicar.
     * @param matrizRes La matriz en la que se almacenará el resultado de la multiplicación.
     * @param N El número de filas de la matriz A y el número de filas de la matriz resultado.
     * @param P El número de columnas de la matriz A y el número de filas de la matriz B.
     * @param M El número de columnas de la matriz B y el número de columnas de la matriz resultado.
     */

    public void algNaivLoopUnrollingTwo(double[][] matrizA, double[][] matrizB, double[][] matrizRes, int N, int P, int M) {
        int i, j, k;
        double aux;
        if (P % 2 == 0 ) {
            for (i = 0; i < N; i++){
                for (j = 0; j < M; j++ ){
                    aux = 0.0;
                    for (k = 0 ; k < P; k += 2){
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
                        aux += matrizA[i][k] * matrizB[k][j] + matrizA[i][k +1] * matrizB[k + 1][j];
                    }
                }
            }
        }
    }
}
