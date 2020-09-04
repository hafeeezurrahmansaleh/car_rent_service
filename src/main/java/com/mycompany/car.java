/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
public class car {
//    File processCheck = new File("car.txt");
//        boolean successful = processCheck.renameTo(processCheck);
//        JOptionPane.showMessageDialog(null, successful);
    String registrationNumber;
    String carName;
    String costPerDay;

    public car(String registrationNumber, String carName, String costPerDay) {
        this.registrationNumber = registrationNumber;
        this.carName = carName;
        this.costPerDay = costPerDay;
    }

    public car() {

    }

    int n;

    public void addCar() {
        
        try {
            //find the number of line (number of cars) in user.txt
            File file = new File("car.txt");
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
        String carRegistrationNumber[] = new String[n];
        try {
            //read all the car registration number
            BufferedReader br = new BufferedReader(new FileReader("car.txt"));
            String line = null;
            int i = 0, j = 0;
            //for(int i=0;i<n.length;i++){
            while ((line = br.readLine()) != null) {

                String tmp[] = line.split("\t");
                carRegistrationNumber[i++] = tmp[0];
            }
            br.close();
        } catch (Exception ex) {
            //System.out.println("File not Found");
        }
        try {
            //check if the given car is already added or not
            int i, k = 0;
            for (i = 0; i < n; i++) {
                if (registrationNumber.equals(carRegistrationNumber[i])) {
                    JOptionPane.showMessageDialog(null, "Failed to add \nThe car with this registration number is already in the list! ");
                    k = 1;
                    break;
                }
            }
            if (k == 0) {
                try {
                    //if car not in the list, then insert the user into file
                    FileWriter myWriter = new FileWriter("car.txt", true);
                    myWriter.write(registrationNumber + "\t" + carName + "\t" + costPerDay + "\r\n");
                    myWriter.close();

                } catch (IOException e) {
                    System.out.println("An error occurred.");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String[] getAllCars() {
        try {//pull car data
            //find the number of line (number of cars) in user.txt
            File file = new File("car.txt");
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
        String carRegistrationNumber[] = new String[n];
        try {
            //read all the car registration number
            BufferedReader br = new BufferedReader(new FileReader("car.txt"));
            String line = null;
            int i = 0, j = 0;
            //for(int i=0;i<n.length;i++){
            while ((line = br.readLine()) != null) {

                String tmp[] = line.split("\t");
                carRegistrationNumber[i++] = tmp[0];
            }
            br.close();

        } catch (Exception ex) {
            //System.out.println("File not Found");

        }
        return carRegistrationNumber;
    }

    public void editCar(String carReg, String newLine) {
        int i;
        String carInfo[] = getCarInfo(carReg);
        String oldLine = carInfo[0] + "\t" + carInfo[1] + "\t" + carInfo[2];

        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader("car.txt"));
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
            FileOutputStream fileOut = new FileOutputStream("car.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }

    }

    public String[] getCarInfo(String carReg) {
        double rentPerDay = 0;
        
        try {//pull car data
            //find the number of line (number of cars) in user.txt
            File file = new File("car.txt");
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
        String carRegistrationNumber[] = new String[n];
        String carInfo[] = new String[3];
        try {
            //read all the car registration number
            BufferedReader br = new BufferedReader(new FileReader("car.txt"));
            String line = null;
            int i = 0, j = 0;
            //for(int i=0;i<n.length;i++){
            while ((line = br.readLine()) != null) {

                String tmp[] = line.split("\t");
                carRegistrationNumber[i++] = tmp[0];
                if (carReg.equals(tmp[0])) {
                    carInfo[0] = tmp[0];
                    carInfo[1] = tmp[1];
                    carInfo[2] = tmp[2];
                    br.close();
                    break;

                }
            }
            br.close();

        } catch (Exception ex) {
            //System.out.println("File not Found");
            rentPerDay = 0;

        }
        
        return carInfo;

    }

    void deleteCar(String carReg) {

        try {
            File inputFile = new File("car.txt");
            File tempFile = new File("myTempFile.txt");

            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            String lineToRemove = "n	Nadia	10000";
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToRemove)) {
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            reader.close();
            writer.close();

//            File processCheck = new File("car.txt");
//            File processCheck1 = new File("myTempFile.txt");
////        Boolean canBeDeleted = processCheck.canWrite();
////        JOptionPane.showMessageDialog(null, canBeDeleted);
////            File demo = new File("demo.txt");
//            boolean successful = processCheck.renameTo(processCheck);
//            JOptionPane.showMessageDialog(null, successful);
        } catch (Exception e) {

        }
    }
    
    void delet(String license){
        String[] cInfo = getCarInfo(license);
        String infoToBeRemoved = cInfo[0]+"\t"+cInfo[1]+"\t"+cInfo[2];
        try {//pull car data
            //find the number of line (number of cars) in user.txt
            File file = new File("car.txt");
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
       
        String carInfo[] = new String[n];
        try {
            //read all the car registration number
            
            BufferedReader br = new BufferedReader(new FileReader("car.txt"));
            String line = null;
            int i = 0, j = 0;
            //for(int i=0;i<n.length;i++){
            while ((line = br.readLine()) != null) {

                if (infoToBeRemoved.equals(line)) {
                    
                }
                else{
                    carInfo[i++] = (String)line;
                }
            }
            br.close();
        try {
            //check if the given user is already added or not
            int o;
            FileWriter fw = new FileWriter("car.txt");
            for (o = 0; o < n-1; o++) {
                try {
                    fw.write(carInfo[o]+ "\r\n");
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
