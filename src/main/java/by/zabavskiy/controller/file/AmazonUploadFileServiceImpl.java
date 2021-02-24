package by.zabavskiy.controller.file;

import by.zabavskiy.config.AmazonConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AmazonUploadFileServiceImpl implements AmazonUploadFileService {

    private final S3Client s3Client;
    private final AmazonConfig amazonS3Config;

    @Override
    public String uploadFile(byte[] fileBytes, Long reportId) {

        String imageUUID = UUID.randomUUID().toString();

        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(amazonS3Config.getBucket())
                        .contentType("xlsx")
                        .contentLength((long) fileBytes.length)
                        .acl(ObjectCannedACL.PUBLIC_READ)
                        .key(String.format("%s/%s/%s.xlsx", amazonS3Config.getReportFolder(), reportId, imageUUID))
                        .build(),
                RequestBody.fromBytes(fileBytes)
        );

        return String.format("%s/%s/%s/%s/%s.xlsx",
                amazonS3Config.getServerUrl(),
                amazonS3Config.getBucket(),
                amazonS3Config.getReportFolder(),
                reportId, imageUUID);
    }
}
