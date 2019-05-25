package generic.node;

public class Node {
	public int key;
	public int value;
	public Node next;

	public Node(int key, int val) {
		this.key = key;
		this.value = val;
		this.next = null;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getVal() {
		return value;
	}

	public void setVal(int val) {
		this.value = val;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

}
