package by.zabavskiy.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties("amazon.files")
public class AmazonConfig {

    private String s3;

    private String accessKeyId;

    private String secretKey;

    private String bucket;

    private String serverUrl;

    private String reportFolder;

    private String region;
}
