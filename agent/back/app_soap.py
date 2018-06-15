from bs4 import BeautifulSoup
import xml.etree.ElementTree as ET

# pariranje xmlova i ostalo

root = ET.Element("root")
doc = ET.SubElement(root, "doc")

ET.SubElement(doc, "field1", name="blah", mirkela='kraaaa').text = "some value1"
ET.SubElement(doc, "field2", name="asdfasd").text = "some vlaue2"

tree = ET.ElementTree(root)
tree.write("proba_xml_python.xml")