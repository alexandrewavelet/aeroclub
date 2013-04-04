package models

import play.api.db._
import anorm._
import play.api.Play.current

case class Account(
	id:     Long, 
	amount: Double, 
	user:   User
)

object Account {

	def create(user: User) {
		DB.withConnection { implicit c =>
			SQL(
				"""
					INSERT INTO account(amount, user_id) VALUES ({amount}, {user_id})
				"""
			).on(
				'amount  -> 0.0, 
				'user_id -> user.id
			).executeInsert()
		}
	}
}