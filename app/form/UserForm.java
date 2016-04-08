package form;

import play.data.validation.Constraints;

/**
 * Created by Summer on 4/8/16.
 */
public class UserForm {
    @Constraints.Required
    private String identifier;

    @Constraints.Required
    private String name;

    @Constraints.Required
    private String phone;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
