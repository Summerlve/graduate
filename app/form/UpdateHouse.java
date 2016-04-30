package form;

import play.data.validation.Constraints;

/**
 * Created by Summer on 16/4/30.
 */
public class UpdateHouse {
    @Constraints.Required
    private Long state;

    @Constraints.Required
    private Integer floor;

    @Constraints.Required
    private Integer no;

    @Constraints.Required
    private Integer space;

    @Constraints.Required
    private String buy_date;

    @Constraints.Required
    private String in_date;

    @Constraints.Required
    private Integer price;

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Integer getSpace() {
        return space;
    }

    public void setSpace(Integer space) {
        this.space = space;
    }

    public String getBuy_date() {
        return buy_date;
    }

    public void setBuy_date(String buy_date) {
        this.buy_date = buy_date;
    }

    public String getIn_date() {
        return in_date;
    }

    public void setIn_date(String in_date) {
        this.in_date = in_date;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
