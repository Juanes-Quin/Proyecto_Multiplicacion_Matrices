public class NaivLoopUnrollingTwo {
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
