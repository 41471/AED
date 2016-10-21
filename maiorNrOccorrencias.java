package series.serie1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Pedro on 20/10/2016.
 */
public class maiorNrOccorrencias {

    public static int MAXWORD = 10;
    public static int NUMBERFILE=3;


    public static void main(String[] args) {
        BufferedReader[] br1;
        String[] array_file = {"f1.txt","f2.txt","f3.txt"};
        String[] lines = new String[NUMBERFILE];
        String[] str_array_max = new String[MAXWORD];
        int[] count = new int[MAXWORD];
        int index=0,currentCount=0;
        try {
            br1= new BufferedReader[NUMBERFILE];
            for (int i = 0; i < NUMBERFILE-1; i++) {
                br1[i] = new BufferedReader(new FileReader(array_file[i]));
            }

            for (int i = 0; i < NUMBERFILE-1; i++) {
                lines[i]=br1[i].readLine();
            }
            while(true){

                for (int i = 0; i < NUMBERFILE-1; i++) {

                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static int findLowerWord(){
        return -1;
    }


}
