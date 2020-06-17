delete from transaksi;
delete from rekening;
delete from nasabah;

insert into nasabah(id, nomor, nama)
values ('n001', 'N-001', 'Nasabah 001');

insert into rekening(id, id_nasabah, nomor, saldo)
values ('r001', 'n001', 'R-001', 0);
