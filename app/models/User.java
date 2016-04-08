package models;

import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Role;
import be.objectify.deadbolt.core.models.Subject;
import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 2016/3/1.
 */
@Entity
@Table(name = "zh_user")
public class User extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sfz",  nullable = false, unique = true, columnDefinition = "varchar(255) CHARACTER SET utf8 COLLATE utf8_bin")
    private String sfz;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255) CHARACTER SET utf8 COLLATE utf8_bin")
    private String name;

    @Column(name = "telephone", nullable = false, unique = true, columnDefinition = "varchar(255) CHARACTER SET utf8 COLLATE utf8_bin")
    private String telephone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<House> houses = new ArrayList<House>();

    public static final Finder<Long, User> find = new Finder<Long, User>(User.class);

    public static final User findUserByName (String username) {
        return find.where().eq("name", username).findUnique();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSfz() {
        return sfz;
    }

    public void setSfz(String sfz) {
        this.sfz = sfz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }
}
