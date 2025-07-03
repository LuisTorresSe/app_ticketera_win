package com.win.managerat.application.port.out;
import com.win.managerat.domain.ManagerAt;
import java.util.Optional;

public interface ManagerPort {

    Optional<ManagerAt> findById(Long id);
}
