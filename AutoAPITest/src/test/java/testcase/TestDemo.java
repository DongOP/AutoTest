package testcase;

import org.tcl.autotest.utils.Constants;
import org.tcl.autotest.utils.OkHttpUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestDemo {

    @Test
    public void testGet(){
        Reporter.log("用例开始执行");
        OkHttpUtils.getInstance().doGetAsyn(Constants.testGetUrl, new OkHttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                Reporter.log("接口返回数据=" + result);
                Assert.assertNotNull(result);
            }

            @Override
            public void onRequestWithResponse(byte[] response) {
                System.out.println("response" + response);
            }

            @Override
            public void onRequestFail(String reason) {
                System.out.println("reason" + reason);
            }
        });
    }

    @Test
    public void test1() {
        System.out.println("test1 Begin!");
        int a = 1;
        Reporter.log("校验数据 a=" + a);
        Assert.assertEquals(a, 2);
    }

}
