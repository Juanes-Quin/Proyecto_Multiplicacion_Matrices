import xml.etree.ElementTree as ET
from dataclasses import dataclass, field
from typing import List
from persistence.ResultData import ResultData

@dataclass
class Results:
    results: List[ResultData] = field(default_factory=list)

    def to_xml(self):
        root = ET.Element("results")
        for result in self.results:
            root.append(result.to_xml_element())
        return ET.tostring(root, encoding='unicode')

    @staticmethod
    def from_xml(xml_data):
        tree = ET.fromstring(xml_data)
        results = [ResultData.from_xml_element(elem) for elem in tree.findall('result')]
        return Results(results)