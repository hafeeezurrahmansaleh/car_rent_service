/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import static junit.framework.Assert.assertEquals;

/**
 *
 * @author mandr
 */
public class RentCar {

    String carRegNumber;
    static String[] car = new String[100];
    static String[] customer = new String[100];
    static String[] trnCar = new String[200];
    static String[] trnCustomer = new String[200];

    RentCar(String reg) {
        this.carRegNumber = reg;
    }

    public static void main(String[] args) {

        new mainMenu().setVisible(true);

    }

    void addTransaction(Date sd, Date ed, String car, String customer, long td, String tc) {
        //sd = new SimpleDateFormat("dd/M/yyyy").parse(sd);
        long diffInMillies = Math.abs(ed.getTime() - sd.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        Calendar today = Calendar.getInstance();
        Date d = today.getTime();
        try {
            FileWriter myWriter = new FileWriter("rentdetails.txt", true);
            myWriter.write(sd + "\t" + ed + "\t" + car + "\t" + customer + "\t" + td + "\t" + tc + "\t" +d+ "\r\n");
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
    int n;

    Boolean checkCarAvailability(Date sd) {
        try {
            //find the number of line (number of cars) in user.txt
            File file = new File("rentdetails.txt");
            FileReader fr = new FileReader(file);
            LineNumberReader lnr = new LineNumberReader(fr);
            int linenumber = 0;
            while (lnr.readLine() != null) {
                linenumber++;
            }
            n = linenumber;
            lnr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        String cars[] = new String[n];
        String startd[] = new String[n];
        String endd[] = new String[n];
        try {
            //read all the car registration number
            BufferedReader br = new BufferedReader(new FileReader("rentdetails.txt"));
            String line = null;
            int i = 0, j = 0;
            //for(int i=0;i<n.length;i++){
            while ((line = br.readLine()) != null) {
                String tmp[] = line.split("\t");
                cars[i] = tmp[2];
                startd[i] = tmp[0];
                endd[i] = tmp[1];
                i++;
            }
            br.close();

        } catch (Exception ex) {
            //System.out.println("File not Found");
        }
        try {
            //check if the given car is already added or not
            int i, k = 0;
            for (i = 0; i < n; i++) {
                    Date date1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(startd[i]);
                    Date date2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(endd[i]);
                    
                
                if (carRegNumber.equals(cars[i]) && sd.after(date1) && sd.before(date2)) {
                    JOptionPane.showMessageDialog(null, "The car is not available on that day ");
                    return false;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }
    
    
}
