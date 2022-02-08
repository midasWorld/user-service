package com.midas.userservice.domain.users;

import com.midas.userservice.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(length = 50, nullable = false)vcb x
    private String name;

    @Column(length = 20, nullable = false)
    private String password;

    @Builder
    public UserEntity(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    //== 비지니스 로직 ==//
    public void update(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
