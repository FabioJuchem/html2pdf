package com.poc.converter.resource;

import com.poc.converter.service.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class Html2PdfResource {

    private Converter converter;

    private Logger logger = LoggerFactory.getLogger(Html2PdfResource.class);

    @Autowired
    public Html2PdfResource(Converter converter) {
        this.converter = converter;
    }

    @GetMapping("api/getpdf")
    public @ResponseBody ResponseEntity<byte[]> getPdf() throws Exception {
        logger.info("pdf convertido");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        converter.convert();
        byte[] content = Files.readAllBytes(Paths.get("C:\\Projetos\\POCS\\poc-html-pdf\\hello.pdf"));

        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(content, headers, HttpStatus.OK);

        return response;
    }
}
