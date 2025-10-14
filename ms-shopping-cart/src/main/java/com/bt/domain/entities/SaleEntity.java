package com.bt.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "sales")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id")
    private UserEntity clientEntity;

}
