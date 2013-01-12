package models;

import java.util.*;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.format.Formats.*;
import play.data.validation.Constraints.*;
import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Entity
public class Flight extends Model {
	
	@Id
	public Long id;

	@DateTime(pattern="dd/MM/yyyy")
	@Required
	public Date date = new Date();

	@Required
	public Integer duration;

	@Required
	public Double flightReduction;

	@Required
	public Double weekReduction;

	@Required
	public Double specialPrice;

	public Boolean initiationFee = false;

	// missing plane, instructor, person

	public static Finder<Long, Flight> find = new Finder(Long.class, Flight.class);

	public String toString() {
		return "Vol n°" + id + " du " + new SimpleDateFormat("dd/MM/yyyy").format(date) + " d'une durée de " + duration + " heure(s)";
	}
}