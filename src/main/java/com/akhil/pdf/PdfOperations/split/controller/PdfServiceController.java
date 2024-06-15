package com.akhil.pdf.PdfOperations.split.controller;

import com.akhil.pdf.PdfOperations.split.service.PdfServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.service.annotation.PostExchange;

import java.io.IOException;

@RestController
public class PdfServiceController {

    private final PdfServiceImpl service;

    public PdfServiceController(PdfServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/split-pdf")
    void splitPDF(@RequestParam MultipartFile pdfFile) throws IOException {
        service.splitPdf(pdfFile);
    }

}
