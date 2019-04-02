package fi.vamk.tka.reactspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.*;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    private String name;
    private String email;
}