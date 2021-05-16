package springsecurity.demo.entitites.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import springsecurity.demo.entitites.abstracts.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student extends User {

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    private String studentNumber;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "student")
    Set<Course> courses = new HashSet<>();
}
