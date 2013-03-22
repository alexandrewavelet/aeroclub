package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import views.html.users.*;
import models.User;
import play.i18n.*;

@Security.Authenticated(Secured.class)
public class Users extends Controller {

	static Form<User> userForm = Form.form(User.class);
	static Result GO_HOME = redirect(routes.Users.edit());

	// GET /user/edit
	public static Result edit() {
		User user = User.find
						.where()
						.eq("username", session().get("user"))
						.findUnique();
		Form<User> filledForm = userForm.fill(user);
		return ok(edit.render(filledForm));
	}

	// POST /user
	public static Result update() {
		Form<User> filledForm = userForm.bindFromRequest();
		Long id = User.find.where()
					  	   .eq("username", session().get("user"))
					  	   .findUnique()
					       .id;
		//play.Logger.info(filledForm.errors().toString());
		if (filledForm.hasErrors()) {
			flash("error", Messages.get("controllers.error"));
			return badRequest(edit.render(filledForm));
		}
		else {
			User.update(id, filledForm.get());
			flash("success", Messages.get("controllers.updateSuccess", filledForm.get()));
			return GO_HOME;
		}
	}
}