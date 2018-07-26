package car.demo.server;

import car.demo.util.Base64Util;
import car.demo.util.FileUtil;
import car.demo.util.HttpUtil;
import car.demo.model.ImageAPI;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

@Service
public class CarModelDemo {

    /**
     * 车型识别Demo
     * @param imagePath
     * @param accessToken
     * @return 字符串
     * @throws Exception
     */
    public static String getCarModeResult(String imagePath,String accessToken) throws Exception{
        byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
        String param = "image=" + URLEncoder.encode(imgStr,"UTF-8");
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
        String result = HttpUtil.post(ImageAPI.CAR_API, accessToken, param);
        System.out.println(result);
        return result;
    }
}
