package com.akhil.pdf.PdfOperations.split.controller;

import com.akhil.pdf.PdfOperations.split.service.PdfServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * This class serves as a Spring Boot REST controller for splitting PDF files.
 * It exposes a single endpoint for splitting PDFs uploaded by the client.
 *
 * @author akhilkanakendran
 */
@RestController
public class PdfServiceController {

    private final PdfServiceImpl service;

    /**
     * Constructor to inject the PdfServiceImpl dependency.
     *
     * @param service the PdfServiceImpl instance responsible for PDF splitting logic.
     */
    public PdfServiceController(PdfServiceImpl service) {
        this.service = service;
    }

    /**
     * This endpoint accepts a multipart file containing the PDF to be split.
     * It delegates the splitting logic to the injected PdfServiceImpl instance.
     *
     * @param pdfFile the MultipartFile representing the uploaded PDF.
     * @throws IOException if there's an issue processing the uploaded file.
     */
    @PostMapping("/split-pdf")
    void splitPDF(@RequestParam MultipartFile pdfFile) throws IOException {
        service.splitPDF(pdfFile);
    }

}
