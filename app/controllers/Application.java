package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import views.html.*;
import play.i18n.*;
import models.User;
import play.libs.Json;
import org.codehaus.jackson.node.ObjectNode;
import java.util.*;

public class Application extends Controller {
	
	public static class Login {

		public String username;
		public String password;
 
   		public String validate() {
			if (User.authenticate(username, password) == null) {
				return Messages.get("controllers.application.loginError");
			}
			return null;
		}
	}

	static Form<Login> loginForm = Form.form(Login.class);

	// GET /
	public static Result index() {
		return ok(index.render(loginForm));
	}

	// GET /lang/:code
	public static Result setLang(String code) {
		changeLang(code);
		return redirect(request().getHeader("Referer"));
	}

	// POST /login
	@BodyParser.Of(BodyParser.Json.class)
	public static Result authenticate() {
		Form<Login> filledForm = loginForm.bindFromRequest();
		ObjectNode result = Json.newObject();
		if (filledForm.hasErrors()) {
			result.put("result", "error");
			result.put("message", filledForm.globalError().message());
			return ok(result);
		}
		else {
			session("user", filledForm.get().username);
			result.put("result", "success");
			result.put("message", Messages.get("controllers.application.login"));
			return ok(result);
		}
	}

	// GET /logout
	public static Result logout() {
		session().clear();
		flash("success", Messages.get("controllers.application.logout"));
		return redirect(routes.Application.index());
	}

	// GET /getI18nMessages
	@BodyParser.Of(BodyParser.Json.class)
	public static Result getI18nMessages(String keys) {
		ObjectNode result = Json.newObject();
		for (String key : keys.split(",")) {
			result.put(key, Messages.get(key));
		}
		return ok(result);
	}

	// Javascript routes
   	public static Result javascriptRoutes() {
    	response().setContentType("text/javascript");
        return ok(
            Routes.javascriptRouter("jsRoutes",
                controllers.routes.javascript.Application.authenticate(),
                controllers.routes.javascript.Application.getI18nMessages()
            )
        );
    }
}