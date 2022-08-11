package testcase;

import com.alibaba.fastjson.JSONObject;
import org.tcl.autotest.utils.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestDemo {

    @Test(description = "接口测试2")
    public void test1() {
        System.out.println("test1 Begin!");
        int a = 1;
        Reporter.log("校验数据 a=" + a);
        Assert.assertEquals(a, 2);
    }

    @Test(description = "遍历检查Map不为空")
    public void checkMapNotNull() {
        Map<Integer, Object> msgs = ExcelUtils.readExcel(1, 2);
        msgs = ExcelUtils.readExcel(1, 2);
        for (Map.Entry<Integer, Object> entry : msgs.entrySet()) {
            Reporter.log("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            Assert.assertNotNull(entry.getValue());
        }
    }

    @Test(description = "doGet 请求测试")
    public void testHttpGet() {
        Map<Integer, Object> excelMap = ExcelUtils.readExcel(1, 2);
        String requestUrl = excelMap.get(Constants.EXCEL_REQUEST_URL_INDEX).toString();
        Reporter.log("测试URL（来自excel）=" + requestUrl);
        HttpUtils.getInstance().doGet(requestUrl, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                Map<Integer, Object> excelMap = ExcelUtils.readExcel(2, 2);
                String responseCodeKey = excelMap.get(Constants.EXCEL_RESPONSE_CODE_KEY).toString();
                int responseCodeValue = Integer.parseInt(excelMap.get(Constants.EXCEL_RESPONSE_CODE_VALUE).toString());
                boolean okFlag = CheckResponseUtils.checkResponseData(result, responseCodeKey, responseCodeValue);
                Reporter.log("HttpUtils responseCodeKey=" + responseCodeKey + ", 校验 responseCodeValue=" + responseCodeValue);
                Reporter.log("HttpUtils测试 okFlag=" + okFlag + ", ---result=" + result);
                Assert.assertTrue(okFlag);
            }

            @Override
            public void onRequestFail(String reason) {
                System.out.println("ERROR reason=" + reason);
            }
        });
    }

    @Test(description = "doPost 请求测试")
    public void testHttpPost() {
        String url = "http://localhost:8082/api/core/login";
        JSONObject json = new JSONObject();
        json.put("key", "==g43sEvsUcbcunFv3mHkIzlHO4iiUIT R7WwXuSVKTK0yugJnZSlr6qNbxsL8OqCUAFyCDCoRKQ882m6cTTi0q9uCJsq JJvxS+8mZVRP/7lWfEVt8/N9mKplUA68SWJEPSXyz4MDeFam766KEyvqZ99d");
        HttpUtils.getInstance().doPost(url, json.toJSONString(), new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                // 检查返回数据
                Reporter.log("testHttpPost 测试 result=" + result);
            }

            @Override
            public void onRequestFail(String reason) {

            }
        });

    }

    @Test(description = "doPostForm 请求测试")
    public void testHttpPostMap() {
        String url = "http://localhost:8082/api/core/login";
        Map<String, String> map = new HashMap<>();
        map.put("name", "测试表单请求");
        HttpUtils.getInstance().doPostForm(url, map, new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                // 检查返回数据
                Reporter.log("testHttpPostMap 测试 result=" + result);
            }

            @Override
            public void onRequestFail(String reason) {

            }
        });

    }

    @Test(description = "请求测试(okhttp)，跳过不执行", enabled = false)
    public void testOkHttpGet() {
        Reporter.log("用例开始执行");
        Map<Integer, Object> excelMap = ExcelUtils.readExcel(1, 2);
        String requestUrl = excelMap.get(Constants.EXCEL_REQUEST_URL_INDEX).toString();
        String requestType = excelMap.get(Constants.EXCEL_REQUEST_TYPE_INDEX).toString();
        Reporter.log("请求方式（来自excel）=" + requestType + "，测试URL（来自excel）=" + requestUrl);
        OkHttpUtils.getInstance().doGetAsyn(requestUrl, new OkHttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                Reporter.log("接口返回数据=" + result);
                boolean okFlag = CheckResponseUtils.checkResponseData(result, "err_no", 0);
                System.out.println("----------OkHttpUtils okFlag=" + okFlag);
                Assert.assertEquals(1, 2);
                Assert.assertFalse(okFlag);
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

    @Test(timeOut = 3000)
    public void testTimeOut() {
        try {
            Thread.sleep(3100);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
    }

    @Test(dataProvider = "provideNumbers")
    public void test(int number, int expected) {
        Assert.assertEquals(number + 10, expected);
    }

    @DataProvider(name = "provideNumbers")
    public Object[][] provideData() {
        return new Object[][]{{10, 20}, {100, 110}, {200, 210}};
    }

}
