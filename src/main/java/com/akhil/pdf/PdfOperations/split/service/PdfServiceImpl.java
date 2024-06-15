package com.akhil.pdf.PdfOperations.split.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class PdfServiceImpl {

    public void splitPdf(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty() && file.getContentType().equals("application/pdf")) {
            try (PDDocument document = Loader.loadPDF(file.getBytes())) {
                //int totalPages = document.getNumberOfPages();

                int currentPage = 1;
                int splitIndex = 1;
                PDDocument newDocument = new PDDocument();

                for (PDPage page : document.getPages()) {
                    if (currentPage > 7) {
                        // Close previous PDF and create new one (performance improvement)
                        if (newDocument.getNumberOfPages() > 0) {
                            newDocument.save("split_pdf_" + splitIndex + ".pdf");
                            newDocument.close();
                        }
                        newDocument = new PDDocument();
                        currentPage = 1;
                        splitIndex++;
                    }

                    newDocument.addPage(page); // Use copyAsNewPage for efficiency
                    currentPage++;
                }

                // Save the last split PDF if pages are less than 7
                if (currentPage > 1) {
                    newDocument.save("split_pdf_" + splitIndex + ".pdf");
                }
            }
        } else {
            throw new IllegalArgumentException("Please upload a valid PDF file.");
        }
    }

}
