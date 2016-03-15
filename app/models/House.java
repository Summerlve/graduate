package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by Summer on 2016/3/1.
 */

@Entity
@Table(name = "zh_house")
public class House extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    private Building buildingId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "house_state_id", referencedColumnName = "id")
    private HouseState state;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "house_no")
    private Integer houseNo;

    @Column(name = "buy_price")
    private Number buyPrice;

    @Column(name = "space")
    private Integer space;

    @Column(name = "buy_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar buyDate;

    @Column(name = "in_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar inDate;

    public static final Finder<Long, House> find = new Finder<Long, House>(House.class);
}
