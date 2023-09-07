package Task1;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class TransactionHistory {
    private ArrayList<Transaction> transactionsList = new ArrayList<>();

    private static class Transaction {
        String type;
        float amount;
        float balance;

        Transaction(String type, float amount, float balance) {
            this.type = type;
            this.amount = amount;
            this.balance = balance;
        }

        @Override
        public String toString() {
            return "Transaction Type: " + type + ", Operation Amount: " + amount + ", Balance: " + balance;
        }
    }

    public void addTransaction(String type, float amount, float balance) {
        Transaction transaction = new Transaction(type, amount, balance);
        transactionsList.add(transaction);
    }

    public void printTransactions() {
        for (Transaction transaction : transactionsList) {
            System.out.println(transaction);
        }
    }
}

class Withdraw {
    private static Scanner sc = new Scanner(System.in);

    public static float withdraw(float balance, float withdraw_amount) {
        if (withdraw_amount <= balance) {
            balance = balance - withdraw_amount;
            System.out.println("Balance after Withdrawal: " + balance);
        } else {
            System.out.println("Insufficient Balance: " + balance);
        }
        return balance;
    }
}

class Deposit {
    private static Scanner sc = new Scanner(System.in);

    public static float deposit(float balance, float deposit_amount) {
        balance = balance + deposit_amount;
        System.out.println("Balance after Deposit: " + balance);
        return balance;
    }
}

class Transfer {
    private static Scanner sc = new Scanner(System.in);

    public static float transfer(float balance, float transfer_amount, double account_no) {
        if (transfer_amount <= balance) {
            System.out.println(transfer_amount + " Amount transfer Successful to " + account_no);
            balance = balance - transfer_amount;
            System.out.println("Balance after Transfer: " + balance);
        } else {
            System.out.println("Insufficient Balance: " + balance);
        }
        return balance;
    }
}

public class ATM {
    private static Scanner sc = new Scanner(System.in);
    private static float balance = 10000;

    public static void main(String[] args) {
        String ID, pin;
        String username = "Demo";
        String password = "123";
        int choice;
        TransactionHistory history = new TransactionHistory();

        while (true) {
        	System.out.println("Welcome to ATM");
            System.out.println("Enter ID: ");
            ID = sc.nextLine();
            System.out.println("Enter PIN: ");
            pin = sc.nextLine();

            if (ID.equals(username) && pin.equals(password)) {
                System.out.println("Welcome to ATM Operations");

                boolean continueTransactions = true;
                while (continueTransactions) {
                    System.out.println("\nOperations:");
                    System.out.println("1: Transaction History");
                    System.out.println("2: Withdraw");
                    System.out.println("3: Deposit");
                    System.out.println("4: Transfer");
                    System.out.println("5: Quit");

                    try {
                        System.out.println("Enter Choice: ");
                        choice = sc.nextInt();
                        sc.nextLine();

                        switch (choice) {
                            case 1:
                                history.printTransactions();
                                break;
                            case 2:
                                float withdraw_amount;
                                System.out.println("Enter Withdraw amount: ");
                                withdraw_amount = sc.nextFloat();
                                balance = Withdraw.withdraw(balance, withdraw_amount);
                                history.addTransaction("Withdraw", withdraw_amount, balance);
                                break;
                            case 3:
                                float deposit_amount;
                                System.out.println("Enter Deposit amount: ");
                                deposit_amount = sc.nextFloat();
                                balance = Deposit.deposit(balance, deposit_amount);
                                history.addTransaction("Deposit", deposit_amount, balance);
                                break;
                            case 4:
                                float transfer_amount;
                                double account_no;
                                System.out.println("Enter Transfer amount: ");
                                transfer_amount = sc.nextFloat();
                                System.out.println("Enter Account number to transfer: ");
                                account_no = sc.nextDouble();
                                balance = Transfer.transfer(balance, transfer_amount, account_no);
                                history.addTransaction("Transfer", transfer_amount, balance);
                                break;
                            case 5:
                                System.out.println("Thank you for using the ATM. Goodbye!");
                                continueTransactions = false;
                                break;
                            default:
                                System.out.println("Wrong Choice!");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        sc.nextLine();
                    }
                }
            } else {
                System.out.println("Invalid Credentials");
            }

            System.out.println("Perform another transaction? (yes/no)");
            String anotherTransaction = sc.nextLine().toLowerCase();
            if (!anotherTransaction.equals("yes")) {
                break; 
            }
        }
    }
}
