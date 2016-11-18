package series.serie2;

import java.util.Comparator;

public class ListUtils<E> {
	public static void main(String[] args) {




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

	public static <E> Node<E> partition(Node<E> first, Node<E> last, Comparator cmp){
		Node j = last;
		Node i = first.previous;
		E value =  last.value;
		for (;;){

			do {
				if (i==null)
					i=first;
				else if(i.next!=null)i = i.next;
			}
			while(cmp.compare(i.value,value)<0);

			do {
				if(j.previous!=null)j = j.previous;
				if(j.equals(first))break;
			}while(cmp.compare(value,j.value)<0);

			if(i.equals(j) || i.previous!=null && i.previous.equals(j))break;

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
		Node<Node<String>> frases = new Node<>();
		Node headList = list;
		Node<Node<String>> last=new Node<>();
		Node<Node<String>> currW=last;
		Node<String> currW2 =null;
		frases.previous = frases.next = frases;
		boolean first=true;
		if(list.next==list.previous&&list.next==list ) {
			return frases;
		}


		for (;list.next!=headList;){
			if(list.next.value!="."){
				if(first){

					last=new Node(list.next.remove(),frases);
					currW2 = last.value;
					//currW2=last.value;
					currW=last;
					first=false;

				}
				else {
					last.value.add(list.next.remove());
					last.value=last.value.next;
				}
			}
			else {
					last.value=currW2;
					list = list.next;
					last=currW;
					//last.next=new Node<>(new Node(),frases);
					last=last.next;
					first=true;
				}
			}
		last.value=currW2;
		return frases;
	}

	public static <E> Node<E> copyNode(Node<E> b){
		Node<E> aux = new Node<>();
		aux.next=b.next;
		aux.previous=b.previous;
		aux.value=b.value;
		return aux;
	}


}