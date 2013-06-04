package com.gozap.spider.conf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chouchris
 * Date: 13-6-4
 * Time: PM2:44
 * To change this template use File | Settings | File Templates.
 */
public class Arguments {
    private List<Argument> argList = new ArrayList<Argument>();

    public void addArgument(Argument arg){
        argList.add(arg);
    }

    public int totalCount(){
        int count = 0;
        Iterator<Argument> iterator = argList.iterator();
        while (iterator.hasNext()){
            Argument arg = iterator.next();
            if (count == 0){
                count = arg.count();
            }else{
                count *= arg.count();
            }
        }
        return count;
    }

    public List<String> argListatindex(int index){
        List<String> result = new ArrayList<String>();
        Iterator<Argument> iterator = argList.iterator();
        while (iterator.hasNext()){
            Argument arg = iterator.next();
            String argStr = arg.argAtIndex(index);
            result.add(argStr);
        }

        return result;
    }
}
