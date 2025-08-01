package com.demo.order_simulate_demo.response;

import lombok.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileResponse {
    private String fileName;
    private String fileType;
    private String fileSize;
    private byte[] fileData;

    public static ResponseEntity<?> file(FileResponse fileResponse) {
        if (fileResponse == null ||
                fileResponse.getFileName() == null ||
                fileResponse.getFileType() == null ||
                fileResponse.getFileData() == null) {
            throw new IllegalArgumentException("fileResponse, fileName, fileType, and fileData must not be null");
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +
                        fileResponse.getFileName() +
                        fileResponse.getFileType()
                )
                .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                .body(new ByteArrayResource(fileResponse.getFileData()));
    }
}
