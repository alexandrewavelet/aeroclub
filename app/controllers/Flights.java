package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.*;
import models.Flight;

public class Flights extends Controller {
  
	static Form<Flight> flightForm = form(Flight.class);

	public static Result index() {
		return ok(views.html.flight.render(Flight.all(), flightForm));
	}

	public static Result create() {
		Form<Flight> filledForm = flightForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.flight.render(Flight.all(), filledForm));
		}
		else {
			Flight.create(filledForm.get());
			return redirect(routes.Flights.index());
		}
	}

	public static Result read(Long id) {
		return TODO;
	}
	
	public static Result update(Long id) {
		Form<Flight> filledForm = flightForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.flight.render(Flight.all(), filledForm));
		}
		else {
			Flight.update(id);
			return redirect(routes.Flights.index());
		}
	}

	public static Result delete(Long id) {
    	Flight.delete(id);
    	return redirect(routes.Flights.index());
	}
}