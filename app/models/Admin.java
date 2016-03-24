package models;

/**
 * Created by Summer on 2016/3/1.
 */
import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Role;
import be.objectify.deadbolt.core.models.Subject;
import com.avaje.ebean.Model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "zh_admin")
public class Admin extends Model implements Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sfz",  nullable = false, unique = true, columnDefinition = "varchar(255) CHARACTER SET utf8 COLLATE utf8_bin")
    private String sfz;

    @Column(name = "username", nullable = false, unique = true, columnDefinition = "varchar(255) CHARACTER SET utf8 COLLATE utf8_bin")
    private String username;

    @Column(name = "password_hash", length = 255, nullable = false)
    private String passwordHash;

    @Column(name = "telephone", nullable = false, unique = true, columnDefinition = "varchar(255) CHARACTER SET utf8 COLLATE utf8_bin")
    private String telephone;

    public static final Finder<Long, Admin> find = new Finder<Long, Admin>(Admin.class);

    public static final Map<String, Object> auth(String username, String password) {
        Admin admin = find.where().eq("username", username).eq("password_hash", password).findUnique();
        Boolean isAccess = false;
        Long id = -1l;

        if (admin != null) {
            isAccess = true;
            id = admin.getId();
        }

        Map<String, Object> result = new HashMap<>();
        result.put("isAccess", isAccess);
        result.put("id", id);

        return result;
    }

    public static final Admin findbyUsername (String username) {
        return find.where().eq("username", username).findUnique();
    }

    @Override
    public String getIdentifier() {
        return String.valueOf(this.id);
    }

    @Override
    public List<? extends Role> getRoles() {
        List<FakeRole> roles = new ArrayList<>();
        roles.add(FakeRole.ADMIN);
        return roles;
    }

    @Override
    public List<? extends Permission> getPermissions() {
        List<FakePermission> permissions = new ArrayList<>();
        permissions.add(FakePermission.ADMIN_PERMISSION);
        return permissions;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
