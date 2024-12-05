package com.example.demo_markdown.Form;

import java.util.List;

public class SpecSheetStep2Form {
    // 前職の名称
    private String previousJobName;

    // 前職の詳細
    private String previousJobDetails;

    // 業務外で取り組んでいることのタイトルリスト
    private List<String> outsideWorkTitles;

    // 業務外で取り組んでいることの内容リスト
    private List<String> outsideWorkContents;

    // 資格名リスト
    private List<String> qualificationNames;

    // 資格の取得年リスト
    private List<String> qualificationYears;

    // 資格の取得月リスト
    private List<String> qualificationMonths;

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

    public List<String> getOutsideWorkTitles() {
        return outsideWorkTitles;
    }

    public void setOutsideWorkTitles(List<String> outsideWorkTitles) {
        this.outsideWorkTitles = outsideWorkTitles;
    }

    public List<String> getOutsideWorkContents() {
        return outsideWorkContents;
    }

    public void setOutsideWorkContents(List<String> outsideWorkContents) {
        this.outsideWorkContents = outsideWorkContents;
    }

    public List<String> getQualificationNames() {
        return qualificationNames;
    }

    public void setQualificationNames(List<String> qualificationNames) {
        this.qualificationNames = qualificationNames;
    }

    public List<String> getQualificationYears() {
        return qualificationYears;
    }

    public void setQualificationYears(List<String> qualificationYears) {
        this.qualificationYears = qualificationYears;
    }

    public List<String> getQualificationMonths() {
        return qualificationMonths;
    }

    public void setQualificationMonths(List<String> qualificationMonths) {
        this.qualificationMonths = qualificationMonths;
    }

    
}
