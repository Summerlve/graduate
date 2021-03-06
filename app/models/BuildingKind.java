package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 2016/3/2.
 */

@Entity
@Table(name = "zh_building_kind")
public class BuildingKind extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonBackReference("building_kind")
    private Long id;

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "varchar(255) CHARACTER SET utf8 COLLATE utf8_bin")
    @JsonProperty("name")
    private String name;

    @OneToMany(mappedBy = "buildingKind", cascade = CascadeType.ALL)
    @JsonBackReference("building_kind")
    private List<Building> buildings = new ArrayList<Building>();

    public static final Finder<Long, BuildingKind> find = new Finder<>(BuildingKind.class);

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

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }
}
