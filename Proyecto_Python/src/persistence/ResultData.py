import xml.etree.ElementTree as ET
from dataclasses import dataclass, field

@dataclass
class ResultData:
    size: int = field(default=0)
    algorithm: str = field(default="")
    language: str = field(default="")
    executionTime: int = field(default=0)

    def to_xml(self):
        root = ET.Element("ResultData")
        ET.SubElement(root, "size").text = str(self.size)
        ET.SubElement(root, "algorithm").text = self.algorithm
        ET.SubElement(root, "language").text = self.language
        ET.SubElement(root, "executionTime").text = str(self.executionTime)
        return ET.tostring(root, encoding='unicode')

    @staticmethod
    def from_xml(xml_data):
        tree = ET.fromstring(xml_data)
        return ResultData(
            size=int(tree.find('size').text),
            algorithm=tree.find('algorithm').text,
            language=tree.find('language').text,
            executionTime=int(tree.find('executionTime').text)
        )
    
    def to_xml_element(self):
        root = ET.Element("result")
        ET.SubElement(root, "size").text = str(self.size)
        ET.SubElement(root, "algorithm").text = self.algorithm
        ET.SubElement(root, "language").text = self.language
        ET.SubElement(root, "executionTime").text = str(self.executionTime)
        return root

    @staticmethod
    def from_xml_element(elem):
        return ResultData(
            size=int(elem.find('size').text),
            algorithm=elem.find('algorithm').text,
            language=elem.find('language').text,
            executionTime=int(elem.find('executionTime').text)
        )
