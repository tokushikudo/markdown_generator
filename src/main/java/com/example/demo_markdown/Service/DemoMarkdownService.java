package com.example.demo_markdown.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo_markdown.Domain.SpecSheetStep1;
import com.example.demo_markdown.Domain.SpecSheetStep2;
import com.example.demo_markdown.Form.SpecSheetStep1Form;
import com.example.demo_markdown.Form.SpecSheetStep2Form;
import com.example.demo_markdown.Form.SpecSheetStep3Form;
import com.example.demo_markdown.Repository.DemoMarkdownRepository;

@Service
public class DemoMarkdownService {

    @Autowired
    private DemoMarkdownRepository demoMarkdownRepository;

    /**
     * デモで1ページ目MarkDown生成ロジックの実装
     * @param form
     * @return
     */
    public StringBuilder markdownGenerater(SpecSheetStep1Form form) {
        // Markdown生成
        StringBuilder markdown = new StringBuilder();
        markdown.append("<style>\r\n" + //
                        "  body {\r\n" + //
                        "    line-height: 1.2; /* 行間を狭くする */\r\n" + //
                        "    font-size: 12px;\r\n" + //
                        "  }\r\n" + //
                        "</style>\n\n");
        markdown.append("# スペックシート");
        markdown.append(engineerTypeMatching(form.getEngineerType())).append("\n");
        // 基本情報
        markdown.append("## 基本情報\n");
        markdown.append("- 株式会社ラクスパートナーズ").append("\n");
        markdown.append("- エンジニア ID: ").append(engineerIdMatching(form.getEngineerType())).append(form.getEngineerId()).append("\n\n");

        // スキル
        markdown.append("\n## スキル\n");
        markdown.append("|                  |                                                    |\n");
        markdown.append("| ---------------- | -------------------------------------------------- |\n");

        form.getLanguages().remove(0);
        form.getFrameworks().remove(0);
        form.getLibraries().remove(0);
        form.getOsSoftware().remove(0);
        markdown.append("| 言語           | ").append(String.join(", ", form.getLanguages())).append(" |\n");
        markdown.append("| フレームワーク | ").append(String.join(", ", form.getFrameworks())).append(" |\n");
        markdown.append("| ライブラリ     | ").append(String.join(", ", form.getLibraries())).append(" |\n");
        markdown.append("| OS・ソフトウェア | ").append(String.join(", ", form.getOsSoftware())).append(" |\n");
        markdown.append("|                  |                                                    |\n");

        markdown.append("\n");
        markdown.append("## アピールポイント\n");
        for (int i = 0; i < form.getTitles().size(); i++) {
            markdown.append("- 【").append(form.getTitles().get(i)).append("】  \n");
            markdown.append("  ").append(form.getContents().get(i)).append("\n\n");
        }

        return markdown;
    }



    /**
     * デモで2ページ目MarkDown生成ロジックの実装
     * @param form
     * @return
     */
    public StringBuilder markdownGenerater2(SpecSheetStep2Form form) {
        // Markdown生成
        StringBuilder markdown = new StringBuilder();
        // 前職入力欄
        markdown.append("## 前職\n");
        markdown.append("- ").append(form.getPreviousJobName()).append("\n");
        markdown.append("  ").append(form.getPreviousJobDetails()).append("\n\n");

        // 業務外で取り組んでいること
        markdown.append("\n## 業務外で取り組んでいること\n");
        for (int i = 0; i < form.getOutsideWorkTitles().size(); i++) {
            markdown.append("- ").append(form.getOutsideWorkTitles().get(i)).append("  \n");
            markdown.append("  ").append(form.getOutsideWorkContents().get(i)).append("\n\n");
        }

        // 資格
        markdown.append("\n## 資格\n");
        for (int i = 0; i < form.getQualificationNames().size(); i++) {
            markdown.append("- ").append(form.getQualificationNames().get(i));
            markdown.append("（").append(form.getQualificationYears().get(i)).append("年").append(form.getQualificationMonths().get(i)).append("月 取得）").append("  \n");
        }

        return markdown;
    }

