import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class login extends User{
    public login(String e, String p) {
        this.email = e;
        this.password = p;

    }

    public boolean Authenticate() {
        try {
            File f = new File("User.txt");
            Scanner fileScanner = new Scanner(f);

            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(", ");
                if (parts.length >= 2) {
                    String storedEmail = parts[0];
                    String storedPassword = parts[1];
                    String storedUniqueUsername = parts[2];
                    this.uniqueusername = storedUniqueUsername;
                    if (storedEmail.equals(this.email) && storedPassword.equals(this.password)) {
                        System.out.println("Login successful. Welcome, " + this.uniqueusername  + "!");
                        fileScanner.close();
                        return true;
                    }
                }
            }

            fileScanner.close();
            System.out.println("Login failed. Invalid email or password.");
        } catch (FileNotFoundException var7) {
            System.out.println("error retrieving the users, please try again later.");
        }
       return false;

    }

}