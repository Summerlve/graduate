package form;

import play.data.validation.Constraints;

/**
 * Created by Summer on 16/4/16.
 */
public class SearchUserForm {
    @Constraints.Required
    private String sfz;

    public String getSfz() {
        return sfz;
    }

    public void setSfz(String sfz) {
        this.sfz = sfz;
    }
}
