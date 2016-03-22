import models.Admin;
import play.Application;
import play.GlobalSettings;

/**
 * Created by Summer on 3/22/16.
 */
public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        super.onStart(app);

        if (Admin.find.where().eq("username", "test").findUnique() == null) {
            Admin admin = new Admin();
            admin.setUsername("test");
            admin.setPasswordHash("123456");
            admin.setSfz("123123123123");
            admin.setTelephone("123123");
            admin.save();
        }
    }
}
