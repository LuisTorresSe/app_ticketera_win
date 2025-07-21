package com.win.auth.application.port.out;

import com.win.managerat.domain.ManagerAt;

import java.util.Optional;

public interface AuthPort {

    Optional<ManagerAt> findByEmail(String email);

}
