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
        BufferedReader[] br1,br2,br3;
        String line1,line2,line3;
        String[] str_array_max = new String[MAXWORD];
        int[] count = new int[MAXWORD];
        int index=0,currentCount=0;
        try {
            br1= new BufferedReader[NUMBERFILE];
            for (int i = 0; i < NUMBERFILE-1; i++) {
                br1[i] = new BufferedReader(new FileReader("f1.txt"));
            }
//            (new FileReader("f1.txt"));
//            br2= new BufferedReader(new FileReader("f2.txt"));
//            br3= new BufferedReader(new FileReader("f3.txt"));
            line1 = br1.readLine();
            line2 = br1.readLine();
            line3 = br1.readLine();
            while(true){
                if(br1==null&&br2==null&&br3==null)
                    break;
            if(line1.charAt(0)>)


            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void findLowerWord(){

    }


}
