package view;

import java.util.Scanner;
import model.Perpustakaan;
import model.Perpustakaan;

public class Menu {

    private final Scanner input = new Scanner(System.in);
    private final Perpustakaan perpustakaan;

    public Menu(Perpustakaan perpustakaan) {
        this.perpustakaan = perpustakaan;
    }

    public void tampilkanMenuUtama() {
        int pilihan;

        do {
            System.out.println("\n==================================");
            System.out.println(" SISTEM MANAJEMEN PERPUSTAKAAN");
            System.out.println("==================================");
            System.out.println("1. Kategori Buku (Array 1D)");
            System.out.println("2. Statistik Peminjaman (Array 2D)");
            System.out.println("3. Lokasi Buku (Array 3D)");
            System.out.println("4. Kelola Buku (ArrayList)");
            System.out.println("5. Riwayat Transaksi (Stack)");
            System.out.println("6. Antrean Mahasiswa (Queue)");
            System.out.println("0. Keluar");

            pilihan = bacaAngka("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    perpustakaan.tampilkanKategori();
                    break;

                case 2:
                    perpustakaan.tampilkanStatistik();
                    break;

                case 3:
                    perpustakaan.tampilkanLokasi();
                    break;

                case 4:
                    menuBuku();
                    break;

                case 5:
                    menuTransaksi();
                    break;

                case 6:
                    menuAntrean();
                    break;

                case 0:
                    System.out.println("Program selesai. Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 0);
    }

    private void menuBuku() {
        int pilihan;

        do {
            System.out.println("\n=== KELOLA BUKU ===");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Tampilkan Buku");
            System.out.println("3. Hapus Buku");
            System.out.println("4. Pinjam Buku");
            System.out.println("5. Kembalikan Buku");
            System.out.println("0. Kembali");

            pilihan = bacaAngka("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    tambahBuku();
                    break;

                case 2:
                    perpustakaan.tampilkanBuku();
                    break;

                case 3:
                    perpustakaan.tampilkanBuku();
                    String idHapus = bacaTeks("Masukkan ID buku: ");
                    perpustakaan.hapusBuku(idHapus);
                    break;

                case 4:
                    prosesPeminjaman();
                    break;

                case 5:
                    String namaKembali = bacaTeks("Nama mahasiswa: ");
                    String idKembali = bacaTeks("ID buku: ");

                    perpustakaan.kembalikanBuku(
                        namaKembali, idKembali
                    );
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 0);
    }

    private void tambahBuku() {
        String judul = bacaTeks("Judul buku: ");

        perpustakaan.tampilkanKategori();

        int pilihanKategori = bacaAngka("Pilih kategori (1-4): ");

        if (pilihanKategori < 1 || pilihanKategori > 4) {
            System.out.println("Kategori tidak valid!");
            return;
        }

        perpustakaan.tambahBuku(judul, pilihanKategori - 1);
    }

    private void prosesPeminjaman() {
        perpustakaan.tampilkanBuku();

        String nama = bacaTeks("Nama mahasiswa: ");
        String id = bacaTeks("ID buku: ");

        String[] hari = perpustakaan.getHari();

        System.out.println("\nPilih hari peminjaman:");

        for (int i = 0; i < hari.length; i++) {
            System.out.println((i + 1) + ". " + hari[i]);
        }

        int pilihanHari = bacaAngka("Hari (1-7): ");

        if (pilihanHari < 1 || pilihanHari > 7) {
            System.out.println("Hari tidak valid!");
            return;
        }

        perpustakaan.pinjamBuku(nama, id, pilihanHari - 1);
    }

    private void menuTransaksi() {
        int pilihan;

        do {
            System.out.println("\n=== RIWAYAT TRANSAKSI ===");
            System.out.println("1. Tampilkan Semua Riwayat");
            System.out.println("2. Lihat Transaksi Terakhir");
            System.out.println("3. Batalkan Transaksi Terakhir");
            System.out.println("0. Kembali");

            pilihan = bacaAngka("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    perpustakaan.tampilkanRiwayat();
                    break;

                case 2:
                    perpustakaan.lihatTransaksiTerakhir();
                    break;

                case 3:
                    String konfirmasi = bacaTeks(
                        "Yakin membatalkan transaksi terakhir? (y/n): "
                    );

                    if (konfirmasi.equalsIgnoreCase("y")) {
                        perpustakaan.batalkanTransaksiTerakhir();
                    } else {
                        System.out.println("Pembatalan dibatalkan.");
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 0);
    }

    private void menuAntrean() {
        int pilihan;

        do {
            System.out.println("\n=== ANTREAN MAHASISWA ===");
            System.out.println("1. Tambah Antrean");
            System.out.println("2. Tampilkan Antrean");
            System.out.println("3. Layani Antrean");
            System.out.println("0. Kembali");

            pilihan = bacaAngka("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    String nama = bacaTeks("Nama mahasiswa: ");
                    perpustakaan.tambahAntrean(nama);
                    break;

                case 2:
                    perpustakaan.tampilkanAntrean();
                    break;

                case 3:
                    String mahasiswa = perpustakaan.layaniAntrean();

                    if (mahasiswa != null) {
                        String jawab = bacaTeks(
                            "Lanjutkan peminjaman buku? (y/n): "
                        );

                        if (jawab.equalsIgnoreCase("y")) {
                            prosesPeminjamanUntuk(mahasiswa);
                        }
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 0);
    }

    private void prosesPeminjamanUntuk(String nama) {
        perpustakaan.tampilkanBuku();

        String id = bacaTeks("ID buku yang dipinjam: ");
        String[] hari = perpustakaan.getHari();

        for (int i = 0; i < hari.length; i++) {
            System.out.println((i + 1) + ". " + hari[i]);
        }

        int pilihanHari = bacaAngka("Pilih hari (1-7): ");

        if (pilihanHari < 1 || pilihanHari > 7) {
            System.out.println("Hari tidak valid!");
            return;
        }

        perpustakaan.pinjamBuku(nama, id, pilihanHari - 1);
    }

    private String bacaTeks(String pesan) {
        System.out.print(pesan);
        return input.nextLine().trim();
    }

    private int bacaAngka(String pesan) {
        while (true) {
            System.out.print(pesan);
            String teks = input.nextLine().trim();

            try {
                return Integer.parseInt(teks);
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid!");
            }
        }
    }
}

