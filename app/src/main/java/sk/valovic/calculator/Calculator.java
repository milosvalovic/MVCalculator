package sk.valovic.calculator;

import android.util.Log;

import org.decimal4j.util.DoubleRounder;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Calculator {

    public static String getResult(String equation, String errorString) {
            equation = equation.replace("÷","/");
            equation = equation.replace("×","*");
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("rhino");
        try {
            String result = ((Double)engine.eval(equation)).toString();
            int decimalPointIndex = result.indexOf(".");
            if(result.substring(decimalPointIndex,result.length()-1).length() > 10){
                double decNum = DoubleRounder.round(Double.parseDouble(result), 10);
                result = "" + decNum;
            }
            int index = result.indexOf(".0");
            if(index != -1) {
                if(index == result.length() - 2){
                    result = result.substring(0, index);
                }
            }
            result = result.replace("/","÷");
            result = result.replace("*", "×");
            return result;
        } catch (ScriptException e) {
            return errorString;
        }
    }
}
