package form;

import play.data.validation.Constraints;

/**
 * Created by Summer on 4/13/16.
 */
public class InfoForm extends AdminForm{
    @Constraints.Required
    private String phone;

    @Constraints.Required
    private String sfz;

    @Constraints.Required
    private String password_again;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSfz() {
        return sfz;
    }

    public void setSfz(String sfz) {
        this.sfz = sfz;
    }

    public String getPassword_again() {
        return password_again;
    }

    public void setPassword_again(String password_again) {
        this.password_again = password_again;
    }
}
