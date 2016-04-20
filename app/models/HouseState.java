package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 2016/3/2.
 */
@Entity
@Table(name = "zh_house_state")
public class HouseState extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonBackReference("state")
    private Long id;

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "varchar(255) CHARACTER SET utf8 COLLATE utf8_bin")
    @JsonProperty("name")
    private String name;

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
    @JsonBackReference("state")
    private List<House> houses = new ArrayList<House>();

    public static final Finder<Long, HouseState> find = new Finder<Long, HouseState>(HouseState.class);

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

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
}
