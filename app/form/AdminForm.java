package form;

import play.data.validation.Constraints;

/**
 * Created by Summer on 3/29/16.
 */
public class AdminForm {
    @Constraints.Required
    protected String username;

    @Constraints.Required
    protected String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

