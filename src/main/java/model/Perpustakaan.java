/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author MSI THIN 15
 */

public class Perpustakaan {

    // 1. Array 1 Dimensi
    private final String[] kategori = {
        "Pemrograman",
        "Matematika",
        "Jaringan Komputer",
        "Basis Data"
    };

    // 2. Array 2 Dimensi: kategori x hari
    private final String[] hari = {
        "Senin", "Selasa", "Rabu", "Kamis",
        "Jumat", "Sabtu", "Minggu"
    };

    private final int[][] statistik = new int[4][7];

    // 3. Array 3 Dimensi: lantai x rak x posisi
    private final String[][][] lokasi = new String[2][3][4];

    // 4. ArrayList
    private final ArrayList<Buku> daftarBuku = new ArrayList<>();

    // 5. Stack
    private final Stack<Transaksi> riwayat = new Stack<>();

    // 6. Queue
    private final Queue<String> antrean = new LinkedList<>();

    private int nomorBuku = 1;

    public Perpustakaan() {
        tambahBukuAwal("Belajar Java", 0);
        tambahBukuAwal("Algoritma Pemrograman", 0);
        tambahBukuAwal("Matematika Diskrit", 1);
        tambahBukuAwal("Jaringan Komputer", 2);
        tambahBukuAwal("Sistem Basis Data", 3);
    }

    public String[] getKategori() {
        return kategori.clone();
    }

    public String[] getHari() {
        return hari.clone();
    }

    public void tampilkanKategori() {
        System.out.println("\n=== KATEGORI BUKU ===");

        for (int i = 0; i < kategori.length; i++) {
            System.out.println((i + 1) + ". " + kategori[i]);
        }
    }

    private String buatIdBuku() {
        return String.format("B%03d", nomorBuku++);
    }

    private int[] cariTempatKosong() {
        for (int l = 0; l < lokasi.length; l++) {
            for (int r = 0; r < lokasi[l].length; r++) {
                for (int p = 0; p < lokasi[l][r].length; p++) {
                    if (lokasi[l][r][p] == null) {
                        return new int[]{l, r, p};
                    }
                }
            }
        }
        return null;
    }

    private void tambahBukuAwal(String judul, int indeksKategori) {
        int[] tempat = cariTempatKosong();

        if (tempat == null) {
            return;
        }

        String id = buatIdBuku();

        Buku buku = new Buku(
                id, judul, indeksKategori,
                tempat[0], tempat[1], tempat[2]
        );

        daftarBuku.add(buku);
        lokasi[tempat[0]][tempat[1]][tempat[2]] = id;
    }

    public void tambahBuku(String judul, int indeksKategori) {
        if (judul == null || judul.trim().isEmpty()) {
            System.out.println("Judul buku tidak boleh kosong!");
            return;
        }

        if (indeksKategori < 0 || indeksKategori >= kategori.length) {
            System.out.println("Kategori tidak valid!");
            return;
        }

        int[] tempat = cariTempatKosong();

        if (tempat == null) {
            System.out.println("Tempat penyimpanan buku penuh!");
            return;
        }

        String id = buatIdBuku();

        Buku buku = new Buku(
                id, judul.trim(), indeksKategori,
                tempat[0], tempat[1], tempat[2]
        );

        daftarBuku.add(buku);
        lokasi[tempat[0]][tempat[1]][tempat[2]] = id;

        System.out.println("Buku berhasil ditambahkan.");
        System.out.println("ID buku: " + id);
    }

    public void tampilkanBuku() {
        System.out.println("\n=== DAFTAR BUKU ===");

        if (daftarBuku.isEmpty()) {
            System.out.println("Belum ada buku.");
            return;
        }

        for (Buku buku : daftarBuku) {
            System.out.println(
                    buku.toString(kategori[buku.getIndeksKategori()])
            );
        }
    }

    private Buku cariBuku(String id) {
        for (Buku buku : daftarBuku) {
            if (buku.getId().equalsIgnoreCase(id)) {
                return buku;
            }
        }
        return null;
    }

    public void hapusBuku(String id) {
        Buku buku = cariBuku(id);

        if (buku == null) {
            System.out.println("Buku tidak ditemukan.");
            return;
        }

        if (!buku.isTersedia()) {
            System.out.println("Buku sedang dipinjam dan tidak dapat dihapus.");
            return;
        }

        daftarBuku.remove(buku);
        lokasi[buku.getLantai()][buku.getRak()][buku.getPosisi()] = null;

        System.out.println("Buku berhasil dihapus.");
    }

