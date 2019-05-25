package hashchaining;

import generic.node.*;

public class ChainHashing {

	int size;
	Node[] hashtable = null;
	double loadFactor;
	int collisions;

	public int getCollisions() {
		return collisions;
	}

	public void setCollisions(int collisions) {
		this.collisions = collisions;
	}

	public ChainHashing(int size) {
		this.size = size;
		collisions = 0;
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
			Node curr = hashtable[hash];
			while (curr.next != null) {
				if (curr.key == key) {
					curr.value = val;
					return;
				}
				curr = curr.next;
			}
			curr.next = node;
		}
	}

	public int search(int key) {
		int val = 0;
		int hash = generateHash(key);
		if (hashtable[hash] != null) {
			Node curr = hashtable[hash];
			while (curr != null) {
				if (curr.key == key) {
					return curr.value;
				}
				curr = curr.next;
			}
		}
		return val;
	}

	public void printHashtable() {}
}