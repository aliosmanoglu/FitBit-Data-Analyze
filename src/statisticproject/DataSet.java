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
public class DataSet {

    String[][] dataMatrix;
    int column;

    public DataSet(int column) {
        this.column = column;
        dataMatrix = readStringFromFile("C:\\Users\\Ali Haydar\\Documents\\NetBeansProjects\\statisticProject\\src\\statisticproject\\dailyactivity_v3.csv");
    }

    //Ortalama hesaplayan metot.
    public double findMean() {
        double mean = 0.0;
        for (int i = 1; i < dataMatrix[1].length; i++) {
            mean += Double.parseDouble(dataMatrix[i][column]);
        }
        mean /= dataMatrix.length;

        return mean;
    }
    
    
    //Medyanı hesaplayan metot.
    public double findMedian() {
        double[] a = createArray();
        arraySort(a);
        double median = 0;
        
        if (a.length % 2 == 0) {
            median = (a[(a.length / 2)] + a[(a.length / 2) + 1]) / 2 ;
        } else {
            median = a[(a.length + 1) / 2];
        }
        return median;
    }

    
    //Varyansı hesaplayan metot.
    public double findVarians() {
        double[] a = createArray();
        arraySort(a);
        double mean = findMean();
        double[] deviations = new double[a.length];

        for (int i = 0; i < deviations.length; i++) {
            deviations[i] = Math.pow((mean - a[i]), 2);
        }

        double deviationsSum = 0;
        for (int i = 0; i < deviations.length; i++) {
            deviationsSum += deviations[i];
        }
        return deviationsSum / (deviations.length);
    }

    public double standardDeviation() {
        return Math.sqrt(findVarians());
    }

    public double standartError() {
        return standardDeviation() / Math.sqrt(dataMatrix.length);
    }

    
   /* //Aykırı değer varsa bulmamızı sağlayan metot.
    public void findOutliers() {
        double zPoint = 0;
        ArrayList<Double> d = new ArrayList<>();
        double[] a = createArray();
        for (int i = 0; i < a.length; i++) {
            zPoint = (a[i] - findMean()) + standardDeviation();
            if (zPoint <= 3 && zPoint >= -3) {
                continue;
            } else {
                d.add(zPoint);
            }
        }
        System.out.print("Aykırı değerler :");
        int counter = 0;
        for (int i = 0; i < d.size(); i++) {
            System.out.println(d.get(i));
            counter++;
        }
        System.out.println("counter = " + counter);
    }*/

    
    //Güven aralığını hesaplayan metot.
    public void confidenceInterval() {
        int numberOfSample = 700;
        double[] a = createArray();
        double[] sampleArray = new double[numberOfSample];
        
        for (int i = 0; i < numberOfSample; i++) {
            int index =  25;
            if (a[index] != -1) {
                sampleArray[i] = a[index];
                a[index] = -1;
            }
        }
        double sampleMean = 0;
        for (int i = 0; i < sampleArray.length; i++) {
            sampleMean = sampleArray[i];
        }
        sampleMean /= sampleArray.length;
        double standartDeviation = 0;
        for (int i = 0; i < sampleArray.length; i++) {
            standartDeviation += Math.pow(sampleArray[i] - sampleMean, 2);
        }
        standartDeviation = Math.sqrt(standartDeviation / sampleArray.length);

        double standartError = 1.96 * (standartDeviation / Math.sqrt(numberOfSample));

        System.out.println("Güven aralığı = " + (sampleMean - standartError) + " - " + (sampleMean + standartError) + " arasında.");
    }

    
    //Marj değerini bulmamızı sağlayan metot.
    public double findMarj() {
        double zPoint = 1.645; // %90 güven düzeyi için z değeri
        double s = standardDeviation() / (dataMatrix[1].length);
        
        
        double marj = Math.pow((zPoint * s / 0.1), 2);

        return marj;
    }

    
    //Arrayi küçükten büyüğe sıralayan metot.
    public double[] arraySort(double[] array) {
        
        for (int swapIndex = 0; swapIndex < array.length-1; swapIndex++) {
            
            int minIndex = swapIndex;
            for (int i = swapIndex; i < array.length; i++) {
                if(array[minIndex] > array[i]) {
                    minIndex = i;
                }
            }
            if(minIndex != swapIndex) {
                double temp = array[swapIndex];
                array[swapIndex] = array[minIndex];
                array[minIndex] = temp;
            }
        }
        return array;
    }

    
    //Matrisdeki istediğimiz seçilen kolondaki bütün değerleri bir arraye atan metot.
    public double[] createArray() {
        double[] newArray = new double[dataMatrix.length];

        for (int i = 1, j = 0; i < newArray.length; i++, j++) {
            newArray[j] = Double.parseDouble(dataMatrix[i][this.column]);
        }
        return newArray;

    }

    public static String[][] readStringFromFile(String filePath) {
        List<String[]> dataList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                dataList.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int rows = dataList.size();
        int cols = dataList.get(0).length;

        String[][] dataArray = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            String[] data = dataList.get(i);
            for (int j = 0; j < cols; j++) {
                dataArray[i][j] = data[j];
            }
        }

        return dataArray;
    }
}
