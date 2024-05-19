import xml.etree.ElementTree as ET

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
            for item in row:
                item_element = ET.SubElement(row_element, "item")
                item_element.text = str(item)
        return root

    def from_xml(self, element):
        self.matrix = []
        for row_element in element.findall('row'):
            row_data = []
            for item_element in row_element.findall('item'):
                row_data.append(float(item_element.text))
            self.matrix.append(row_data)

    def __str__(self):
        return '\n'.join(' '.join(map(str, row)) for row in self.matrix)
