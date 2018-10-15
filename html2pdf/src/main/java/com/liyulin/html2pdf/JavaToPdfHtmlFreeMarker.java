package com.liyulin.html2pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

/**
 * 通过html模板，生成pdf
 *
 * @author liyulin
 * @date 2018年10月15日下午11:53:29
 */
@Slf4j
public class JavaToPdfHtmlFreeMarker {

	private static final String DEST = "target/bill_test.pdf";
	private static final String HTML = "template2.html";
	private static final String FONT = "simhei.ttf";

	private static Configuration freemarkerCfg = null;

	static {
		freemarkerCfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		// freemarker的模板目录
		try {
			URL url = JavaToPdfHtmlFreeMarker.class.getClassLoader().getResource("");
			log.info(url.getPath());
			File dir = new File(url.getPath());
			freemarkerCfg.setDirectoryForTemplateLoading(dir);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Test
	public void testHtml2Pdf() throws IOException, DocumentException, com.lowagie.text.DocumentException {
		Map<String, Object> data = new HashMap<>();
		data.put("name", "鲁家宁");
		String content = freeMarkerRender(data, HTML);
		createPdf(content, DEST);
	}

	/**
	 * freemarker渲染html
	 */
	public static String freeMarkerRender(Map<String, Object> data, String htmlTmp) {
		try (Writer out = new StringWriter();) {
			// 获取模板,并设置编码方式
			Template template = freemarkerCfg.getTemplate(htmlTmp);
			// 合并数据模型与模板
			template.process(data, out); // 将合并后的数据和模板写入到流中，这里使用的字符流
			out.flush();
			return out.toString();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	public static void createPdf(String content, String dest)
			throws IOException, DocumentException, com.lowagie.text.DocumentException {
		ITextRenderer render = new ITextRenderer();
		ITextFontResolver fontResolver = render.getFontResolver();
		fontResolver.addFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		// 解析html生成pdf
		render.setDocumentFromString(content);
		// 解决图片相对路径的问题
		// render.getSharedContext().setBaseURL("file://e:");
		render.layout();
		render.createPDF(new FileOutputStream(dest));
	}
}