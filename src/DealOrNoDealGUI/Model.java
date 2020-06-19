package DealOrNoDealGUI;
import SpecialClassPackage.FlashButton;
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

/**
 * PDC Assignment 2
 * This is the Model Class, part of the MVC
 * @author Shivneel Singh (18021394)
 * @since 11/06/2020
 */
public class Model extends Observable
{
    /**
     * Variables
     */
    private static NumberFormat nf = NumberFormat.getNumberInstance();
    protected int caseCounter;
    protected Scanner input;
    protected String file;
    protected Random rand = new Random();
    protected Database playerDB;
    protected String username, password;
    public boolean caseSelected;
    UpdateInfo update;
    
    /**
     * Constructor
     */
    public Model()
    {
        file = "caseValues.txt"; //file that contains case values
        caseCounter = 0;
        caseSelected = false;
        update = new UpdateInfo();
        playerDB = new Database();
        this.setUpCases();
        this.setUpFlashes();
    }
    
    /**
     * This method is called when the user quits the game
     * It will close the database and then from view, close the JFrame window
     */
    public void quitGame()
    {
        playerDB.closeDatabase();
        update.setQuitGameFlag(true);
        setChanged();
        notifyObservers(update);
    }
    
    /**
     * This method checks the login of a player by calling the checkLogin method from the DB class
     * @param un   Username player entered
     * @param pw   Password player entered
     */
    public void checkLogin(String un, String pw)
    {
        boolean loginStatus = playerDB.checkLogin(un, pw);
        update.setLoginFlag(loginStatus);
        update.setPlayerName(un);
        setChanged();
        notifyObservers(update);
    }
    
    /**
     * This method checks a player's high score after they finish a game
     * If they're previous high score is less than the value they just won,
     * It will update the player's high score in the DB
     */
    public void checkHighScore()
    {
        int highscore = playerDB.getPlayerHighScore(update.getPlayerName());        
        if(update.getDealAcceptedFlag())
        {
            if (highscore < update.getBankOffer())
            {
                playerDB.updateScore(update.getPlayerName(), update.getBankOffer());
            }
        }
        else
        {
            if (highscore < update.getPlayerCase().getCaseValue())
            {
                playerDB.updateScore(update.getPlayerName(), update.getPlayerCase().getCaseValue());
            }
        }
        //Setting high score in update class to display at end of game panel
        highscore = playerDB.getPlayerHighScore(update.getPlayerName());
        update.setPlayerHighScore(highscore);
    }
    
    /**
     * This method gets the highest score across all players
     * It calls the getAllTimeHighScore() method from the DB class
     */
    public void getAllTimeScore()
    {
        int topScore = playerDB.getAllTimeHighScore();
        update.setAllTimeScore(topScore);
    }
    
    /**
     * This method signals the end of the game
     * Either there are no more cases to be open or the player accepted a deal
     */
    public void endGame()
    {
        update.setEndOfGameFlag(true);
        this.checkHighScore();
        this.getAllTimeScore();
        setChanged();
        notifyObservers(update);
    }
    
