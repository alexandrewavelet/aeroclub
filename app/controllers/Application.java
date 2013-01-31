package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.*;

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

	// POST /login
	public static Result login() {
		Form<Login> filledForm = loginForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			flash("error", "Une erreur est survenue");
			return badRequest(index.render(filledForm));
		}
		else {
			flash("success", "Vous êtes connecté sous le compte");
			return redirect(routes.Application.index());
		}
	}

	// GET /logout
	public static Result logout() {
		flash("success", "Vous avez été déconnecté avec succès");
		return redirect(routes.Application.index());
	}
}