package com.midas.userservice.domain.users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "auth")
@Entity(name = "auth")
public class AuthEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String refreshToken;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Builder
    public AuthEntity(String refreshToken, UserEntity user) {
        this.refreshToken = refreshToken;
        this.user = user;
    }

    //== 비지니스 로직 ==//
    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
