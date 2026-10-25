package view;
import model.Perpustakaan;

public class Main {

    public static void main(String[] args) {
        Perpustakaan perpustakaan = new Perpustakaan();

        Menu menu = new Menu(perpustakaan);

        menu.tampilkanMenuUtama();
    }
}
