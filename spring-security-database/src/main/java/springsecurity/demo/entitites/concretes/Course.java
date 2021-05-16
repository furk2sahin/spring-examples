package springsecurity.demo.entitites.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String courseName;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"courses", "username", "password", "role", "grantedAuthorities", "studentName",
    "studentNumber", "enabled", "accountNonLocked", "credentialsNonExpired", "accountNonExpired", "authorities"})
    private Student student;
}
