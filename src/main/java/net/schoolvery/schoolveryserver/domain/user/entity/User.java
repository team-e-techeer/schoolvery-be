package net.schoolvery.schoolveryserver.domain.user.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;
import net.schoolvery.schoolveryserver.domain.school.entity.School;
import net.schoolvery.schoolveryserver.global.common.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
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
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name", length = 10)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schoolId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private School school;

    @Column(name = "nick_name", length = 10)
    private String nickname;

    @Column(name = "email", length = 20)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "profile_image_url", length = 30)
    private String profileImageUrl;

    @Column(name = "phone_num", length = 25)
    private String phoneNum;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")}
    )
    private Set<Authority> authorities;

    public void modifyUser(String nickname, String password, String phoneNum, String profileImageUrl) {
        this.nickname = nickname;
        this.password = password;
        this.phoneNum = phoneNum;
        this.profileImageUrl = profileImageUrl;
    }

}
