package com.akhil.pdf.PdfOperations.split;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PdfOperations {

    void splitPDF(MultipartFile file) throws IOException;
}