    /**
     * This method can will set the case the player has chosen to be their own
     * It will also open cases the player opens in the game
     * @param c   Case to be opened/set
     */
    public void openOrSetCase(Case c)
    {
        if (!update.getCaseSelectedFlag()) //If the player hasn't chosen their case yet
        {
            update.setPlayerCase(c);
            update.getCaseList().get(c.getCaseNumber()-1).setPlayerCase(true);
        }
        else //Opening cases
        {
            update.getCaseList().get(c.getCaseNumber() - 1).setOpenStatus(true);
            update.getMoneyLabels().get(c.getCaseValue()).setOpen();
            update.setTotalCasesLeft(update.getTotalCasesLeft() - 1);
            update.setCasesRemainingThisRound(update.getCasesRemainingThisRound() - 1);
            if(update.getCasesRemainingThisRound() == 0)
            {
                if(update.getRoundNumber() != update.getMaxRounds())
                {
                    this.calculateBankOffer(update.getRoundNumber(), update.getTotalCasesLeft(), update.getCaseList());
                    this.setUpNewRound();
                    update.setEndOfRoundFlag(true);
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
    
    /**
     * This method sets up the flashing buttons in the game
     */
    public void setUpFlashes()
    {
        int[] xLocations = new int[]{70, 110, 150, 1025, 1065, 1105};
        int yLocation = 15;
        MoneyValueType mvt;
        for (int k = 0; k < 6; k++)
        {
            if(k == 0 || k == 3)
            {
                mvt = SpecialClassPackage.MoneyValueType.RED;
            }
            else if(k == 1 || k == 4)
            {
                mvt = SpecialClassPackage.MoneyValueType.GREEN;
            }
            else
            {
                mvt = SpecialClassPackage.MoneyValueType.BLUE;
            }
            FlashButton flashButton = new FlashButton(xLocations[k], yLocation, mvt);
            update.getFlashBtns().add(flashButton);
        }
    }
    
    /**
     * This method will be called when the buttons are flashing
     * This will change the buttons colour, switching them between the two colours they are assigned
     */
    public void invert()
    {
        for(FlashButton fb : update.getFlashBtns())
        {
            fb.invert();
        }
    }
    
    /**
     * This method will calculate the bank offer
     * It does this by calculating the sum of the unopened cases, divide by the amount of cases left, multiplied by the deductor
     * @param roundNumber   The round the game is on
     * @param totalCasesLeft   The amount of cases that aren't opened
     * @param cases   The list of cases
     * @return   The bank offer (this was added for the testing methods in the Test Package, as the bank offer is stored in the Update class)
     */
    public int calculateBankOffer(int roundNumber, int totalCasesLeft, ArrayList<Case> cases)
    {
        int sum = 0;
        for(Case c : cases)
        {
            if(!c.getOpenStatus())
            {
                sum += c.getCaseValue();
            }
        }
        float bankOffer = (sum / totalCasesLeft) * update.getPercentageDeductions()[roundNumber];
        System.out.println("BANK OFFER...\n" + nf.format((int)bankOffer));
        update.setBankOffer((int)bankOffer);
        return (int)bankOffer;
    }
    
    /**
     * This method will set up a new round in the game once one round has been completed
     */
    public void setUpNewRound()
    {
        if(update.getTotalCasesToOpen() > 1)
        {
            update.setTotalCasesToOpen(update.getTotalCasesToOpen() - 1);
        }
        update.setCasesRemainingThisRound(update.getTotalCasesToOpen());
        update.setRoundNumber(update.getRoundNumber() + 1);
    }
    
    /**
     * This method will set up the cases at the start of the game
     * It will read case values from a file and use those values to create cases
     * If there is an error with the file, appropriate error handling is in place to resolve any issues
     */
    public void setUpCases()
    {
        int multiplier = 1;
        try
        {
            input = new Scanner(new FileReader(file));
            while (caseCounter < update.getTotalAmountOfCases())
            {
                try
                {
                    update.getMoneyValueForCases().add(input.nextInt()); //Read next value
                    caseCounter++;
                }
                catch (InputMismatchException e)  //Non-integer value in file
                {
                    System.err.println("Non-integer value in caseValues file, unable to assign value to array. This value has been ignored.");
                    input.nextLine();
                }
                catch (NoSuchElementException e) //There are not enough values to meet the total amount of cases (missing values)
                {
                    int substituteValue = 360 * multiplier;
                    System.err.println("There is a value missing from the caseValues file. A random default value of $" + substituteValue + " will replace the missing value.");
                    update.getMoneyValueForCases().add(substituteValue);
                    multiplier++;
                    caseCounter++;
                }
            }
            input.close();
            update.setDuplicateCaseValues((ArrayList<Integer>)update.getMoneyValueForCases().clone());
            Collections.sort(update.getDuplicateCaseValues());
            
            for (int caseNumbers = 1; caseNumbers <= update.getTotalAmountOfCases(); caseNumbers++)
            {
                int x = rand.nextInt(update.getMoneyValueForCases().size());
                update.getCaseList().add(new Case(caseNumbers, update.getMoneyValueForCases().get(x)));
                update.getMoneyValueForCases().remove(x);           
            }
        }
        catch (FileNotFoundException e) //Case value file not found, random values will be used
        {
            System.err.println("File for the case values was not found (caseValues.txt). The proper game values are unable to be used.");
            System.err.println("Case values have been defaulted to $1000 multiplied by a random integer between 1-100 as opposed to proper game values.");
            System.err.println("For the proper values to be used, the case values files must be in the right file location with the right name.");
            ArrayList<Integer> newValueList = new ArrayList();
            for (int x = 1; x <= update.getTotalAmountOfCases(); x++)
            {
                int value;
                do
                {
                    value = (rand.nextInt(100) + 1) * 1000;
                } while (newValueList.contains(value)); //Cannot have duplicate values
                newValueList.add(value);
                update.getCaseList().add(new Case(x, value));
            }
            update.setDuplicateCaseValues(newValueList);
            Collections.sort(update.getDuplicateCaseValues());
        }
        this.setUpValues();
    }
    
    /**
     * This method will set up the value labels in the game that exist on the side
     */
    public void setUpValues()
    {
        for (int k = 1; k <= update.getTotalAmountOfCases(); k++)
        {
            int num = update.getDuplicateCaseValues().get(k - 1);
            if (k <= 13)
            {
                update.getMoneyLabels().put(num, new GradientLabel("$" + nf.format(num), MoneyValueType.BLUE));
            }
            else if (k >= 14 && k <= 22)
            {
                update.getMoneyLabels().put(num, new GradientLabel("$" + nf.format(num), MoneyValueType.RED));
            }
            else
            {
                update.getMoneyLabels().put(num, new GradientLabel("$" + nf.format(num), MoneyValueType.GREEN));
            }
        }
    }
}