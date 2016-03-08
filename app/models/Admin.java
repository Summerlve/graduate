package models;

/**
 * Created by Summer on 2016/3/1.
 */
import com.avaje.ebean.Model;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "no",  nullable = false, unique = true, columnDefinition = "varchar(255) CHARACTER SET utf8 COLLATE utf8_bin")
    private String no;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255) CHARACTER SET utf8 COLLATE utf8_bin")
    private String name;

    @Column(name = "telephone", nullable = false, unique = true, columnDefinition = "varchar(255) CHARACTER SET utf8 COLLATE utf8_bin")
    private String telephone;

    public static final Finder<Long, Admin> find = new Finder<Long, Admin>(Admin.class);
}
