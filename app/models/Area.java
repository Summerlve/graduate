package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 2016/3/1.
 */
@Entity
@Table(name = "zh_area")
public class Area extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255) CHARACTER SET utf8 COLLATE utf8_bin")
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "img", nullable = false)
    private String img;

    @Column(name = "management_company")
    private String management;

    @Column(name = "building_num")
    private Integer buildingNum;

    @OneToMany(mappedBy = "area")
    private List<Building> buildings = new ArrayList<Building>();

    public static final Finder<Long, Area> find = new Finder<Long, Area>(Area.class);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    public Integer getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(Integer buildingNum) {
        this.buildingNum = buildingNum;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }
}
