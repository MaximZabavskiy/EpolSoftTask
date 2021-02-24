package by.zabavskiy.controller.file;

public interface AmazonUploadFileService {

    String uploadFile(byte[] fileBytes, Long reportId);
}
