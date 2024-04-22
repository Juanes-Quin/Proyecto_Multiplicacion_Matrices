package co.org.uniquindio.algoritmos;

public class NaivOnArray {
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

    public static void multiply(double[][] matrizA, double[][] matrizB) {
        int N = matrizA.length;
        int P = matrizB.length;
        int M = matrizB[0].length;
        double[][] matrizRes = new double[N][M];
        algNaivOnArray(matrizA, matrizB, matrizRes, N, P, M);
    }
}
