package series.serie1;

import java.io.*;
import java.sql.Time;

/**
 * Created by Pedro on 20/10/2016.
 */
public class maiorNrOccorrencias {

    private static int MAXWORD;
    private static int NUMBERFILE;
    public static BufferedReader[] br1;
    public static BufferedWriter bw;
    public static String[] array_file;
    public static String[] lines;
    public static String[] str_array_max;
    public static int[] count;

    public static void main(String[] args) {

        MAXWORD = stringToInt(args[0]);
        str_array_max = new String[MAXWORD];
        count = new int[MAXWORD+1];
        NUMBERFILE = args.length-2;
        lines = new String[NUMBERFILE];
        array_file=new String[NUMBERFILE+1];
        long time = System.currentTimeMillis();
        for (int i = 2; i < args.length; i++) {
            array_file[i-2]=args[i];
        }
        array_file[array_file.length-1] = args[1];

        System.out.println(MAXWORD);
        int index=0,currentCount;
        String currString,actualStr;

        try {
            br1= new BufferedReader[NUMBERFILE];
            for (int i = 0; i < NUMBERFILE; i++) {
                br1[i] = new BufferedReader(new FileReader("C:\\Users\\Pedro\\IdeaProjects\\Aed\\src\\"+array_file[i]));
            }

            for (int i = 0; i < NUMBERFILE; i++) {
                lines[i]=br1[i].readLine();
            }

            while(true){
                index=findLowerWord(lines);
                currentCount=1;

                if(index==-1) break;
                actualStr = lines[index];

                for (int i = index; i < lines.length&&lines[i]!=null&&lines[i].equals(actualStr); i++) {
                    while(lines[i].equals(currString = br1[i].readLine())) {
                        ++currentCount;
                    }
                    if(i!=index)
                        ++currentCount;


                    lines[i] = currString;
                }


                insertInArray(count,str_array_max,actualStr,currentCount);
                //inserir nos arrays


            }
            File file = new File("C:\\Users\\Pedro\\IdeaProjects\\Aed\\src\\"+array_file[array_file.length-1]);
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < MAXWORD; i++) {
                bw.write(str_array_max[i]);
                bw.newLine();
            }
            bw.close();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis()-time);
    }

    private static int stringToInt(String arg) {
        int x = 0;
        for (int i = 0; i < arg.length(); i++) {
            x = x*10 + (arg.charAt(i) - '0');
        }
            return x;

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
    private static void insertInArray( int[] count, String[] str_array_max,String actualString, int currentCount ) {

        int auxInt;
        String auxString;
        int i;

        count[count.length - 1] = currentCount;

        if (count[MAXWORD - 1 ] < count[MAXWORD])
            count[MAXWORD - 1] = count[MAXWORD];
        else return;

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
        str_array_max[i+1] = actualString;
    }


}
