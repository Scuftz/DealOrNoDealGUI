package DealOrNoDealGUI;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;

public class Model extends Observable
{
    UpdateInfo update;
    protected int caseCounter;
    protected Scanner input;
    protected String file;
    protected Random rand = new Random();
    protected Database databaseConnection;
    protected String username, password;
    
    public Model()
    {
        file = "caseValues.txt";
        caseCounter = 0;
        update = new UpdateInfo();
        databaseConnection = new Database();
        this.setUpCases();
    }
    
    public void checkLogin(String un, String pw)
    {
        boolean loginStatus = databaseConnection.checkLogin(un, pw);
        update.loginFlag = loginStatus;
        setChanged();
        notifyObservers(update);
    }
    
    
    //will change the value of case opened to its case value
    public void openCase()
    {
        
    }
    
    public void setUpCases()
    {
        try
        {
            input = new Scanner(new FileReader(file));
            while (caseCounter < update.totalAmountOfCases)
            {
                try
                {
                    update.moneyValuesForCases.add(input.nextInt());
                    caseCounter++;
                }
                catch (InputMismatchException e)  //Non-integer value in file
                {
                    System.err.println("Non-integer value in caseValues file, unable to assign value to array. This value has been ignored.");
                    input.nextLine();
                }
                catch (NoSuchElementException e) //There are not enough values to meet the total amount of cases (missing values)
                {
                    System.err.println("There is a value missing from the caseValues file. A default $1000 will replace the missing value.");
                    update.moneyValuesForCases.add(1000);
                    caseCounter++;
                }
            }
            input.close();
            
            update.duplicateCaseValues = (ArrayList<Integer>)update.moneyValuesForCases.clone();
            Collections.sort(update.duplicateCaseValues);
            
            for (int caseNumbers = 1; caseNumbers <= update.totalAmountOfCases; caseNumbers++)
            {
                int x = rand.nextInt(update.moneyValuesForCases.size());
                update.caseList.add(new Case(caseNumbers, update.moneyValuesForCases.get(x)));
                update.moneyValuesForCases.remove(x);           
            }
        }
        catch (FileNotFoundException e) //Case value file not found, random values will be used
        {
            System.err.println("File for the case values was not found (caseValues.txt). The proper game values are unable to be used.");
            System.err.println("Case values have been defaulted to $1000 multiplied by a random integer between 1-100 as opposed to proper game values.");
            System.err.println("For the proper values to be used, the case values files must be in the right file location with the right name.");
            for (int x = 1; x <= update.totalAmountOfCases; x++)
            {
                update.caseList.add(new Case(x, (1000 * (rand.nextInt(100) + 1))));
            }
        }
    }
}