    /**
     * デモで3ページ目MarkDown生成ロジックの実装
     * @param form
     * @return
     */
    public StringBuilder markdownGenerater3(SpecSheetStep3Form form) {
        // Markdown生成
        StringBuilder markdown = new StringBuilder();
    
        // プロジェクト経歴
        markdown.append("\n## プロジェクト経歴\n\n");
        for (int i = 0; i < form.getProjectNames().size(); i++) {
            markdown.append("---\n");
            markdown.append("### **PJ：").append(form.getProjectNames().get(i)).append("**\n\n");
    
            // 基本情報の表
            markdown.append("|        |          |          |     |             |      |\n");
            markdown.append("| ------ | -------- | -------- | --- | ----------- | ---- |\n");
            markdown.append("| 開始月 | ").append(form.getStartMonths().get(i));
            markdown.append(" | 担当役割 | ").append(form.getRoles().get(i));
            markdown.append(" | チーム人数 | ").append(form.getTeamSizes().get(i)).append(" 名 |\n");
            markdown.append("| 期間 | ").append(form.getDurations().get(i));
            markdown.append(" | 担当工程 | ").append(String.join("、", form.getPhases().get(i))).append(" |\n");
            markdown.append("| PJ全体人数 | ").append(form.getOverallTeamSizes().get(i)).append(" 名 |\n\n");
    
            // 技術スタックの表
            markdown.append("|                  |                                  |\n");
            markdown.append("| ---------------- | -------------------------------- |\n");
            markdown.append("| 言語             | ").append(String.join(", ", form.getLanguages().get(i))).append(" |\n");
            markdown.append("| フレームワーク   | ").append(String.join(", ", form.getFrameworks().get(i))).append(" |\n");
            markdown.append("| ライブラリ       | ").append(String.join(", ", form.getLibraries().get(i))).append(" |\n");
            markdown.append("| OS・ソフトウェア | ").append(String.join(", ", form.getOsSoftware().get(i))).append(" |\n\n");
    
            // プロジェクト概要と担当内容
            markdown.append("**【プロジェクト概要】**\n\n");
            markdown.append("- ").append(form.getSummaries().get(i)).append("\n\n");
    
            markdown.append("**【担当内容】**\n");
            markdown.append("- ").append(form.getResponsibilities().get(i)).append("\n\n");
    
            // 構成図アップロード
            markdown.append("#### 構成図\n\n");
            markdown.append("<p align=\"center\">\n");
            markdown.append("  <img width=\"80%\" height=\"300px\" src=\"").append(form.getDiagrams().get(i)).append("\">\n");
            markdown.append("</p>\n\n");
    
            // ページ区切り
            markdown.append("<div style=\"page-break-before:always\"></div>\n");
        }
    
        return markdown;
    }
    

