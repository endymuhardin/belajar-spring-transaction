package com.muhardin.endy.belajar.belajarspringtransaction.controller;

import com.muhardin.endy.belajar.belajarspringtransaction.dao.RekeningDao;
import com.muhardin.endy.belajar.belajarspringtransaction.dao.TransaksiDao;
import com.muhardin.endy.belajar.belajarspringtransaction.entity.Rekening;
import com.muhardin.endy.belajar.belajarspringtransaction.entity.Transaksi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
public class TransaksiController {

    @Autowired private TransaksiDao transaksiDao;
    @Autowired private RekeningDao rekeningDao;

    @PostMapping("/transaksi/")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertTransaksi(@RequestBody @Valid Transaksi transaksi) throws Exception{
        Rekening rekening = rekeningDao.findById(transaksi.getRekening().getId()).get();
        transaksiDao.save(transaksi);

        if (transaksi.getNilai()
                .remainder(new BigDecimal(7000))
                .compareTo(BigDecimal.ZERO) == 0) {
            throw new RuntimeException("Kelipatan 7000 menimbulkan error");
        }
        
        rekening.setSaldo(rekening.getSaldo().add(transaksi.getNilai()));
        rekeningDao.save(rekening);
    }

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
