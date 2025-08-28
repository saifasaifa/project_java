import java.util.Scanner;

public class HashingDemo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // กำหนดขนาดของตาราง
        System.out.print("Enter size of hash table: ");
        int size = sc.nextInt();
        int[] table = new int[size];

        // กำหนดค่าเริ่มต้น -1 = ว่าง
        for (int i = 0; i < size; i++) {
            table[i] = -1;
        }

        // จำนวนตัวเลขที่จะใส่
        System.out.print("Enter number of elements to insert: ");
        int n = sc.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            numbers[i] = sc.nextInt();
        }

        // เลือกวิธีการ hashing
        System.out.println("Choose probing method:");
        System.out.println("A. Linear Probing");
        System.out.println("B. Quadratic Probing");
        System.out.print("Your choice (A/B): ");
        char choice = sc.next().toUpperCase().charAt(0);

        if (choice == 'A') {
            for (int num : numbers) {
                insertLinear(table, num);
            }
            System.out.println("Result with Linear Probing:");
        } else if (choice == 'B') {
            for (int num : numbers) {
                insertQuadratic(table, num);
            }
            System.out.println("Result with Quadratic Probing:");
        } else {
            System.out.println("Invalid choice!");
            return;
        }

        // แสดงผลตาราง
        printTable(table);
    }

    // Linear Probing
    public static void insertLinear(int[] table, int key) {
        int size = table.length;
        int hash = key % size;
        int index = hash;

        while (table[index] != -1) {
            index = (index + 1) % size; // เลื่อนทีละ 1
        }
        table[index] = key;
    }

    // Quadratic Probing
    public static void insertQuadratic(int[] table, int key) {
        int size = table.length;
        int hash = key % size;
        int index = hash;
        int i = 1;

        while (table[index] != -1) {
            index = (hash + i * i) % size; // เลื่อนแบบกำลังสอง
            i++;
            if (i == size) {
                System.out.println("Table full, cannot insert " + key);
                return;
            }
        }
        table[index] = key;
    }

    // แสดงผลตาราง
    public static void printTable(int[] table) {
        System.out.println("Hash Table:");
        for (int i = 0; i < table.length; i++) {
            System.out.println("Index " + i + ": " + (table[i] == -1 ? "empty" : table[i]));
        }
    }
}
