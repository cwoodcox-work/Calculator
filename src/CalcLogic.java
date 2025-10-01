import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Arrays.*;

public class CalcLogic {



    public CalcLogic(){


    }
//need to work on consecutive multiplication/division operations.
    public String result(String text){

        ArrayList<String> splitEquate = new ArrayList<>(asList(text.split("(?<=[+\\-/*])|(?=[+\\-/*])")));
        String[] tempHold;
        int tempNum1 = 0;
        int tempNum2 = 0;
        String holdSign = "";
        int total = 0;
        int loopCount = 0;
        while(true) {
            loopCount += 1;
            for (int i = 0; i < splitEquate.size(); i++) {
                if (i % 2 == 0) {
                    if (holdSign.equals("")) {
                        tempNum1 = Integer.valueOf(splitEquate.get(i));
                    } else if (holdSign.equals("/")) {
                        tempNum2 = tempNum1 / Integer.valueOf(splitEquate.get(i));
                        splitEquate.subList(i - 2, i+1).clear();
                        splitEquate.add(i - 2, String.valueOf(tempNum2));
                        tempNum1 = Integer.valueOf(tempNum2);
                        i -= 2;
                    } else if (holdSign.equals("*")) {
                        tempNum2 = tempNum1 * Integer.valueOf(splitEquate.get(i));
                        splitEquate.subList(i - 2, i+1).clear();
                        splitEquate.add(i - 2, String.valueOf(tempNum2));
                        tempNum1 = Integer.valueOf(tempNum2);
                        i -= 2;
                    } else if (holdSign.equals("+")){
                        if(loopCount == 1){
                            tempNum1 = Integer.valueOf(splitEquate.get(i));
                        }else{
                            tempNum2 = tempNum1 + Integer.valueOf(splitEquate.get(i));
                            splitEquate.subList(i - 2, i+1).clear();
                            splitEquate.add(i - 2, String.valueOf(tempNum2));
                            tempNum1 = Integer.valueOf(tempNum2);
                            i -= 2;
                        }
                    }else if (holdSign.equals("-")) {
                        if (loopCount == 1) {
                            tempNum1 = Integer.valueOf(splitEquate.get(i));
                        } else {
                            tempNum2 = tempNum1 - Integer.valueOf(splitEquate.get(i));
                            splitEquate.subList(i - 2, i+1).clear();
                            splitEquate.add(i - 2, String.valueOf(tempNum2));
                            tempNum1 = Integer.valueOf(tempNum2);
                            i -= 2;
                        }
                    }

                } else {
                    holdSign = splitEquate.get(i);
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
