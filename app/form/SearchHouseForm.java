package form;

import play.data.validation.Constraints;

/**
 * Created by Summer on 4/3/16.
 */
public class SearchHouseForm {
    @Constraints.Required
    private String building_kind;

    @Constraints.Required
    private String space_kind;

    public String getBuilding_kind() {
        return building_kind;
    }

    public void setBuilding_kind(String building_kind) {
        this.building_kind = building_kind;
    }

    public String getSpace_kind() {
        return space_kind;
    }

    public void setSpace_kind(String space_kind) {
        this.space_kind = space_kind;
    }
}
