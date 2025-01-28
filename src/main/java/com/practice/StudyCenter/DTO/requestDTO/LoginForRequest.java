package com.practice.StudyCenter.DTO.requestDTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginForRequest {

    private String username;

    private String password;
}