    public void tampilkanStatistik() {
        System.out.println("\n=== STATISTIK PEMINJAMAN ===");

        System.out.printf("%-22s", "Kategori");

        for (String namaHari : hari) {
            System.out.printf("%-10s", namaHari);
        }

        System.out.println();

        for (int i = 0; i < kategori.length; i++) {
            System.out.printf("%-22s", kategori[i]);

            for (int j = 0; j < hari.length; j++) {
                System.out.printf("%-10d", statistik[i][j]);
            }

            System.out.println();
        }
    }

    public void tampilkanLokasi() {
        System.out.println("\n=== LOKASI PENYIMPANAN ===");

        for (int l = 0; l < lokasi.length; l++) {
            System.out.println("\nLantai " + (l + 1));

            for (int r = 0; r < lokasi[l].length; r++) {
                System.out.print("Rak " + (r + 1) + ": ");

                for (int p = 0; p < lokasi[l][r].length; p++) {
                    String id = lokasi[l][r][p];

                    System.out.print(
                            id == null ? "[Kosong] " : "[" + id + "] "
                    );
                }

                System.out.println();
            }
        }
    }

    public void pinjamBuku(String nama, String id, int indeksHari) {
        Buku bukuDipinjam = cariBuku(id); 

        if (bukuDipinjam != null) {
            if (bukuDipinjam.isTersedia()) {
                bukuDipinjam.setTersedia(false);
                bukuDipinjam.setNamaPeminjam(nama); 

                System.out.println("Buku berhasil dipinjam oleh " + nama);
            } else {
                System.out.println("Maaf, buku sedang dipinjam oleh " + bukuDipinjam.getNamaPeminjam());
            }
        } else {
            System.out.println("Buku dengan ID tersebut tidak ditemukan!");
        }
    }

    public void kembalikanBuku(String nama, String id) {
        Buku bukuDikembalikan = cariBuku(id);

        if (bukuDikembalikan != null) {
            if (!bukuDikembalikan.isTersedia()) {
                if (bukuDikembalikan.getNamaPeminjam().equalsIgnoreCase(nama)) {
                    bukuDikembalikan.setTersedia(true);
                    bukuDikembalikan.setNamaPeminjam("-"); 

                    System.out.println("Buku berhasil dikembalikan oleh " + nama);
                } else {
                    System.out.println("Buku ini dipinjam oleh " + bukuDikembalikan.getNamaPeminjam() + ", bukan oleh " + nama + "!");
                }
            } else {
                System.out.println("Buku tersebut saat ini tersedia (tidak sedang dipinjam).");
            }
        } else {
            System.out.println("Buku dengan ID tersebut tidak ditemukan!");
        }
}

    public void tampilkanRiwayat() {
        System.out.println("\n=== RIWAYAT TRANSAKSI ===");

        if (riwayat.isEmpty()) {
            System.out.println("Belum ada transaksi.");
            return;
        }

        for (int i = riwayat.size() - 1; i >= 0; i--) {
            System.out.println(riwayat.get(i));
        }
    }

    public void lihatTransaksiTerakhir() {
        if (riwayat.isEmpty()) {
            System.out.println("Belum ada transaksi.");
            return;
        }

        System.out.println("Transaksi terakhir: " + riwayat.peek());
    }

    public void batalkanTransaksiTerakhir() {
        if (riwayat.isEmpty()) {
            System.out.println("Tidak ada transaksi untuk dibatalkan.");
            return;
        }

        Transaksi transaksi = riwayat.pop();
        Buku buku = transaksi.getBuku();

        if (transaksi.getJenis().equals("PINJAM")) {
            buku.setTersedia(true);

            int indeksKategori = buku.getIndeksKategori();
            int indeksHari = transaksi.getIndeksHari();

            statistik[indeksKategori][indeksHari]--;
            System.out.println("Peminjaman berhasil dibatalkan.");

        } else if (transaksi.getJenis().equals("KEMBALI")) {
            buku.setTersedia(false);
            System.out.println("Pengembalian berhasil dibatalkan.");
        }
    }

    public void tambahAntrean(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            System.out.println("Nama mahasiswa tidak boleh kosong!");
            return;
        }

        antrean.offer(nama.trim());
        System.out.println(nama + " masuk ke antrean.");
    }

    public void tampilkanAntrean() {
        System.out.println("\n=== ANTREAN MAHASISWA ===");

        if (antrean.isEmpty()) {
            System.out.println("Antrean masih kosong.");
            return;
        }

        int nomor = 1;

        for (String nama : antrean) {
            System.out.println(nomor++ + ". " + nama);
        }

        System.out.println("Berikutnya dilayani: " + antrean.peek());
    }

    public String layaniAntrean() {
        String nama = antrean.poll();

        if (nama == null) {
            System.out.println("Antrean masih kosong!");
        } else {
            System.out.println("Mahasiswa yang dilayani: " + nama);
        }

        return nama;
    }
}

