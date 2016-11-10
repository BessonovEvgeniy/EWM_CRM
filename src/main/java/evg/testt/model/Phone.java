package evg.testt.model;

import javax.persistence.*;
import lombok.Data;

@Entity(name = "phones")
public @Data class Phone extends BaseModel{

    private String phone;

    @ManyToOne()
    @JoinColumn(name = "person_id")
    private Person person;
}
