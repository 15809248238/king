package car.demo.controller;

import car.demo.server.CarModelDemo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@RestController
@RequestMapping("/car")
public class TestController {

    @Autowired
    private CarModelDemo carModelDemo;

    //图片路径
    private String imagePath = null;

    private String AccessToken = "24.c40ca995c3cd58fe933a5d535d44a5d1.2592000.1535114547.282335-11587794";

    @RequestMapping("/uploadfile")
    @ResponseBody
    public String getCarModel(@RequestParam("file")MultipartFile file, HttpServletRequest request, HttpServletResponse response)
    {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();

        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
        try {

            imagePath = filePath + fileName;

            uploadFile(file.getBytes(), filePath, fileName);
            String result = carModelDemo.getCarModeResult(imagePath, AccessToken);

            JSONObject jsonObject = JSONObject.parseObject(result);

            JSONArray jsonArray = (JSONArray)jsonObject.get("result");

            JSONObject jsonObject1 = (JSONObject)jsonArray.get(0);

            String score = jsonObject1.get("score").toString().substring(2,4);
            String name = jsonObject1.get("name").toString();

            return "存在百分之"+score+"可能性为"+name;

        } catch (Exception e) {

        }
        return "fail";
    }

    //这个尚未测试
    @RequestMapping("/uploadfiles")
    public String getManyCarModel(HttpServletRequest request,HttpServletResponse response)
    {
        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");

        List<MultipartFile> imagesList = ((MultipartHttpServletRequest)request).getFiles("file");
        MultipartFile file = null;

        try {

            String msg = null;

            for(int i=0;i<imagesList.size();i++)
            {
                file = imagesList.get(i);
                String filename = file.getOriginalFilename();
                imagePath = filePath + filename;
                uploadFile(file.getBytes(), filePath,filename);

                String result = carModelDemo.getCarModeResult(imagePath, AccessToken);

                JSONObject jsonObject = JSONObject.parseObject(result);

                JSONArray jsonArray = (JSONArray)jsonObject.get("result");

                JSONObject jsonObject1 = (JSONObject)jsonArray.get(0);

                String score = jsonObject1.get("score").toString().substring(2,4);
                String name = jsonObject1.get("name").toString();

                msg += "图片"+(i+1)+"存在百分之"+score+"可能性为"+name+"\n";

            }

            return msg;

        }catch (Exception e)
        {

        }
        return "fail";
    }




    //文件上传
    public void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

}
