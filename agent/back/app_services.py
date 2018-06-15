import jwt
import db_model
import db_crud

def login_attempt( login_data ):
	# user_query = User.query.filter_by(username=login_data.username).first()
	db_crud.create_user(username = login_data.username, password = login_data.password)

def register_attempt( register_params ):
	return register_params