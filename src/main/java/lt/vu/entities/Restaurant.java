package lt.vu.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@NamedQueries({
        @NamedQuery(name = "Restaurant.findAll", query = "select s from Restaurant as s")
})
@Table(name = "RESTAURANT")
@Getter @Setter
@EqualsAndHashCode
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    @Column(name = "TITLE")
    private String name;

    @OneToMany(mappedBy = "restaurant")
    private List<Section> sections = new ArrayList<>();
}
