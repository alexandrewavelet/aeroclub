package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import views.html.flights.*;
import models.Flight;
import play.i18n.*;
import flexjson.JSONSerializer;

public class Flights extends Controller {
  
	static Form<Flight> flightForm = Form.form(Flight.class);
	static Result GO_HOME = redirect(routes.Flights.index(0, 10));

	// GET /flights.json
	public static Result indexJson() {
		return ok(new JSONSerializer().exclude("class").serialize(Flight.find.all()));
	}

	// GET /flights?page=0&pageSize=10
	@Security.Authenticated(Secured.class)
	public static Result index(int page, int pageSize) {
		return ok(index.render(Flight.page(page, pageSize)));
	}

	// GET /flights/:id
	@Security.Authenticated(Secured.class)
	public static Result show(Long id) {
		return ok(show.render(Flight.find.byId(id)));
	}

	// GET /flights/new
	@Security.Authenticated(Secured.class)
	public static Result _new() {
		return ok(_new.render(flightForm));
	}

	// GET /flights/:id/edit
	@Security.Authenticated(Secured.class)
	public static Result edit(Long id) {
		Form<Flight> filledForm = flightForm.fill(Flight.find.byId(id));
		return ok(edit.render(id, filledForm));
	}

	// POST /flights
	@Security.Authenticated(Secured.class)
	public static Result create() {
		Form<Flight> filledForm = flightForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			flash("error", Messages.get("controllers.error"));
			return badRequest(_new.render(filledForm));
		}
		else {
			filledForm.get().save();
			flash("success", Messages.get("controllers.createSuccess", filledForm.get()));
			return GO_HOME;
		}
	}

	// POST /flights/:id
	@Security.Authenticated(Secured.class)
	public static Result update(Long id) {
		Form<Flight> filledForm = flightForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			flash("error", Messages.get("controllers.error"));
			return badRequest(edit.render(id, filledForm));
		}
		else {
			filledForm.get().update(id);
			flash("success", Messages.get("controllers.updateSuccess", filledForm.get()));
			return GO_HOME;
		}
	}

	// POST /flights/:id/delete
	@Security.Authenticated(Secured.class)
	public static Result delete(Long id) {
		Flight.find.byId(id).delete();
		flash("success", Messages.get("controllers.flights.deleteSuccess"));
		return GO_HOME;
	}
}