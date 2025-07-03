package com.win.serverdown.adapter.out;

import com.win.client.adapter.out.ClientJpaEntity;
import com.win.subticket.adapter.out.SubticketJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Entity
@Table(name = "serverdowns")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServerDownJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long breakDownId;

    @ManyToOne
    @JoinColumn(name = "client_client_id")
    ClientJpaEntity client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subticket_subticket_id")
    SubticketJpaEntity  subticket;

}
