package com.genspark.backend.Controller;

import com.genspark.backend.Entity.UserAccount;
import com.genspark.backend.Service.QRCodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@Controller
public class WebController {

    final Logger logger = LoggerFactory.getLogger(com.genspark.backend.Controller.Controller.class);

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    // 2fa
    // http://localhost:8080/generateQRCode/code/350/350
    @GetMapping(value = "/generateQRCode/{userID}/{width}/{height}")
    public ResponseEntity<byte[]> generateQRCode(
            @PathVariable("codeText") String codeText,
            @PathVariable("width") Integer width,
            @PathVariable("height") Integer height)
            throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(QRCodeGenerator.getQRCodeImage(codeText, width, height));
    }
    /*// http://localhost:8080/generateAndDownloadQRCode/code/350/350
    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";
    @GetMapping(value = "/generateAndDownloadQRCode/{codeText}/{width}/{height}")
    public void download(
            @PathVariable("codeText") String codeText,
            @PathVariable("width") Integer width,
            @PathVariable("height") Integer height)
            throws Exception {
        QRCodeGenerator.generateQRCodeImage(codeText, width, height, QR_CODE_IMAGE_PATH);
    }*/
}

