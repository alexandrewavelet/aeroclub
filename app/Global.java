import play.*;
import models.User;

public class Global extends GlobalSettings {

	@Override
	// Add admin account into database if it's not in
	public void onStart(Application app) {
		if (User.find.all().size() == 0) {
			User user = new User(1L, "admin", "fc312767fb4c127a8d2759a5e513b03a33483b21", "admin@aeroclub.git");
			user.save();
		}
	}
}