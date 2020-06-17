package com.muhardin.endy.belajar.belajarspringtransaction.dao;

import com.muhardin.endy.belajar.belajarspringtransaction.entity.Rekening;
import org.springframework.data.repository.CrudRepository;

public interface RekeningDao extends CrudRepository<Rekening, String> {
}