    /**
     * デモで1,2,3ページ目を結合してMarkDownを作成してみる
     * @param form
     * @return
     */
    public StringBuilder markdownGeneraterConcat(SpecSheetStep1Form form1, SpecSheetStep2Form form2, SpecSheetStep3Form form3) {
        StringBuilder markdown = new StringBuilder();
        markdown.append("<style>\r\n" + //
                        "  body {\r\n" + //
                        "    line-height: 1.2; /* 行間を狭くする */\r\n" + //
                        "    font-size: 12px;\r\n" + //
                        "  }\r\n" + //
                        "</style>\n\n");
        markdown.append("# スペックシート");
        markdown.append(engineerTypeMatching(form1.getEngineerType())).append("\n");
        // 基本情報
        markdown.append("## 基本情報\n");
        markdown.append("- 株式会社○○").append("\n");
        markdown.append("- エンジニア ID: ").append(engineerIdMatching(form1.getEngineerType())).append(form1.getEngineerId()).append("\n\n");

        // スキル
        markdown.append("\n## スキル\n");
        markdown.append("|                  |                                                    |\n");
        markdown.append("| ---------------- | -------------------------------------------------- |\n");

        markdown.append("| 言語           | ").append(String.join(", ", form1.getLanguages())).append(" |\n");
        markdown.append("| フレームワーク | ").append(String.join(", ", form1.getFrameworks())).append(" |\n");
        markdown.append("| ライブラリ     | ").append(String.join(", ", form1.getLibraries())).append(" |\n");
        markdown.append("| OS・ソフトウェア | ").append(String.join(", ", form1.getOsSoftware())).append(" |\n");
        markdown.append("|                  |                                                    |\n");

        markdown.append("\n");
        markdown.append("## アピールポイント\n");
        for (int i = 0; i < form1.getTitles().size(); i++) {
            markdown.append("- 【").append(form1.getTitles().get(i)).append("】  \n");
            markdown.append("  ").append(form1.getContents().get(i)).append("\n\n");
        }
        // 前職入力欄
        markdown.append("## 前職\n");
        markdown.append("- ").append(form2.getPreviousJobName()).append("\n");
        markdown.append("  ").append(form2.getPreviousJobDetails()).append("\n\n");

        // 業務外で取り組んでいること
        markdown.append("\n## 業務外で取り組んでいること\n");
        for (int i = 0; i < form2.getOutsideWorkTitles().size(); i++) {
            markdown.append("- ").append(form2.getOutsideWorkTitles().get(i)).append("  \n");
            markdown.append("  ").append(form2.getOutsideWorkContents().get(i)).append("\n\n");
        }

        // 資格
        markdown.append("\n## 資格\n");
        for (int i = 0; i < form2.getQualificationNames().size(); i++) {
            markdown.append("- ").append(form2.getQualificationNames().get(i));
            markdown.append("（").append(form2.getQualificationYears().get(i)).append("年").append(form2.getQualificationMonths().get(i)).append("月 取得）").append("  \n");
        }

        // プロジェクト経歴
        markdown.append("\n## プロジェクト経歴\n\n");
        for (int i = 0; i < form3.getProjectNames().size(); i++) {
            markdown.append("---\n");
            markdown.append("### **PJ：").append(form3.getProjectNames().get(i)).append("**\n\n");
    
            // 基本情報の表
            markdown.append("|        |          |          |     |             |      |\n");
            markdown.append("| ------ | -------- | -------- | --- | ----------- | ---- |\n");
            markdown.append("| 開始月 | ").append(form3.getStartMonths().get(i));
            markdown.append(" | 担当役割 | ").append(form3.getRoles().get(i));
            markdown.append(" | チーム人数 | ").append(form3.getTeamSizes().get(i)).append(" 名 |\n");
            markdown.append("| 期間 | ").append(form3.getDurations().get(i));
            markdown.append(" | 担当工程 | ").append(String.join("、", form3.getPhases().get(i))).append(" |\n");
            markdown.append("| PJ全体人数 | ").append(form3.getOverallTeamSizes().get(i)).append(" 名 |\n\n");
    
            // 技術スタックの表
            markdown.append("|                  |                                  |\n");
            markdown.append("| ---------------- | -------------------------------- |\n");
            markdown.append("| 言語             | ").append(String.join(", ", form3.getLanguages().get(i))).append(" |\n");
            markdown.append("| フレームワーク   | ").append(String.join(", ", form3.getFrameworks().get(i))).append(" |\n");
            markdown.append("| ライブラリ       | ").append(String.join(", ", form3.getLibraries().get(i))).append(" |\n");
            markdown.append("| OS・ソフトウェア | ").append(String.join(", ", form3.getOsSoftware().get(i))).append(" |\n\n");
    
            // プロジェクト概要と担当内容
            markdown.append("**【プロジェクト概要】**\n\n");
            markdown.append("- ").append(form3.getSummaries().get(i)).append("\n\n");
    
            markdown.append("**【担当内容】**\n");
            markdown.append("- ").append(form3.getResponsibilities().get(i)).append("\n\n");
    
            // 構成図アップロード
            markdown.append("#### 構成図\n\n");
            markdown.append("<p align=\"center\">\n");
            markdown.append("  <img width=\"80%\" height=\"300px\" src=\"").append(form3.getDiagrams().get(i)).append("\">\n");
            markdown.append("</p>\n\n");
    
            // ページ区切り
            markdown.append("<div style=\"page-break-before:always\"></div>\n");
        }

        return markdown;
    }






