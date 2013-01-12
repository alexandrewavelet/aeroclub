package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.*;
import models.Flight;

public class Flights extends Controller {
  
	static Form<Flight> flightForm = form(Flight.class);
	static Result GO_HOME = redirect(routes.Flights.index());

	// GET /flights
	public static Result index() {
		return ok(views.html.flights.index.render(Flight.find.all()));
	}

	// GET /flights/:id
	public static Result show(Long id) {
		return ok(views.html.flights.show.render(Flight.find.byId(id)));
	}

	// GET /flights/new
	public static Result _new() {
		return ok(views.html.flights._new.render(flightForm));
	}

	// GET /flights/:id/edit
	public static Result edit(Long id) {
		Form<Flight> filledForm = flightForm.fill(Flight.find.byId(id));
		return ok(views.html.flights.edit.render(id, filledForm));
	}

	// POST /flights
	public static Result create() {
		Form<Flight> filledForm = flightForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.flights._new.render(filledForm));
		}
		else {
			filledForm.get().save();
			flash("success", filledForm.get() + " a été correctement créé");
			return GO_HOME;
		}
	}

	// POST /flights/:id
	public static Result update(Long id) {
		Form<Flight> filledForm = flightForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.flights.edit.render(id, filledForm));
		}
		else {
			filledForm.get().update(id);
			flash("success", filledForm.get() + " a été correctement modifié");
			return GO_HOME;
		}
	}

	// POST /flights/:id/delete
	public static Result delete(Long id) {
		Flight.find.ref(id).delete();
		flash("success", "Le vol a été correctement supprimé");
		return GO_HOME;
	}
}