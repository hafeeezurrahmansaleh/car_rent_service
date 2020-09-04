/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import javax.swing.JOptionPane;

/**
 *
 * @author mandr
 */
public class customer {

    String drivingLicenseNumber;
    String name;
    String phoneNumber;

    public customer(String drivingLicenseNumber, String name, String phoneNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    public customer(){
        
    }
    int n;

    void addNewCustomer() {
        try {
            //find the number of line (number of user) in user.txt
            File file = new File("customer.txt");
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
        String customerLicenseNumber[] = new String[n];
        try {
            //read all the user license number
            BufferedReader br = new BufferedReader(new FileReader("customer.txt"));
            String line = null;
            int i = 0, j = 0;
            //for(int i=0;i<n.length;i++){
            while ((line = br.readLine()) != null) {

                String tmp[] = line.split("\t");
                customerLicenseNumber[i++] = tmp[0];
            }
            br.close();
        } catch (Exception ex) {
            //System.out.println("File not Found");
        }
        try {
            //check if the given user is already added or not
            int i, k = 0;
            for (i = 0; i < n; i++) {
                if (drivingLicenseNumber.equals(customerLicenseNumber[i])) {
                    JOptionPane.showMessageDialog(null, "Failed to add \nThis user is already in the list! ");
                    k = 1;
                    break;
                }
            }
            if (k == 0) {
                //if user not in the list, then insert the user into file
                try {
                    FileWriter fw = new FileWriter("customer.txt", true);
                    fw.write(drivingLicenseNumber + "\t" + name + "\t" + phoneNumber + "\r\n");
                    //fw.write("\n");
                    fw.close();
                    JOptionPane.showMessageDialog(null, "Successfully added! ");
                } catch (Exception e) {
                    System.out.println("File not Found");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public String[] getAllCustomerName(){
        try {//pull customer data
            //find the number of line (number of user) in user.txt
            File file = new File("customer.txt");
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
        String customerName[] = new String[n];
        try {
            //read all the user license number
            BufferedReader br = new BufferedReader(new FileReader("customer.txt"));
            String line = null;
            int i = 0, j = 0;
            //for(int i=0;i<n.length;i++){
            while ((line = br.readLine()) != null) {

                String tmp[] = line.split("\t");
                customerName[i++] = tmp[1];
            }
            br.close();
        } catch (Exception ex) {
            //System.out.println("File not Found");
        }
        return customerName;
    }
    
    public void editCustomer(String name, String newLine) {
        int i;
        String customerInfo[] = getCustomerInfo(name);
        String oldLine = customerInfo[0] + "\t" + customerInfo[1] + "\t" + customerInfo[2];

        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader("customer.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            String inputStr = inputBuffer.toString();

            System.out.println(oldLine); // display the original file for debugging

            // logic to replace lines in the string (could use regex here to be generic)
            inputStr = inputStr.replace(oldLine, newLine);

            // display the new file for debugging
            System.out.println("----------------------------------\n" + newLine);

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream("customer.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }

    }
    
    public String[] getCustomerInfo(String name) {
        try {//pull car data
            //find the number of line (number of cars) in user.txt
            File file = new File("customer.txt");
            FileReader fr = new FileReader(file);
            LineNumberReader lnr = new LineNumberReader(fr);
            int linenumber = 0;
            while (lnr.readLine() != null) {
                linenumber++;
            }
            n = linenumber;
            fr.close();
            lnr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
         
        String customerName[] = new String[n];
        String customerInfo[] = new String[3];
        try {
            //read all the car registration number
            
            BufferedReader br = new BufferedReader(new FileReader("customer.txt"));
            String line = null;
            int i = 0, j = 0;
            //for(int i=0;i<n.length;i++){
            while ((line = br.readLine()) != null) {

                String tmp[] = line.split("\t");
                customerName[i++] = tmp[1];
                if (name.equals(tmp[1])) {
                    customerInfo[0] = tmp[0];
                    customerInfo[1] = tmp[1];
                    customerInfo[2] = tmp[2];
                    br.close();
                    
                    break;
                }
            }
            br.close();

        } catch (Exception ex) {
            //System.out.println("File not Found");
            
           
        }
        return customerInfo;

    }
 
    void delet(String name){
        String[] cInfo = getCustomerInfo(name);
        String infoToBeRemoved = cInfo[0]+"\t"+cInfo[1]+"\t"+cInfo[2];
        try {//pull car data
            //find the number of line (number of cars) in user.txt
            File file = new File("customer.txt");
            FileReader fr = new FileReader(file);
            LineNumberReader lnr = new LineNumberReader(fr);
            int linenumber = 0;
            while (lnr.readLine() != null) {
                linenumber++;
            }
            n = linenumber;
            fr.close();
            lnr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        String customerInfo[] = new String[n];
        try {
            //read all the car registration number
            
            BufferedReader br = new BufferedReader(new FileReader("customer.txt"));
            String line = null;
            int i = 0, j = 0;
            //for(int i=0;i<n.length;i++){
            while ((line = br.readLine()) != null) {

                if (infoToBeRemoved.equals(line)) {
                    
                }
                else{
                    customerInfo[i++] = (String)line;
                }
            }
            br.close();
        try {
            //check if the given user is already added or not
            int o;
            FileWriter fw = new FileWriter("customer.txt");
            for (o = 0; o < n-1; o++) {
                try {
                    fw.write(customerInfo[o]+ "\r\n");
                    //fw.write("\n");
                } catch (Exception e) {
                    System.out.println("File not Found");
                }
            }
            fw.close();
            JOptionPane.showMessageDialog(null, "Successfully Deleted! ");
        } catch (Exception e) {
            System.out.println(e);
        }
        } catch (Exception ex) {
            //System.out.println("File not Found");
        }
    }
}
