from flask import Flask, request, Response
from flask_restful import Resource, Api
from flask_cors import CORS
from flask_sqlalchemy import SQLAlchemy
import app_services
import soap_server
import db_model

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://root:root@localhost/xmlAgentDB'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
api = Api(app)
CORS(app)
db = SQLAlchemy(app)

# soap ws

class WSDL(Resource):
	def get(self):
		return Response(open('agent_services.wsdl', 'r').read(), mimetype='text/xml')

class SoapServices(Resource):
	def post(self):
		return soap_server.soap_handler(request.data)

# rest ws

class Login(Resource):
	def post(self):
		return app_services.login_agent(request.data)

class Register(Resource):
	def post(self):
		return app_services.register_agent(request.data)

class Accomodation(Resource):
	def get(self):
		return app_services.read_accomodation(request.data)
	def post(self):
		return app_services.edit_accomodation(request.data)
	def put(self):
		return app_services.create_accomodation(request.data)
	def delete(self):
		return app_services.delete_accomodation(request.data)

class Reservation(Resource):
	def get(self):
		return app_services.read_reservation(request.data)
	def post(self):
		return app_services.edit_reservation(request.data)
	def put(self):
		return app_services.create_reservation(request.data)
	def delete(self):
		return app_services.delete_reservation(request.data)

class Message(Resource):
	def get(self):
		return app_services.read_messages(request.data)
	def post(self):
		return app_services.send_message(request.data)

api.add_resource(WSDL, '/ws/agent_services.wsdl')
api.add_resource(SoapServices, '/ws/agent_services')
api.add_resource(Login, '/login')
api.add_resource(Register, '/register')
api.add_resource(Accomodation, '/accomodation')
api.add_resource(Reservation, '/reservation')
api.add_resource(Message, '/messages')

if __name__ == '__main__':
	db_model.create_drop_db()
	app.run(debug=True)
