from bs4 import BeautifulSoup
import xml.etree.ElementTree as ET

def soap_handler(soap_request):
	tree = ET.parse(soap_request)
	root = tree.getroot()
	# iz xmla pronadjemo koji servis klijent trazi i pozovemo taj servis
	# response servisa vratimo klijentu
	service_name = ''
	if(service_name == 'ovaj'):
		return save_accomodation(soap_request)
	elif(service_name == 'onaj'):
		return save_reservation(soap_request)
	else:
		return error_handler(soap_request)

def save_accomodation(soap_request):
	# servis
	root = ET.Element("root")
	doc = ET.SubElement(root, "doc")
	ET.SubElement(doc, "field1", name="blah", mirkela='kraaaa').text = "some value1"
	ET.SubElement(doc, "field2", name="asdfasd").text = "some vlaue2"
	tree = ET.ElementTree(root)
	return tree

def save_reservation(soap_request):
	# servis
	root = ET.Element("root")
	doc = ET.SubElement(root, "doc")
	ET.SubElement(doc, "field1", name="blah", mirkela='kraaaa').text = "some value1"
	ET.SubElement(doc, "field2", name="asdfasd").text = "some vlaue2"
	tree = ET.ElementTree(root)
	return tree

def error_handler(soap_request):
	return 'error'