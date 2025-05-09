import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SignUp extends verifyUser{
    verifyUser verify = new verifyUser();
    String name;
    String userName;
    String Password;
    String email;
    Scanner scanner = new Scanner(System.in);

    public void getDetails(){
        System.out.println("Insert your name: ");
        name = scanner.nextLine();
        System.out.println("Insert your userName: ");
        userName = scanner.nextLine();
        System.out.println("Insert your password: ");
        Password = scanner.nextLine();
        System.out.println("Insert your email: ");
        email = scanner.nextLine();
    }
    public void saveToFile(){
        File file = new File("User.txt");
        if (file.exists() && file.canWrite()){
            try (FileWriter writer = new FileWriter(file,true)) {
                if ((verify.verifyUsername(userName))
                        && (verify.verifyEmail(email))
                        && (verify.verifyName(name))
                        && (verify.verifyPassword(Password))
                        && (verify.uniqueUserName(userName))
                ){
                    writer.write("\n");
                    writer.write(email + ", ");
                    writer.write(Password + ", ");
                    writer.write(userName + ", ");
                    writer.write( name+ "\n");
                    System.out.println("-------------------------------------------------------------------\n");
                    System.out.println("SignedUP successfully");
                    System.out.println("Now you have to login with your new account lets try new adventure !");
                    System.out.println();
                }else {
                    System.out.println("Insert a valid data...");
                    System.out.println();
                }
            } catch (IOException e) {
                System.out.println("Failed to save data...");
            }
        }else {
            System.out.println("File doesn't exist");
        }
    }
}
