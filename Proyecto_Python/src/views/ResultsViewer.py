import matplotlib.pyplot as plt
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
import tkinter as tk
from tkinter import ttk
from collections import defaultdict

class ResultsViewer(tk.Tk):
    def __init__(self, results):
        super().__init__()
        self.title("Resultados de Algoritmos")
        self.geometry("800x600")

        self.results = self.group_results_by_size(results)
        self.current_matrix_size = next(iter(self.results.keys()), None)

        # Caja para seleccionar el tamaño de la matriz
        controls_panel = tk.Frame(self)
        controls_panel.pack(pady=10)

        tk.Label(controls_panel, text="Seleccione el tamaño de la matriz:", font=('Helvetica', 10)).pack(side=tk.LEFT)
        self.size_selector = ttk.Combobox(controls_panel, values=list(self.results.keys()), state="readonly")
        self.size_selector.pack(side=tk.LEFT, padx=10)
        self.size_selector.bind("<<ComboboxSelected>>", self.update_chart)

        # Configurar el panel de matplotlib
        self.fig, self.ax = plt.subplots()
        self.canvas = FigureCanvasTkAgg(self.fig, self)  # Area de dibujo de Tk
        self.canvas.get_tk_widget().pack(fill=tk.BOTH, expand=True)

        if self.current_matrix_size:
            self.size_selector.set(self.current_matrix_size)
            self.update_chart()

    def group_results_by_size(self, results):
        grouped = defaultdict(list)
        for result in results:
            grouped[result.size].append(result)
        return grouped

    def update_chart(self, event=None):
        size = int(self.size_selector.get())
        results_for_size = self.results.get(size, [])
        self.ax.clear()  # Limpiar gráficos anteriores por si algo

        dataset = defaultdict(lambda: {'java': 0.0, 'python': 0.0})
        for result in results_for_size:
            dataset[result.algorithm][result.language] = result.executionTime / 1_000_000

        algorithms = list(dataset.keys())
        java_times = [dataset[algo]['java'] for algo in algorithms]
        python_times = [dataset[algo]['python'] for algo in algorithms]

        bar_width = 0.35
        x = range(len(algorithms))

        self.ax.bar(x, java_times, width=bar_width, label='Java', color='red')
        self.ax.bar([p + bar_width for p in x], python_times, width=bar_width, label='Python', color='blue')

        self.ax.set_xlabel('Algoritmos')
        self.ax.set_ylabel('Tiempo (Milisegundos)')
        self.ax.set_title(f'Comparación de Tiempos de Ejecución - Tamaño {size}', fontweight='bold')
        self.ax.set_xticks([p + bar_width / 2 for p in x])
        self.ax.set_xticklabels(algorithms, rotation=45, ha="right")
        self.ax.legend()
        self.ax.grid(True, which='both', linestyle='--', linewidth=0.5)
        self.fig.tight_layout()


        self.canvas.draw()

