package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by Summer on 2016/3/1.
 */

@Entity
@Table(name = "zh_house")
public class House extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference("houses")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    @JsonManagedReference("building")
    @JsonProperty("building_info")
    private Building buildingId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "house_state_id", referencedColumnName = "id")
    @JsonManagedReference("state")
    @JsonProperty("state")
    private HouseState state;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "house_no")
    @JsonProperty("house_no")
    private Integer houseNo;

    @Column(name = "buy_price")
    @JsonProperty("buy_price")
    private Number buyPrice;

    @Column(name = "space")
    private Integer space;

    @Column(name = "buy_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("buy_date")
    private Calendar buyDate;

    @Column(name = "in_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("in_date")
    private Calendar inDate;

    @Column(name = "sell_price_per_square_meter")
    @JsonProperty("price_per_sm")
    private Integer pricePerSM;

    @Column(name = "img", nullable = false)
    private String img;

    public static final Finder<Long, House> find = new Finder<Long, House>(House.class);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Building getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Building buildingId) {
        this.buildingId = buildingId;
    }

    public HouseState getState() {
        return state;
    }

    public void setState(HouseState state) {
        this.state = state;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(Integer houseNo) {
        this.houseNo = houseNo;
    }

    public Number getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Number buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getSpace() {
        return space;
    }

    public void setSpace(Integer space) {
        this.space = space;
    }

    public Calendar getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Calendar buyDate) {
        this.buyDate = buyDate;
    }

    public Calendar getInDate() {
        return inDate;
    }

    public void setInDate(Calendar inDate) {
        this.inDate = inDate;
    }

    public Integer getPricePerSM() {
        return pricePerSM;
    }

    public void setPricePerSM(Integer pricePerSM) {
        this.pricePerSM = pricePerSM;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
