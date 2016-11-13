package evg.testt.model;

import javax.persistence.*;
import java.util.Set;
import lombok.Data;
import lombok.Builder;

@Entity(name = "groups")
@Builder
public @Data class Group extends BaseModel {

    private String name;

    private String level;

    @ManyToOne
    private Teacher teacher;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Event> eventSet;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Student> studentSet;
}
