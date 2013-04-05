import play.api._
import play.api.mvc._
import play.api.mvc.Results._
import views.html._
import models.User

object Global extends GlobalSettings {

	override def onHandlerNotFound(request: RequestHeader): Result = {
		NotFound(views.html.notFound(request))
	}

	override def onStart(app: Application) {
		if(User.find.all.size == 0) {
			val user = new User(1L, "admin", "fc312767fb4c127a8d2759a5e513b03a33483b21", "admin@aeroclub.git")
			user.save()
		}
	}
}