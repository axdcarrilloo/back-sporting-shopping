package com.bt.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "sale_details")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_sale", referencedColumnName = "id")
    private SaleEntity saleEntity;

    @Column(name = "cant_product")
    private Integer cantProduct;

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private ProductEntity productEntity;
}
