package com.midas.userservice.domain.users;

import com.midas.userservice.domain.BaseTimeEntity;
import com.midas.userservice.domain.roles.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(
        name = "unq_user_email",
        columnNames = "email"
)})
@Entity(name = "users")
public class UserEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String encryptedPwd;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @Builder
    public UserEntity(String email, String name, String encryptedPwd) {
        this.email = email;
        this.name = name;
        this.encryptedPwd = encryptedPwd;
    }

    //== 비지니스 로직 ==//
    public void update(String name, String encryptedPwd) {
        this.name = name;
        this.encryptedPwd = encryptedPwd;
    }
}
