import app

# mozda ovo bude trebalo za svaki entitet	
#	def __repr__(self):
#		return '<User %r>' % self.username

db = app.db

def create_drop_db():
	db.drop_all()
	db.create_all()

class Accomodation(db.Model):
	accomodation_id = db.Column(db.BigInteger, primary_key=True)
	entity_version = db.Column(db.Integer, nullable=False)
	accomodation_name = db.Column(db.String(255), unique=True, nullable=False)
	capacity = db.Column(db.Integer, unique=True, nullable=False)
	category = db.Column(db.Integer, unique=True, nullable=False)
	accomodation_type_id = db.Column(db.BigInteger, db.ForeignKey('accomodation_type.accomodation_type_id'), nullable=False)
	accomodation_type = db.relationship('AccomodationType')
	location_id = db.Column(db.BigInteger, db.ForeignKey('location.location_id'), nullable = False)
	location = db.relationship('Location')

class AccomodationType(db.Model):
	accomodation_type_id = db.Column(db.BigInteger, primary_key=True)
	entity_version = db.Column(db.Integer, nullable=False)
	name = db.Column(db.String(255), nullable=False)

class Reservation(db.Model):
	reservation_id = db.Column(db.BigInteger, primary_key=True)
	entity_version = db.Column(db.Integer, nullable=False)
	number_of_persons = db.Column(db.Integer, nullable=False)
	start_date = db.Column(db.BigInteger, nullable=False)
	end_date = db.Column(db.BigInteger, nullable=False)
	accomodation_id = db.Column(db.BigInteger, db.ForeignKey('accomodation.accomodation_id'), nullable=False)
	accomodation = db.relationship('Accomodation', backref=db.backref('reservations'), lazy=True)
	user_id = db.Column(db.BigInteger, db.ForeignKey('user.user_id'), nullable=False)
	user = db.relationship('User', backref=db.backref('reservations'), lazy=True)

class User(db.Model):
	user_id = db.Column(db.BigInteger, primary_key=True)
	entity_version = db.Column(db.Integer, nullable=False)
	user_name = db.Column(db.String(255), unique=True, nullable=False)
	password = db.Column(db.String(255), unique=True, nullable=False)
	first_name = db.Column(db.String(255), unique=True, nullable=True)
	last_name = db.Column(db.String(255), unique=True, nullable=True)
	pid = db.Column(db.String(255), unique=True, nullable=True)
	user_type = db.Column(db.Enum('admin', 'user', 'agent', name='user_type'))

class Message(db.Model):
	message_id = db.Column(db.BigInteger, primary_key=True)
	entity_version = db.Column(db.Integer, unique=False, nullable=False)
	content = db.Column(db.String(255), unique=False, nullable=False)
	sender_id = db.Column(db.BigInteger, db.ForeignKey('user.user_id'), nullable = False)
	sender = db.relationship('User', backref=db.backref('sent_messages', lazy=True))
	receiver_id = db.Column(db.BigInteger, db.ForeignKey('user.user_id'), nullable = False)
	receiver = db.relationship('User', backref=db.backref('received_messages', lazy=True))

class Comment(db.Model):
	comment_id = db.Column(db.BigInteger, primary_key=True)
	entity_version = db.Column(db.Integer, nullable=False)
	approved = db.Column(db.Boolean, nullable=False)
	content = db.Column(db.String(255), unique=False, nullable=False)
	rating = db.Column(db.Integer, unique=False, nullable=False)
	accomodation_id = db.Column(db.BigInteger, db.ForeignKey('accomodation.accomodation_id'), nullable=False)
	accomodation = db.relationship('Accomodation', backref=db.backref('comments'), lazy=True)
	author_id = db.Column(db.BigInteger, db.ForeignKey('user.user_id'), nullable=False)
	author = db.relationship('User', backref=db.backref('comments'), lazy=True)

class Location(db.Model):
	location_id = db.Column(db.BigInteger, primary_key=True)
	entity_version = db.Column(db.Integer, nullable=False)
	city = db.Column(db.String(255), nullable=False)
	state = db.Column(db.String(255), nullable=False)
	street_name = db.Column(db.String(255), nullable=False)
	street_number = db.Column(db.String(255), nullable=False)

class Picture(db.Model):
	picture_id = db.Column(db.BigInteger, primary_key=True)
	entity_version = db.Column(db.Integer, nullable=False)
	content = db.Column(db.LargeBinary, nullable=False)
	accomodation_id = db.Column(db.BigInteger, db.ForeignKey('accomodation.accomodation_id'), nullable=False)
	accomodation = db.relationship('Accomodation', backref=db.backref('pictures'), lazy=True)

class BonusService(db.Model):
	bonus_service_id = db.Column(db.BigInteger, primary_key=True)
	entity_version = db.Column(db.Integer, nullable=False)
	name = db.Column(db.String(255), nullable=False)

class Price(db.Model):
	price_id = db.Column(db.BigInteger, primary_key=True)
	entity_version = db.Column(db.Integer, nullable=False)
	price = db.Column(db.Integer, nullable=False)
	start_date = db.Column(db.BigInteger, nullable=False)
	end_date = db.Column(db.BigInteger, nullable=False)
	accomodation_id = db.Column(db.BigInteger, db.ForeignKey('accomodation.accomodation_id'), nullable=False)
	accomodation = db.relationship('Accomodation', backref=db.backref('prices'), lazy=True)

class AccomodationAgent(db.Model):
	accomodation_agent_id = db.Column(db.BigInteger, primary_key=True)
	entity_version = db.Column(db.Integer, nullable=False)
	main_agent = db.Column(db.Boolean, nullable=False)
	agent_id = db.Column(db.BigInteger, db.ForeignKey('user.user_id'), nullable=False)
	agent = db.relationship('User', backref=db.backref('accomodation_agent'), lazy=True)
	accomodation_id = db.Column(db.BigInteger, db.ForeignKey('accomodation.accomodation_id'), nullable=False)
	accomodation = db.relationship('Accomodation', backref=db.backref('agents'), lazy=True)

accomodation_bonus_service = db.Table('accomodation_bonus_service',
	db.Column('bonus_service_id', db.BigInteger, db.ForeignKey('bonus_service.bonus_service_id'), primary_key=True),
	db.Column('accomodation_id', db.BigInteger, db.ForeignKey('accomodation.accomodation_id'), primary_key=True)
)
