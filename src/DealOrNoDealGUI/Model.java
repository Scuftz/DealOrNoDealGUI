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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

public class Model extends Observable
{
    private static NumberFormat nf = NumberFormat.getNumberInstance();
    UpdateInfo update;
    protected int caseCounter; //reset
    protected Scanner input;
    protected String file;
    protected Random rand = new Random();
    protected Database playerDB;
    protected String username, password;
    public boolean caseSelected; //reset
    
    public Model()
    {
        file = "caseValues.txt";
        caseCounter = 0;
        caseSelected = false;
        update = new UpdateInfo();
        playerDB = new Database();
        this.setUpCases();
        this.setUpFlashes();
    }
    
    public void quitGame()
    {
        playerDB.closeDatabase();
//        update.quitGame = true;
        update.setQuitGameFlag(true);
        setChanged();
        notifyObservers(update);
    }
    public void restartGame()
    {
        update = new UpdateInfo();
        update.restarting = true;
        caseCounter = 0;
        caseSelected = false;
        this.setUpCases();
        this.setUpFlashes();
        setChanged();
        notifyObservers(update);
    }
    
    public void checkLogin(String un, String pw)
    {
        boolean loginStatus = playerDB.checkLogin(un, pw);
        update.setLoginFlag(loginStatus);
//        update.loginFlag = loginStatus;
        update.setPlayerName(un);// = un;
        setChanged();
        notifyObservers(update);
    }
    
    public void checkHighScore()
    {
        int highscore = playerDB.getPlayerHighScore(update.getPlayerName());
        System.out.println("PLAYER CURRENT HIGH SCORE: " + highscore);
        System.out.println("MOST RECENT BANK OFFER: " + update.getBankOffer());
        System.out.println("USER CASE VALUE: " + update.getPlayerCaseValue());
        
        if(update.getDealAcceptedFlag())
        {
            if (highscore < update.getBankOffer())
            {
                playerDB.updateScore(update.getPlayerName(), update.getBankOffer());
            }
        }
        else
        {
            if (highscore < update.getPlayerCaseValue())
            {
                playerDB.updateScore(update.getPlayerName(), update.getPlayerCaseValue());
            }
        }
        highscore = playerDB.getPlayerHighScore(update.getPlayerName());
        update.setPlayerHighScore(highscore);
//        update.userHighScore = highscore;
        System.out.println("PLAYER CURRENT HIGH SCORE: " + highscore);
    }
    
    public void getAllTimeScore()
    {
        int topScore = playerDB.getAllTimeHighScore();
        update.setAllTimeScore(topScore);
//        update.allTimeHighScore = topScore;
    }
    
    public void endGame()
    {
//        update.endOfGame = true;
        update.setEndOfGameFlag(true);
        this.checkHighScore();
        this.getAllTimeScore();
        setChanged();
        notifyObservers(update);
    }
    
    //will change the value of case opened to its case value
    public void openOrSetCase(Case c)
    {
        if (!update.getCaseSelectedFlag())
        {
//            update.caseList.get(c.getCaseNumber()-1).setPlayerCase(true);
            update.getCaseList().get(c.getCaseNumber()-1).setPlayerCase(true);
//            update.userCaseValue = c.getCaseValue();
            update.setPlayerCaseValue(c.getCaseValue());
        }
        else
        {
            update.getCaseList().get(c.getCaseNumber() - 1).setOpenStatus(true);
            update.getMoneyLabels().get(c.getCaseValue()).setOpen();
            update.setTotalCasesLeft(update.getTotalCasesLeft() - 1);
//            update.totalCasesLeft--;
//            update.casesRemainingThisRound--;
            update.setCasesRemainingThisRound(update.getCasesRemainingThisRound() - 1);
            if(update.getCasesRemainingThisRound() == 0)
            {
                if(update.getRoundNumber() != update.getMaxRounds())
                {
                    this.calculateBankOffer(update.getRoundNumber(), update.getCaseList());
                    this.setUpNewRound();
//                    update.endOfRound = true;
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
    
    public void invert()
    {
        for(FlashButton fb : update.getFlashBtns())
        {
            fb.invert();
        }
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
        float bankOffer = (sum / update.getTotalCasesLeft()) * update.getPercentageDeductions()[update.getRoundNumber()];
        System.out.println("BANK OFFER...\n" + nf.format((int)bankOffer));
//        update.bankOffer = (int)bankOffer;
        update.setBankOffer((int)bankOffer);
    }
    
    public void setUpNewRound()
    {
        if(update.getTotalCasesToOpen() > 1)
        {
//            update.totalCasesToOpen--;
            update.setTotalCasesToOpen(update.getTotalCasesToOpen() - 1);
        }
//        update.casesRemainingThisRound = update.getTotalCasesToOpen();
        update.setCasesRemainingThisRound(update.getTotalCasesToOpen());
//        update.roundNumber++;
        update.setRoundNumber(update.getRoundNumber() + 1);
        System.out.println("Round Number" + update.getRoundNumber());
    }
    
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
                    update.getMoneyValueForCases().add(input.nextInt());
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
                } while (newValueList.contains(value));
                newValueList.add(value);
                update.getCaseList().add(new Case(x, value));
            }
            update.setDuplicateCaseValues(newValueList);
            Collections.sort(update.getDuplicateCaseValues());
        }
        this.setUpValues();
    }
    
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