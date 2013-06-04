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
        //String alpArg1 = "a-g";
        //AlphaArgument arg1 = new AlphaArgument(alpArg1);

        //System.out.println("alpArg1 '" + alpArg1 + "' count:" + arg1.count());
    }
}
