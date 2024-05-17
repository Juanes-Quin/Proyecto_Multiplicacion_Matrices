import xml.etree.ElementTree as ET
from xml.dom import minidom

class MatrixWrapper:
    def __init__(self, matrix=None):
        if matrix is not None:
            self.matrix = matrix
        else:
            self.matrix = []

    def to_xml(self):
        root = ET.Element("matrix")
        for row in self.matrix:
            row_element = ET.SubElement(root, "row")
            row_element.text = ' '.join(map(str, row))
        return root

    def from_xml(self, element):
        self.matrix = []
        for row_element in element.findall('row'):
            row_data = list(map(float, row_element.text.split()))
            self.matrix.append(row_data)

    def __str__(self):
        return '\n'.join(' '.join(map(str, row)) for row in self.matrix)
