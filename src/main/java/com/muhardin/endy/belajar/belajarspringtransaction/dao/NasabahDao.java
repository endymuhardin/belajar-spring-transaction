package com.muhardin.endy.belajar.belajarspringtransaction.dao;

import com.muhardin.endy.belajar.belajarspringtransaction.entity.Nasabah;
import org.springframework.data.repository.CrudRepository;

public interface NasabahDao extends CrudRepository<Nasabah, String> {
}
