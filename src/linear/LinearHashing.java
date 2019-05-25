package linear;

import generic.node.Node;

public class LinearHashing {

	Node[] hashtable = null;
	int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	int collisions = 0;

	public int getCollisions() {
		return collisions;
	}

	public void setCollisions(int collisions) {
		this.collisions = collisions;
	}

	public LinearHashing(int size) {
		this.size = size;
		hashtable = new Node[size];
	}

	public int generateHash(int n) {
		return n % size;
	}

	public void set(int key, int val) {
		Node node = new Node(key, val);
		int ctr = 0;
		int hash = generateHash(key);
		if (hashtable[hash] == null) {
			hashtable[hash] = node;
		} else {
			collisions++;
			while (hashtable[hash] != null) {
				if (ctr == size) {
					// resize Hashtable
					//System.out.println("Hash table full");
					return;
				}
				hash = (hash + 1) % size;
				ctr++;
			}
			hashtable[hash] = node;
		}
	}

	public int search(int key) {
		int value = 0;
		int ctr = 0;
		int hash = generateHash(key);
		if (hashtable[hash] != null) {
			while (hashtable[hash] != null) {
				if (hashtable[hash].key == key)
					return hashtable[hash].value;
				if(ctr==size) {
					System.out.println("Element not found");
					return -1;
				}
				hash = (hash + 1) % size;
				++ctr;
			}
		}
		return value;
	}

	public void delete(int key) {
		int id = generateHash(key);
		if (hashtable[id] != null) {
			while (hashtable[id].key != key) {
				id = (id + 1) % size;
			}
			int j = (id + 1) % size;
			while (hashtable[j] != null) {
				if (generateHash(hashtable[j].key) < j) {
					hashtable[id] = hashtable[j];
					id = j;
				}
				j = (j + 1) % size;
			}
			hashtable[id] = null;
		}
	}
}