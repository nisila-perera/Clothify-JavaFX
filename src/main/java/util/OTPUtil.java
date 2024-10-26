package util;

import java.util.Random;

public class OTPUtil {
    public static String generateOTP() {
        Random random = new Random();
        return String.valueOf(100000 + random.nextInt(900000));
    }

    public static String hashPassword(String plainPassword) {
        return new Encryptor().encryptString(plainPassword);
    }
}
