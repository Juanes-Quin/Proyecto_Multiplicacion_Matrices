public class WinogradOriginal {
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
