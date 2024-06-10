package com.snipe.myTuitionCenter.admin.config;
import org.springframework.stereotype.Component;

@Component
public class CustomIdGenerator {

    public String generateId(String prefix) {
		/* long timestamp = System.currentTimeMillis(); */
        int randomNumber = (int) (Math.random() * 100000);
        return prefix + "-" + randomNumber;
    }
}
