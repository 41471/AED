package series.serie2;

import java.util.Comparator;

public class ListUtils<E> {

	public static void main(String[] args) {
		Node sentinela = new Node(2);
		Node a = new Node(5);
		Node b = new Node(10);
		Node c = new Node (3);
		Node d = new Node(8);
		Node e = new Node(1);
		sentinela.next=a;
		a.previous=sentinela;
		a.next=b;
		b.previous=a;
		b.next=c;
		c.previous=b;
		c.next=d;
		d.previous=c;
		d.next=e;
		e.previous=d;
		Comparator<Integer> cmp = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		};
		Node aux = a;
		for (int i = 0; i < 5; i++) {
			System.out.print(aux.value+" ");
			aux=aux.next;
		}
		System.out.println();

		quicksort(a, e, cmp );

		aux = a;
		for (int i = 0; i < 5; i++) {
			System.out.print(aux.value+ " ");
			aux=aux.next;
		}

	}
	
	public static <E> void quicksort(Node<E> first, Node<E> last, Comparator<E> cmp){
		//if(last==null || first == null || first.next==null || last.previous==null) throw new UnsupportedOperationException();
		if(first==null || last==null || first.equals(last.next) || first.equals(last)) return;
		Node<E> idx = partition(first,last,cmp);
		if(!idx.equals(first))
			quicksort(first,idx.previous,cmp);
		if(!idx.equals(last))
			quicksort(idx.next,last,cmp);
	}

	public static Node partition(Node first, Node last, Comparator cmp){
		Node j = last;
		Node i = first.previous;
		int value =  (int)last.value;
		for (;;){

			do {
				if(i.next!=null)i = i.next;
			}
			while(cmp.compare(i.value,value)<0);

			do {
				if(j.previous!=null)j = j.previous;
				if(j.equals(first))break;
			}while(cmp.compare(value,j.value)<0);

			if(i.equals(j) || i.previous.equals(j))break;

			exch(i,j);

		}

		exch(i,last);
		return i;

	}

	private static <E> void exch(Node<E> a , Node<E> b) {
		Node<E> aux = new Node<>();
		aux.value=a.value;
		a.value=b.value;
		b.value=aux.value;
	}


	public static Node<Node<String>> splitBySentence(Node<String> list) {
		throw new UnsupportedOperationException();
	}
	


}
