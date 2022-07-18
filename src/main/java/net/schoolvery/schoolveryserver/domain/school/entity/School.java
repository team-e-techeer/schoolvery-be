package net.schoolvery.schoolveryserver.domain.school.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "school")
@Entity
@Builder
public class School {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "uuid2"
    )
    @Column(columnDefinition = "BINARY(16)")
    private UUID schoolId;

    @Column(name = "school_name")
    private String schoolName;

    public void update(String schoolName) {
        this.schoolName = schoolName;
    }
}
