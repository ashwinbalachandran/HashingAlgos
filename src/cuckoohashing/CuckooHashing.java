package cuckoohashing;

import generic.node.Node;

public class CuckooHashing {

	Node[] hashtableTwo = null;
	Node[] hashtableOne = null;
	int keyCount = 0;
	int size;
	int collisions;

	public int getCollisions() {
		return collisions;
	}

	public void setCollisions(int collisions) {
		this.collisions = collisions;
	}

	public CuckooHashing(int size) {
		collisions = 0;
		this.size = size;
		hashtableOne = new Node[size];
		hashtableTwo = new Node[size];
	}

	public int generateHash(int n) {
		return n % size;
	}

	public int generateHashTwo(int n) {
		return (n / (size)) % size;
	}

	public void set(int key, int val) {
		int h1 = generateHash(key);
		if (hashtableOne[h1] == null) {
			hashtableOne[h1] = new Node(key, val);
			return;
		} else {
			collisions++;
			Node tmp = hashtableOne[h1];
			hashtableOne[h1] = new Node(key, val);
			keyCount = 0;
			cycleCount(tmp.key, tmp, key, 1);
		}
	}

	private void cycleCount(int key, Node node, int key2, int x) {
		if (key == key2) {
			keyCount++;
			if (keyCount >= 2) {
				// System.out.println("Looping while inserting "+key);
				// Need to restructure the hash table here
				return;
			}
		}
		if (x == 0) {
			int h1 = generateHash(key);
			if (hashtableOne[h1] == null) {
				hashtableOne[h1] = node;
				return;
			} else {
				Node tmp = hashtableOne[h1];
				hashtableOne[h1] = node;
				cycleCount(tmp.key, tmp, key2, 1);
			}
		} else {
			int h2 = generateHashTwo(key);
			if (hashtableTwo[h2] == null) {
				hashtableTwo[h2] = node;
				return;
			} else {
				Node tmp = hashtableTwo[h2];
				hashtableTwo[h2] = node;
				cycleCount(tmp.key, tmp, key2, 0);
			}
		}
	}

	public int search(int key) {
		int h1 = generateHash(key);
		int h2 = generateHashTwo(key);
		if (hashtableOne[h1].key == key)
			return hashtableOne[h1].value;
		if (hashtableTwo[h2].key == key)
			return hashtableTwo[h2].value;
		return 0;
	}

	public void delete(int key) {
		int h1 = generateHash(key);
		int h2 = generateHashTwo(key);
		if (hashtableOne[h1].key == key)
			hashtableOne[h1] = null;
		else if (hashtableTwo[h2].key == key)
			hashtableTwo[h2] = null;
	}

	public void print() {
		for (int i = 0; i < size; i++) {
			System.out.print(i + " ");
			if (hashtableOne[i] == null) {
				System.out.print("NULL ");
			} else {
				System.out.print(hashtableOne[i].key + " ");
			}

			if (hashtableTwo[i] == null) {
				System.out.print("NULL");
			} else {
				System.out.print(hashtableTwo[i].key);
			}
			System.out.println();
		}
	}
}
