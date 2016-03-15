package models;

import com.avaje.ebean.Model;

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
    private Long id;

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "varchar(255) CHARACTER SET utf8 COLLATE utf8_bin")
    private String name;

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
    private List<House> houses = new ArrayList<House>();
}