    /**
     * エンジニア種別によってタイトル変更を行う
     * @param enginnerType
     * @return
     */
    private String engineerTypeMatching(String enginnerType) {
        if (enginnerType.equals("web")) {
            return "(Webエンジニア)";
        } else if (enginnerType.equals("fr")) {
            return "(フロントエンドエンジニア)";
        } else if (enginnerType.equals("ml")) {
            return "(機械学習エンジニア)";
        } else if (enginnerType.equals("cl")) {
            return "(クラウドエンジニア)";
        } else {
            return "(QAエンジニア)";
        }
    }

    /**
     * エンジニアIDによってID部分を変更
     * @param enginnerType
     * @return
     */
    private String engineerIdMatching(String enginnerType) {
        if (enginnerType.equals("web")) {
            return "AP-202-";
        } else if (enginnerType.equals("fr")) {
            return "FR-204-";
        } else if (enginnerType.equals("ml")) {
            return "ML-203-";
        } else if (enginnerType.equals("cl")) {
            return "NW-201-";
        } else {
            return "QA-205-";
        }
    }

    /**
     * 1ページ目の内容を保存
     * formとDomainの組み換えを行ってから格納（リストはそのままではDBに格納できない）
     * @param form
     */
    public Integer saveStep1(SpecSheetStep1Form form) {
        SpecSheetStep1 specSheetStep1 = new SpecSheetStep1();

        // フォームデータをエンティティに変換
        specSheetStep1.setEngineerType(form.getEngineerType());
        specSheetStep1.setEngineerId(form.getEngineerId());

        specSheetStep1.setLanguages(String.join(",", form.getLanguages())); // カンマ区切りに変換
        specSheetStep1.setFrameworks(String.join(",", form.getFrameworks()));
        specSheetStep1.setLibraries(String.join(",", form.getLibraries()));
        specSheetStep1.setOsSoftware(String.join(",", form.getOsSoftware()));
        specSheetStep1.setTitles(String.join(",", form.getTitles()));
        specSheetStep1.setContents(String.join(",", form.getContents()));

        // エンティティを保存
        Integer step1Id = demoMarkdownRepository.insert(specSheetStep1);
        return step1Id;
    }


    /**
     * 1ページ目の内容を保存
     * formとDomainの組み換えを行ってから格納（リストはそのままではDBに格納できない）
     * @param form
     */
    public void updateStep1(SpecSheetStep1Form form, Integer specSheetStep1Id) {
        SpecSheetStep1 specSheetStep1 = new SpecSheetStep1();

        // フォームデータをエンティティに変換
        specSheetStep1.setId(specSheetStep1Id);
        specSheetStep1.setEngineerType(form.getEngineerType());
        specSheetStep1.setEngineerId(form.getEngineerId());

        specSheetStep1.setLanguages(String.join(",", form.getLanguages())); // カンマ区切りに変換
        specSheetStep1.setFrameworks(String.join(",", form.getFrameworks()));
        specSheetStep1.setLibraries(String.join(",", form.getLibraries()));
        specSheetStep1.setOsSoftware(String.join(",", form.getOsSoftware()));
        specSheetStep1.setTitles(String.join(",", form.getTitles()));
        specSheetStep1.setContents(String.join(",", form.getContents()));

        // エンティティを保存
        demoMarkdownRepository.update(specSheetStep1);
    }



    /**
     * 1ページ目の情報をDBから取得
     * @param specSheetStep1
     * @return
     */
    public SpecSheetStep1Form findBySpecSheet1Id(Integer specSheetStep1Id) {
        SpecSheetStep1 specSheetStep1 = demoMarkdownRepository.findBySpecSheetStep1Id(specSheetStep1Id);
        return convertToForm(specSheetStep1);
    }

    /**
     * 2ページ目の内容を保存
     * formとDomainの組み換えを行ってから格納（リストはそのままではDBに格納できない）
     * @param form
     */
    public Integer saveStep2(SpecSheetStep2Form form, Integer page1Id) {
        SpecSheetStep2 specSheetStep = new SpecSheetStep2();

        // フォームデータをエンティティに変換
        specSheetStep.setPage1Id(page1Id);
        specSheetStep.setPreviousJobName(form.getPreviousJobName());
        specSheetStep.setPreviousJobDetails(form.getPreviousJobDetails());
        specSheetStep.setOutsideWorkTitles(String.join(",", form.getOutsideWorkTitles())); // カンマ区切りに変換
        specSheetStep.setOutsideWorkContents(String.join(",", form.getOutsideWorkContents()));
        specSheetStep.setQualificationNames(String.join(",", form.getQualificationNames()));
        specSheetStep.setQualificationYears(String.join(",", form.getQualificationYears()));
        specSheetStep.setQualificationMonths(String.join(",", form.getQualificationMonths()));

        // エンティティを保存
        Integer step2Id = demoMarkdownRepository.insertSecond(specSheetStep);
        return step2Id;
    }


