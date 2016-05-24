package json;

import models.User;

/**
 * Created by Summer on 16/5/24.
 */
public class UserInfo {
    private String sfz;
    private String name;
    private String telephone;

    public UserInfo() {}

    public UserInfo(User user) {
        this.sfz = user.getSfz();
        this.name = user.getName();
        this.telephone = user.getTelephone();
    }

    public String getSfz() {
        return sfz;
    }

    public void setSfz(String sfz) {
        this.sfz = sfz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
