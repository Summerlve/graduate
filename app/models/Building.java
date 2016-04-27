package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Summer on 2016/3/1.
 */
@Entity
@Table(name = "zh_building")
public class Building extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonBackReference("building")
    private Long id;

    @OneToMany(mappedBy = "buildingId", cascade = CascadeType.ALL)
    @JsonBackReference("building")
    private List<House> houses = new ArrayList<House>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    @JsonBackReference("building")
    private Area area;

    @ManyToOne(optional = false)
    @JoinColumn(name = "building_kind_id", referencedColumnName = "id")
    @JsonManagedReference("building_kind")
    @JsonProperty("building_kind")
    private BuildingKind buildingKind;

    @Column(name = "acreage")
    @JsonBackReference("building")
    private Integer acreage;

    @Column(name = "completion_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonBackReference("building")
    private Calendar completionDate;

    @Column(name = "house_num")
    @JsonBackReference("building")
    private Integer houseNum;

    @Column(name = "selled_num")
    @JsonBackReference("building")
    private Integer sold = 0;

    @Column(name = "order_num")
    @JsonBackReference("building")
    private Integer orderNum = 0;

    @Column(name = "description")
    @JsonBackReference("building")
    private String description;

    @Column(name = "img", nullable = false)
    @JsonBackReference("building")
    private String img;

    public static final Finder<Long, Building> find = new Finder<Long, Building>(Building.class);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public BuildingKind getBuildingKind() {
        return buildingKind;
    }

    public void setBuildingKind(BuildingKind buildingKind) {
        this.buildingKind = buildingKind;
    }

    public Integer getAcreage() {
        return acreage;
    }

    public void setAcreage(Integer acreage) {
        this.acreage = acreage;
    }

    public Calendar getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Calendar completionDate) {
        this.completionDate = completionDate;
    }

    public Integer getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(Integer houseNum) {
        this.houseNum = houseNum;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
