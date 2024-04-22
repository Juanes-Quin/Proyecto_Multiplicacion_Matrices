public class NaivOnArray {
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
