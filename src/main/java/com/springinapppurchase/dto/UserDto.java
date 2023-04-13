package com.springinapppurchase.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.springinapppurchase.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;

    private String userName;

    private User.LoginType loginType;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public UserDto(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}
