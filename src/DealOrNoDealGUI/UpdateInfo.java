package DealOrNoDealGUI;
import SpecialClassPackage.FlashButton;
import SpecialClassPackage.GradientLabel;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * PDC Assignment 2
 * This is the UpdateInfo Class, where variables are given values from the Model and taken from the View
 * @author Shivneel Singh (18021394)
 * @since 11/06/2020
 */
public class UpdateInfo
{
    /**
     * Flags
     */
    private boolean loginFlag;
    private boolean gameStartedFlag;
    private boolean caseSelectedFlag;
    private boolean endOfRoundFlag;
    private boolean dealAcceptedFlag;
    private boolean endOfGameFlag;
    private boolean quitGameFlag;
    private boolean blankLogin;
    
    /**
     * Variables
     */
    private final int TOTAL_AMOUNT_OF_CASES = 26;
    private final int MAX_ROUNDS = 9;
    private int totalCasesLeft;
    private int totalCasesToOpen;
    private int casesRemainingThisRound;
    private int roundNumber;
    private int playerCaseValue;
    private int bankOffer;
    private int allTimeHighScore;
    private String playerUsername;
    private int playerHighScore;
    private Case playerCase;
    
    /**
     * Arrays, Lists and a Map Variables
     */
    private final float[] PERCENTAGE_DEDUCTIONS = new  float[]{ 0.15f, 0.25f, 0.35f, 0.45f, 0.55f, 0.65f, 0.75f, 0.85f, 1.0f };
    private ArrayList<Case> caseList;// = new ArrayList<>();
    private ArrayList<Integer> moneyValuesForCases;// = new ArrayList<>();
    private ArrayList<Integer> duplicateCaseValues;// = new ArrayList<>();    
    private LinkedHashMap<Integer, GradientLabel> moneyLabels;// = new LinkedHashMap<>();
    private ArrayList<FlashButton> flashBtn;// = new ArrayList<>();
    
    /**
     * Constructor
     */
    public UpdateInfo()
    {
        //flags
        loginFlag = false;
        gameStartedFlag = false;
        caseSelectedFlag = false;
        endOfRoundFlag = false;
        dealAcceptedFlag = false;
        endOfGameFlag = false;
        quitGameFlag = false;
        blankLogin = false;
    
        //variables
        totalCasesLeft = 26;
        totalCasesToOpen = 6; //decrements after each round
        casesRemainingThisRound = 6; //decrements after each case opened, end of round, value reassigned to totalCasesToOpen value
        roundNumber = 0;
        bankOffer = 0;
        playerCaseValue = 0;
        playerHighScore = 0;
        allTimeHighScore = 0;
        playerUsername = "";
    
        //arrays, lists, map
        caseList = new ArrayList<>();
        moneyValuesForCases = new ArrayList<>();
        duplicateCaseValues = new ArrayList<>();    
        moneyLabels = new LinkedHashMap<>();
        flashBtn = new ArrayList<>();
    }
        
    /**
     * This method will get the player's username
     * @return   The player's username
     */
    public String getPlayerName() {
        return this.playerUsername;
    }
    
    /**
     * This method will set the player's user name
     * @param name   The string of the username to be stored
     */
    public void setPlayerName(String name) {
        this.playerUsername = name;
    }
    
    /**
     * This method will get the max amount of rounds in the game
     * @return   The max amount of rounds in this game
     */
    public int getMaxRounds() {
        return this.MAX_ROUNDS;
    }
    
    /**
     * This method will get the all time high score
     * @return   The highest all time score
     */
    public int getAllTimeScore() {
        return this.allTimeHighScore;
    }
    
    /**
     * This method will see the all time high score
     * @param score   The high score to be set
     */
    public void setAllTimeScore(int score) {
        this.allTimeHighScore = score;
    }
    
    /**
     * This method will get the player's high score
     * @return   The player's high score
     */
    public int getPlayerHighScore() {
        return this.playerHighScore;
    }
    
    /**
     * This method will set the player's high score
     * @param score   The player's score to be set
     */
    public void setPlayerHighScore(int score){
        this.playerHighScore = score;
    }

    /**
     * This method sets the player case
     * @param c   Case to be assigned to the player
     */
    public void setPlayerCase(Case c) {
        playerCase = c;
    }
    
    /**
     * This method gets the player's case
     * @return   The player's case
     */
    public Case getPlayerCase() {
        return this.playerCase;
    }
    
    /**
     * This method gets the bank offer
     * @return   The bank offer
     */
    public int getBankOffer() {
        return this.bankOffer;
    }
    
    /**
     * This method sets the bank offer
     * @param bankOffer   The bank offer value to be set
     */
    public void setBankOffer(int bankOffer) {
        this.bankOffer = bankOffer;
    }
    
    /**
     * This method gets the current round number
     * @return   The round number the game is currently on
     */
    public int getRoundNumber() {
        return this.roundNumber;
    }
    
    /**
     * This method sets the current round number
     * @param roundNum   The round number to be set
     */
    public void setRoundNumber(int roundNum) {
        this.roundNumber = roundNum;
    }
    
    /**
     * This method gets the cases remaining in the round
     * @return   The amount of cases left to open this round
     */
    public int getCasesRemainingThisRound() {
        return this.casesRemainingThisRound;
    }
    
    /**
     * This method sets the amount of cases left to open this round
     * @param casesRemaining   The amount of cases left to open this round
     */
    public void setCasesRemainingThisRound(int casesRemaining) {
        this.casesRemainingThisRound = casesRemaining;
    }
    
    /**
     * This method gets the total amount of cases in the game
     * @return   The total amount of cases in the game
     */
    public int getTotalAmountOfCases() {
        return this.TOTAL_AMOUNT_OF_CASES;
    }
    
