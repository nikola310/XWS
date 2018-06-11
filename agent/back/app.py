from flask import Flask, request
from flask_restful import Resource, Api
from flask_cors import CORS
import services.login as log_serv
import services.register as reg_serv

app = Flask(__name__)
api = Api(app)
CORS(app)

class Login(Resource):
	def get(self):
		return { 'data' : 'test data works'}
	def post(self):
		return log_serv.login_attempt(request.data)

class Register(Resource):
	def post(self):
		return reg_serv.register_attempt(request.data)

api.add_resource(Login, '/login')
api.add_resource(Register, '/register')

if __name__ == '__main__':
    app.run(debug=True)
