package com.akhil.pdf.PdfOperations.split.service;

import com.akhil.pdf.PdfOperations.split.PdfOperations;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

/**
 * This service class provides functionalities for splitting PDF files.
 * It's intended to be used internally by other components and is annotated with
 *
 * @author akhilkanakendran
 */
@Component
public class PdfServiceImpl implements PdfOperations {

    /**
     * Splits an uploaded PDF file into multiple smaller PDFs based on a page limit.
     * This method expects a valid MultipartFile containing the PDF data.
     *
     * @param file the MultipartFile representing the uploaded PDF.
     * @throws IOException if there's an issue processing or saving the PDF files.
     * @throws IllegalArgumentException if the uploaded file is empty or not a PDF.
     */
    @Override
    public void splitPDF(MultipartFile file) throws IOException {
        if (!file.isEmpty() && Objects.equals(file.getContentType(), "application/pdf")) {
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
