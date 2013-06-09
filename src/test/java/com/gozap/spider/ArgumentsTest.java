package com.gozap.spider;

import com.gozap.spider.conf.AlphaArgument;
import com.gozap.spider.conf.Arguments;
import com.gozap.spider.conf.NumberArgument;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: chouchris
 * Date: 13-6-6
 * Time: PM12:55
 * To change this template use File | Settings | File Templates.
 */
public class ArgumentsTest {
    @Test
    public void testArguments() throws Exception {
        try {
            Arguments arguments = new Arguments();
            AlphaArgument alphaArgument = new AlphaArgument("abc-bgz,bhz-bja");

            NumberArgument numberArgument = new NumberArgument("1-10");
            arguments.addArgument(numberArgument);
            arguments.addArgument(alphaArgument);

            System.out.println("arguments count:" + arguments.totalCount());
            System.out.println("arguments index:" + (arguments.totalCount()-1) + " value:" + arguments.argListAtIndex(arguments.totalCount()-1));

        }finally {

        }
    }
}
