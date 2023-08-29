package com.example.lurenjiaspring.controller.file;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author THT
 */
@RestController
@RequestMapping("/upload")
public class BigFileUpload {
    //private static final int CHUNK_SIZE = 2 * 1024 * 1024; // 2MB
    private static final int CHUNK_SIZE = 2 * 1024 * 1024; // 2MB

    @PostMapping("/urls")
    public ResponseEntity<Map<String,List<String>>> getUploadUrls(@RequestParam int count) {
        List<String> urls = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            urls.add(String.format("/upload/chunk/%d", i));
        }
        HashMap<String, List<String>> urlsMap = new HashMap<>();
        urlsMap.put("urls", urls);

        return ResponseEntity.ok(urlsMap);
    }

    @PostMapping("/chunk/{index}")
    public ResponseEntity<Void> uploadChunk(
            @PathVariable int index,
            @RequestParam("chunk") MultipartFile file,
            @RequestParam("md5") String md5) throws IOException {
        File tempDir = new File(System.getProperty("java.io.tmpdir")+"\\upload");
        File tempFile = new File(tempDir, md5);
        if (!tempFile.exists()) {
            file.transferTo(tempFile);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/merge")
    public ResponseEntity<Void> mergeChunks(
            @RequestBody Map<String, Object> body) throws IOException {
        //文件碎片
        List<String> md5s = (List<String>) body.get("md5s");
        String filename = (String) body.get("filename");
        File tempDir = new File(System.getProperty("java.io.tmpdir")+"\\upload");
        File targetFile = new File(tempDir, filename);
        try (FileOutputStream fos = new FileOutputStream(targetFile)) {
            for (int i = 0; i < md5s.size(); i++) {
                File chunkFile = new File(tempDir, md5s.get(i));
                try (FileInputStream fis = new FileInputStream(chunkFile)) {
                    byte[] buffer = new byte[CHUNK_SIZE];
                    int len;
                    while ((len = fis.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                    }
                }
                chunkFile.delete();
            }
        }

        return ResponseEntity.ok().build();
    }

}
