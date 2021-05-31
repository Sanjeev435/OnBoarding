	
CREATE TABLE T_PROJECT_DETAILS (
PROJECT_ID NUMERIC(9,0) PRIMARY KEY,
PROJECT_APPLI_CODE VARCHAR(20) NOT NULL,
PROJECT_SHORT_NAME VARCHAR(50),
PROJECT_LONG_NAME VARCHAR(200), 
PROJECT_UNIT_NAME VARCHAR(200)
);

CREATE SEQUENCE S_PROJECT_DETAILS INCREMENT BY 1 MAXVALUE 999999999 START WITH 1;

CREATE TABLE T_EMPLOYEE_DETAIL ( 
EMPLOYEE_ID NUMERIC(9,0),
F_NAME VARCHAR(80) NOT NULL,
L_NAME VARCHAR(150) NOT NULL,
EMAIL_ID VARCHAR(150) NOT NULL,
SUB_GROUP VARCHAR(100),
PROJECT_ID NUMERIC(9,0),
CONSTRAINT PK_EMPLOYEE_DETAIL PRIMARY KEY (EMPLOYEE_ID),
CONSTRAINT FK_EMPLOYEE_DETAIL FOREIGN KEY (PROJECT_ID) REFERENCES T_PROJECT_DETAILS (PROJECT_ID));

CREATE SEQUENCE S_EMPLOYEE_DETAIL INCREMENT BY 1 MAXVALUE 999999999 START WITH 1;

CREATE TABLE T_ONBOARDING_PROCESS (
PROCESS_ID NUMERIC(9,0),
DOCUMENT_NAME VARCHAR(100), 
DOCUMENT_CONTENT bytea,
DOCUMENT_TYPE VARCHAR(20),
VIDEO_NAME VARCHAR(100),
VIDEO_CONTENT bytea,
VIDEO_TYPE VARCHAR(20),
PIC_NAME VARCHAR(100),
PIC_CONTENT bytea,
PIC_TYPE VARCHAR(20),
SORT_ORDER NUMERIC(3,0),
PROJECT_ID NUMERIC(9,0),
CONSTRAINT PK_ON_BOARDING_PROCESS PRIMARY KEY (PROCESS_ID),
CONSTRAINT FK_ON_BOARDING_PROCESS FOREIGN KEY (PROJECT_ID) REFERENCES T_PROJECT_DETAILS (PROJECT_ID)
);

CREATE SEQUENCE S_ONBOARDING_PROCESS INCREMENT BY 1 MAXVALUE 999999999 START WITH 1;


CREATE TABLE T_ONBOARDING_PROGRESS (
PROGRESS_ID NUMERIC(9,0),
IS_COMPLETED SMALLINT,
PROCESS_ID NUMERIC(9,0),
EMPLOYEE_ID NUMERIC(9,0),
CONSTRAINT PK_ONBOARDING_PROGRESS PRIMARY KEY (PROGRESS_ID),
CONSTRAINT FK_ONBOARDING_PROGRESS_1 FOREIGN KEY (EMPLOYEE_ID) REFERENCES T_EMPLOYEE_DETAIL (EMPLOYEE_ID),
CONSTRAINT FK_ONBOARDING_PROGRESS_2 FOREIGN KEY (PROCESS_ID) REFERENCES T_ONBOARDING_PROCESS (PROCESS_ID)
);

CREATE SEQUENCE S_ONBOARDING_PROGRESS INCREMENT BY 1 MAXVALUE 999999999 START WITH 1;

COMMIT;