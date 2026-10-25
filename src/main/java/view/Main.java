/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import model.Perpustakaan;

/**
 *
 * @author MSI THIN 15
 */

public class Main {

    public static void main(String[] args) {
        Perpustakaan perpustakaan = new Perpustakaan();

        Menu menu = new Menu(perpustakaan);

        menu.tampilkanMenuUtama();
    }
}
