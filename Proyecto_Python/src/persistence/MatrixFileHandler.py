import os
from pathlib import Path
import xml.etree.ElementTree as ET
from xml.dom import minidom
from persistence import MatrixWrapper

class MatrixFileHandler:
    DEFAULT_DIRECTORY = Path(__file__).resolve().parent.parent.parent.parent / "src/main/resources/matrices"

    @staticmethod
    def save_matrix(matrix, filename):
        if not filename.endswith(".xml"):
            filename += ".xml"
        path = os.path.join(MatrixFileHandler.DEFAULT_DIRECTORY, filename)
        if not os.path.exists(MatrixFileHandler.DEFAULT_DIRECTORY):
            os.makedirs(MatrixFileHandler.DEFAULT_DIRECTORY)

        wrapper = MatrixWrapper.MatrixWrapper(matrix)
        tree = wrapper.to_xml()
        tree_str = ET.tostring(tree, 'utf-8')
        pretty_xml_as_string = minidom.parseString(tree_str).toprettyxml(indent="  ")

        with open(path, 'w') as f:
            f.write(pretty_xml_as_string)

    @staticmethod
    def load_matrix(filename):
        if not filename.endswith(".xml"):
            filename += ".xml"
        path = os.path.join(MatrixFileHandler.DEFAULT_DIRECTORY, filename)

        tree = ET.parse(path)
        root = tree.getroot()
        wrapper = MatrixWrapper.MatrixWrapper()
        wrapper.from_xml(root)
        return wrapper.matrix

