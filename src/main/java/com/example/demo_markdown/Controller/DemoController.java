package com.example.demo_markdown.Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo_markdown.Form.SpecSheetStep1Form;
import com.example.demo_markdown.Form.SpecSheetStep2Form;
import com.example.demo_markdown.Form.SpecSheetStep3Form;
import com.example.demo_markdown.Service.DemoMarkdownService;

@Controller
@RequestMapping("")
public class DemoController {

    @ModelAttribute
    public SpecSheetStep1Form setUpASpecSheetStep1Form() {
        return new SpecSheetStep1Form();
    }

    @ModelAttribute
    public SpecSheetStep2Form setUpASpecSheetStep2Form() {
        return new SpecSheetStep2Form();
    }

    @Autowired
    public DemoMarkdownService demoMarkdownService;


    @RequestMapping("/")
    public String form1() {
        return "markdown_1";
    }

    /**
     * 1ページ目の内容をDBにインサート
     * @param form
     * @return markdown_2.html
     */
    @PostMapping("/submit/first")
    public String specSheetStep1(@ModelAttribute SpecSheetStep1Form form, Model model) {
        Integer specSheetStep1Id = demoMarkdownService.saveStep1(form);
        model.addAttribute("specSheetStep1Id", specSheetStep1Id);
        return "markdown_2.html";
    }

    /**
     * 2ページ目の内容をDBにインサート
     * @param form
     * @return markdown_3.html
     */
    @PostMapping("/submit/second")
    public String specSheetStep2(@ModelAttribute SpecSheetStep2Form form, Integer specSheetStep1Id, Model model) {
        Integer specSheetStep2Id = demoMarkdownService.saveStep2(form, specSheetStep1Id);
        model.addAttribute("specSheetStep1Id", specSheetStep1Id);
        model.addAttribute("specSheetStep2Id", specSheetStep2Id);
        return "markdown_3.html";
    }

    /**
     * EngineerFormでバインドして内容をMarkDown書式で出力を行う
     * @param form
     * @return response
     */
    @PostMapping("/submit/third")
    public ResponseEntity<Resource> generateAndDownloadMarkdown(@ModelAttribute SpecSheetStep3Form form3, Integer specSheetStep1Id, Integer specSheetStep2Id) {
        // スペックシート1ページ目取得
        SpecSheetStep1Form form1 = demoMarkdownService.findBySpecSheet1Id(specSheetStep1Id);
        // スペックシート2ページ目取得
        SpecSheetStep2Form form2 = demoMarkdownService.findBySpecSheet2Id(specSheetStep2Id);

        // Markdown生成
        StringBuilder markdown = demoMarkdownService.markdownGeneraterConcat(form1, form2, form3);

        // Markdownファイルに保存
        Path filePath = Paths.get("spec-sheet.md");
        try (OutputStreamWriter writer = new OutputStreamWriter(
                Files.newOutputStream(filePath), StandardCharsets.UTF_8)) {
            writer.write(markdown.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .header(HttpHeaders.CONTENT_TYPE, "text/plain")
                    .body(null);
        }

        // ファイルをリソースとして読み込む
        Resource resource = new FileSystemResource(filePath);

        // HTTPレスポンスを構築して返却
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=spec-sheet.md")
                .body(resource);
    }


    
}
