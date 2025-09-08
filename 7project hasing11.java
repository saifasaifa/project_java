import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int size = 0;
		while (true) {
			try {
				System.out.print("Enter size of hash table: ");
				size = sc.nextInt();
				if (size <= 0) {
					System.out.println("Size must be greater than 0. Try again.");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Error: Please enter a number.");
				sc.next(); // clear wrong input
			}
		}

		int[] table = new int[size];
		for (int i = 0; i < size; i++) {
			table[i] = -1;
		}

		int n = 0;
		while (true) {
			try {
				System.out.print("Enter number of elements to insert: ");
				n = sc.nextInt();
				if (n > size || n <= 0) {
					System.out.println("=========================================");
					System.out.println("Error: Number of elements must be between 1 and " + size);
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("=========================================");
				System.out.println("Error: Please enter a number.");
				sc.next();
			}
		}

        System.out.println("=========================================");
            
		int[] numbers = new int[n];
		for (int i = 0; i < n; i++) {
			while (true) {
				try {
                    
					System.out.print("Enter number " + (i + 1) + ": ");
					numbers[i] = sc.nextInt();
					break;
				} catch (InputMismatchException e) {
					System.out.println("=========================================");
					System.out.println("Error: Please enter a number.");
					sc.next();
				}
			}
		}

		char choice;
		while (true) {
			System.out.println("=========================================");
			System.out.println("Choose probing method:");
			System.out.println("A. Linear Probing");
			System.out.println("B. Quadratic Probing");
			System.out.println("C. Both Linear and Quadratic");
			System.out.print("Your choice (A/B/C): ");
			choice = sc.next().toUpperCase().charAt(0);
			if (choice == 'A' || choice == 'B' || choice == 'C') break;
			System.out.println("=========================================");
			System.out.println("Invalid choice! Try again.");
		}

		if (choice == 'A') {
			for (int num : numbers) insertLinear(table, num);
			System.out.println("=========================================");
			System.out.println("- Result with Linear Probing:");
			printTable(table);
		} else if (choice == 'B') {
			for (int num : numbers) insertQuadratic(table, num);
			System.out.println("=========================================");
			System.out.println("- Result with Quadratic Probing:");
			printTable(table);
		} else {
			int[] linearTable = new int[size];
			int[] quadraticTable = new int[size];
			for (int i = 0; i < size; i++) {
				linearTable[i] = -1;
				quadraticTable[i] = -1;
			}
			for (int num : numbers) {
				insertLinear(linearTable, num);
				insertQuadratic(quadraticTable, num);
			}
			System.out.println("=========================================");
			System.out.println("- Result with Linear Probing:");
			printTable(linearTable);
			System.out.println("=========================================");
			System.out.println("- Result with Quadratic Probing:");
			printTable(quadraticTable);
		}
	}

	public static void insertLinear(int[] table, int key) {
		int size = table.length;
		int index = key % size;
		int startIndex = index;
		while (table[index] != -1) {
			index = (index + 1) % size;
			if (index == startIndex) {
				System.out.println("Linear Table full, cannot insert " + key);
				return;
			}
		}
		table[index] = key;
	}

	public static void insertQuadratic(int[] table, int key) {
		int size = table.length;
		int hash = key % size;
		int index = hash;
		int i = 1;
		while (table[index] != -1) {
			index = (hash + i * i) % size;
			i++;
			if (i == size) {
				System.out.println("Quadratic Table full, cannot insert " + key);
				return;
			}
		}
		table[index] = key;
	}

	public static void printTable(int[] table) {
	    
		System.out.println("+ Hash Table +");
		for (int i = 0; i < table.length; i++) {
			System.out.println("Index " + i + ": " + (table[i] == -1 ? "empty" : table[i]));
		}
	}
}
