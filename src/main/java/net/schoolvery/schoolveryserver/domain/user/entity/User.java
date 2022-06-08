package net.schoolvery.schoolveryserver.domain.user.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import net.schoolvery.schoolveryserver.domain.school.entity.School;
import net.schoolvery.schoolveryserver.global.common.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "User")
@Entity
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "uuid2"
    )
    private UUID id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schoolId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private School school;

    private String nickname;
    private String email;
    private String password;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "phone_num")
    private String phoneNum;

    private String address;

    public void modifyUser(String nickname, String password, String phoneNum, String profileImageUrl) {
        this.nickname = nickname;
        this.password = password;
        this.phoneNum = phoneNum;
        this.profileImageUrl = profileImageUrl;
    }

}
