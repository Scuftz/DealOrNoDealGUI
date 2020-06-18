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
    public boolean setUp = false;
    public boolean loginFlag = false;
    public boolean gameStarted = false;
    public boolean caseSelected = false;
    public boolean endOfRound = false;
    public boolean dealAccepted = false;
    public boolean endOfGame = false;
    public boolean quitGame = false;
    
    //variables
    public final int totalAmountOfCases = 26;
    public int totalCasesLeft = 26;
    public int totalCasesToOpen = 6;
    public int casesRemainingThisRound = 6;
    public int roundNumber = 0;
    public int bankOffer;
    public int userCaseValue;
    public int userHighScore;
    public int allTimeHighScore;
    public int maxRounds = 9;
    public String playerUsername;
    
    public float[] percentageDeductions = new  float[]{ 0.15f, 0.25f, 0.35f, 0.45f, 0.55f, 0.65f, 0.75f, 0.85f, 1.0f };
    public ArrayList<Case> caseList = new ArrayList<>();
    public ArrayList<Integer> moneyValuesForCases = new ArrayList<>();
    public ArrayList<Integer> duplicateCaseValues = new ArrayList<>();    
    public LinkedHashMap<Integer, GradientLabel> tester = new LinkedHashMap<>();
    
    public ArrayList<FlashButton> flashBtn = new ArrayList<>();
}