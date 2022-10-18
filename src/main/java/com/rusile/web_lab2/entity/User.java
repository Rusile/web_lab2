package com.rusile.web_lab2.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class User {

    private String login;

    private String password;

    private Long validTill;

    private String sign;
}
