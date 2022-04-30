package net.schoolvery.schoolveryserver.domain.user.entity;

import lombok.*;
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
//    private String school_id;
    private String school;
    private String nickname;
    private String email;
    private String password;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name ="school_num")
    private int schoolNum;

    @Column(name = "phone_num")
    private int phoneNum;
    private String address;

    public void modifyUser(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

}
