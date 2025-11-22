/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package statisticproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ali Haydar
 */
public class Test {
    
    
    public static void main(String[] args) {
       
        DataSet s = new DataSet(11);
        
        String[][] matrix = s.readStringFromFile("C:\\Users\\Ali Haydar\\Documents\\NetBeansProjects\\statisticProject\\src\\statisticproject\\dailyactivity_v3.csv");
       
        System.out.println(s.createArray().length);
        
        System.out.println(matrix[0][11]);
        System.out.println("Değerlerin ortalamsı : " + s.findMean());
        System.out.println("Değerlerin Medyanı : "+s.findMedian());
        System.out.println("Varyans : " + s.findMedian());
        System.out.println("Standart sapma : " + s.standardDeviation());
        System.out.println("Standart Hata : " + s.standartError());
        s.confidenceInterval();
    }
    
   
    
}
