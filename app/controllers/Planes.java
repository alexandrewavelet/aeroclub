package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.planes.*;
import models.Plane;

public class Planes extends Controller {
  
	static Form<Plane> planeForm = Form.form(Plane.class);
	static Result GO_HOME = redirect(routes.Planes.index());

	// GET /planes
	public static Result index() {
		return ok(index.render(Plane.find.all()));
	}

	// GET /planes/:id
	public static Result show(Long id) {
		return ok(show.render(Plane.find.byId(id)));
	}

	// GET /planes/new
	public static Result _new() {
		return ok(_new.render(planeForm));
	}

	// GET /planes/:id/edit
	public static Result edit(Long id) {
		Form<Plane> filledForm = planeForm.fill(Plane.find.byId(id));
		return ok(edit.render(id, filledForm));
	}

	// POST /planes
	public static Result create() {
		Form<Plane> filledForm = planeForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			flash("error", "Une erreur est survenue");
			return badRequest(_new.render(filledForm));
		}
		else {
			filledForm.get().save();
			flash("success", filledForm.get() + " a été correctement créé");
			return GO_HOME;
		}
	}

	// POST /planes/:id
	public static Result update(Long id) {
		Form<Plane> filledForm = planeForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			flash("error", "Une erreur est survenue");
			return badRequest(edit.render(id, filledForm));
		}
		else {
			filledForm.get().update(id);
			flash("success", filledForm.get() + " a été correctement modifié");
			return GO_HOME;
		}
	}

	// POST /planes/:id/delete
	public static Result delete(Long id) {
		Plane.find.byId(id).delete();
		flash("success", "L'avion a été correctement supprimé");
		return GO_HOME;
	}
}