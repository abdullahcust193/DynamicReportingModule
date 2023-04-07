/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abdullahimran.mavenapp;

import com.opencsv.exceptions.CsvValidationException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author chabd
 */
public class MainClass {

    public static void main(String[] args) throws CsvValidationException {
//        System.out.println("Hello Abdullah");

//        MDForm rf = new MDForm();
//        rf.setVisible(true);

        String file = "C:\\Users\\chabd\\Downloads\\Dbs\\login.csv";
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {

                String[] row = line.split(",");

                for (String index : row) {
                    System.out.printf("%-10s", index);
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

    }
}
