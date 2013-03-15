package models;

import java.util.*;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.format.Formats.*;
import play.data.validation.Constraints.*;
import javax.persistence.*;
import play.i18n.*;
import com.avaje.ebean.*;

@Entity
public class Planetype extends Model {
	

	@Id
	public Long id;

	@OneToMany
	public List<Plane> planes;

	@Required
	public String description;

	@Required
	public Double fee1;

	@Required
	public Double fee2;

	@Required
	public double fee3;

	@Required
	public int fee_time1;

	@Required
	public int fee_time2;

	@Required
	public int fee_time3;


	public static Finder<Long, Planetype> find = new Finder(Long.class, Planetype.class);


	/*public String toString() {
		return Messages.get("models.flight.toString", id, new SimpleDateFormat("dd/MM/yyyy").format(date), duration);
	}*/
}