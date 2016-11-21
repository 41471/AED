package series.serie2;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class Iterables<E> {

	public static Iterable<Integer> src;


	public static <T> Iterable<T> flatten(final Iterable<Iterable<T>> src) {

		return () -> new Iterator<T>() {
			boolean end = false;
			boolean consumed = true;
			T element;
			boolean first = true;
			boolean proximo = false;
			int count = 0;
			int currI = 0;
			Iterator<Iterable<T>> it = src.iterator();

			Iterator<T> it1;

			@Override
			public boolean hasNext() {

				if (end)
					return false;
				if (consumed) {
					// Get next element if it exists one

					for (; it.hasNext(); ) {
						if(first){
							it1 = it.next().iterator();
							first=false;
						}
						for (; ;) {
							element = it1.next();
							if(element==null)break;
							proximo = true;
							consumed = false;
							return true;
						}
						first=true;
						it1 = it.next().iterator();
					}
					end = true;
					return false;
				} else
					// Next element exists but it was not consumed yet, so return true
					return true;
			}

			@Override
			public T next() {
				if (!proximo) {
					if (!hasNext())
						throw new NoSuchElementException("alternateEvenoOdd: no more elements");
				}
				// Set this element to the state 'consumed'
				consumed = true;
				// Return element

				return element;
			}
		};
	}

	public static Iterable<Integer> alternateEvenOdd(final Iterable<Integer> src) {

		Iterable<Integer> aux = () -> new Iterator<Integer>() {

			boolean end = false;
			boolean consumed = true;
			int element;
			private int prev;
			boolean first = true;
			boolean proximo = false;
			Iterator<Integer> it = src.iterator();


			@Override
			public boolean hasNext() {
				if (end) {
					proximo = false;
					return false;
				}

				if (consumed) {

					while (it.hasNext()) {
						element = it.next();
						if (isOdd(element) && test(element, prev)) {
							prev = element;
							first = false;
							proximo = true;
							return true;
						} else {
							if (test(element, prev) && !first) {
								consumed = false;
								prev = element;
								proximo = true;
								return true;
							}
						}
					}
					end = true;
					proximo = false;
					return false;
				} else {
					proximo = true;
					return true;
				}
			}

			@Override
			public Integer next() {
				if (!proximo) {
					if (!hasNext())
						throw new NoSuchElementException("alternateEvenoOdd: no more elements");
				}
				// Set this element to the state 'consumed'
				consumed = true;
				// Return element

				return element;

			}

			@Override
			public void remove() {

				throw new UnsupportedOperationException("alternateEvenoOdd: remove not supported");
			}
		};
		return aux;
	}

	private static boolean test(int elem, int previous) {
		return (elem % 2) != (previous % 2);
	}

	private static boolean isOdd(int elem) {
		return (elem % 2) == 1;
	}
}
