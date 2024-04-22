public class NaivLoopUnrollingFour {
    public void algNaivLoopUnrollingFour(double[][] matrizA, double[][] matrizB, double[][] matrizRes, int N, int P, int M ) {
        int i, j, k;
        double aux;
        if (P % 4 == 0) {
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0.0;
                    for (k = 0; k < P; k+=4) {
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
