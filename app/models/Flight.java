package models;

import java.util.*;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.format.Formats.*;
import play.data.validation.Constraints.*;
import javax.persistence.*;

@Entity
public class Flight extends Model {
    
    @Id
	public Long id;

	@DateTime(pattern="dd/MM/yyyy")
	@Required
	public Date date = new Date();

	@Required
	public Double duration;

	@Required
	public Double flightReduction;

	@Required
	public Double weekReduction;

	@Required
	public Double specialPrice;

	@Required
	public Boolean initiationFee;

	// missing plane, instructor, person

	public static Finder<Long, Flight> find = new Finder(Long.class, Flight.class);

	public static List<Flight> all() {
		return find.all();
	}

	public static void create(Flight flight) {
		flight.save();
	}

	public static void update(Long id) {
		find.ref(id).update();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}
}