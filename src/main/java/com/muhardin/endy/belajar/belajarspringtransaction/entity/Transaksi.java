package com.muhardin.endy.belajar.belajarspringtransaction.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

@Entity @Data
public class Transaksi {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime waktuTransaksi;

    @NotNull
    @ManyToOne @JoinColumn(name = "id_rekening")
    private Rekening rekening;

    @NotNull @NotEmpty @Size(max = 255)
    private String keterangan;

    @NotNull @Min(0)
    private BigDecimal nilai;
}
