package form;

import play.data.validation.Constraints;

/**
 * Created by Summer on 16/4/27.
 */
public class OperationOfBuilding {
    @Constraints.Required
    private Long area;

    @Constraints.Required
    private Long kind;

    @Constraints.Required
    private Integer house_num;

    @Constraints.Required
    private String description;

    @Constraints.Required
    private Integer acreage;

    @Constraints.Required
    public String completion_date;

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public Long getKind() {
        return kind;
    }

    public void setKind(Long kind) {
        this.kind = kind;
    }

    public Integer getHouse_num() {
        return house_num;
    }

    public void setHouse_num(Integer house_num) {
        this.house_num = house_num;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAcreage() {
        return acreage;
    }

    public void setAcreage(Integer acreage) {
        this.acreage = acreage;
    }

    public String getCompletion_date() {
        return completion_date;
    }

    public void setCompletion_date(String completion_date) {
        this.completion_date = completion_date;
    }
}
