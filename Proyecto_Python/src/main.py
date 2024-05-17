import numpy as np
import matplotlib.pyplot as plt
import tkinter as tk
from tkinter import ttk
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
import time

from algoritmos import NaivOnArray, NaivLoopUnrollingTwo, NaivLoopUnrollingFour, III_3_Sequential_Block, III_4_Parallel_Block, III_5_Enhanced_Parallel_Block, IV_3_Sequential_Block, IV_4_Parallel_Block, IV_5_Enhanced_Parallel_Block, V_3_Sequential_Block, V_4_Parallel_Block,StrassenNaiv, WinogradOriginal, WinogradScaled, StrassenWinograd

from persistence import ResultFileHandler, MatrixFileHandler, ResultsManager

from views import ResultsViewer

def matrix_generator(n, min_digits):
    max_val = 10 ** min_digits - 1
    return np.random.randint(1, max_val, size=(n, n))

def process_algorithm(size, algorithm_name, algorithm_executor):
    matrix_a = MatrixFileHandler.MatrixFileHandler.load_matrix(f"matrix_A_{size}x{size}")
    matrix_b = MatrixFileHandler.MatrixFileHandler.load_matrix(f"matrix_B_{size}x{size}")

    start_time = time.perf_counter_ns()
    algorithm_executor(matrix_a, matrix_b)
    end_time = time.perf_counter_ns()
    execution_time = end_time - start_time
    
    ResultFileHandler.ResultFileHandler.save_result(size, algorithm_name, execution_time)

def display_chart(results):
    app = ResultsViewer.ResultsViewer(results)
    app.mainloop()

if __name__ == "__main__":
    minDig = 7
    sizes = [64, 128]
    for size in sizes:
        # matrix_a = matrix_generator(size, minDig)
        # matrix_b = matrix_generator(size, minDig)
        # MatrixFileHandler.MatrixFileHandler.save_matrix(matrix_a, f"matrix_A_{size}x{size}")
        # MatrixFileHandler.MatrixFileHandler.save_matrix(matrix_a, f"matrix_B_{size}x{size}")

        process_algorithm(size, "NaivOnArray", NaivOnArray.multiply)
        # process_algorithm(size, "NaivLoopUnrollingTwo", NaivLoopUnrollingTwo.multiply)
        # process_algorithm(size, "NaivLoopUnrollingFour", NaivLoopUnrollingFour.multiply)
        # process_algorithm(size, "WinogradOriginal", WinogradOriginal.multiply)
        # process_algorithm(size, "WinogradScaled", WinogradScaled.multiply)
        # process_algorithm(size, "StrassenNaiv", StrassenNaiv.multiply)
        # process_algorithm(size, "StrassenWinograd", StrassenWinograd.multiply)
        # process_algorithm(size, "III_3_Sequential_Block", III_3_Sequential_Block.multiply)
        # process_algorithm(size, "III_4_Parallel_Block", III_4_Parallel_Block.multiply)
        # process_algorithm(size, "IV_3_Sequential_Block", IV_3_Sequential_Block.multiply)
        # process_algorithm(size, "IV_4_Parallel_Block", IV_4_Parallel_Block.multiply)
        # process_algorithm(size, "V_3_Sequential_Block", V_3_Sequential_Block.multiply)
        # process_algorithm(size, "V_4_Parallel_Block", V_4_Parallel_Block.multiply)
        # process_algorithm(size, "III_5_Enhanced_Parallel_Block", III_5_Enhanced_Parallel_Block.multiply)
        # process_algorithm(size, "IV_5_Enhanced_Parallel_Block", IV_5_Enhanced_Parallel_Block.multiply)
    
    results = ResultsManager.ResultsManager.get_combined_results()
    display_chart(results)
