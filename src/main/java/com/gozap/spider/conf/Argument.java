package com.gozap.spider.conf;

import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chouchris
 * Date: 13-6-3
 * Time: PM5:39
 * To change this template use File | Settings | File Templates.
 */
public class Argument {

    /*

     */
    protected List<Pair<String, String>> args;

    public Argument(String argString) throws Exception{
        this.args = parseArgString(argString);
    }

    public int count(){
        return 0;
    }
    public String argAtIndex(int index){
        return null;
    }

    protected boolean isValidArgument(String arg){
        String[] values = arg.split("-");
        if (values.length > 2 || values.length == 0){
            return false;
        }
        return true;
    }

    private List<Pair<String, String>> parseArgString(String arg) throws Exception{
        List<Pair<String, String>> result = new ArrayList<Pair<String, String>>();
        String[] args = arg.split(",");
        for (int i = 0; i < args.length; i++){
            if (!isValidArgument(args[i])){
                //
                //throw new Exception("invalid argument format..");
            }
            String[] values = args[i].split("-");
            //if (values.length > 2 || values.length == 0){
            //    return null;
            //}

            String from = values[0];
            String to = (values.length == 1) ? values[0] : values[1];
            result.add(new Pair<String, String>(from, to));
        }
        return result;
    }
}
