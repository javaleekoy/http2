package com.peramdy.ios;

import com.peramdy.ios.builder.ApnsClientBuilder;
import com.peramdy.ios.model.Notification;
import com.peramdy.ios.okhttpClient.ApnsClient;
import okhttp3.*;
import okio.BufferedSink;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by peramdy on 2017/7/18.
 */
public class MyTest {


    @Test
    public void testTwo() throws Exception {
        String token = "765803bd54428cb6849d708afb325ee6cac278736d090763e952d674b24c0d0b";
        InputStream in = new FileInputStream("D:\\program\\apns\\certification\\prod.p12");
        ApnsClientBuilder builder = new ApnsClientBuilder(in,"123456","com.zhibaicc.PushDemo");
        builder.isProduction(true);
        ApnsClient apnsClient = builder.build();
        Notification notification = new Notification.Builder(token)
                .alertBody("你好")
                .alertTitle("nihao")
                .badge(2)
                .customField("type",1111)
                .build();
        System.out.println(notification);
        apnsClient.push(notification);

    }


    @Test
    public void testThree() throws Exception {
        String token = "765803bd54428cb6849d708afb325ee6cac278736d090763e952d674b24c0d0b";
        String apnsAuthKey="MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQgW+6Fh6mCdVaSXj6X33idTW3qxGFn5XTdjny50BRKpwmgCgYIKoZIzj0DAQehRANCAARbkTCjG3zhjZImGj54rYC+SmoiFCr3dgPJ6MD160rqbJzhgqmYNAZo2P4cmvtMuan/WycpAoM/0S4G5G66s80Q";
        String teamId="32VNW8Q34V";
        String keyId="DE8Q8YQC49";
        ApnsClientBuilder builder = new ApnsClientBuilder(apnsAuthKey,teamId,keyId,"com.zhibaicc.PushDemo");
        builder.isProduction(true);
        builder.isAsynchronous(true);
        ApnsClient apnsClient = builder.build();
        Notification notification = new Notification.Builder(token)
                .alertBody("测试！")
                .alertTitle("你好")
                .badge(1)
                .customField("type",1)
                .build();
        apnsClient.push(notification);

    }

    @Test
    public void testOne(){
        OkHttpClient client =new OkHttpClient();
        Request request=new Request.Builder()
                .url("http://192.168.10.131:8210/msg1/send")
                .post(new RequestBody() {
                    @Override
                    public MediaType contentType() {
                        return MediaType.parse("application/json");
                    }

                    @Override
                    public void writeTo(BufferedSink sink) throws IOException {
                        sink.writeUtf8("{\"userId\":110,\"title\":\"hello boot!\",\"body\":\"你好吗？\",\"type\":1}");
                    }
                })
                .build();


        try {
            client.newCall(request).enqueue(new Callback() {
                public void onFailure(Call call, IOException e) {
                    System.out.println("fail");
                }

                public void onResponse(Call call, Response response) throws IOException {
                    System.out.println("success");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}
