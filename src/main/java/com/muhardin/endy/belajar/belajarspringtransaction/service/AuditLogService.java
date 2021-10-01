package com.muhardin.endy.belajar.belajarspringtransaction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service 
public class AuditLogService {

    // suspend/pause t1
    // begin t2
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void writeLog(String message){
        // nanti di sini insert ke db,
        // panggil AuditLogDao
    }
    // commit/rollback t2
    // resume t1
}
