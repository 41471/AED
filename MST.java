package series.serie3;

import java.util.LinkedList;

/**
 * Created by Pedro on 06/01/2017.
 */
public class MST {

    public static LinkedList<Vertex> connectMST = new LinkedList<>();
    public static int DIM;

    public static void main(String[] args) {

        System.out.println(isEdgeInAnMST(init(), 0, 6));

    }

    public static Vertex[] init() {
        LinkedList<Edge> e0 = new LinkedList<>();
        LinkedList<Edge> e1 = new LinkedList<>();
        LinkedList<Edge> e2 = new LinkedList<>();
        LinkedList<Edge> e3 = new LinkedList<>();
        LinkedList<Edge> e4 = new LinkedList<>();
        LinkedList<Edge> e5 = new LinkedList<>();
        LinkedList<Edge> e6 = new LinkedList<>();

        Vertex v0 = new Vertex(0, e0);
        Vertex v1 = new Vertex(1, e1);
        Vertex v2 = new Vertex(2, e2);
        Vertex v3 = new Vertex(3, e3);
        Vertex v4 = new Vertex(4, e4);
        Vertex v5 = new Vertex(5, e5);
        Vertex v6 = new Vertex(6, e6);


        e0.add(new Edge(v1, 2, null));
        e0.add(new Edge(v3, 10, null));
        e0.add(new Edge(v2, 3, null));

        e1.add(new Edge(v0, 2, null));
        e1.add(new Edge(v3, 5, null));
        e1.add(new Edge(v5, 4, null));

        e2.add(new Edge(v0, 3, null));
        e2.add(new Edge(v3, 3, null));

        e3.add(new Edge(v2, 3, null));
        e3.add(new Edge(v1, 5, null));
        e3.add(new Edge(v4, 6, null));
        e3.add(new Edge(v0, 10, null));

        e4.add(new Edge(v3, 6, null));
        e4.add(new Edge(v5, 8, null));

        e5.add(new Edge(v1, 4, null));
        e5.add(new Edge(v4, 8, null));
        e5.add(new Edge(v6, 2, null));

        e6.add(new Edge(v5, 2, null));
        Vertex[] graf = {v0, v1, v2, v3, v4, v5, v6};
        return graf;


    }

    static class Vertex {
        int id;
        LinkedList<Edge> edgeList = new LinkedList<>();
        double cost = Integer.MAX_VALUE; //EXTRA
        boolean isVisited = false; //EXTRA
        int parent; //EXTRA


        public Vertex(int id, LinkedList<Edge> edge) {
            this.id = id;
            edgeList = edge;
        }


    }

    static class Edge {

        Vertex adjacent;
        double weight;
        Edge next;

        public Edge(Vertex a, double w, Edge n) {
            adjacent = a;
            weight = w;
            next = n;
        }

    }


    public static int isEdgeInAnMST(Vertex[] graph, int origId, int destId) {
        DIM = graph.length-1;
        Vertex root = graph[0];
        root.cost = 0;
        root.isVisited = true;
        connectMST.add(root);
        setMST(root);
        if(verify(origId,destId))
            return 1;
        return 0;
    }

    public static boolean verify(int origin,int end){
        for (Vertex v: connectMST) {
            if(v.id==end&&v.parent==origin  || v.id==origin&&v.parent==end)
                return true;
        }
        return false;
    }

    public static void setMST(Vertex root) {
        double min = Integer.MAX_VALUE;
        Edge minorE = root.edgeList.get(0);
        LinkedList<Edge> auxlist = new LinkedList<>();
        Vertex aux = root;
        for (Edge i : root.edgeList) {
            auxlist.add(i);
        }
        while (DIM > 0) {
            for (Edge i : auxlist) {
                if (!i.adjacent.isVisited && i.adjacent.cost > i.weight) {
                    i.adjacent.cost = i.weight;

                }
                if (min > i.weight && !i.adjacent.isVisited) {
                    min = i.weight;
                    minorE = i;
                }
            }


            minorE.adjacent.isVisited = true;
            setParent(minorE.adjacent);
            connectMST.add(minorE.adjacent);
            auxlist.remove(minorE);
            for (Edge i : minorE.adjacent.edgeList) {
                if (!i.adjacent.isVisited) {
                    auxlist.add(i);
                    i.adjacent.cost = i.weight;

                }
            }
            min = Integer.MAX_VALUE;
            DIM--;
        }

    }

    public static void setParent(Vertex v){
        double min=Integer.MAX_VALUE ;
        int count = 0;
        for(Edge i: v.edgeList){
            if(min>i.weight)
                min=i.weight;
        }
        for (Edge p: v.edgeList) {
            if(p.adjacent.isVisited)count++;
        }
        for (Edge p: v.edgeList) {
            if(p.adjacent.isVisited&&p.weight==min || p.adjacent.isVisited&&count==1)
                v.parent=p.adjacent.id;
        }

    }
}
