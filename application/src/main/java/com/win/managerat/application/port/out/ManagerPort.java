package com.win.managerat.application.port.out;
import com.win.managerat.domain.ManagerAt;
import java.util.Optional;
import java.util.UUID;

public interface ManagerPort {

    Optional<ManagerAt> findById(UUID id);
}
