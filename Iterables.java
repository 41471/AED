package series.serie2;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class Iterables<E>{

	public static boolean end;
	public static boolean consumed;
	public static int element;


	public static <T> Iterable<T> flatten(final Iterable< Iterable<T> > src) {
		throw new UnsupportedOperationException();
	}

	public static  Iterable<Integer> alternateEvenOdd(final Iterable<Integer> src){
		Iterable<Integer> aux = () -> new Iterator<Integer>() {
			private int prev;
			boolean first=true;

			@Override
			public boolean hasNext() {
				if(!src.iterator().hasNext())return false;
				if (end)
					return false;
				if (consumed) {
					// Get next element if it exists one
					for (Integer i: src) {
						// Get next element
						element = i;

						if(first){
							if(isOdd(element)){
								prev=element;
								first=false;
								return true;
							}
						}

						else{
							if (test(element, prev)) {
								consumed = false;
								prev=element;
								return true;
							}
						}

					}
					end = true;
					return false;
				}
				else
					// Next element exists but it was not consumed yet, so return true
					return true;
			}

			@Override
			public Integer next() {
				if (!hasNext())
					throw new NoSuchElementException();
				// Set this element to the state 'consumed'
				consumed = true;
				// Return element
				return element;
			}

			@Override
			public void remove() {

			}
		};
		return aux;
	}

	private static boolean test(int elem,int previous){
		return (elem%2)!=(previous%2);
	}

	private static boolean isOdd(int elem){
		return (elem%2)==1;
	}
}
