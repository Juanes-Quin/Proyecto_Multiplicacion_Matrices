from concurrent.futures import ThreadPoolExecutor, as_completed

def block_multiply(matrizA, matrizB, matrizRes, i1, size1, size2):
    for j1 in range(0, size1, size2):
        for k1 in range(0, size1, size2):
            for i in range(i1, min(i1 + size2, size1)):
                for j in range(j1, min(j1 + size2, size1)):
                    for k in range(k1, min(k1 + size2, size1)):
                        matrizRes[i][j] += matrizA[i][k] * matrizB[k][j]

def alg_III_4_Parallel_Block(matrizA, matrizB, size1, size2):
    matrizRes = [[0.0 for _ in range(size1)] for _ in range(size1)]
    with ThreadPoolExecutor() as executor:
        futures = [executor.submit(block_multiply, matrizA, matrizB, matrizRes, i1, size1, size2)
                for i1 in range(0, size1, size2)]
        for future in as_completed(futures):
            pass  # Just waiting for all futures to complete
    return matrizRes

def multiply(matrizA, matrizB):
    N = len(matrizA)
    P = len(matrizB[0])
    alg_III_4_Parallel_Block(matrizA, matrizB, N, P)

