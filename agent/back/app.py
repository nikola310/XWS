from flask import Flask, request, Response
from flask_restful import Resource, Api
from flask_cors import CORS
from flask_sqlalchemy import SQLAlchemy
import app_services
import db_model

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://root:root@localhost/xmlAgentDB'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
api = Api(app)
CORS(app)
db = SQLAlchemy(app)

class WSDL(Resource):
	def get(self):
		return Response(open('wsdl.xml', 'r').read(), mimetype='text/xml')

class Login(Resource):
	def get(self):
		return { 'data' : 'test data works'}
	def post(self):
		return app_services.login_attempt(request.data)

class Register(Resource):
	def post(self):
		return app_services.register_attempt(request.data)

api.add_resource(WSDL, '/wsdl')
api.add_resource(Login, '/login')
api.add_resource(Register, '/register')

if __name__ == '__main__':
	db_model.create_drop_db()
	app.run(debug=True)
