package pwr.po.webcrawler.service.auth;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Service
public class TokenMd5Service {
    /**
     * Encrypts input long and randomly generated long number in MD5 to create user token
     *
     * @param input long value to initialize token generation
     *
     * @return md5 encrypted token
     */
    public String getMD5(Long input) {
        //Check if input is null
        if (input == null) {
            return null;
        }

        //Add random number to id
        Random rand = new Random();
        input += rand.nextLong();

        //Try to create MD5 digester
        MessageDigest message = null;
        try {
            message = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //Convert input to String in order to put into byte array & use MD5 digester
        String value = String.valueOf(input);
        byte buffer[] = value.getBytes();

        for (int count = 0; count < value.length(); count++) {
            message.update(buffer, 0, count);
        }
        byte bufferDigest[] = message.digest();
        BigInteger bi = new BigInteger(bufferDigest);

        //Set radix to hex
        return (bi.toString(16));
    }
}

