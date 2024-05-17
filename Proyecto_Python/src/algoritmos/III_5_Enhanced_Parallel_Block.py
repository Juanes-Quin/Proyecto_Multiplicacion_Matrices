from concurrent.futures import ThreadPoolExecutor

def block_multiply_section(matrizA, matrizB, matrizRes, start, end, size2):
    size1 = len(matrizA)
    for i1 in range(start, end, size2):
        for j1 in range(0, size1, size2):
            for k1 in range(0, size1, size2):
                for i in range(i1, min(i1 + size2, size1)):
                    for j in range(j1, min(j1 + size2, size1)):
                        for k in range(k1, min(k1 + size2, size1)):
                            matrizRes[i][j] += matrizA[i][k] * matrizB[k][j]

def alg_III_5_Enhanced_Parallel_Block(matrizA, matrizB, size1, size2):
    matrizRes = [[0.0 for _ in range(size1)] for _ in range(size1)]
    mid_point = size1 // 2
    with ThreadPoolExecutor(max_workers=2) as executor:
        executor.submit(block_multiply_section, matrizA, matrizB, matrizRes, 0, mid_point, size2)
        executor.submit(block_multiply_section, matrizA, matrizB, matrizRes, mid_point, size1, size2)
    return matrizRes

def multiply(matrizA, matrizB):
    N = len(matrizA)
    P = len(matrizB[0])
    alg_III_5_Enhanced_Parallel_Block(matrizA, matrizB, N, P)
