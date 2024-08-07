public class BankAccountTest {

    // Define the BankAccount class
    static class BankAccount {
        private int balance = 0;

        // Synchronized method for deposit
        public synchronized void deposit(int amount) {
            System.out.println(Thread.currentThread().getName() + " is depositing: " + amount);
            int newBalance = balance + amount;
            try {
                Thread.sleep(50); // Simulate time taken for deposit
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance = newBalance;
            System.out.println(Thread.currentThread().getName() + " new balance after deposit: " + balance);
        }

        // Synchronized method for withdrawal
        public synchronized void withdraw(int amount) {
            if (balance >= amount) {
                System.out.println(Thread.currentThread().getName() + " is withdrawing: " + amount);
                int newBalance = balance - amount;
                try {
                    Thread.sleep(50); // Simulate time taken for withdrawal
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance = newBalance;
                System.out.println(Thread.currentThread().getName() + " new balance after withdrawal: " + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + " insufficient funds for withdrawal: " + amount);
            }
        }

        public int getBalance() {
            return balance;
        }
    }

    // Define the Transaction thread class
    static class Transaction extends Thread {
        private final BankAccount account;
        private final boolean deposit;
        private final int amount;

        public Transaction(BankAccount account, boolean deposit, int amount) {
            this.account = account;
            this.deposit = deposit;
            this.amount = amount;
        }

        @Override
        public void run() {
            if (deposit) {
                account.deposit(amount);
            } else {
                account.withdraw(amount);
            }
        }
    }

    // Main method to execute the program
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        // Create and start multiple transaction threads
        Transaction t1 = new Transaction(account, true, 100); // Deposit 100
        Transaction t2 = new Transaction(account, false, 50); // Withdraw 50
        Transaction t3 = new Transaction(account, true, 200); // Deposit 200
        Transaction t4 = new Transaction(account, false, 150); // Withdraw 150

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Wait for all transactions to complete
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final balance
        System.out.println("Final balance: " + account.getBalance());
    }
}

