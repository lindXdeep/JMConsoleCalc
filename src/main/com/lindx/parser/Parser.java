package main.com.lindx.parser;

import java.util.HashMap;
import java.util.Map;

import main.com.lindx.calc.Roman;
import main.com.lindx.calc.Types;

public class Parser {

    private boolean roman_flag = false;

    private String expression;

    private Map<Roman, Integer> romans_map = new HashMap<>();  
   

    public void parse(final String exp) {

        for (int i = 0; i < Types.romans.size() ; i++)
            romans_map.put(Roman.values()[i], i+1);
        

        System.out.println(romans_map);

        this.expression = cleanSpaces(exp);

        Result result = plusMinus(this.expression);

        
        

    }

    private String cleanSpaces(final String exp){

        String[] tmp = exp.split(" ");  

        StringBuilder str = new StringBuilder();     

        if(tmp.length > 1)
            for (int i = 0; i < tmp.length; i++) 
                str.append(tmp[i]);               

        return new String( str.toString());
    }

    private Result plusMinus(final String exp) {

        Result current = modDiv(exp);

        return null;
    }

    private Result modDiv(final String exp) {
        
        Result current = subNum(exp);
         
        System.out.println("----");
        System.out.println(current.accomulator);
        System.out.println(current.remainder_expression);
        

        return null;
    }

    private Result subNum(final String exp) {

        int idx = 0;

        while (!(Types.signs.contains(exp.charAt(++idx))));

        String arg = exp.substring(0, idx);
        String rem = exp.substring(idx, exp.length());
        
        if(Types.romans.contains(Roman.valueOf(arg)))
            roman_flag = true;
        
        if(roman_flag)
            return new Result(romans_map.get(Roman.valueOf(arg)), rem);
       
        return new Result(Integer.parseInt(arg), rem);
    }
}
    