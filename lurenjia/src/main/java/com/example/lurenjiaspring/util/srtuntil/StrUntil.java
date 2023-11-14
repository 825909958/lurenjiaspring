package com.example.lurenjiaspring.util.srtuntil;

import cn.hutool.http.HtmlUtil;
import org.junit.Test;

public class StrUntil {
    @Test
    public void test1() {
        String escape = "&lt;html&gt;&lt;body&gt;123&#039;123&#039;&lt;/body&gt;&lt;/html&gt;";
// 结果为：<html><body>123'123'</body></html>
        String unescape = HtmlUtil.unescape(escape);
        System.out.println("unescape = " + unescape);
        String escape1 = "<html><body>123'123'</body></html>";
        String escape2 = HtmlUtil.escape(escape1);
        System.out.println("escape2 = " + escape2);
    }
}
