/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowlingassignment;

import java.util.*;

/**
 *
 * @author Eric Bloom
 * 
 * 
 *    Tasks which are out of scope
 *       No need to check for valid rolls.
 *       No need to check for correct number of rolls and frames.
 *       No need to provide scores for intermediate frames.
 * 
 *    Scoring Logic
 *      Each game, or "line" of bowling, includes ten turns, or "frames" for the bowler.
 *      In each frame, the bowler gets up to two tries to knock down all the pins.
 *      If in two tries, he fails to knock them all down, his score for that frame is the total number of pins knocked down in his two tries.
 *      If in two tries he knocks them all down, this is called a "spare" and his score for the frame is ten plus the number of pins knocked down on his next throw (in his next turn).
 *      If on his first try in the frame he knocks down all the pins, this is called a "strike". His turn is over, and his score for the frame is ten plus the simple total of the pins knocked down in his next two rolls.
 *      If he gets a spare or strike in the last (tenth) frame, the bowler gets to throw one or two more bonus balls, respectively. These bonus throws are taken as part of the same turn. If the bonus throws knock down all the pins, the process does not repeat: the bonus throws are only used to calculate the score of the final frame.
 *      The game score is the total of all frame scores.
 * 
 */
public class BowlingAssignment {

    BowlingAssignment(String input)
    {
        System.out.println(bowlingScore(input));
    }
    
    public static void main(String[] args) {
        //user Input
        String userInput1 = "X 7/ 9- X -8 8/ -6 X X X81"; //Spaces added to input to differentiate between each bowling frame.
        
        BowlingAssignment bowling = new BowlingAssignment(userInput1);
        
    }
    
    private int bowlingScore(String scoreInput)
    {   
        //Replaced - with 0 and removed spaces.  This way moving down the string by position is easier
        String scoreDashReplaced = scoreInput.replace('-','0').replace(" ","");
        //Put string into aray separating after every space this way sets of 2 are kept together
        String[] splitScore = scoreInput.split(" ");
        
        //Lines 49-54 are test cases
        //System.out.println(splitScore[0]);
        //System.out.println(splitScore[1]);
        //System.out.println(splitScore[2]);
        //System.out.println(splitScore[3]);
        //System.out.println(scoreDashReplaced.length());
        //System.out.println(splitScore[9].length());
        
        
        //Declares total as 0 and starts finding what our actual score is.
        int scoreTotal = 0;
        for (int i = 0; i < scoreDashReplaced.length(); i++)
        {
            scoreTotal += this.scoreAmount(i, scoreDashReplaced); 
            //If we returned the score now you would just get all of the numbers added together. So we need more if statements for our rules.
            //splitScore[9] is the last spot in the array.  This checks to make sure on the last throw we aren't adding incorrectly if there is a strike or spare.
            if (i < scoreDashReplaced.length() - splitScore[9].length()) 
            {
                if (scoreDashReplaced.substring(i, i + 1).equals("/")) 
                {
                    //Since we got a spare we add only the next throw
                    scoreTotal += this.scoreAmount(i + 1, scoreDashReplaced);
                }
                else if (scoreDashReplaced.substring(i, i + 1).equals("X")) 
                {
                    //Since we got a strike we add the next two rolls is added.
                    scoreTotal += (this.scoreAmount(i + 1, scoreDashReplaced) + this.scoreAmount(i + 2, scoreDashReplaced));
                } 
            }
        }
        
        
        return scoreTotal;
    }
    
    private int scoreAmount(int i, String scoreDashReplaced)
    {
        String number = scoreDashReplaced.substring(i, i + 1);
        //System.out.println(i-1);
        //System.out.println(i);
        //System.out.println(i+1);
        if (number.equals("/"))
        {
            //Spares are 10 points but we need to add the correct amount, so we minus our previous roll from 10 for it to be added
            return 10 - (Integer.parseInt(scoreDashReplaced.substring(i - 1, i)));
        }
        else if (number.equals("X"))
        {
            //Strikes are 10 points
            return 10;
        }
        else
        {
            //Other numbers are counted as their valure
            return Integer.parseInt(scoreDashReplaced.substring(i, i +1));
        }
    }
}

