package fi.vamk.tka.reactspring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.Instant;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.FetchType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Event {

    @Id
    @GeneratedValue
    private Long id;
    private Instant date;
    private String title;
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> attendees;

    /*public void addAttendee(User user){
        if(attendees==null){
            attendees = new HashSet<User>();
        }
        attendees.add(user);
    }*/
}