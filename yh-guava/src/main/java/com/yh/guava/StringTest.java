package com.yh.guava;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.Arrays;

/**
 *
 *  https://www.cnblogs.com/wihainan/p/7091775.html
 * @author
 * @create 2017-12-15 16:16
 **/
public class StringTest {

    public static void main(String args[]){
        StringTest tester = new StringTest();
//        tester.testJoiner();

        tester.testSplitter();

//        tester.testCharMatcher();

//        tester.testCaseFormat();
    }

    @Test
    public void testJoiner(){
        System.out.println(Joiner.on(",")
                .skipNulls()
                .join(Arrays.asList(1,2,3,4,5,null,6)));
    }

    private void testSplitter(){
        System.out.println(Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("the ,quick, , brown         , fox,              jumps, over, the, lazy, little dog."));
    }


    private void testCharMatcher(){
        System.out.println(CharMatcher.digit().retainFrom("mahesh123")); // only the digits
        System.out.println(CharMatcher.whitespace().trimAndCollapseFrom("  w   Mah     esh     Parashar ", ' '));
        // trim whitespace at ends, and replace/collapse whitespace into single spaces
        System.out.println(CharMatcher.javaDigit().replaceFrom("maheshg23434ggggggggggf123", "*")); // star out all digits
        System.out.println(CharMatcher.javaDigit().or(CharMatcher.javaLowerCase()).retainFrom("mSDsdfagsdGGhesh123FD"));
        // eliminate all characters that aren't digits or lowercase
    }


    private void testCaseFormat(){
        String data = "test_data";
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data"));
    }
}
