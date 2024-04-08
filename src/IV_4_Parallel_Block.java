import java.util.stream.IntStream;
/**
 * Esta clase contiene un método para multiplicar dos matrices utilizando un enfoque secuencial por bloques.
 * Dividiendo las matrices en bloques más pequeños y realizando operaciones de multiplicación en cada bloque.
 *  @author Marlon Stiven Espinosa Joaqui
 *  @author Juan Esteban Quintero Rodriguez
 *  @author Jesus Santiago Ramon Ramos
 */
public class IV_4_Parallel_Block {

    /**
     * Este método realiza la multiplicación de dos matrices utilizando un enfoque secuencial por bloques en paralelo.
     * @param matrizA La primera matriz a multiplicar.
     * @param matrizB La segunda matriz a multiplicar.
     * @param size1 El tamaño de la matriz resultado en la dimensión 1.
     * @param size2 El tamaño de los bloques utilizados para la multiplicación.
     */
    public void alg_IV_4_Parallel_Block(double[][] matrizA, double[][] matrizB, int size1, int size2) {
        double[][] matrizRes = new double[size1][size2];
        IntStream.range(0, size1 / size2).parallel().forEach(i1 -> {
            for (int j1 = 0; j1 < size1; j1 += size2) {
                for (int k1 = 0; k1 < size1; k1 += size2) {
                    for (int i = i1 * size2; i < (i1 + 1) * size2 && i < size1; i++) {
                        for (int j = j1; j < j1 + size2 && j < size1; j++) {
                            for (int k = k1; k < k1 + size2 && k < size1; k++) {
                                matrizRes[i][k] += matrizA[i][j] * matrizB[j][k];
                            }
                        }
                    }
                }
            }
        });
    }
}
