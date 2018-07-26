package car.demo;

import car.demo.util.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    /**
     * 获取百度AI的AccessToken
     * @throws Exception
     */
    @Test
    public static void main(String[] args) throws Exception {

        // API Key
        String APIKEY = "V0exFs7rP9zjDxtKSXi6Mncf";
        //Secret Key
        String SECRETKEY = "mg97UOGDC6pWSHmUG3gTIGBVKvQjbs3N";
        String url = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=APIKEY&client_secret=SECRETKEY";
        String requsetURL = url.replaceAll("APIKEY",APIKEY).replaceAll("SECRETKEY",SECRETKEY);
        System.out.println(requsetURL);
        String jsonObject = HttpUtil.postToken(requsetURL,"");
        System.err.println(jsonObject);
    }

    @Test
    public void test()
    {

    }

}
