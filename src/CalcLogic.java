import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Arrays.*;

public class CalcLogic {



    public CalcLogic(){


    }
//need to work on consecutive multiplication/division operations.
    public String result(String text){

        ArrayList<String> splitEquate = new ArrayList<>(asList(text.split("(?<=[+/*])|(?=[+\\-/*])")));
        String[] tempHold;
        int tempNum1 = 0;
        int tempNum2 = 0;
        String holdSign = "";
        boolean prevNum = false;
        boolean curNum = true;
        boolean negNum = false;
        int total = 0;
        int loopCount = 0;
        while(true) {
            loopCount += 1;
            for (int i = 0; i < splitEquate.size(); i++) {
                try{
                    Integer.valueOf(splitEquate.get(i));
                    curNum = true;
                }catch(NumberFormatException e){
                    curNum = false;
                }
                if (curNum){
                    if(prevNum && Integer.valueOf(splitEquate.get(i))<0){
                        negNum = true;
                        holdSign = "+";
                    }
                    if (holdSign.equals("")) {
                        tempNum1 = Integer.valueOf(splitEquate.get(i));
                    } else if (holdSign.equals("/")) {
                        tempNum2 = tempNum1 / Integer.valueOf(splitEquate.get(i));
                        splitEquate.subList(i - 2, i + 1).clear();
                        splitEquate.add(i - 2, String.valueOf(tempNum2));
                        tempNum1 = Integer.valueOf(tempNum2);
                        i -= 2;
                    } else if (holdSign.equals("*")) {
                        tempNum2 = tempNum1 * Integer.valueOf(splitEquate.get(i));
                        splitEquate.subList(i - 2, i + 1).clear();
                        splitEquate.add(i - 2, String.valueOf(tempNum2));
                        tempNum1 = Integer.valueOf(tempNum2);
                        i -= 2;
                    } else if (holdSign.equals("+")) {
                        if (loopCount == 1 && negNum) {
                            splitEquate.add(i, String.valueOf(holdSign));
                            i++;
                            tempNum1 = Integer.valueOf(splitEquate.get(i));
                        }else if(loopCount == 1){
                            tempNum1 = Integer.valueOf(splitEquate.get(i));
                        }else {
                            tempNum2 = tempNum1 + Integer.valueOf(splitEquate.get(i));
                            splitEquate.subList(i - 2, i + 1).clear();
                            splitEquate.add(i - 2, String.valueOf(tempNum2));
                            tempNum1 = Integer.valueOf(tempNum2);
                            i -= 2;
                        }
                    }
                    prevNum = true;
                } else if(prevNum){
                    holdSign = splitEquate.get(i);
                    prevNum = false;
                }else{
                    String problem = "Consecutive Operators";
                    return problem;
                }
            }
            if(splitEquate.size() == 1){
                total = Integer.valueOf(splitEquate.get(0));
                break;
            }else{
                tempNum1 = 0;
                tempNum2 = 0;
                holdSign = "";
            }
        }
        return String.valueOf(total);

    }

}
