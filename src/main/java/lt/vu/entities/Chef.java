package lt.vu.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
        @NamedQuery(name= "Chef.findAll", query = "select c from Chef as c")
})
@Table(name = "Chef")
@Getter
@Setter
@EqualsAndHashCode
public class Chef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Column(name = "DOORCODE")
    private String doorCode;

    @ManyToOne
    @JoinColumn(name = "SECTION_ID")
    private Section section;

}
