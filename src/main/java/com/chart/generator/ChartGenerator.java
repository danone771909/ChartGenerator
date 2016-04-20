package com.chart.generator;

/**
 * Created by Daniel on 2016-04-14.
 */
import java.util.HashMap;

public class ChartGenerator {
    //---------------------------------------------
    // VARIABLES
    //---------------------------------------------

    private static final char UP = '1';
    private static final char DOWN = '-';
    private static final char RIGHT = '0';

    private int yAxesLength=0;
    private int xAxesLength = 3;

    //---------------------------------------------
    // PUBLIC METHODS
    //---------------------------------------------

    // ---convert string to HashMap---
    public HashMap<Key, Character> dataToHashMap(String s)
    {
        HashMap<Key, Character> chartParameter = new HashMap<Key, Character>();
        int xPosition = 0;
        int yPosition = 0;

        char previousStep = RIGHT;

        for(int i=0; i<s.length(); i++)
        {
            switch (s.charAt(i))
            {
                case UP:
                    if(i!=0)
                        yPosition = correctPosition(previousStep, UP, yPosition);

                    chartParameter.put(new Key(xPosition, yPosition), '/');
                    previousStep = UP;
                    break;

                case DOWN:
                    if((i == s.length()-1) || (s.charAt(i+1) != '1'))
                    {
                        chartParameter.clear();
                        return null;
                    }
                    else
                    {
                        if(i!=0)
                            yPosition = correctPosition(previousStep, DOWN, yPosition);

                        chartParameter.put(new Key(xPosition, yPosition), '\\');
                        previousStep = DOWN;
                        i++;
                    }
                    break;

                case RIGHT:
                    if(i!=0)
                        yPosition = correctPosition(previousStep, RIGHT, yPosition);

                    chartParameter.put(new Key(xPosition, yPosition), '_');
                    previousStep = RIGHT;
                    break;

                default:
                    chartParameter.clear();
                    return null;
            }
            xAxesLength ++;
            xPosition++;
        }

        return chartParameter;
    }

    // ---draw chart---
    public String dataToString(String s)
    {
        HashMap<Key, Character> chartParameter = dataToHashMap(s);
        if(chartParameter == null)
            return null;
        String result = "";
        yAxesLength +=4;
        int axesX = yAxesLength/2 - 1;
        for(int i=yAxesLength; i>=0; i--)
        {
            result += "\n";
            for (int j=0; j<xAxesLength; j++)
            {
                Key k = new Key(j,i-axesX);
                if(chartParameter.containsKey(k))
                {
                    result += chartParameter.get(k);
                }
                else
                {
                    if(i==axesX)
                    {
                        if(j==xAxesLength-1)
                            result += "X";
                        else if(j==xAxesLength-2)
                            result+=">";
                        else
                        result += "-";
                    }
                    else if(j==0)
                    {
                        if(i==yAxesLength)
                            result += "Y";
                        else if(i==yAxesLength-1)
                            result +="^";
                        else
                        result += "|";
                    }
                    else
                    result += " ";
                }
            }
        }
        return result;
    }

    //---------------------------------------------
    // PRIVATE METHODS
    //---------------------------------------------

    // ---modify Y position (caused by horizontal line '_')---
    private int correctPosition(int previousStep, int currentStep, int yPosition)
    {
        int absYPosition=0;
        switch (previousStep)
        {
            case UP:
                if (currentStep != DOWN)
                    yPosition++;

                if(yPosition > yAxesLength)
                    yAxesLength = yPosition;
                break;

            case DOWN:
                if(currentStep == DOWN)
                    yPosition--;

                absYPosition = -1*yPosition;
                if(absYPosition > yAxesLength)
                    yAxesLength = absYPosition;
                break;

            case RIGHT:
                if(currentStep == DOWN)
                    yPosition--;

                absYPosition = -1*yPosition;
                if(absYPosition > yAxesLength)
                    yAxesLength = absYPosition;
                break;
        }
        return yPosition;
    }
}
