package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import views.html.*;
import play.i18n.*;

public class Application extends Controller {
  	
  	public static class Login {

  		public String username;
  		public String password;
  		
  	}

  	static Form<Login> loginForm = Form.form(Login.class);

  	// GET /
	public static Result index() {
		return ok(index.render(loginForm));
	}

	// GET /:code
	public static Result setLang(String code) {
		changeLang(code);
		return redirect(request().getHeader("Referer"));
	}

	// POST /login
	public static Result login() {
		Form<Login> filledForm = loginForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			flash("error", Messages.get("controllers.error"));
			return badRequest(index.render(filledForm));
		}
		else {
			flash("success", Messages.get("controllers.application.login"));
			return redirect(routes.Application.index());
		}
	}

	// GET /logout
	public static Result logout() {
		flash("success", Messages.get("controllers.application.logout"));
		return redirect(routes.Application.index());
	}
}