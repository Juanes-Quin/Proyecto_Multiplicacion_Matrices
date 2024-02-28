public class IV_3_Sequential_Block {
    public  void alg_IV_3_Sequential_Block(double [][] matrizA, double [][] matrizB, int size1, int size2) {
        double[][] matrizRes = new double[size1][size2];
        for (int i1 = 0; i1 < size1; i1 += size2) {
            for (int j1 = 0; j1 < size1; j1 += size2) {
                for (int k1 = 0; k1 < size1; k1 += size2) {
                    for (int i = i1; i < i1 + size2 && i < size1; i++) {
                        for (int j = j1; j < j1 + size2 && j < size1; j++) {
                            for (int k = k1; k < k1 + size2 && k < size1; k++) {
                                matrizRes[i][k] += matrizA[i][j] * matrizB[j][k];
                            }
                        }
                    }
                }
            }
        }
    }
}
