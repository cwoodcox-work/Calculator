import java.util.ArrayList;
import static java.util.Arrays.*;

public class CalcLogic {



    public CalcLogic(){


    }
//need to work on consecutive multiplication/division operations.
    public String result(String text){
        //I take in the string from the calculator display and split it on operators other than subtraction
        //the subtraction stays so that the program just places an addition sign in front and treats the number as negative
        ArrayList<String> splitEquate = new ArrayList<>(asList(text.split("(?<=[+/*()^])|(?=[+\\-/*()^])")));
        //tempNum 1 and 2 are used to hold integers for calculation as the program loops through the split list
        //First loop for multiplication and division, then second loop for
        //subtraction and addition. It uses a while loop and a loop counter
        //it uses recursion for parentheses by tracking the start index and end index.
        double tempNum1 = 0;
        double tempNum2 = 0;
        String holdSign = "";
        //These booleans help the program keep track of what should be next on the list so in order to ensure
        //that what was entered on by the user was a valid equation
        boolean prevNum = false;
        boolean curNum = true;
        boolean negNum = false;
        boolean inPar = false;
        double total = 0;
        String parSubtotal = "";
        int parStart = 0;
        int parOpen = 0;
        int parClose = 0;
        int loopCount = 0;
        String parEquation = "";
        while(true) {
            loopCount += 1;
            for (int i = 0; i < splitEquate.size(); i++) {
                if(inPar){
                    if(splitEquate.get(i).equals(")")){
                        parClose +=1;
                        if(parOpen == parClose) {
                            parSubtotal = this.result(parEquation);
                            inPar = false;
                            splitEquate.subList(parStart, i + 1).clear();
                            splitEquate.add(parStart, parSubtotal);
                            i = parStart;
                        }else{
                            parEquation+= splitEquate.get(i);
                        }
                    }else if(splitEquate.get(i).equals("(")){
                        parOpen +=1;
                        parEquation += splitEquate.get(i);
                    }else{
                        parEquation += splitEquate.get(i);
                    }
                }
                if(!inPar) {
                    try {
                        Double.valueOf(splitEquate.get(i));
                        curNum = true;
                    } catch (NumberFormatException e) {
                        curNum = false;
                    }
                    if (curNum) {
                        //this if statement is to use an add if the number is negative and there is no
                        //other sign before since subtraction is adding a negative number
                        if (prevNum && Double.valueOf(splitEquate.get(i)) < 0) {
                            negNum = true;
                            holdSign = "+";
                        }
                        if (holdSign.equals("")){
                            tempNum1 = Double.valueOf(splitEquate.get(i));
                        }else if (holdSign.equals("^")){
                            tempNum2 = this.exponent(tempNum1,Double.valueOf(splitEquate.get(i)));
                            splitEquate.subList(i-2,i+1).clear();
                            splitEquate.add(i-2,String.valueOf(tempNum2));
                            tempNum1 = Double.valueOf(tempNum2);
                            i-= 2;
                        }else if (holdSign.equals("/")){
                            if(loopCount ==1){
                                tempNum1 = Double.valueOf(splitEquate.get(i));
                            }else if(loopCount == 2) {
                                tempNum2 = tempNum1 / Double.valueOf(splitEquate.get(i));
                                splitEquate.subList(i - 2, i + 1).clear();
                                splitEquate.add(i - 2, String.valueOf(tempNum2));
                                tempNum1 = Double.valueOf(tempNum2);
                                i -= 2;
                            }
                        } else if (holdSign.equals("*")){
                            if(loopCount == 1){
                                tempNum1 = Double.valueOf(splitEquate.get(i));
                            }else if(loopCount == 2) {
                                tempNum2 = tempNum1 * Double.valueOf(splitEquate.get(i));
                                splitEquate.subList(i - 2, i + 1).clear();
                                splitEquate.add(i - 2, String.valueOf(tempNum2));
                                tempNum1 = Double.valueOf(tempNum2);
                                i -= 2;
                            }
                        } else if (holdSign.equals("+")){
                            if (loopCount == 1 && negNum) {
                                splitEquate.add(i, String.valueOf(holdSign));
                                i++;
                                tempNum1 = Double.valueOf(splitEquate.get(i));
                                negNum = false;
                            } else if (loopCount < 3) {
                                tempNum1 = Double.valueOf(splitEquate.get(i));
                            } else if (loopCount == 3){
                                tempNum2 = tempNum1 + Double.valueOf(splitEquate.get(i));
                                splitEquate.subList(i - 2, i + 1).clear();
                                splitEquate.add(i - 2, String.valueOf(tempNum2));
                                tempNum1 = Double.valueOf(tempNum2);
                                i -= 2;
                            }
                        }
                        prevNum = true;
                    } else if (prevNum && !splitEquate.get(i).equals("(")) {
                        holdSign = splitEquate.get(i);
                        prevNum = false;
                    } else if (splitEquate.get(i).equals("(")) {
                        if(prevNum){
                            splitEquate.add(i,"*");
                            holdSign = "*";
                            i++;
                            prevNum = false;
                        }
                        inPar = true;
                        parStart = Integer.valueOf(i);
                        parOpen +=1;
                    } else {
                        String problem = "Consecutive Operators";
                        return problem;
                    }
                }
            }
            //since the program is doing math within the list, the items get combined and the signs get removed.
            // the program breaks out and returns the final number once the list is down to just the final number
            if(splitEquate.size() == 1){
                total = Double.valueOf(splitEquate.get(0));
                break;
            }else{
                tempNum1 = 0;
                tempNum2 = 0;
                holdSign = "";
            }
        }
        return String.valueOf(total);

    }
    private double exponent(double base,double expo){
        if(base % 1 == 0) {
            if (expo == 0) {
                return 1;
            } else if (expo > 0) {
                return base * this.exponent(base, expo - 1);
            } else if (expo < 0) {
                return base / this.exponent(base, expo - 1);
            }
        }
        return 0;
    }

}
