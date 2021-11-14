package net.codejava;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "j12345";
		String encoderPassword = encoder.encode(rawPassword);
		
		System.out.println(encoderPassword);
	}

}
