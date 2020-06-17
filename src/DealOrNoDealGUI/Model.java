package DealOrNoDealGUI;
import SpecialClassPackage.GradientLabel;
import SpecialClassPackage.MoneyValueType;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;

public class Model extends Observable
{
    private static NumberFormat nf = NumberFormat.getNumberInstance();
    UpdateInfo update;
    protected int caseCounter;
    protected Scanner input;
    protected String file;
    protected Random rand = new Random();
    protected Database databaseConnection;
    protected String username, password;
    public boolean caseSelected = false;
    private boolean stopRequest = false;
    
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
    
    public void endGame()
    {
        update.endOfGame = true;
//        sleep(2000);
        setChanged();
        notifyObservers(update);
    }
    
    //will change the value of case opened to its case value
    public void openOrSetCase(Case c)
    {
        if (!update.caseSelected)
        {
            update.caseList.get(c.getCaseNumber()-1).setPlayerCase(true);
        }
        else
        {
            update.caseList.get(c.getCaseNumber() - 1).setOpenStatus(true);
            update.tester.get(c.getCaseValue()).setOpen();
            update.totalCasesLeft--;
            update.casesRemainingThisRound--;
            if(update.casesRemainingThisRound == 0)
            {
                if(update.roundNumber != 9)
                {
                    this.calculateBankOffer(update.roundNumber, update.caseList);
                    this.setUpNewRound();
                    update.endOfRound = true;
                }
                else
                {
                    this.endGame();
                }
            }
        }
        setChanged();
        notifyObservers(update);
    }
    
    public void calculateBankOffer(int roundNumber, ArrayList<Case> cases)
    {
        int sum = 0;
        for(Case c : cases)
        {
            if(!c.getOpenStatus())
            {
                sum += c.getCaseValue();
            }
        }
        //calculate the sum of the unopenned cases, divide by the amount of cases left, multiply by the deductor
        float bankOffer = (sum / update.totalCasesLeft) * update.percentageDeductions[update.roundNumber];
        System.out.println("BANK OFFER...\n" + nf.format((int)bankOffer));
        update.bankOffer = (int)bankOffer;
    }
    
    public void setUpNewRound()
    {
        if(update.totalCasesToOpen > 1)
        {
            update.totalCasesToOpen--;
        }
        update.casesRemainingThisRound = update.totalCasesToOpen;
        update.roundNumber++;
        System.out.println("Round Number" + update.roundNumber);
//        if(update.roundNumber > 9)
//        {
//            update.endOfGame = true;
//        }
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
        this.setUpValues();
    }
    
    public void setUpValues()
    {
        for (int k = 1; k <= 26; k++)
        {                                
                //this is for labels...??
            int num = update.duplicateCaseValues.get(k - 1);
            if (k <= 13)
            {
                update.tester.put(num, new GradientLabel("$" + nf.format(num), MoneyValueType.BLUE));
            }
            else if (k >= 14 && k <= 22)
            {
                update.tester.put(num, new GradientLabel("$" + nf.format(num), MoneyValueType.RED));
            }
            else
            {
                update.tester.put(num, new GradientLabel("$" + nf.format(num), MoneyValueType.GREEN));
            }
        }
    }
}