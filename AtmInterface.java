import java.util.*;

public class AtmInterface {
    private static double balance = 1000;
    private static double recipientBalance = 0;
    //private static int userPin;
    private static List<Transaction> transactions = new ArrayList<>();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("WELCOME!");
        System.out.println("Enter USER Id:");
        String userId = sc.nextLine();

        System.out.println("Enter USER PIN:");
         int userPin = sc.nextInt();

        if (userId != null && userPin != 0) {
            System.out.println("You Entered Details Successfully");

            while (true) {
                System.out.println("\n1. SHOW TRANSACTION HISTORY\n2. WITHDRAW\n3. DEPOSIT\n4. TRANSFER\n5. QUIT");
                System.out.print("Enter your operation (1/2/3/4/5): ");

                int operation = sc.nextInt();

                switch (operation) {
                    case 1:
                        showTransactionHistory();
                        break;
                    case 2:
                     System.out.println("Enter UserPin: ");
                     int pin = sc.nextInt();
                     if (pin == userPin) {
                        withdraw(sc);
                     }else{
                         System.out.println("UserPin did not match, please try again!");
                     }
                        break;
                    case 3:
                        deposit(sc);
                        break;
                    case 4:
                        transfer(sc);
                        break;
                    case 5:
                        System.out.println("THANK YOU!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid operation. Please try again.");
                        break;
                }
            }
        }
        sc.close();
    }

    private static void showTransactionHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions done yet.");
        } else {
            System.out.println("Transaction History:");
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }
    
    private static void withdraw(Scanner sc) {
        System.out.println("Enter Amount to withdraw:");
        double withdrawAmt = sc.nextInt();

        if (withdrawAmt > balance) {
            System.out.println("Cannot perform withdraw operation, you entered more than your balance");
        } else {
                balance = balance - withdrawAmt;
                System.out.println("Your requested amount is debited\nYour current balance = " + balance);
                 // Create a Transaction object and add it to the list
                 Transaction withdrawalTransaction = new Transaction("Withdraw", withdrawAmt);
                 transactions.add(withdrawalTransaction);
            } 
     }

    private static void deposit(Scanner sc) {
        System.out.println("Enter Amount to Deposit:");
        double depositAmt = sc.nextInt();
        balance = balance + depositAmt;
        System.out.println("You successfully deposited: " + depositAmt + " amount");
        System.out.println("Your current balance = " + balance);
        // Add the deposit transaction to the list
        Transaction depositTransaction = new Transaction("Deposit", depositAmt);
        transactions.add(depositTransaction);
    }

    private static void transfer(Scanner sc) {
        System.out.println("Enter recipient User Id:");
        sc.nextLine(); // Consume the newline character
        String recipientId = sc.nextLine();

        if (recipientId != null) {
            System.out.println("Enter Amount to Transfer:");
            double transferAmt = sc.nextInt();

            if (transferAmt > 0 && transferAmt <= balance) {
                balance = balance - transferAmt;
                recipientBalance = recipientBalance + transferAmt;
                System.out.println("Transfer successful. Your current balance = " + balance);
                // Add the transfer transaction to the list
                Transaction transferTransaction = new Transaction(recipientId, transferAmt);
                transactions.add(transferTransaction);
            } else {
                System.out.println("Invalid amount or insufficient funds.");
            }
        } else {
            System.out.println("Recipient not found, please enter correct Recipient userid, Try again");
        }
    }
}

 class Transaction {
        private String type;
        private double amount;

        public Transaction(String type, double amount) {
            this.type = type;
            this.amount = amount;
        }
        
        public String toString() {
            return "Type: " + type + ", Amount: " + amount;
        }
    }

