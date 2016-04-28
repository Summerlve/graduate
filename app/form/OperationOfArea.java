package form;

import play.data.validation.Constraints;

/**
 * Created by Summer on 16/4/27.
 */
public class OperationOfArea {
    @Constraints.Required
    private String area_name;

    @Constraints.Required
    private Integer  building_num;

    @Constraints.Required
    private String description;

    @Constraints.Required
    private String management;

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public Integer getBuilding_num() {
        return building_num;
    }

    public void setBuilding_num(Integer building_num) {
        this.building_num = building_num;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }
}
