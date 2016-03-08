package models;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.ColumnHstore;

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
    private Long id;

    @OneToMany(mappedBy = "buildingId")
    private List<House> houses = new ArrayList<House>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Area area;

    @ManyToOne(optional = false)
    @JoinColumn(name = "building_kind_id", referencedColumnName = "id")
    private BuildingKind buildingKind;

    @Column(name = "acreage")
    private Integer acreage;

    @Column(name = "completion_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar completionDate;

    @Column(name = "house_num")
    private Integer houseNum;

    @Column(name = "selled_num")
    private Integer selledNum;

    @Column(name = "order_num")
    private Integer orderNum;

}
