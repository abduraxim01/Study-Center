package com.practice.StudyCenter.DTO.responseDTO;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginForResponse {

    private String username;

    private String token;

    private int studyCenterId;

    private String role;

    private Map<String, List<String>> permissions;
}
