/**
 * Esta clase contiene un método para multiplicar dos matrices utilizando un enfoque ingenuo con desenrollado de bucles.
 * Este enfoque intenta mejorar el rendimiento desenrollando bucles para realizar múltiples operaciones en paralelo.
 *  @author Marlon Stiven Espinosa Joaqui
 *  @author Juan Esteban Quintero Rodriguez
 *  @author Jesus Santiago Ramon Ramos
 */
public class NaivLoopUnrollingFour {

    /**
     * Este método realiza la multiplicación de dos matrices utilizando un enfoque ingenuo con desenrollado de bucles.
     * @param matrizA La primera matriz a multiplicar.
     * @param matrizB La segunda matriz a multiplicar.
     * @param matrizRes La matriz en la que se almacenará el resultado de la multiplicación.
     * @param N El número de filas de la matriz A y el número de filas de la matriz resultado.
     * @param P El número de columnas de la matriz A y el número de filas de la matriz B.
     * @param M El número de columnas de la matriz B y el número de columnas de la matriz resultado.
     */
    public void algNaivLoopUnrollingFour(double[][] matrizA, double[][] matrizB, double[][] matrizRes, int N, int P, int M ) {
        int i, j, k;
        double aux;
        if (P % 4 == 0) {
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0.0;
                    for (k = 0; k < P; k++) {
                        aux += matrizA[i][k] * matrizB[k][j] + matrizA[i][k+1]*matrizB[k + 1][j]
                                + matrizA[i][k + 2] * matrizB[k +2][j] + matrizA[i][k + 3] * matrizB[k + 3][j];
                    }
                    matrizRes[i][j] = aux;
                }
            }
        } else if (P % 4 == 1) {
            int PP = P -1;
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0.0;
                    for (k = 0; k < PP; k += 4) {
                        aux += matrizA[i][k] * matrizB[k][j] + matrizA[i][k + 1] * matrizB[k + 1][j]
                                + matrizA[i][k + 2] * matrizB[k +2][j] + matrizA[i][k + 3] * matrizB[k + 3][j];
                    }
                    matrizRes[i][j] = aux + matrizA[i][PP] * matrizB[PP][j];
                }
            }
        } else if (P % 4 == 2) {
            int PP = P - 2;
            int PPP = P - 1;
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0.0;
                    for (k = 0; k < PP; k += 4) {
                        aux += matrizA[i][k] * matrizB[k][j] + matrizA[i][k+1] * matrizB[k+1][j]
                                + matrizA[i][k+2] * matrizB[k+2][j] + matrizA[i][k+3] * matrizB[k+3][j];
                    }
                    matrizRes[i][j] = aux + matrizA[i][PP] * matrizB[PP][j] + matrizA[i][PPP] * matrizB[PPP][j];
                }
            }
        } else {
            int PP = P - 3;
            int PPP = P - 2;
            int PPPP = P - 1;
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0.0;
                    for (k = 0; k < PP; k += 4) {
                        aux += matrizA[i][k] * matrizB[k][j] + matrizA[i][k+1] * matrizB[k+1][j]
                                + matrizA[i][k+2] * matrizB[k+2][j] + matrizA[i][k+3] * matrizB[k+3][j];
                    }
                    matrizRes[i][j] = aux + matrizA[i][PP] * matrizB[PP][j] + matrizA[i][PPP] * matrizB[PPP][j]
                            + matrizA[i][PPPP] * matrizB[PPPP][j];
                }
            }
        }
    }
}
