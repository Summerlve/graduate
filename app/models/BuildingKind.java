package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 2016/3/2.
 */

@Entity
@Table(name = "zh_building_kind")
public class BuildingKind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "varchar(255) CHARACTER SET utf8 COLLATE utf8_bin")
    private String name;

    @OneToMany(mappedBy = "buildingKind")
    private List<Building> buildings = new ArrayList<Building>();
}
