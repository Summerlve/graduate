package form;

import play.data.validation.Constraints;

/**
 * Created by Summer on 4/3/16.
 */
public class SearchHoustForm {
    @Constraints.Required
    private String building_kind;

    @Constraints.Required
    private String space;

    public String getBuilding_kind() {
        return building_kind;
    }

    public void setBuilding_kind(String building_kind) {
        this.building_kind = building_kind;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }
}
