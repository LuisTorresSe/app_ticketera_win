package com.win.managerat.adapter.out;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "managers")
@AllArgsConstructor()
@NoArgsConstructor()
@Builder
@Data
public class ManagerAtJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long managerId;
    private String name;
}
