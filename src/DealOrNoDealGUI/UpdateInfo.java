/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DealOrNoDealGUI;

import java.util.ArrayList;

/**
 *
 * @author shivn
 */
public class UpdateInfo
{
    //flags
    public boolean loginFlag = false;
    public boolean gameStarted = false;
    public boolean endOfRound = false;
    public boolean dealAccepted = false;
    public boolean endOfGame = false;
    public boolean quitGame = false;
    
    //variables
    public final int totalAmountOfCases = 26;
    public int totalCasesToOpen = 6;
    public int casesRemainingThisRound;
    public int round = 1;
    public int bankOffer;
    public ArrayList<Case> caseList = new ArrayList<>();
    public ArrayList<Integer> moneyValuesForCases = new ArrayList<>();
    public ArrayList<Integer> duplicateCaseValues = new ArrayList<>();
}
