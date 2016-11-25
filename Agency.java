package series.serie2.Agency;

import series.serie2.Agency.Commands.*;
import series.serie2.Agency.Commands.Exit;
import series.serie2.Agency.Commands.Help;

import java.util.Scanner;


public class Agency {
    public static Command[] cmdList = {
            new NewCostumer(),
            new RemoveCostumer(),
            new RemoveNextCostumer(),
            new GetNextCostumer(),
            new ChangeService(),
            new WaitingTime(),
            new Help(),
            new Exit()};
    public static PriorityQueue priorityQueue = new PriorityQueue();
    public static void init(){
        priorityQueue.pq = new PriorityQueue.StructEP[2];
    }
    public static void resize() {

        PriorityQueue.StructEP[] aux = new PriorityQueue.StructEP[priorityQueue.pq.length*2];
        for (int i = 1; i < priorityQueue.pq.length; i++) {
            if(priorityQueue.pq[i]!=null) {
                aux[i]= priorityQueue.pq[i];
            }
        }
        priorityQueue.pq=aux;
    }
    
    public static void main(String[] args) {
        init();
        String cmd = "";
        String [] cmdArgs = new String[cmdList.length];
        new Help().execute(cmdArgs);
        while(true){
            boolean validCmd = false;
            Scanner in = new Scanner(System.in);
            cmd = in.nextLine();
            cmdArgs = cmd.split(" ");
            for (int i = 0; i < cmdList.length; i++) {
                if(cmdArgs[0].equals(cmdList[i].getCommand())){
                    cmdList[i].execute(cmdArgs);
                    validCmd = true;
                }
            }
            if(!validCmd){
                new Help().execute(cmdArgs);
            }

        }
    }
}
