package faros.ocr;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class OcrmicroservicesApplication {

	@Value("${customconfig.projectName}")
	void setProjectName(String projectName ){
		System.out.println("\n=============================================");
		System.out.println("= Starting Project: "+projectName);
		System.out.println("=============================================\n");

	}

	public static void main(String[] args) {
		SpringApplication.run(OcrmicroservicesApplication.class, args);
	}
}


