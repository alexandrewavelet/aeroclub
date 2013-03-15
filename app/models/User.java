package models;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.format.Formats.*;
import play.data.validation.Constraints.*;
import javax.persistence.*;
import play.i18n.*;

@Entity
public class User extends Model {
	
	@Id
	public Long id;

	@Required
	public String username;

	@Required
	public String password;

	@Required
	@Email
	public String email;

	public static Finder<Long, User> find = new Finder(Long.class, User.class);

    public static User authenticate(String username, String password) {
    	play.Logger.info(username);
        return 
        	find.where()
            	.eq("username", username)
            	.eq("password", password)
            	.findUnique();
    }
    
	public String toString() {
		return Messages.get("models.user.toString", id, username);
	}
}