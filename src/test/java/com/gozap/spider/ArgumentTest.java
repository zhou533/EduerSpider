package com.gozap.spider;

import com.gozap.spider.conf.AlphaArgument;
import com.gozap.spider.conf.Argument;
import com.gozap.spider.conf.NumberArgument;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: chouchris
 * Date: 13-6-4
 * Time: PM3:34
 * To change this template use File | Settings | File Templates.
 */
public class ArgumentTest {


    @Test
    public void testArgumentNumber() throws Exception {

        String numArg1 = "12-34";
        NumberArgument arg1 = new NumberArgument(numArg1);

        String numArg2 = "12-34,45-78";
        NumberArgument arg2 = new NumberArgument(numArg2);

        System.out.println("numArg1 '" + numArg1 + "' count:" + arg1.count());
        System.out.println("numArg2 '" + numArg2 + "' count:" + arg2.count());
    }

    @Test
    public void testArgumentAlpha() throws Exception {
        String alpArg1 = "ag";
        AlphaArgument arg1 = new AlphaArgument(alpArg1);

        String alpArg2 = "af-be,ca-de";
        AlphaArgument arg2 = new AlphaArgument(alpArg2);

        String alpArg3 = "abc-bgz,bhz-bja";
        AlphaArgument arg3 = new AlphaArgument(alpArg3);

        System.out.println("alpArg1 '" + alpArg1 + "' count:" + arg1.count());
        System.out.println("alpArg1 index:" + (arg1.count()-1) + " value:" + arg1.argAtIndex(arg1.count()-1));
        System.out.println("alpArg2 '" + alpArg2 + "' count:" + arg2.count());
        System.out.println("alpArg2 index:" + (arg2.count()-1) + " value:" + arg2.argAtIndex(arg2.count()-1));
        System.out.println("alpArg3 '" + alpArg3 + "' count:" + arg3.count());
        System.out.println("alpArg31 index:" + (arg3.count()-1) + " value:" + arg3.argAtIndex(arg3.count()-1));


        int result = AlphaArgument.offset("af", "be");
        System.out.println("AlphaArgument.offset:" + result);
    }
}
