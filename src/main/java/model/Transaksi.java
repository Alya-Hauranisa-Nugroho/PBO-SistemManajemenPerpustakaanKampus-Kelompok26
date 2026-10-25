    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package model;

    /**
     *
     * @author MSI THIN 15
     */
    public class Transaksi {
        private String jenis;
        private String namaMahasiswa;
        private Buku buku;
        private int indeksHari;

        public Transaksi(String jenis, String namaMahasiswa,
                         Buku buku, int indeksHari) {
            this.jenis = jenis;
            this.namaMahasiswa = namaMahasiswa;
            this.buku = buku;
            this.indeksHari = indeksHari;
        }

        public String getJenis() {
            return jenis;
        }

        public String getNamaMahasiswa() {
            return namaMahasiswa;
        }

        public Buku getBuku() {
            return buku;
        }

        public int getIndeksHari() {
            return indeksHari;
        }

        @Override
        public String toString() {
            return jenis
                    + " | Mahasiswa: " + namaMahasiswa
                    + " | Buku: " + buku.getJudul();
        }
    }
