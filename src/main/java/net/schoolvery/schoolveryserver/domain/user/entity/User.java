package net.schoolvery.schoolveryserver.domain.user.entity;

import lombok.*;
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
    private School school;

    private String nickname;
    private String email;
    private String password;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "phone_num")
    private String phoneNum;

    private String address;

    public void modifyUser(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

}
