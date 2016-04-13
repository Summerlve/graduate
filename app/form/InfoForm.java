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
}
