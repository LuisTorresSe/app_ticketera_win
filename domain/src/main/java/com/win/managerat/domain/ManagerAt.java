package com.win.managerat.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE )
public class ManagerAt {
    private Long managerId;
    private String name;
    public static ManagerAt reconstructor(Long managerId, String name) {
        ManagerAt domain = new ManagerAt();
        domain.managerId = managerId;
        domain.name = name;
        return domain;
    }



}