    /**
     * 2ページ目の内容を更新
     * formとDomainの組み換えを行ってから格納（リストはそのままではDBに格納できない）
     * @param form
     */
    public void updateStep2(SpecSheetStep2Form form, Integer page1Id, Integer page2Id) {
        SpecSheetStep2 specSheetStep = new SpecSheetStep2();

        // フォームデータをエンティティに変換
        specSheetStep.setId(page2Id);
        specSheetStep.setPage1Id(page1Id);
        specSheetStep.setPreviousJobName(form.getPreviousJobName());
        specSheetStep.setPreviousJobDetails(form.getPreviousJobDetails());
        specSheetStep.setOutsideWorkTitles(String.join(",", form.getOutsideWorkTitles())); // カンマ区切りに変換
        specSheetStep.setOutsideWorkContents(String.join(",", form.getOutsideWorkContents()));
        specSheetStep.setQualificationNames(String.join(",", form.getQualificationNames()));
        specSheetStep.setQualificationYears(String.join(",", form.getQualificationYears()));
        specSheetStep.setQualificationMonths(String.join(",", form.getQualificationMonths()));

        // エンティティを保存
        demoMarkdownRepository.updateSecond(specSheetStep);
    }



    /**
     * 2ページ目の情報をDBから取得
     * @param specSheetStep1
     * @return
     */
    public SpecSheetStep2 findBySpecSheet2Id(Integer specSheetStep2Id) {
        SpecSheetStep2 specSheetStep2 = demoMarkdownRepository.findBySpecSheetStep2Id(specSheetStep2Id);
        return specSheetStep2;
    }


    /**
     * 1ページ目の内容をformに成形して返却
     */
    public SpecSheetStep1Form convertToForm(SpecSheetStep1 specSheetStep1) {
        SpecSheetStep1Form form = new SpecSheetStep1Form();
    
        // 基本情報
        form.setEngineerType(specSheetStep1.getEngineerType());
        form.setEngineerId(specSheetStep1.getEngineerId());
    
        // スキル (カンマ区切りからリストに変換)
        form.setLanguages(Arrays.asList(specSheetStep1.getLanguages().split(",")));
        form.setFrameworks(Arrays.asList(specSheetStep1.getFrameworks().split(",")));
        form.setLibraries(Arrays.asList(specSheetStep1.getLibraries().split(",")));
        form.setOsSoftware(Arrays.asList(specSheetStep1.getOsSoftware().split(",")));
        form.setTitles(Arrays.asList(specSheetStep1.getTitles().split(",")));
        form.setContents(Arrays.asList(specSheetStep1.getContents().split(",")));
    
        return form;
    }

     /**
     * 2ページ目の内容をformに成形して返却
     */
    public SpecSheetStep2Form convertToForm2(SpecSheetStep2 specSheetStep2) {
        SpecSheetStep2Form form = new SpecSheetStep2Form();
    
        // 基本情報
        form.setPreviousJobName(specSheetStep2.getPreviousJobName());
        form.setPreviousJobDetails(specSheetStep2.getPreviousJobDetails());
        form.setOutsideWorkTitles(Arrays.asList(specSheetStep2.getOutsideWorkTitles().split(",")));
        form.setOutsideWorkContents(Arrays.asList(specSheetStep2.getOutsideWorkContents().split(",")));
        form.setQualificationNames(Arrays.asList(specSheetStep2.getQualificationNames().split(",")));
        form.setQualificationYears(Arrays.asList(specSheetStep2.getQualificationYears().split(",")));
        form.setQualificationMonths(Arrays.asList(specSheetStep2.getQualificationMonths().split(",")));
    
        return form;
    }
    
}
