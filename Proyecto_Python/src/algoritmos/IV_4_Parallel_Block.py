from concurrent.futures import ThreadPoolExecutor

def block_multiply(matrizA, matrizB, matrizRes, i1, size1, size2):
    for j1 in range(0, size1, size2):
        for k1 in range(0, size1, size2):
            for i in range(i1 * size2, min((i1 + 1) * size2, size1)):
                for j in range(j1, min(j1 + size2, size1)):
                    for k in range(k1, min(k1 + size2, size1)):
                        matrizRes[i][k] += matrizA[i][j] * matrizB[j][k]

def alg_IV_4_Parallel_Block(matrizA, matrizB, size1, size2):
    matrizRes = [[0.0 for _ in range(size1)] for _ in range(size1)]
    num_blocks = (size1 + size2 - 1) // size2  # Calcula el número de bloques
    with ThreadPoolExecutor() as executor:
        # Envía cada bloque a un thread separado
        futures = [executor.submit(block_multiply, matrizA, matrizB, matrizRes, i1, size1, size2)
                for i1 in range(num_blocks)]
        for future in futures:
            future.result()  # Esperar a que todos los threads terminen
    return matrizRes

def multiply(matrizA, matrizB):
    N = len(matrizA)
    P = len(matrizB[0])
    alg_IV_4_Parallel_Block(matrizA, matrizB, N, P)

