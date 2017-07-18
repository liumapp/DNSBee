package com.liumapp.DNSBee.util;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by liumapp on 7/18/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
public class UserZonesUtilsTest {

    @Ignore
    @Test
    public void test() throws IOException {
        String text = "#192.168.9.109\twww.dianping.com\t#test\n" +
                "#127.0.0.1\twww.dianping.com\t#测试服务器\n" +
                "#192.168.9.110\twww.dianping.com";
        String json = UserZonesUtils.toJson(text);
        String jsonSupposed = "[{\"domain\":\"www.dianping.com\",\"config\":[{\"domain\":\"www.dianping.com\",\"active\":false,\"ip\":\"192.168.9.109\",\"comment\":\"test\"},{\"domain\":\"www.dianping.com\",\"active\":false,\"ip\":\"127.0.0.1\",\"comment\":\"测试服务器\"},{\"domain\":\"www.dianping.com\",\"active\":false,\"ip\":\"192.168.9.110\"}]}]";
        Assert.assertEquals(jsonSupposed,json);
        String text2 = UserZonesUtils.fromJson(json);
        Assert.assertEquals(text2,text);
    }

    @Ignore
    @Test
    public void testMerge() {
        String textA = "#127.0.0.1\twww.dianping.com\t#test\n";
        String textB = "#127.0.0.1\twww.dianping.com\n";
        String merge = UserZonesUtils.merge(textA, textB);
        Assert.assertEquals("#127.0.0.1\twww.dianping.com\t#test",merge);
        merge = UserZonesUtils.merge(textB, textA);
        Assert.assertEquals("#127.0.0.1\twww.dianping.com\t#test",merge);
    }
}
