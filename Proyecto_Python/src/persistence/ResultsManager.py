from typing import List
from pathlib import Path
from persistence import ResultFileHandler
from persistence.ResultData import ResultData

class ResultsManager:
    JAVA_RESULTS_PATH = Path(__file__).resolve().parent.parent.parent.parent /"src/main/resources/results/java_results.xml"
    PYTHON_RESULTS_PATH = Path(__file__).resolve().parent.parent.parent.parent /"src/main/resources/results/python_results.xml"

    @staticmethod
    def get_combined_results() -> List[ResultData]:
        java_results = ResultsManager.read_results(ResultsManager.JAVA_RESULTS_PATH)
        python_results = ResultsManager.read_results(ResultsManager.PYTHON_RESULTS_PATH)

        combined_results = java_results + python_results  # Simplemente sumamos las listas en Python
        return combined_results

    @staticmethod
    def read_results(file_path: Path) -> List[ResultData]:
        # Suponiendo que ResultFileHandler.load_results ahora acepta Path y devuelve una lista de ResultData
        results = ResultFileHandler.ResultFileHandler.load_results(file_path)
        return results.results if results else []