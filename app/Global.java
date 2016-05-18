import json.OperationResult;
import models.Admin;
import org.apache.commons.codec.binary.Hex;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.Json;

import java.security.MessageDigest;

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
            String passwordHash = "";
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] temp = md.digest("123456".getBytes("UTF8"));
                passwordHash = new String(Hex.encodeHex(temp));
                Logger.info(passwordHash);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            admin.setPasswordHash(passwordHash);
            admin.setSfz("123123123123");
            admin.setTelephone("123123");
            admin.save();
        }
    }
}
