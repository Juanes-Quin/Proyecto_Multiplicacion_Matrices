package co.org.uniquindio.algoritmos;

/**
 * Esta clase contiene métodos para multiplicar matrices utilizando un enfoque secuencial por bloques.
 * Dividiendo las matrices en bloques más pequeños y realizando operaciones de multiplicación en cada bloque.
 *  @author Marlon Stiven Espinosa Joaqui
 *  @author Juan Esteban Quintero Rodriguez
 *  @author Jesus Santiago Ramon Ramos
 */
public class III_3_Sequential_Block {

    /**
     * Este método realiza la multiplicación de dos matrices utilizando un enfoque secuencial por bloques.
     * @param matrizA La primera matriz a multiplicar.
     * @param matrizB La segunda matriz a multiplicar.
     * @param size1 El tamaño de la matriz resultado en la dimensión 1.
     * @param size2 El tamaño de los bloques utilizados para la multiplicación.
     */
    public static void alg_III_3_SequentialBlock(double[][] matrizA, double[][] matrizB, int size1, int size2) {
        double [][] matrizRes = new double[size1][size2];
        for (int i1 = 0; i1 < size1; i1 += size2) {
            for (int j1 = 0; j1 < size1; j1 += size2) {
                for (int k1 = 0; k1 < size1; k1 += size2) {
                    for (int i = i1; i < i1 + size2 && i < size1; i++) {
                        for (int j = j1; j < j1 + size2 && j < size1; j++) {
                            for (int k = k1; k < k1 + size2 && k < size1; k++) {
                                matrizRes[i][j] += matrizA[i][k] * matrizB[k][j];
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Este método es un envoltorio para el método alg_III_3_SequentialBlock.
     * Toma dos matrices y las multiplica utilizando el algoritmo de bloque secuencial.
     * @param matrizA La primera matriz a multiplicar.
     * @param matrizB La segunda matriz a multiplicar.
     */
    public static void multiply(double[][] matrizA, double[][] matrizB) {
        int N = matrizA.length;
        int P = matrizB.length;
        alg_III_3_SequentialBlock(matrizA, matrizB, N, P);
    }
}
