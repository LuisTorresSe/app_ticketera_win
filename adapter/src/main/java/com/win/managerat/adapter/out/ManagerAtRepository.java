package com.win.managerat.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerAtRepository extends JpaRepository<ManagerAtJpaEntity, Long> {
}
