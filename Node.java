package series.serie2;

public class Node<E> {
	public Node<E> previous;
	public Node<E> next;
	public E value;
	
	public Node(){}
	
	public Node(E e){
		value=e;
	}

	public Node(E e, Node<E> nextNode) {
		value = e;
		next = nextNode;
		previous = nextNode.previous;
		previous.next = this;
		nextNode.previous = this;
	}

	public Node<E> remove() {
		Node<E> aux = new Node<>(this.value);
		if(next!=null){
			next.previous = previous;
		}
		previous.next = next;
		return aux;
	}

	public void add(Node<E> element) {

		Node<E> aux = new Node(element.value);
		this.next = aux;
		aux.previous=this;
	}

	public Node<E> addLast(Node element, Node<E> last) {

		Node<E> aux = new Node(element.value);
		this.next = aux;
		aux.previous=this;
		return aux;
	}
	
}
