package DealOrNoDealGUI;
import SpecialClassPackage.FlashButton;
import SpecialClassPackage.GradientLabel;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author shivn
 */
public class UpdateInfo
{
    //flags
    private boolean loginFlag;// = false;
    private boolean gameStartedFlag;// = false;
    private boolean caseSelectedFlag;// = false;
    private boolean endOfRoundFlag;// = false;
    private boolean dealAcceptedFlag;// = false;
    private boolean endOfGameFlag;// = false;
    private boolean quitGameFlag;// = false;
    public boolean restarting;
    
    //variables
    private final int TOTAL_AMOUNT_OF_CASES = 26;
    private final int MAX_ROUNDS = 9;// = 9;
    private int totalCasesLeft;// = 26;
    private int totalCasesToOpen;// = 6;
    private int casesRemainingThisRound;// = 6;
    private int roundNumber;// = 0;
    private int playerCaseValue;
    private int bankOffer;
    private int allTimeHighScore;
    private String playerUsername;
    private int playerHighScore;
    
    private final float[] PERCENTAGE_DEDUCTIONS = new  float[]{ 0.15f, 0.25f, 0.35f, 0.45f, 0.55f, 0.65f, 0.75f, 0.85f, 1.0f };
    private ArrayList<Case> caseList;// = new ArrayList<>();
    private ArrayList<Integer> moneyValuesForCases;// = new ArrayList<>();
    private ArrayList<Integer> duplicateCaseValues;// = new ArrayList<>();    
    private LinkedHashMap<Integer, GradientLabel> moneyLabels;// = new LinkedHashMap<>();
    private ArrayList<FlashButton> flashBtn;// = new ArrayList<>();
    
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
        restarting = false;
    
        //variables
        totalCasesLeft = 26;
        totalCasesToOpen = 6;
        casesRemainingThisRound = 6;
        roundNumber = 0;
        bankOffer = 0;
        playerCaseValue = 0;
        playerHighScore = 0;
        allTimeHighScore = 0;
        playerUsername = "";
    
        caseList = new ArrayList<>();
        moneyValuesForCases = new ArrayList<>();
        duplicateCaseValues = new ArrayList<>();    
        moneyLabels = new LinkedHashMap<>();
        flashBtn = new ArrayList<>();
    }
    
    public String getPlayerName() {
        return this.playerUsername;
    }
    
    public void setPlayerName(String name) {
        this.playerUsername = name;
    }
    public int getMaxRounds() {
        return this.MAX_ROUNDS;
    }
    
    public int getAllTimeScore() {
        return this.allTimeHighScore;
    }
    
    public void setAllTimeScore(int score) {
        this.allTimeHighScore = score;
    }
    
    public int getPlayerHighScore() {
        return this.playerHighScore;
    }
    
    public void setPlayerHighScore(int score){
        this.playerHighScore = score;
    }
    
    public int getPlayerCaseValue() {
        return this.playerCaseValue;
    }
    
    public void setPlayerCaseValue(int value) {
        this.playerCaseValue = value;
    }
    
    public int getBankOffer() {
        return this.bankOffer;
    }
    
    public void setBankOffer(int bankOffer) {
        this.bankOffer = bankOffer;
    }
    
    public int getRoundNumber() {
        return this.roundNumber;
    }
    
    public void setRoundNumber(int roundNum) {
        this.roundNumber = roundNum;
    }
    
    public int getCasesRemainingThisRound() {
        return this.casesRemainingThisRound;
    }
    
    public void setCasesRemainingThisRound(int casesRemaining) {
        this.casesRemainingThisRound = casesRemaining;
    }
    
    public int getTotalAmountOfCases() {
        return this.TOTAL_AMOUNT_OF_CASES;
    }
    
    public int getTotalCasesLeft() {
        return this.totalCasesLeft;
    }
    
    public void setTotalCasesLeft(int casesLeft) {
        this.totalCasesLeft = casesLeft;
    }
    
    public int getTotalCasesToOpen() {
        return this.totalCasesToOpen;
    }
    
    public void setTotalCasesToOpen(int totalToOpen) {
        this.totalCasesToOpen = totalToOpen;
    }
    
    public boolean getQuitGameFlag() {
        return this.quitGameFlag;
    }
     
    public void setQuitGameFlag(boolean flag) {
        this.quitGameFlag = flag;
    }
    
    public boolean getEndOfGameFlag() {
        return this.endOfGameFlag;
    }
    
    public void setEndOfGameFlag(boolean flag) {
        this.endOfGameFlag = flag;
    }
    
    public boolean getDealAcceptedFlag() {
        return this.dealAcceptedFlag;
    }
    
    public void setDealAcceptedFlag(boolean flag) {
        this.dealAcceptedFlag = flag;
    }
    
    public boolean getEndOfRoundFlag() {
        return this.endOfRoundFlag;
    }
    
    public void setEndOfRoundFlag(boolean flag) {
        this.endOfRoundFlag = flag;
    }
    
    public boolean getCaseSelectedFlag() {
        return this.caseSelectedFlag;
    }
    
    public void setCaseSelectedFlag(boolean flag) {
        this.caseSelectedFlag = flag;
    }
    
    public boolean getLoginFlag() {
        return this.loginFlag;
    }
    
    public void setLoginFlag(boolean flag) {
        this.loginFlag = flag;
    }
    
    public boolean getGameStartedFlag() {
        return this.gameStartedFlag;
    }
    
    public void setGameStartedFlag(boolean flag) {
        this.gameStartedFlag = flag;
    }
    
    public float[] getPercentageDeductions() {
        return this.PERCENTAGE_DEDUCTIONS;
    }
    
    public ArrayList<FlashButton> getFlashBtns() {
        return this.flashBtn;
    }
    public ArrayList<Case> getCaseList() {
        return this.caseList;
    }
    
    public ArrayList<Integer> getMoneyValueForCases() {
        return this.moneyValuesForCases;
    }
    
    public LinkedHashMap<Integer, GradientLabel> getMoneyLabels() {
        return this.moneyLabels;
    }
    
    public ArrayList<Integer> getDuplicateCaseValues() {
        return this.duplicateCaseValues;
    }
    
    public void setDuplicateCaseValues(ArrayList<Integer> duplicates) {
        this.duplicateCaseValues = duplicates;
    }
}