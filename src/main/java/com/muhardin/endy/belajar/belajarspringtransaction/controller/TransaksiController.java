package com.muhardin.endy.belajar.belajarspringtransaction.controller;

import com.muhardin.endy.belajar.belajarspringtransaction.dao.RekeningDao;
import com.muhardin.endy.belajar.belajarspringtransaction.dao.TransaksiDao;
import com.muhardin.endy.belajar.belajarspringtransaction.entity.Rekening;
import com.muhardin.endy.belajar.belajarspringtransaction.entity.Transaksi;
import com.muhardin.endy.belajar.belajarspringtransaction.service.AuditLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
public class TransaksiController {

    @Autowired private TransaksiDao transaksiDao;
    @Autowired private RekeningDao rekeningDao;

    @Autowired private AuditLogService auditLogService;

    // begin t1
    @Transactional
    @PostMapping("/transaksi/")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertTransaksi(@RequestBody @Valid Transaksi transaksi) throws Exception{

        auditLogService.writeLog("Transaksi untuk rekening dengan id "+transaksi.getRekening().getId());

        Rekening rekening = rekeningDao.findById(transaksi.getRekening().getId()).get();
        transaksiDao.save(transaksi);

        auditLogService.writeLog("Menyimpan transaksi senilai "+transaksi.getNilai());

        if (transaksi.getNilai()
                .remainder(new BigDecimal(7000))
                .compareTo(BigDecimal.ZERO) == 0) {
            auditLogService.writeLog("Gagal memproses transaksi");
            throw new Exception("Kelipatan 7000 menimbulkan error");
        } // rollback t1

        rekening.setSaldo(rekening.getSaldo().add(transaksi.getNilai()));
        rekeningDao.save(rekening);

        auditLogService.writeLog("Transaksi sukses");
    }
    // commit t1

    @ResponseBody
    @GetMapping("/rekening/{rekening}/transaksi/")
    public Iterable<Transaksi> rekapTransaksi(@PathVariable String rekening) {
        Rekening rek = rekeningDao.findById(rekening).get();
        return transaksiDao.findByRekening(rek);
    }

    @ResponseBody
    @GetMapping("/rekening/{rekening}/")
    public Rekening infoRekening(@PathVariable String rekening) {
        return rekeningDao.findById(rekening).get();
    }
}