    /**
     * This method gets the total amount of cases left in the game
     * @return   The total amount of cases left in the game
     */
    public int getTotalCasesLeft() {
        return this.totalCasesLeft;
    }
    
    /**
     * This method sets the total cases left in the game to open
     * @param casesLeft   The amount of cases left to open in the game
     */
    public void setTotalCasesLeft(int casesLeft) {
        this.totalCasesLeft = casesLeft;
    }
    
    /**
     * This method gets the amount of cases left to open this round
     * @return   The amount of cases left to open this round
     */
    public int getTotalCasesToOpen() {
        return this.totalCasesToOpen;
    }
    
    /**
     * This method sets the total amount of cases open this round
     * @param totalToOpen   The amount of cases to open this round
     */
    public void setTotalCasesToOpen(int totalToOpen) {
        this.totalCasesToOpen = totalToOpen;
    }
    
    /**
     * This method gets the quitGame flag
     * @return   Boolean value of the quitGame flag
     */
    public boolean getQuitGameFlag() {
        return this.quitGameFlag;
    }
     
    /**
     * This method sets the quitGame flag
     * @param flag   Boolean value to be set to flag
     */
    public void setQuitGameFlag(boolean flag) {
        this.quitGameFlag = flag;
    }
    
    /**
     * This method gets the endOfGame flag
     * @return   Boolean value of the endOfGame flag
     */
    public boolean getEndOfGameFlag() {
        return this.endOfGameFlag;
    }
    
    /**
     * This method sets the endOfGame flag
     * @param flag   Boolean value to be set to flag
     */
    public void setEndOfGameFlag(boolean flag) {
        this.endOfGameFlag = flag;
    }
    
    /**
     * This method gets the dealAccepted flag
     * @return   Boolean value of the endOfGame flag
     */
    public boolean getDealAcceptedFlag() {
        return this.dealAcceptedFlag;
    }
    
    /**
     * This method sets the dealAccepted flag
     * @param flag   Boolean value to be set to flag
     */
    public void setDealAcceptedFlag(boolean flag) {
        this.dealAcceptedFlag = flag;
    }
    
    /**
     * This method gets the endOfRound flag
     * @return   Boolean value of the endOfGame flag
     */
    public boolean getEndOfRoundFlag() {
        return this.endOfRoundFlag;
    }
    
    /**
     * This method sets the endOfRound flag
     * @param flag   Boolean value to be set to flag
     */
    public void setEndOfRoundFlag(boolean flag) {
        this.endOfRoundFlag = flag;
    }
    
    /**
     * This method gets the caseSelected flag
     * @return   Boolean value of the endOfGame flag
     */
    public boolean getCaseSelectedFlag() {
        return this.caseSelectedFlag;
    }
    
    /**
     * This method sets the caseSelected flag
     * @param flag   Boolean value to be set to flag
     */
    public void setCaseSelectedFlag(boolean flag) {
        this.caseSelectedFlag = flag;
    }
    
    /**
     * This method gets the blankLogin flag
     * @return   Boolean value of the blankLogin flag
     */
    public boolean getBlankFlag() {
        return this.blankLogin;
    }
    
    /**
     * This method sets the blankLogin flag
     * @param flag   Boolean value to be set to flag
     */
    public void setBlankFlag(boolean flag) {
        this.blankLogin = flag;
    }
    
    /**
     * This method gets the login flag
     * @return   Boolean value of the endOfGame flag
     */
    public boolean getLoginFlag() {
        return this.loginFlag;
    }
    
    /**
     * This method sets the login flag
     * @param flag   Boolean value to be set to flag
     */
    public void setLoginFlag(boolean flag) {
        this.loginFlag = flag;
    }
    
    /**
     * This method gets the gameStarted flag
     * @return   Boolean value of the endOfGame flag
     */
    public boolean getGameStartedFlag() {
        return this.gameStartedFlag;
    }
    
    /**
     * This method sets the gameStarted flag
     * @param flag   Boolean value to be set to flag
     */
    public void setGameStartedFlag(boolean flag) {
        this.gameStartedFlag = flag;
    }
    
    /**
     * This method gets the float array of percentage deductions used to calculate the bank offer
     * @return   The percentage deductions array
     */
    public float[] getPercentageDeductions() {
        return this.PERCENTAGE_DEDUCTIONS;
    }
    
    /**
     * This method gets the buttons that flash
     * @return   ArrayList of buttons that flash
     */
    public ArrayList<FlashButton> getFlashBtns() {
        return this.flashBtn;
    }
    
    /**
     * This method gets the list of all cases
     * @return   ArrayList of containing all the cases
     */
    public ArrayList<Case> getCaseList() {
        return this.caseList;
    }
    
    /**
     * This method gets the ArrayList of money values used in the game
     * @return   ArrayList of money values used in the game
     */
    public ArrayList<Integer> getMoneyValueForCases() {
        return this.moneyValuesForCases;
    }
    
    /**
     * This method returns all money labels used in the game
     * @return   The LinkedHashMap of labels in the game
     */
    public LinkedHashMap<Integer, GradientLabel> getMoneyLabels() {
        return this.moneyLabels;
    }
    
    /**
     * This method gets the duplicate case values ArrayList
     * @return   An ArrayList of the duplicate case values
     */
    public ArrayList<Integer> getDuplicateCaseValues() {
        return this.duplicateCaseValues;
    }
    
    /**
     * This method sets up the duplicate case values for the Update class
     * @param duplicates   An ArrayList of the duplicate case values
     */
    public void setDuplicateCaseValues(ArrayList<Integer> duplicates) {
        this.duplicateCaseValues = duplicates;
    }
}