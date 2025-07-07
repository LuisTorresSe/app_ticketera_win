package com.win.managerat.adapter.out;

import com.win.managerat.domain.ManagerAt;
import org.antlr.v4.runtime.misc.MultiMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ManagerAtRepository extends JpaRepository<ManagerAtJpaEntity, Long> {

    Optional<ManagerAtJpaEntity> findByManagerId(UUID managerId);
}
