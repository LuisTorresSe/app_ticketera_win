package com.win.client.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository  extends JpaRepository<ClientJpaEntity, Long> {

    @Query("""
    select c from ClientJpaEntity c where c.cto = :cto and c.status != :status 
    """)
    List<ClientJpaEntity> findAllByCtoAndStatusNot(
            @Param("cto") String cto,
            @Param("status") String status);
}
