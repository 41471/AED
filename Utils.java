package series.serie2;

public class Utils {
	
	public static <E> Node<E>[] shrink(Node<E>[] hashTable){

		Node<E>[] auxTable = new Node[hashTable.length/2];

		for (int i = hashTable.length/2; i < hashTable.length; i++) {
			append(auxTable[i-hashTable.length/2]=hashTable[i-hashTable.length/2],hashTable[i]);
		}

		return auxTable;

	}

	public static <E> void append(Node<E> sdst, Node<E> ssrc){
		if(!(ssrc.next==ssrc.previous && ssrc.next==ssrc)) {
			sdst.previous.next = ssrc.next;
			ssrc.next.previous = sdst.previous;
			ssrc.previous.next = sdst;
			sdst.previous = ssrc.previous;
		}
	}

	

}
