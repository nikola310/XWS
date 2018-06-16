import jwt
import db_model
import db_crud
import soap_client

token_secret = 'xml_web'
token_algorithm = 'HS256'

# koristimo da nadjemo korisnika za svaki servis kojem je potreban ulogovan korisnik
def decode_token(token):
	decode = jwt.decode(encoded, token_secret, algorithms=[token_algorithm])
	return decode.username

def login_agent( request_data ):
	# saljemo zahtev za sinhronizaciju sa glavnom bazom
	soap_client.db_sync()

	# user_query = User.query.filter_by(username=request_data.username).first()
	
	if (request_data.password == 'query_pass'):
		jwt_token = jwt.encode({'username': request_data.username}, token_secret, algorithm=token_algorithm)
		return { 'status':'success', 'token':jwt_token }
	else:
		return { 'status':'error' }

def register_agent( request_data ):
	return 'test'

def create_accomodation( request_data ):
	return 'test'

def read_accomodation( request_data ):
	return 'test'

def edit_accomodation( request_data ):
	return 'test'

def delete_accomodation( request_data ):
	return 'test'

def create_reservation( request_data ):
	return 'test'

def read_reservation( request_data ):
	return 'test'

def edit_reservation( request_data ):
	return 'test'

def delete_reservation( request_data ):
	return 'test'

def read_messages( request_data ):
	return 'test'

def send_message( request_data ):
	return 'test'
