package com.springinapppurchase.entity.user;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    public enum LoginType {
        Email,
        Social
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name", length = 45)
    private String userName;


    public User(Long userId) {
        this.id = userId;
    }


    @Builder(builderMethodName = "of")
    public User(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}
