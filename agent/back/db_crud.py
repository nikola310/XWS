import db_model

db = db_model.db

def create_user(username, password):
	user = User(username = username, password = password)
 	db.session.add(user)
 	db.session.commit()