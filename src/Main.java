import java.util.List;
import java.util.Scanner;

public class Main {
    static void  execute_1(boolean flag, String username,login a) {
        Scanner scanner = new Scanner(System.in);
        if (flag) {
            Portfolio portfolio = Portfolio.getInstance(a.uniqueusername);
            AssetService assetService = new AssetServiceImpl(portfolio);

            while (true) {
                System.out.println("\nChoose an operation:");
                System.out.println("1. Add Asset");
                System.out.println("2. Edit Asset");
                System.out.println("3. Remove Asset");
                System.out.println("4. View All Assets");
                System.out.println("5. zakat calculator");
                System.out.println("6. returning to main menu");

                int choice = scanner.nextInt();
                scanner.nextLine();

                List<Asset> assets;
                switch (choice) {
                    case 1:
                        System.out.print("Enter Asset Type (1. Stock, 2. RealEstate): ");
                        int type = scanner.nextInt();
                        scanner.nextLine();
                        Asset asset = assetFactory.createAsset(type, scanner);
                        assetService.addAsset(asset);
                        break;

                    case 2:
                        assets = portfolio.getAssets();
                        if (assets.isEmpty()) {
                            System.out.println("No assets to edit.");
                        } else {
                            printAssets(assets);
                            System.out.print("Enter index to edit: ");
                            int editIndex = scanner.nextInt();
                            scanner.nextLine();

                            if (!assetService.isValidIndex(editIndex)) {
                                System.out.println("Invalid index.");
                            } else {
                                int assetType = typeFromAsset(assets.get(editIndex));
                                Asset updatedAsset = assetFactory.createAsset(assetType, scanner);
                                assetService.editAsset(editIndex, updatedAsset);
                            }
                        }
                        break;

                    case 3:
                        assets = portfolio.getAssets();
                        if (assets.isEmpty()) {
                            System.out.println("No assets to remove.");
                        } else {
                            printAssets(assets);
                            System.out.print("Enter index to remove: ");
                            int removeIndex = scanner.nextInt();
                            scanner.nextLine();

                            if (!assetService.isValidIndex(removeIndex)) {
                                System.out.println("Invalid index.");
                            } else {
                                assetService.removeAsset(removeIndex);
                            }
                        }
                        break;

                    case 4:
                        assets = portfolio.getAssets();
                        if (assets.isEmpty()) {
                            System.out.println("No assets in the portfolio.");
                        } else {
                            printAssets(assets);
                        }
                        break;
                        case 5:

                            portfolio.getZakatcalculator().calculateZakat();
                            portfolio.getZakatcalculator().generateReport(Portfolio.getUsername());
                            portfolio.getZakatcalculator().downloadReport(Portfolio.getUsername());

                            break;

                    case 6:
                        System.out.println("returning to main menu");
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } else {
            System.out.println("Authentication failed.");
        }    }

    private static void printAssets(List<Asset> assets) {
        for (int i = 0; i < assets.size(); i++) {
            System.out.println(i + ". " + assets.get(i));
        }
    }

    private static int typeFromAsset(Asset asset) {
        if (asset instanceof Stock) return 1;
//        else if (asset instanceof RealEstate) return 2;
        return -1;
    }

    public static void main(String[] args) {
        boolean flag = true;
        while (flag) {
            System.out.println("Welcome to  Invest Wise!");
            System.out.println("choose one of the following options:");
            System.out.println("1) Login      2) Sign up      3) Quit");
            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Enter your email: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();
                    login a = new login(username, password);
//                    Scanner in2 = new Scanner(System.in);
//                    String choice2 = in2.nextLine();
//                    switch (choice2) {
//                        case "1":
//                    }
                    execute_1(a.Authenticate(), username, a);
                    break;
                case 2:
                    SignUp user = new SignUp();
                    user.getDetails();
                    user.saveToFile();
                    flag = true;
                    break;
                case 3:
                    flag = false;
                    break;



            }

        }

    }
}
