package server.PAC_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@EnableScheduling
@SpringBootApplication
public class PacProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PacProjectApplication.class, args);
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}

}
