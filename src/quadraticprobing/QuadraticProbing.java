package quadraticprobing;

import generic.node.Node;

public class QuadraticProbing {

	int size;
	Node[] hashtable = null;
	int collisions = 0;

	public int getCollisions() {
		return collisions;
	}

	public void setCollisions(int collisions) {
		this.collisions = collisions;
	}

	public QuadraticProbing(int size) {
		this.size = size;
		hashtable = new Node[size];
	}

	public int generateHash(int n) {
		return n % size;
	}

	public void set(int key, int val) {
		Node node = new Node(key, val);
		int hash = generateHash(key);
		if (hashtable[hash] == null) {
			hashtable[hash] = node;
		} else {
			collisions++;
			int i = 0;
			while (hashtable[hash] != null) {
				i++;
				hash = Math.abs((hash + (i * i)) % size);
			}
			hashtable[hash] = node;
		}
	}

	public int search(int key) {
		int val = 0;
		int hash = generateHash(key);
		if (hashtable[hash] != null) {
			int i = 0;
			while (hashtable[hash] != null) {
				if (hashtable[hash].key == key)
					return hashtable[hash].value;
				hash = Math.abs((hash + (i * i)) % size);
			}
		}
		return val;
	}

}