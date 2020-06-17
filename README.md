# Belajar Database Transaction dengan Spring Boot #

Implementasi database transaction dengan Spring Boot dengan simulasi error dan rollback ketika terjadi kesalahan.

## Setup Database ##

1. Create user database

    ```
    createuser -P belajar
    Password: java
    ```

2. Create database

    ```
    createdb -Obelajar belajartransaction
    ```

## Menjalankan Aplikasi ##

1. Jalankan aplikasi

    ```
    mvn clean spring-boot:run
    ```
   
2. Cek saldo rekening

    ```
    curl --request GET 'localhost:8080/rekening/r001/'
    ```

3. Cek histori transaksi

    ```
    curl --request GET 'localhost:8080/rekening/r001/transaksi/'
    ```

4. Insert transaksi

    ```
    curl --request POST 'localhost:8080/transaksi/' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    	"rekening": {
    		"id": "r001"
    	},
    	"waktuTransaksi":"2020-12-31T13:59:59",
    	"keterangan" : "Test transaksi",
    	"nilai" : "10000"
    }'
    ```

## Versi Aplikasi ##

1. Kondisi awal. Bisa insert transaksi dengan lancar

    ```
    git checkout versi-awal
    ```

2. Simulasi error. Akan menghasilkan error code `500` apabila nilai transaksi kelipatan `7000`. Pada saat terjadi error, data akan masuk ke tabel `transaksi`, tapi tidak mengubah saldo di tabel `rekening`.

    ```
    git checkout error-tanpa-rollback
    ```

3. Implementasi rollback dengan Spring. Akan menghasilkan error code `500` apabila nilai transaksi kelipatan `7000`. Pada saat terjadi error, data tidak masuk ke tabel `transaksi` dan tidak mengubah saldo di tabel `rekening`.

    ```
    git checkout error-dengan-rollback
    ```

4. Kesalahan implementasi rollback. Akan menghasilkan error code `500` apabila nilai transaksi kelipatan `7000`. Pada saat terjadi error, data akan masuk ke tabel `transaksi`, tapi tidak mengubah saldo di tabel `rekening`.

    ```
    git checkout kesalahan-rollback
    ```