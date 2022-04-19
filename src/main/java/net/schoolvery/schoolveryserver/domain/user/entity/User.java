package net.schoolvery.schoolveryserver.domain.user.entity;

import lombok.*;
import net.schoolvery.schoolveryserver.global.common.BaseEntity;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "User")
@Builder
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id = UUID.randomUUID().toString();

    private String name;
    private int school_id;
    private String nickname;
    private String email;
    private String password;
    private String profile_image_url;
    private int school_num;
    private int phone_num;
    private String address;
}
