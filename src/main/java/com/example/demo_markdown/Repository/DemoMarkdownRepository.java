package com.example.demo_markdown.Repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.example.demo_markdown.Domain.SpecSheetStep1;
import com.example.demo_markdown.Domain.SpecSheetStep2;

@Repository
public class DemoMarkdownRepository {

	private static final RowMapper<SpecSheetStep1> SPEC_SHEET_STEP1_ROW_MAPPER =(rs,i)->{
		SpecSheetStep1 specSheetStep1 = new SpecSheetStep1();
		specSheetStep1.setId(rs.getInt("id"));
		specSheetStep1.setEngineerType(rs.getString("engineer_type"));
		specSheetStep1.setEngineerId(rs.getString("engineer_id"));
		specSheetStep1.setLanguages(rs.getString("languages"));
		specSheetStep1.setFrameworks(rs.getString("frameworks"));
		specSheetStep1.setLibraries(rs.getString("libraries"));
		specSheetStep1.setOsSoftware(rs.getString("os_software"));
		specSheetStep1.setTitles(rs.getString("titles"));
		specSheetStep1.setContents(rs.getString("contents"));
		return specSheetStep1;
	};

	private static final RowMapper<SpecSheetStep2> SPEC_SHEET_STEP2_ROW_MAPPER =(rs,i)->{
		SpecSheetStep2 specSheetStep2 = new SpecSheetStep2();
		specSheetStep2.setId(rs.getInt("id"));
		specSheetStep2.setPage1Id(rs.getInt("page1_id"));
		specSheetStep2.setPreviousJobName(rs.getString("previous_job_name"));
		specSheetStep2.setPreviousJobDetails(rs.getString("previous_job_details"));
		specSheetStep2.setOutsideWorkTitles(rs.getString("outside_work_titles"));
		specSheetStep2.setOutsideWorkContents(rs.getString("outside_work_contents"));
		specSheetStep2.setQualificationNames(rs.getString("qualification_names"));
		specSheetStep2.setQualificationYears(rs.getString("qualification_years"));
		specSheetStep2.setQualificationMonths(rs.getString("qualification_months"));
		return specSheetStep2;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;


	/**
      * idをもとにスペックシートの1ページ目を条件一致検索
      */
	public SpecSheetStep1 findBySpecSheetStep1Id(Integer id) {
        String findByIdSql = """
			SELECT id, engineer_type, engineer_id, languages, frameworks, libraries, 
			os_software, titles, contents 
			FROM markdown1 WHERE id=:id;
			""";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<SpecSheetStep1> specList = template.query(findByIdSql, param, SPEC_SHEET_STEP1_ROW_MAPPER);
        if (specList == null || specList.isEmpty()) {
            return null;
        }
        return specList.get(0);
    }


	
	/**
	 * 1ページ目の内容をDB登録
	 * @param user
	 * @return
	 */
    public Integer insert(SpecSheetStep1 spec) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(spec);
		String insertSql = """
        INSERT INTO markdown1 (engineer_type, engineer_id, languages, frameworks, libraries, 
		os_software, titles, contents) 
	    VALUES (:engineerType, :engineerId, :languages, :frameworks, :libraries, :osSoftware, 
		:titles, :contents);
        """;	
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnNames = {"id"};
		template.update(insertSql, param, keyHolder, keyColumnNames);
        Integer insertId = keyHolder.getKey().intValue();
        return insertId;	
	}

	/**
	 * 2ページ目の内容をDB登録
	 * @param user
	 * @return
	 */
    public Integer insertSecond(SpecSheetStep2 spec) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(spec);
		String insertSql = """
        INSERT INTO markdown2 (page1_id, previous_job_name, previous_job_details, 
		outside_work_titles, outside_work_contents, 
		qualification_names, qualification_years, qualification_months) 
	    VALUES (:page1Id, :previousJobName, :previousJobDetails, :outsideWorkTitles, 
		:outsideWorkContents, :qualificationNames, :qualificationYears, :qualificationMonths);
        """;	
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnNames = {"id"};
		template.update(insertSql, param, keyHolder, keyColumnNames);
        Integer insertId = keyHolder.getKey().intValue();
        return insertId;	
	}

	/**
      * idをもとにスペックシートの2ページ目を条件一致検索
      */
	  public SpecSheetStep2 findBySpecSheetStep2Id(Integer id) {
        String findByIdSql = """
			SELECT id, page1_id, previous_job_name, previous_job_details, outside_work_titles, 
			outside_work_contents, qualification_names, qualification_years, qualification_months
			FROM markdown2 WHERE id=:id;
			""";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<SpecSheetStep2> specList = template.query(findByIdSql, param, SPEC_SHEET_STEP2_ROW_MAPPER);
        if (specList == null || specList.isEmpty()) {
            return null;
        }
        return specList.get(0);
    }
    
}
