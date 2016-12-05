package series.serie2.Agency.Commands;


import series.serie2.Agency.Agency;
import series.serie2.Agency.PriorityQueue;

public class WaitingTime implements Command {
    @Override
    public String getCommand() {
        return "waitingTime";
    }

    @Override
    public String help() {
        return "Obter a informação do tempo estimado de espera para um dado cliente, indicando o seu identificador de senha";
    }

    @Override
    public void execute(String[] args) {
        //args[1] = key, args[2] = number of employees
        int numEmplyees = Integer.parseInt(args[2]);
        int key = Integer.parseInt(args[1]);

        if (numEmplyees > Agency.EMPLOYEES)
            numEmplyees = Agency.EMPLOYEES;
        Agency.aEmplyees = new int[numEmplyees];


        Agency.priorityQueue.heapSort();
        for (int i = 1; /*Agency.priorityQueue.aux[i].key!=key*/i<Agency.priorityQueue.aux.length; ++i) {
            if(Agency.priorityQueue.less(Agency.priorityQueue.aux[i],Agency.priorityQueue.aux[Agency.priorityQueue.hashTables.get(key)]))
                Agency.aEmplyees[min()]+=Agency.priorityQueue.prioToInt(Agency.priorityQueue.aux[i].prio);
        }
//        for (int i = 1; i < Agency.priorityQueue.size; i++) {
//            if (Agency.priorityQueue.pq[i] != Agency.priorityQueue.pq[key])
//                if (Agency.priorityQueue.less(Agency.priorityQueue.pq[i], Agency.priorityQueue.pq[Agency.priorityQueue.hashTables.get(key/*Agency.priorityQueue.pq[key].key*/)]))
//                    Agency.aEmplyees[min()]+= Agency.priorityQueue.prioToInt(Agency.priorityQueue.pq[i].prio);
//        }
       System.out.println(Agency.aEmplyees[min()]);
    }




    int min(){
        int min = 0;
        for (int i = 1; i < Agency.aEmplyees.length; i++) {
            if (Agency.aEmplyees[i] < Agency.aEmplyees[min])
                min = i;
        }
        return min;
    }
}
