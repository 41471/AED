package series.serie3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;


/**
 * Created by Pedro on 11/01/2017.
 */
public class SchoolBusRouting {

    public static int N_CRUZAMENTOS;
    public static int N_RUAS;
    public static Cruzamento[] cruzamento;
    public static int startIdx;


    public static void main(String[] args) throws Exception {
        String input=null;
        loadRoute(args[2]);
        Scanner in = new Scanner(System.in);

        while(true) {
            input=in.nextLine();
            if(input.equals("e"))
                System.exit(0);
            if(input !=null)
                 loadPath(input);
            verifyRoute(cruzamento[startIdx]);
            System.out.println("end");
        }



    }

    static class Cruzamento{
        int id;
        boolean isStart;
        LinkedList<Rua> ruaList = new LinkedList<>();
        public Cruzamento(int id) {
            this.id=id;
        }
    }

    static class Rua{
        int origin;
        int end;
        double weight;
        boolean isPassed;
        public Rua(int origin,int end,double weight){
            this.origin=origin;
            this.end=end;
            this.weight=weight;
        }
    }

    public static void loadRoute(String fileName){
        String str;
        String[] phrase;

        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Pedro\\IdeaProjects\\Aed\\src\\series\\serie3\\"+fileName));
            str=br.readLine();
            phrase = str.split(" ");
            N_CRUZAMENTOS=Integer.parseInt(phrase[2]);
            N_RUAS=Integer.parseInt(phrase[3]);
            cruzamento = new Cruzamento[N_CRUZAMENTOS+1];

            while((str=br.readLine())!=null) {
                phrase=str.split(" ");
                if(phrase[0].charAt(0)=='c')
                    System.out.println(str);
                else {

                    if(cruzamento[Integer.parseInt(phrase[1])]==null)
                        cruzamento[Integer.parseInt(phrase[1])]= new Cruzamento(Integer.parseInt(phrase[1]));
                    if(cruzamento[Integer.parseInt(phrase[2])]==null)
                        cruzamento[Integer.parseInt(phrase[2])]= new Cruzamento(Integer.parseInt(phrase[2]));
                    cruzamento[Integer.parseInt(phrase[1])].ruaList.add(new Rua(Integer.parseInt(phrase[1]),Integer.parseInt(phrase[2]),Integer.parseInt(phrase[3])));
                    cruzamento[Integer.parseInt(phrase[2])].ruaList.add(new Rua(Integer.parseInt(phrase[2]),Integer.parseInt(phrase[1]),Integer.parseInt(phrase[3])));

                }

            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void loadPath(String fileName){
        String str;

        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Pedro\\IdeaProjects\\Aed\\src\\series\\serie3\\"+fileName));
            br.readLine();
            int idx1;
            int idx2=0;
            boolean end=false;
            idx1=Integer.parseInt(br.readLine());
            cruzamento[idx1].isStart=true;
            startIdx = idx1;
            while(true){
                str=br.readLine();
                if(str!=null) {
                    idx2 = Integer.parseInt(str);
                }
                else end=true;

                for (int i = idx1+1; i < idx2 || end&&i<N_CRUZAMENTOS+1; i++) {
                    for (Rua j: cruzamento[i].ruaList) {
                        for (Rua k: cruzamento[j.end].ruaList) {
                            if(i==k.end){
                                cruzamento[j.end].ruaList.remove(k);
                                break;
                            }
                        }
                    }
                }
                if(end)
                    break;

                idx1=idx2;

            }


        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static Stack<Cruzamento> stk = new Stack<>();
    public static Stack<String> output = new Stack<>();
    public static boolean isStk=false;
    public static boolean toPrint=true;
    public static void verifyRoute(Cruzamento curr) throws Exception {
        Cruzamento aux;
        int count = 0;

        if ((curr.ruaList.size() % 2) == 1) {
            System.out.println("Not Euler Circuit");
            throw new Exception();
        }
        for (Rua r : curr.ruaList) {
            if (!r.isPassed) count++;
        }
        if (count == 1)
            isStk = true;

        if (count > 1 && !cruzamento[curr.id].isStart) {
            stk.push(curr);
            toPrint = false;
        }


        for (Rua i : curr.ruaList) {

            if (!i.isPassed) {
                for (Rua k : cruzamento[i.end].ruaList) {
                    if (k.end == i.origin)
                        k.isPassed = true;
                }
                i.isPassed = true;
                System.out.println(i.origin + " -> " + i.end + "("+i.weight + ")");


                verifyRoute(cruzamento[i.end]);

            }

            if (cruzamento[i.end].isStart && count < 2 && !isStk) {

                isStk = false;
                return;
            }
        }
        if (!stk.empty()) {
            aux = stk.pop();
            aux.isStart = true;

            for (Rua r : cruzamento[aux.id].ruaList) {
                if (!r.isPassed) verifyRoute(aux);


            }

        }


    }
    public static void printOutput(){
        while(!output.empty()){
            System.out.println(output.pop());
        }
    }


}
