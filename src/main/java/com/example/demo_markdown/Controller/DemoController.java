package com.example.demo_markdown.Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.example.demo_markdown.Domain.SpecSheetStep2;
import com.example.demo_markdown.Form.SpecSheetStep1Form;
import com.example.demo_markdown.Form.SpecSheetStep2Form;
import com.example.demo_markdown.Form.SpecSheetStep3Form;
import com.example.demo_markdown.Service.DemoMarkdownService;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    public String form1(Integer specSheetStep1Id, Integer specSheetStep2Id, Model model) throws Exception {
        // 初回遷移に関してはmarkdown1にフォワード
        if (specSheetStep1Id == null) {
            return "markdown_1";
        }

        // 2ページから1ページ目へ戻る際は下記の処理
        SpecSheetStep1Form form1 = demoMarkdownService.findBySpecSheet1Id(specSheetStep1Id);
        model.addAttribute("specSheetStep1Form", form1);
        model.addAttribute("specSheetStep1Id", specSheetStep1Id);
        model.addAttribute("specSheetStep2Id", specSheetStep2Id);

        // titles と contents のペアリストを作成
        List<Map<String, String>> pairedList = new ArrayList<>();
        if (form1.getTitles() != null && form1.getContents() != null) {
            for (int i = 0; i < form1.getTitles().size(); i++) {
                Map<String, String> pair = new HashMap<>();
                pair.put("title", form1.getTitles().get(i));
                pair.put("content", form1.getContents().get(i));
                pairedList.add(pair);
            }
        }
        model.addAttribute("pairedList", pairedList);

        // スキル（言語・フレームワーク・ライブラリ・OS・ソフトウェア）はMapで対応させてJSONにして渡す
        List<String> selectedLanguages = form1.getLanguages();
        List<String> selectedFrameworks = form1.getFrameworks();
        List<String> selectedLibraries = form1.getLibraries();
        List<String> selectedOsSoftware = form1.getOsSoftware();

        // JSONデータを生成
        Map<String, Object> initialData = new HashMap<>();
        initialData.put("languages", selectedLanguages);
        initialData.put("frameworks", selectedFrameworks);
        initialData.put("libraries", selectedLibraries);
        initialData.put("os-software", selectedOsSoftware);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInitialData = objectMapper.writeValueAsString(initialData);

        // Thymeleafに渡す
        model.addAttribute("initialData", jsonInitialData);
    
        return "markdown_1";
    }

    /**
     * 1ページ目の内容をDBにインサート
     * @param form
     * @return markdown_2.html
     */
    @RequestMapping("/submit/first")
    public String specSheetStep1(@ModelAttribute SpecSheetStep1Form form, Integer specSheetStep1Id, Integer specSheetStep2Id, Model model) {
        // 初回遷移に関しては通常通りINSERTしてフォワード
        if (specSheetStep1Id == null) {
            Integer specSheet1Id = demoMarkdownService.saveStep1(form);
            model.addAttribute("specSheetStep1Id", specSheet1Id);
            return "markdown_2";
        }

        // 3ページから2ページ目へ戻ったためspexSheetStep2Idが存在する際は下記の処理
        if (specSheetStep2Id != null) {
            SpecSheetStep2 specSheetStep2 = demoMarkdownService.findBySpecSheet2Id(specSheetStep2Id);
            model.addAttribute("specSheetStep1Id", specSheetStep2.getPage1Id());
            model.addAttribute("specSheetStep2Id", specSheetStep2Id);
            SpecSheetStep2Form form2 = demoMarkdownService.convertToForm2(specSheetStep2);
            model.addAttribute("specSheetStep2Form", form2);

            // outsideWorkTitles と outsideWorkContents のペアリストを作成
            List<Map<String, String>> pairedList = new ArrayList<>();
            if (form2.getOutsideWorkTitles() != null && form2.getOutsideWorkContents() != null) {
                for (int i = 0; i < form2.getOutsideWorkTitles().size(); i++) {
                    Map<String, String> pair = new HashMap<>();
                    pair.put("title", form2.getOutsideWorkTitles().get(i));
                    pair.put("content", form2.getOutsideWorkContents().get(i));
                    pairedList.add(pair);
                }
            }
            model.addAttribute("pairedList", pairedList);

            // 資格をMapで格納
            List<Map<String, String>> qualificationList = new ArrayList<>();
            if (form2.getQualificationNames() != null && form2.getQualificationYears() != null && form2.getQualificationMonths() != null) {
                for (int i = 0; i < form2.getQualificationNames().size(); i++) {
                    Map<String, String> pair = new HashMap<>();
                    pair.put("name", form2.getQualificationNames().get(i));
                    pair.put("year", form2.getQualificationYears().get(i));
                    pair.put("month", form2.getQualificationMonths().get(i));
                    qualificationList.add(pair);
                }
            }
            model.addAttribute("qualificationList", qualificationList);

            return "markdown_2";
        }

        // 2ページ目から1ページ目へ戻ったためspexSheetStep1Idが存在する際はUPDATE
        demoMarkdownService.updateStep1(form, specSheetStep1Id);
        model.addAttribute("specSheetStep1Id", specSheetStep1Id);
        return "markdown_2";
        
    }

    /**
     * 2ページ目の内容をDBにインサート
     * @param form
     * @return markdown_3.html
     */
    @PostMapping("/submit/second")
    public String specSheetStep2(@ModelAttribute SpecSheetStep2Form form, Integer specSheetStep1Id, Integer specSheetStep2Id, Model model) {
        // 初回遷移に関しては通常通りINSERTしてフォワード
        if (specSheetStep2Id == null) {
            Integer specSheet2Id = demoMarkdownService.saveStep2(form, specSheetStep1Id);
            model.addAttribute("specSheetStep1Id", specSheetStep1Id);
            model.addAttribute("specSheetStep2Id", specSheet2Id);
            return "markdown_3.html";
        }
        // 3ページから2ページ目へ戻ったためspexSheetStep2Idが存在する際はUPDATE
        demoMarkdownService.updateStep2(form, specSheetStep1Id, specSheetStep2Id);
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
        SpecSheetStep2 specSheetStep2 = demoMarkdownService.findBySpecSheet2Id(specSheetStep2Id);
        SpecSheetStep2Form form2 = demoMarkdownService.convertToForm2(specSheetStep2);

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
