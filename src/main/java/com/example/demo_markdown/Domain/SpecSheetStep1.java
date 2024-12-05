package com.example.demo_markdown.Domain;

import java.util.List;

public class SpecSheetStep1 {
    // id
    private Integer id;
    // エンジニア種別
    private String engineerType;
    // エンジニアID
    private String engineerId;
    // 言語
    private String languages;
    // フレームワーク
    private String frameworks;
    // ライブラリ
    private String libraries;
    // OS・ソフトウェア
    private String osSoftware;
    // アピールポイントタイトル
    private String titles;
    // アピールポイント内容
    private String contents;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
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
    public String getLanguages() {
        return languages;
    }
    public void setLanguages(String languages) {
        this.languages = languages;
    }
    public String getFrameworks() {
        return frameworks;
    }
    public void setFrameworks(String frameworks) {
        this.frameworks = frameworks;
    }
    public String getLibraries() {
        return libraries;
    }
    public void setLibraries(String libraries) {
        this.libraries = libraries;
    }
    public String getOsSoftware() {
        return osSoftware;
    }
    public void setOsSoftware(String osSoftware) {
        this.osSoftware = osSoftware;
    }
    public String getTitles() {
        return titles;
    }
    public void setTitles(String titles) {
        this.titles = titles;
    }
    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }
    @Override
    public String toString() {
        return "SpecSheetStep1 [id=" + id + ", engineerType=" + engineerType + ", engineerId=" + engineerId
                + ", languages=" + languages + ", frameworks=" + frameworks + ", libraries=" + libraries
                + ", osSoftware=" + osSoftware + ", titles=" + titles + ", contents=" + contents + "]";
    }
    
    
}
