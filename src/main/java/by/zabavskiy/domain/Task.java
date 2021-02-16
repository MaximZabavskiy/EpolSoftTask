package by.zabavskiy.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Data
@Entity
// @EqualsAndHashCode(exclude = {
//        "role", "programs", "workouts", "calendar"})
@Table(name = "m_tasks")
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
// @Cacheable
public class Task implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column
  private String description;

  @Column(name = "start_date")
  @Temporal(TemporalType.DATE)
  private Date startDate;

  @Column(name = "end_date")
  @Temporal(TemporalType.DATE)
  private Date endDate;

  @Column private Long status;

  @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonManagedReference
  private Set<Element> elements = Collections.emptySet();
}
