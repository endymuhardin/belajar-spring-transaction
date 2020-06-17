package com.muhardin.endy.belajar.belajarspringtransaction.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity @Data
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "nomor")
})
public class Nasabah {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotEmpty
    @Size(min = 3, max = 50)
    private String nomor;

    @NotEmpty @Size(max = 255)
    private String nama;
}
