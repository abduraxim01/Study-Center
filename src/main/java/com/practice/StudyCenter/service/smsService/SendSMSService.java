package com.practice.StudyCenter.service.smsService;

import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SendSMSService {

    final private Logger logger = LogManager.getLogger(SendSMSService.class);

    @Autowired
    private SMSTokenSchedular smsTokenSchedular;

    public void sendSMSForAuth(String phoneNumber) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("mobile_phone", "998" + phoneNumber)
                    .addFormDataPart("message", "This is test from Eskiz")
                    .addFormDataPart("from", "4546")
                    .addFormDataPart("callback_url", "https://abduraxim.uz")
                    .build();
            Request request = new Request.Builder()
                    .url("https://notify.eskiz.uz/api/message/sms/send")
                    .method("POST", body)
                    .addHeader("Authorization", "Bearer " + smsTokenSchedular.SMS_TOKEN)
                    .build();
            Response response = client.newCall(request).execute();
            logger.info(response.body().string());
        } catch (IOException exception) {
            logger.error("SMS jo'natilmadi", exception);
        }
    }
}
