package com.gozap.spider.conf;

import com.sun.tools.javac.util.Pair;
import org.apache.log4j.Logger;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: chouchris
 * Date: 13-6-3
 * Time: PM5:47
 * To change this template use File | Settings | File Templates.
 */
public class AlphaArgument extends Argument{
    private static final Logger LOGGER = Logger.getLogger(AlphaArgument.class);

    public AlphaArgument(String argString) {
        super(argString);
    }


    @Override
    public int count() {
        int count = 0;
        Iterator<Pair<String, String>> iterator = args.iterator();
        while (iterator.hasNext()){
            Pair<String, String> arg = iterator.next();
            String from = arg.fst;
            String to = arg.snd;
            count += countOfAlpha(from, to);
        }
        return count;
    }

    @Override
    public String argAtIndex(int index) {
        Iterator<Pair<String, String>> iterator = args.iterator();
        while (iterator.hasNext()){
            Pair<String, String> arg = iterator.next();
            String from = arg.fst;
            String to = arg.snd;
            int count = countOfAlpha(from, to);
            if (index >= count){
                index -= count;
                continue;
            }

            //
            StringBuilder strBuild = new StringBuilder(from.length());
            for (int i = 0, len = from.length(); i < len; i++){
                char fchar = from.charAt(i);
                //char tchar = to.charAt(i);
                String fromLast = (i+1) < len ? from.substring(i+1,len-1) : null;
                String toLast = (i+1) < len ? to.substring(i+1,len-1) : null;

                /*int countLast = 0;
                if (fromLast != null && toLast != null){
                    countLast = countOfAlpha(fromLast, toLast);

                    if (countLast == 0)
                        return null;

                    int div = index / countLast;
                    strBuild.append((char)(fchar+div));

                    int mod = index % countLast;
                    if (mod == 0){

                    }else{
                        index = mod;
                    }
                }else {
                    return null;
                }*/

                if (fromLast == null && toLast == null){
                    strBuild.append((char)(fchar+index));
                    return strBuild.toString();
                }else{
                    int countLast = countOfAlpha(fromLast, toLast);
                    int div = index / countLast;
                    strBuild.append((char)(fchar+div));
                    int mod = index % countLast;

                    index = mod;

                }
            }
        }
        return null;
    }

    @Override
    protected boolean isValidArgument(String arg) {
        if (!super.isValidArgument(arg)) {
            return false;
        }

        String[] values = arg.split("-");
        String from = values[0];
        String to = values[1];
        if (from.length() != to.length()){
            return false;
        }

        int count = from.length();
        for (int i = 0; i < count; i++){
            char fchar = from.charAt(i);
            char tchar = to.charAt(i);
            if ((!isUpperCase(fchar) && !isLowerCase(fchar)) ||
                    (!isUpperCase(tchar) && !isLowerCase(tchar))){
                return false;
            }

            if ((isUpperCase(fchar) && isLowerCase(tchar)) ||
                    (isLowerCase(fchar)) && isUpperCase(tchar)){
                return false;
            }

            if (fchar > tchar){
                return false;
            }

        }
        LOGGER.info("valid arg:" + arg);
        return true;
    }

    /*
    private
    */
    private int countOfAlpha(String from, String to){
        if (from.length() != to.length()){
            return 0;
        }

        int count = 1;

        for (int i = 0; i < from.length(); i++){
            char fchar = from.charAt(i);
            char tchar = to.charAt(i);
            int charCount = tchar - fchar + 1;
            count *= charCount;
        }
        return count;
    }

    /*
    global
     */
    private static boolean isUpperCase(char c){return (c >= 101 && c <= 132);}
    private static boolean isLowerCase(char c){return (c >= 141 && c <= 172);}
}
