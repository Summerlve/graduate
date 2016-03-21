package models;

import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Role;
import be.objectify.deadbolt.core.models.Subject;
import com.avaje.ebean.Model;
import com.avaje.ebean.text.StringFormatter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 2016/3/1.
 */
@Entity
@Table(name = "zh_user")
public class User extends Model implements Subject{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sfz",  nullable = false, unique = true, columnDefinition = "varchar(255) CHARACTER SET utf8 COLLATE utf8_bin")
    private String sfz;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255) CHARACTER SET utf8 COLLATE utf8_bin")
    private String name;

    @Column(name = "telephone", nullable = false, unique = true, columnDefinition = "varchar(255) CHARACTER SET utf8 COLLATE utf8_bin")
    private String telephone;

    @OneToMany(mappedBy = "user")
    private List<House> houses = new ArrayList<House>();

    public static final Finder<Long, User> find = new Finder<Long, User>(User.class);

    public static final User findUserByName (String username) {
        return find.where().eq("name", username).findUnique();
    }
    
    @Override
    public String getIdentifier() {
        return String.valueOf(this.id);
    }

    @Override
    public List<? extends Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(() -> "user");
        return roles;
    }

    @Override
    public List<? extends Permission> getPermissions() {
        List<Permission> permissions = new ArrayList<>();
        permissions.add(() -> "common_user");
        return permissions;
    }
}
