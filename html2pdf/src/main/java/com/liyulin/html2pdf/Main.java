package com.liyulin.html2pdf;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.DocumentException;

public class Main {
	private static final String DEST = "target/bill_test.pdf";
	private static final String HTML = "template/template2.html";

	public static void main(String[] args) throws IOException, DocumentException, com.lowagie.text.DocumentException {
		Map<String, Object> data = new HashMap<>();
		data.put("name", "鲁家宁");
		String content = JavaToPdfHtmlFreeMarker.freeMarkerRender(data, HTML);
		JavaToPdfHtmlFreeMarker.createPdf(content, DEST);
	}
}
