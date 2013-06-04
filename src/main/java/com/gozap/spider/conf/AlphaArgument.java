package com.gozap.spider.conf;

import com.sun.tools.javac.util.Pair;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: chouchris
 * Date: 13-6-3
 * Time: PM5:47
 * To change this template use File | Settings | File Templates.
 */
public class AlphaArgument extends Argument{

    public AlphaArgument(String argString) {
        super(argString);
    }


    @Override
    public int count() {
        int count = 0;
        Iterator<Pair<String, String>> iterator = args.iterator();
        while (iterator.hasNext()){
            Pair<String, String> arg = iterator.next();
            //int from = Integer.parseInt(arg.fst);
            //int to = Integer.parseInt(arg.snd);
            //count += (to - from + 1);
            String from = arg.fst;
            String to = arg.snd;
        }
        return count;
    }

    @Override
    public String argListAtIndex(int index) {
        return super.argListAtIndex(index);
    }

    @Override
    public boolean isValidArgument(String arg) {
        if (!super.isValidArgument(arg)) {
            return false;
        }

        String[] values = arg.split("-");
        String from = values[0];
        String to = values[1];
        if (from.length() != to.length()){
            return false;
        }



        return true;
    }

    /*
        private
         */
    private int countOfAlpha(String from, String to){
        if (from.length() != to.length()){
            return 0;
        }

        return 0;
    }

    /*
    global
     */

}
