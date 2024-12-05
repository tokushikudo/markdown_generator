package com.example.demo_markdown.Form;

import java.util.List;

public class SpecSheetStep1Form {
    // エンジニア種別
    private String engineerType;
    // エンジニアID
    private String engineerId;
    // 言語
    private List<String> languages;
    // フレームワーク
    private List<String> frameworks;
    // ライブラリ
    private List<String> libraries;
    // OS・ソフトウェア
    private List<String> osSoftware;
    // アピールポイントタイトル
    private List<String> titles;
    // アピールポイント内容
    private List<String> contents;
    public String getEngineerType() {
        return engineerType;
    }
    public void setEngineerType(String engineerType) {
        this.engineerType = engineerType;
    }
    public String getEngineerId() {
        return engineerId;
    }
    public void setEngineerId(String engineerId) {
        this.engineerId = engineerId;
    }
    public List<String> getLanguages() {
        return languages;
    }
    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }
    public List<String> getFrameworks() {
        return frameworks;
    }
    public void setFrameworks(List<String> frameworks) {
        this.frameworks = frameworks;
    }
    public List<String> getLibraries() {
        return libraries;
    }
    public void setLibraries(List<String> libraries) {
        this.libraries = libraries;
    }
    public List<String> getOsSoftware() {
        return osSoftware;
    }
    public void setOsSoftware(List<String> osSoftware) {
        this.osSoftware = osSoftware;
    }
    public List<String> getTitles() {
        return titles;
    }
    public void setTitles(List<String> titles) {
        this.titles = titles;
    }
    public List<String> getContents() {
        return contents;
    }
    public void setContents(List<String> contents) {
        this.contents = contents;
    }
    
}
