package series.serie2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Iterables<E> {

	public static <T> Iterable<T> flatten(final Iterable<Iterable<T>> src) {

		return () -> new Iterator<T>() {

			T element;
			Iterator<Iterable<T>> it = src.iterator();
			Iterator<T> subIt = null;

			@Override
			public boolean hasNext() {
				if (it == null)	// se não tiver mais elementos
					return false;

				if (element == null) {	//se foi "consumido" o elemento obtem proximo se tiver
					while (it.hasNext() || subIt!= null && subIt.hasNext()) {
						if (subIt != null && subIt.hasNext()) {	//obter o proximo elemento se o sub iterador ainda tiver elementos
							element = subIt.next();
							return true;
						} else {
							subIt = it.next().iterator();	//caso o sub iterador não tenha elementos obter o prox sub iterador
						}
					}
					return false;	//não existem mais elementos
				} else
					return true;	//tem proximo elemento mas ainda não foi "consumido"
			}

			@Override
			public T next() {
				if (!hasNext())
					throw new NoSuchElementException("Flatten: no more elements");

				T tmp = element;
				element = null;
				return tmp;
			}
		};
	}

	public static Iterable<Integer> alternateEvenOdd(final Iterable<Integer> src) {

		return () -> new Iterator<Integer>() {

			Iterator<Integer> it = src.iterator();
			Integer element;
			private int prev;


			@Override
			public boolean hasNext() {
				if (it == null)
					return false;

				if (element == null) {
					while (it.hasNext()) {
						element = it.next();
						if (src.iterator().next().equals(element) && isOdd(element)) {
							prev = element;
							return true;
						} else if (test(element, prev)) {
							prev = element;
							return true;
						}
					}
					return false;
				} else
					return true;
			}

			@Override
			public Integer next() {
				if (!hasNext())
					throw new NoSuchElementException("alternateEvenoOdd: no more elements");

				Integer tmp = element;
				element = null;
				return tmp;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("alternateEvenoOdd: remove not supported");
			}
		};
	}

	private static boolean test(int elem, int previous) {
		return (elem % 2) != (previous % 2);
	}

	private static boolean isOdd(int elem) {
		return (elem % 2) == 1;
	}
}
