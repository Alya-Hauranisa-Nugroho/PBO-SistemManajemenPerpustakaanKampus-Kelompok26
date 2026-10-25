package model;

public class Buku {
    private String id;
    private String judul;
    private int indeksKategori;
    private int lantai;
    private int rak;
    private int posisi;
    private boolean tersedia;
    private String namaPeminjam; 

    public Buku(String id, String judul, int indeksKategori,
                int lantai, int rak, int posisi) {
        this.id = id;
        this.judul = judul;
        this.indeksKategori = indeksKategori;
        this.lantai = lantai;
        this.rak = rak;
        this.posisi = posisi;
        this.tersedia = true;
        this.namaPeminjam = "-"; 
    }

    public String getId() { return id; }
    public String getJudul() { return judul; }
    public int getIndeksKategori() { return indeksKategori; }
    public int getLantai() { return lantai; }
    public int getRak() { return rak; }
    public int getPosisi() { return posisi; }
    public boolean isTersedia() { return tersedia; }
    
    public void setTersedia(boolean tersedia) { 
        this.tersedia = tersedia; 
    }
    
    public String getNamaPeminjam() { 
        return namaPeminjam; 
    }
    
    public void setNamaPeminjam(String namaPeminjam) { 
        this.namaPeminjam = namaPeminjam; 
    }

    public String toString(String namaKategori) {
        String status = tersedia ? "Tersedia" : "Dipinjam oleh: " + namaPeminjam;
        
        return "ID: " + id
                + " | Judul: " + judul
                + " | Kategori: " + namaKategori
                + " | Lokasi: Lantai " + (lantai + 1)
                + ", Rak " + (rak + 1)
                + ", Posisi " + (posisi + 1)
                + " | Status: " + status;
    }
}