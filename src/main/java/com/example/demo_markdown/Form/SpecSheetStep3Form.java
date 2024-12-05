package com.example.demo_markdown.Form;

import java.util.List;

public class SpecSheetStep3Form {
    // プロジェクト名
    private List<String> projectNames;
    // 開始月
    private List<String> startMonths;
    // 担当役割
    private List<String> roles;
    // チーム人数
    private List<Integer> teamSizes;
    // 期間
    private List<String> durations;
    // 担当工程
    private List<String> phases;
    // PJ全体人数
    private List<Integer> overallTeamSizes;
    // 言語
    private List<List<String>> languages;
    // フレームワーク
    private List<List<String>> frameworks;
    // ライブラリ
    private List<List<String>> libraries;
    // OS・ソフトウェア
    private List<List<String>> osSoftware;
    // プロジェクト概要
    private List<String> summaries;
    // 担当内容
    private List<String> responsibilities;
    // 構成図ファイル名
    private List<String> diagrams;
    public List<String> getProjectNames() {
        return projectNames;
    }
    public void setProjectNames(List<String> projectNames) {
        this.projectNames = projectNames;
    }
    public List<String> getStartMonths() {
        return startMonths;
    }
    public void setStartMonths(List<String> startMonths) {
        this.startMonths = startMonths;
    }
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public List<Integer> getTeamSizes() {
        return teamSizes;
    }
    public void setTeamSizes(List<Integer> teamSizes) {
        this.teamSizes = teamSizes;
    }
    public List<String> getDurations() {
        return durations;
    }
    public void setDurations(List<String> durations) {
        this.durations = durations;
    }
    public List<String> getPhases() {
        return phases;
    }
    public void setPhases(List<String> phases) {
        this.phases = phases;
    }
    public List<Integer> getOverallTeamSizes() {
        return overallTeamSizes;
    }
    public void setOverallTeamSizes(List<Integer> overallTeamSizes) {
        this.overallTeamSizes = overallTeamSizes;
    }
    public List<List<String>> getLanguages() {
        return languages;
    }
    public void setLanguages(List<List<String>> languages) {
        this.languages = languages;
    }
    public List<List<String>> getFrameworks() {
        return frameworks;
    }
    public void setFrameworks(List<List<String>> frameworks) {
        this.frameworks = frameworks;
    }
    public List<List<String>> getLibraries() {
        return libraries;
    }
    public void setLibraries(List<List<String>> libraries) {
        this.libraries = libraries;
    }
    public List<List<String>> getOsSoftware() {
        return osSoftware;
    }
    public void setOsSoftware(List<List<String>> osSoftware) {
        this.osSoftware = osSoftware;
    }
    public List<String> getSummaries() {
        return summaries;
    }
    public void setSummaries(List<String> summaries) {
        this.summaries = summaries;
    }
    public List<String> getResponsibilities() {
        return responsibilities;
    }
    public void setResponsibilities(List<String> responsibilities) {
        this.responsibilities = responsibilities;
    }
    public List<String> getDiagrams() {
        return diagrams;
    }
    public void setDiagrams(List<String> diagrams) {
        this.diagrams = diagrams;
    }

    
}
