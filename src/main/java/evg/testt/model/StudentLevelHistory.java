package evg.testt.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity(name = "StudentLevelHistory")
public @Data class StudentLevelHistory extends BaseModel{

    @ManyToOne(cascade = CascadeType.MERGE)
    private Student student;

    @Enumerated
    private StudentTestType testType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date checkpointDate;

    @Transient
    private String testingDate;

    @Enumerated
    private StudentLevelPoints grammar;

    @Enumerated
    private StudentLevelPoints speaking;

    @Enumerated
    private StudentLevelPoints listening;

    @Enumerated
    private StudentLevelPoints reading;

    @Enumerated
    private StudentLevelPoints vocabulary;

    @Enumerated
    private StudentLevelPoints pronunciation;

    @Enumerated
    private StudentLevelPoints writing;

    @Enumerated
    private StudentLevelPoints fluency;

    @Enumerated
    private StudentLevelPoints spelling;
}