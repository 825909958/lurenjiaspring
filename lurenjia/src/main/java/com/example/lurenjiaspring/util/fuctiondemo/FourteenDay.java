package com.example.lurenjiaspring.util.Apil;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author THT
 */
public class FourteenDay {
    public void main1() throws Exception {
        StringValid stringValid = new StringValid();
        stringValid.valid("a",".");
    }

    public static void main(String[] args) throws Exception {
        FourteenDay fourteenDay = new FourteenDay();
        fourteenDay.main1();
    }

    public class StringValid extends BasicValid {
        String regex = ".";

        @Override
        public void valid(String value, String... regex) throws Exception {
            if (regex != null && regex.length != 0) {
                if (!Pattern.compile(regex[0]).matcher(value).matches()) {
                    throw new Exception("外部正则");
                }
            }
            if (!Pattern.compile(this.regex).matcher(value).matches()) {
                throw new Exception("内部正则");
            }
        }


    }

    abstract class BasicValid {
        Map MAP = new HashMap<>();

        {

        }

        abstract void valid(String value, String... regex) throws Exception;
    }
}

