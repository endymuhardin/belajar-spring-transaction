package com.muhardin.endy.belajar.belajarspringtransaction.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity @Data
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = "nomor"),
    @UniqueConstraint(columnNames = "id_nasabah")
})
public class Rekening {
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotEmpty @Size(min = 3, max = 50)
    private String nomor;

    @NotNull
    @ManyToOne @JoinColumn(name = "id_nasabah")
    private Nasabah nasabah;

    @NotNull
    @Min(0)
    private BigDecimal saldo = BigDecimal.ZERO;
}
