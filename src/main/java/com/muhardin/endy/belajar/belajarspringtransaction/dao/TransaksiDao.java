package com.muhardin.endy.belajar.belajarspringtransaction.dao;

import com.muhardin.endy.belajar.belajarspringtransaction.entity.Rekening;
import com.muhardin.endy.belajar.belajarspringtransaction.entity.Transaksi;
import org.springframework.data.repository.CrudRepository;

public interface TransaksiDao extends CrudRepository<Transaksi, String> {
    Iterable<Transaksi> findByRekening(Rekening rekening);
}
