package by.zabavskiy.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode(exclude = {"task"})
@Table(name = "m_elements")
public class Element implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column
  private String description;

  @Column
  private String value;

  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "task_id", nullable = false)
  @JsonBackReference
  private Task task;
}
