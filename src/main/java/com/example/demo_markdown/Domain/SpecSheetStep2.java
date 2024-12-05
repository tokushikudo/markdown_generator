package com.example.demo_markdown.Domain;

import java.util.List;

public class SpecSheetStep2 {
    // id
    private Integer id;
    // 1ページ目のページid
    private Integer page1Id;
    // 前職の名称
    private String previousJobName;
    // 前職の詳細
    private String previousJobDetails;
    // 業務外で取り組んでいることのタイトルリスト
    private String outsideWorkTitles;
    // 業務外で取り組んでいることの内容リスト
    private String outsideWorkContents;
    // 資格名リスト
    private String qualificationNames;
    // 資格の取得年リスト
    private String qualificationYears;
    // 資格の取得月リスト
    private String qualificationMonths;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getPage1Id() {
        return page1Id;
    }
    public void setPage1Id(Integer page1Id) {
        this.page1Id = page1Id;
    }
    public String getPreviousJobName() {
        return previousJobName;
    }
    public void setPreviousJobName(String previousJobName) {
        this.previousJobName = previousJobName;
    }
    public String getPreviousJobDetails() {
        return previousJobDetails;
    }
    public void setPreviousJobDetails(String previousJobDetails) {
        this.previousJobDetails = previousJobDetails;
    }
    public String getOutsideWorkTitles() {
        return outsideWorkTitles;
    }
    public void setOutsideWorkTitles(String outsideWorkTitles) {
        this.outsideWorkTitles = outsideWorkTitles;
    }
    public String getOutsideWorkContents() {
        return outsideWorkContents;
    }
    public void setOutsideWorkContents(String outsideWorkContents) {
        this.outsideWorkContents = outsideWorkContents;
    }
    public String getQualificationNames() {
        return qualificationNames;
    }
    public void setQualificationNames(String qualificationNames) {
        this.qualificationNames = qualificationNames;
    }
    public String getQualificationYears() {
        return qualificationYears;
    }
    public void setQualificationYears(String qualificationYears) {
        this.qualificationYears = qualificationYears;
    }
    public String getQualificationMonths() {
        return qualificationMonths;
    }
    public void setQualificationMonths(String qualificationMonths) {
        this.qualificationMonths = qualificationMonths;
    }
}
