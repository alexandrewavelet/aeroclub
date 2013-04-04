package models

import play.api.db._
import anorm._
import anorm.SqlParser._
import play.api.Play.current

case class Account(
	id:     Pk[Long], 
	amount: Double, 
	userId: Long
)

object Account {

	val account = {
		get[Pk[Long]]("id")~
		get[Double]("amount")~
		get[Long]("user_id") map {
			case id~amount~user_id => Account(id, amount, user_id)
		}
	}

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

	def getByUser(user: User): Account = {
		DB.withConnection { implicit c =>
			SQL(
				"""
					SELECT * FROM account
					WHERE user_id = {user_id}
				"""
			).on(
				'user_id -> user.id
			).as(account.single)
		}
	}
}