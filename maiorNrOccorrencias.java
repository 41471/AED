package series.serie1;

import java.io.*;

/**
 * Created by Pedro on 20/10/2016.
 */
public class maiorNrOccorrencias {

    private static final int MAXWORD = 10;
    private static final int NUMBERFILE=3;
    public static BufferedReader[] br1;
    public static BufferedWriter bw;
    public static String[] array_file = {"f1.txt","f2.txt","f3.txt"};
    public static String[] lines = new String[NUMBERFILE];
    public static String[] str_array_max = new String[MAXWORD];
    public static int[] count = new int[MAXWORD+1];

    public static void main(String[] args) {

        int index=0,currentCount;

        try {
            br1= new BufferedReader[NUMBERFILE];
            for (int i = 0; i < NUMBERFILE-1; i++) {
                br1[i] = new BufferedReader(new FileReader("C:\\Users\\Pedro\\IdeaProjects\\Aed\\src\\"+array_file[i]));
            }

            for (int i = 0; i < NUMBERFILE-1; i++) {
                lines[i]=br1[i].readLine();
            }
            while(true){
                index=findLowerWord(lines);
                currentCount=0;
                if(index==-1) break;
                while(lines[index].equals(br1[index].readLine())){
                    currentCount++;
                }

                insertInArray(count,str_array_max,lines,currentCount,index);
                //inserir nos arrays

                lines[index]=br1[index].readLine();


            }
            bw = new BufferedWriter(new FileWriter("output.txt"));
            for (int i = 0; i < MAXWORD; i++) {
                bw.write(str_array_max[i]);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static int findLowerWord(){
        return -1;
    }

    static void exchange(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static int findLowerWord(String[] array) {

        int lowerWord = -1;
        boolean first = true;

        for (int i = 0; i < array.length; i++) {

            if ( array[i] != null && !first && array[i].compareTo(array[lowerWord]) < 0) {

                lowerWord = i;
            }

            if (first && array[i] != null) {
                lowerWord = i;
                first = false;
            }
        }

        return lowerWord;
    }
    private static void insertInArray( int[] count, String[] str_array_max, String[] lines, int currentCount, int idxOfLines ) {

        int auxInt;
        String auxString;
        int i;

        count[count.length - 1] = currentCount;

        if (count[MAXWORD - 1] < count[MAXWORD])
            count[MAXWORD - 1] = count[MAXWORD];

        for (i = MAXWORD - 2; i >= 0; i--) {

            if (count[i] < count[i + 1]) {
                auxInt = count[i];
                auxString = str_array_max[i];

                count[i] = count[i + 1];
                str_array_max[i] = str_array_max[i + 1];

                count[i + 1] = auxInt;
                str_array_max[i + 1] = auxString;
            } else break;

        }
        str_array_max[i+1] = lines[idxOfLines];
    }


}
