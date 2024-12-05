-- 1ページ目
drop table if exists markdown1 cascade;
CREATE TABLE markdown1 (
    id SERIAL PRIMARY KEY,
    engineer_type VARCHAR(20) NOT NULL,
    engineer_id VARCHAR(10) NOT NULL,
    languages TEXT,
    frameworks TEXT,
	libraries TEXT,
	os_software TEXT,
	titles TEXT,
	contents TEXT
);

-- 2ページ目
drop table if exists markdown2 cascade;
CREATE TABLE markdown2 (
    id SERIAL PRIMARY KEY,
    page1_id INTEGER NOT NULL, 
    previous_job_name TEXT,
    previous_job_details TEXT,
	outside_work_titles TEXT,
	outside_work_contents TEXT,
    qualification_names TEXT,
    qualification_years TEXT,
    qualification_months TEXT
);