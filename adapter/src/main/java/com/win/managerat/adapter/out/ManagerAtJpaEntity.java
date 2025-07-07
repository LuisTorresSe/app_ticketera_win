package com.win.managerat.adapter.out;

import com.win.role.adapter.RoleJpaEntity;
import com.win.role.domain.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "managers")
@AllArgsConstructor()
@NoArgsConstructor()
@Builder
@Data
public class ManagerAtJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private UUID managerId;

    private String email;
    private String password;
    private String fullname ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleJpaEntity role;

}
