package com.snhu.sslserver;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}

@RestController
class ServerController{
	//FIXME:  Add hash function to return the checksum value for the data string that should contain your name.    
    
	@RequestMapping("/hash")
    
    public static String bytesToHex(byte[] bytes) {
    	StringBuilder builder = new StringBuilder();
    	for (byte val : bytes) {
    		builder.append(String.format("%02x", val&0xff));
    	}
    	return builder.toString();
    }
    
    private String createHash(String message) {
    	try {
			MessageDigest SHAdigest = MessageDigest.getInstance("SHA-256");
			byte[] SHAbyte = SHAdigest.digest();
			return bytesToHex(SHAbyte);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
    	return message;
    }
    
    @ResponseBody
    public String myHash(){
    	String data = "Hello Seth Hamrick!";
    	String hash = createHash(data);
        return "<p>data:"+data+hash;
    }
}