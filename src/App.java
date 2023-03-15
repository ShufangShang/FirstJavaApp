import java.util.Scanner;
//import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  
import java.text.DecimalFormat;

//import javax.lang.model.util.ElementScanner6;

public class App {
    static float totalBill;
    //static float 1totalBil; //syntax error on token "1"
    //static float *tipRate; //Syntax error on token "*"
    //static float #tipRate; //Syntax error on token "Invalid Character"
    static float tipRate;
    static float taxRate = 0.0825f;
    static String name;
    public static void main(String[] args) throws Exception {
        try (
        Scanner input = new Scanner(System.in)) {
            //prompt for the name of the guest
            
            //Tried to print name without initialize it first
            //System.out.println(name); //name has a value of null
            
            System.out.println("\nWhat is your name?");
            name = input.nextLine();
            
            //prompt for the total number of the guests
            System.out.println("How many guests in your group?");
            int guests = Integer.parseInt(input.nextLine());

            System.out.println("Enter the billed amount per guest:");
            float billPerGuest = Float.parseFloat(input.nextLine());
            

            DisplayMenu();

            int tipOption = input.nextInt();

            try 
            {
                //check the tip option
                while((tipOption < 1) || (tipOption > 5))
                {
                    DisplayMenu();
                    tipOption = input.nextInt();    
                }

            }
            catch(NumberFormatException e) 
            {
                System.out.println("Please enter an whole number from 1 to 5:"); 
                while((tipOption < 1) || (tipOption > 5))
                {
                    DisplayMenu();
                    tipOption = input.nextInt();                    
                }
            } 
            
            System.out.printf("\nThe tip option you selected is: %d. Are you sure? \nSelect 1 for Yes and 0 for No\n", tipOption);

            try
            {
                int confirm = input.nextInt();
                if (confirm != 1)
                {
                    DisplayMenu();
                    tipOption = input.nextInt(); 

                    while((tipOption < 1) || (tipOption > 5))
                    {
                        DisplayMenu();
                        tipOption = input.nextInt();                   
                    }

                }
            }
            catch(Exception e)
            {
                System.out.println("Your input is invalid.");

                System.out.printf("\nThe tip option you selected is: %d. Are you sure? \nSelect 1 for Yes and 0 for No", tipOption);
                int confirm = input.nextInt(); 

                if(confirm != 1)
                {
                    DisplayMenu();
                    tipOption = input.nextInt(); 

                    while((tipOption < 1) || (tipOption > 5))
                    {
                        DisplayMenu();
                        tipOption = input.nextInt();                   
                    } 
                }               
            }    

            //Get the tip option from the guest
            if(tipOption > 4)
            {
                tipRate = 0.20f;
            }
            else if (tipOption == 4)
            {
                tipRate = 0.15f;
            }
            else if(tipOption == 3)
            {
                tipRate = 0.10f;
            }

            else if(tipOption == 2)
            {
                tipRate = 0.05f;
            }
            else
            {
                tipRate = 0.00f;
            } 
            
            
            /* System.out.printf("The tip option that you confirmed is %.2f", tipRate);
            System.out.print('%'+ '.' + '\n'); */

            DecimalFormat decFormat = new DecimalFormat("#%");
            System.out.printf("\nThe tip option that you confirmed is %s.", decFormat.format(tipRate));

            showFinalBill(billPerGuest, guests);
        
        }
    }

    static void DisplayMenu()
    {
        System.out.println("\nSelect an option for tips:\n");

        System.out.println("5: 20%");
        System.out.println("4: 15%");
        System.out.println("3: 10%");
        System.out.println("2: 5%");
        System.out.println("1: 0%\n");
    }

    static void showFinalBill(float amountPerGuest, int numOfGuests)
    {
        float total =  (amountPerGuest * numOfGuests);
        float tax = total*taxRate;
        float tips = total*tipRate;

        //System.out.printf("Your total bill is :%.2f\n", totalBill); //totalBill was automatically initialized to 0
        totalBill = total+tax+tips;

        System.out.println("\n\n\nHere is your final bill for today's service:");
        System.out.println("\n---------------------\n");
        DidplayCurrentDateTime();
        System.out.printf("Guest name: " + "%s\n", name);
        System.out.printf("Total amount before tips: $%.2f\n", total);
        System.out.printf("Tips: $%.2f\n", tips);
        System.out.printf("Tax: $%.2f\n", tax);
        System.out.printf("Total bills: $%.2f\n\n", totalBill);
    }

    static void  DidplayCurrentDateTime()
    {  
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/YYYY HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.printf("Service date and time: %s\n", dtf.format(now));
    }  
}


