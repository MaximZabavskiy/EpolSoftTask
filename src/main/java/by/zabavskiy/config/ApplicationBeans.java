package by.zabavskiy.config;

import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class ApplicationBeans {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build();
  }

  @Bean
  public S3Client s3Client(AmazonConfig amazonConfiguration) {
    return S3Client
            .builder()
            .region(Region.of(amazonConfiguration.getRegion()))
            .credentialsProvider(() ->
                    AwsBasicCredentials.create(
                            amazonConfiguration.getAccessKeyId(),
                            amazonConfiguration.getSecretKey()
                    ))
            .build();
  }
}
