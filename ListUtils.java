package series.serie2;

import java.util.Comparator;

public class ListUtils<E> {

	public static void main(String[] args) {
		Node<Integer> sentinela = new Node<Integer>(2);
		Node<Integer> a = new Node<Integer>(5);
		Node<Integer> b = new Node<Integer>(10);
		Node<Integer> c = new Node<Integer>(3);
		Node<Integer> d = new Node<Integer>(8);
		Node<Integer> e = new Node<Integer>(1);
		sentinela.next = a;
		a.previous = sentinela;
		a.next = b;
		b.previous = a;
		b.next = c;
		c.previous = b;
		c.next = d;
		d.previous = c;
		d.next = e;
		e.previous = d;
		Comparator<Integer> cmp = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		};
		Node<Integer> aux = a;
		for (int i = 0; i < 5; i++) {
			System.out.print(aux.value + " ");
			aux = aux.next;
		}
		System.out.println();

		quicksort(a, e, cmp);

		aux = a;
		for (int i = 0; i < 5; i++) {
			System.out.print(aux.value + " ");
			aux = aux.next;
		}

	}

	public static <E> void quicksort(Node<E> first, Node<E> last, Comparator<E> cmp) {
		//if(last==null || first == null || first.next==null || last.previous==null) throw new UnsupportedOperationException();

		if (first == null || last == null || first.equals(last.next) || first.equals(last))
			return;
		Node idx = partition(first, last, cmp);

		if (!idx.equals(first))
			quicksort(first, idx.previous, cmp);
		if (!idx.equals(last))
			quicksort(idx.next, last, cmp);
	}

	public static <E> Node<E> partition(Node<E> first, Node<E> last, Comparator cmp) {
		Node<E> j = last;
		Node<E> i = first.previous;
		E value = last.value;

		for (; ; ) {
			do {
				if (i == null)
					i=first;
				else if (i.next != null)
					i = i.next;
			}
			while ( cmp.compare(i.value, value)< 0);

			do {
				if (j.previous != null)
					j = j.previous;
				if (j.equals(first))
					break;
			} while (cmp.compare(value, j.value) < 0);

				if (i.equals(j) || i.previous != null && i.previous.equals(j)) break;

			exch(i, j);
		}

		exch(i, last);
		return i;
	}

	private static <E> void exch(Node<E> a, Node<E> b) {
		Node<E> aux = new Node<>();
		aux.value = a.value;
		a.value = b.value;
		b.value = aux.value;
	}


	public static Node<Node<String>> splitBySentence(Node<String> list) {
		throw new UnsupportedOperationException();
	}
}
