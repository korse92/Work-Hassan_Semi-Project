-- 제약조건 확인
--SELECT * FROM USER_CONSTRAINTS;

--SELECT UC.TABLE_NAME, UCC.COLUMN_NAME, UC.CONSTRAINT_NAME,
--       UC.CONSTRAINT_TYPE, UC.SEARCH_CONDITION
--FROM USER_CONSTRAINTS UC JOIN USER_CONS_COLUMNS UCC
--    ON UC.CONSTRAINT_NAME = UCC.CONSTRAINT_NAME;

-- 휴지통 비우기
--PURGE RECYCLEBIN;

--commit

-- 테이블 삭제 구문
-- FK가 많아서 제약조건도 같이 지워줘야함
DROP TABLE  POST CASCADE CONSTRAINTS;
DROP TABLE  CATEGORY CASCADE CONSTRAINTS;
DROP TABLE  CMT CASCADE CONSTRAINTS;
DROP TABLE  MEMBER CASCADE CONSTRAINTS;
DROP TABLE  WORKSPACE CASCADE CONSTRAINTS;
DROP TABLE  CHAT CASCADE CONSTRAINTS;
DROP TABLE  ADMIN CASCADE CONSTRAINTS;
DROP TABLE  TODO CASCADE CONSTRAINTS;
DROP TABLE  CHANNEL CASCADE CONSTRAINTS;
DROP TABLE  WRKS_MEMBER CASCADE CONSTRAINTS;
DROP TABLE  CALENDAR CASCADE CONSTRAINTS;
DROP TABLE  CH_MEMBER CASCADE CONSTRAINTS;
DROP TABLE  HASHTAG CASCADE CONSTRAINTS;
DROP TABLE  FILES CASCADE CONSTRAINTS;

drop sequence SEQ_MEM_NO;
drop sequence SEQ_CAT_NO;
drop sequence SEQ_WORKS_NO;
drop sequence SEQ_CH_NO;
drop sequence SEQ_CAL_NO;
drop sequence SEQ_POST_NO;
drop sequence SEQ_CHAT_NO;
drop sequence SEQ_TODO_NO;
drop sequence SEQ_CMT_NO;

-- 테이블 생성 구문
CREATE TABLE "POST" (
	"POST_ID"	NUMBER		NULL,
	"REF_CHANNEL_ID"	NUMBER		NOT NULL,
	"REF_WORKS_MEMBER_NO"	NUMBER		NOT NULL,
	"REF_CATEGORY_ID"	NUMBER		NULL,
	"POST_TITLE"	VARCHAR2(60)		NOT NULL,
	"POST_CONTENT"	VARCHAR2(4000)		NOT NULL,
	"POST_REG_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"POST_HIT"	NUMBER	DEFAULT 0	NOT NULL,
	"REF_FILE_RENAMED_NAME"	VARCHAR2(200)		NULL
);

COMMENT ON COLUMN "POST"."POST_ID" IS '게시글 번호';

COMMENT ON COLUMN "POST"."REF_CHANNEL_ID" IS '채널 ID';

COMMENT ON COLUMN "POST"."REF_WORKS_MEMBER_NO" IS '작성자 멤버 No';

COMMENT ON COLUMN "POST"."REF_CATEGORY_ID" IS '카테고리 번호';

COMMENT ON COLUMN "POST"."POST_TITLE" IS '제목';

COMMENT ON COLUMN "POST"."POST_CONTENT" IS '내용';

COMMENT ON COLUMN "POST"."POST_REG_DATE" IS '작성일';

COMMENT ON COLUMN "POST"."POST_HIT" IS '조회수';

COMMENT ON COLUMN "POST"."REF_FILE_RENAMED_NAME" IS '첨부파일명';

CREATE TABLE "CATEGORY" (
	"CATEGORY_ID"	NUMBER		NULL,
	"CATEGORY_NAME"	VARCHAR2(30)		NOT NULL
);

COMMENT ON COLUMN "CATEGORY"."CATEGORY_ID" IS '카테고리 번호';

COMMENT ON COLUMN "CATEGORY"."CATEGORY_NAME" IS '카테고리명';

CREATE TABLE "CMT" (
	"CMT_ID"	NUMBER		NULL,
	"CMT_CONTENT"	VARCHAR2(200)		NOT NULL,
	"CMT_REG_DATE"	DATE	DEFAULT SYSDATE	NULL,
	"CMT_REF_POST_ID"	NUMBER		NOT NULL,
	"REF_WORKS_MEMBER_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "CMT"."CMT_ID" IS '댓글 번호';

COMMENT ON COLUMN "CMT"."CMT_CONTENT" IS '댓글 내용';

COMMENT ON COLUMN "CMT"."CMT_REG_DATE" IS '댓글 작성일';

COMMENT ON COLUMN "CMT"."CMT_REF_POST_ID" IS '게시글 번호';

COMMENT ON COLUMN "CMT"."REF_WORKS_MEMBER_NO" IS '작성자 멤버 No';

CREATE TABLE "MEMBER" (
	"MEMBER_ID"	VARCHAR2(20)		NULL,
	"MEMBER_PWD"	VARCHAR2(300)		NOT NULL,
	"MEMBER_NAME"	VARCHAR2(45)		NOT NULL,
	"BIRTHDAY"	DATE		NULL,
	"GENDER"	CHAR(1)		NOT NULL,
	"EMAIL"	VARCHAR2(50)		NOT NULL,
	"PHONE"	CHAR(11)		NOT NULL,
	"ENROLL_DATE"	DATE	DEFAULT SYSDATE	NULL,
	"MEMBER_STATUS"	VARCHAR2(2)	DEFAULT 'A'	NOT NULL,
    "ORIGINAL_PROFILE" VARCHAR2(200),
    "RENAMED_PROFILE" VARCHAR2(200)
);

COMMENT ON COLUMN "MEMBER"."MEMBER_ID" IS '회원 ID';

COMMENT ON COLUMN "MEMBER"."MEMBER_PWD" IS '비밀번호';

COMMENT ON COLUMN "MEMBER"."MEMBER_NAME" IS '회원 이름';

COMMENT ON COLUMN "MEMBER"."BIRTHDAY" IS '생년월일';

COMMENT ON COLUMN "MEMBER"."GENDER" IS '성별';

COMMENT ON COLUMN "MEMBER"."EMAIL" IS '이메일';

COMMENT ON COLUMN "MEMBER"."PHONE" IS '전화번호';

COMMENT ON COLUMN "MEMBER"."ENROLL_DATE" IS '가입일';

COMMENT ON COLUMN "MEMBER"."MEMBER_STATUS" IS '회원 상태';

CREATE TABLE "ADMIN" (
	"ADMIN_ID"	VARCHAR2(20)		NULL,
	"ADMIN_PASSWORD"	VARCHAR2(300)		NOT NULL,
	"ADMIN_EMAIL"	VARCHAR2(50)		NULL,
	"ADMIN_ROLE"	VARCHAR2(2)	DEFAULT 'A'	NULL
);

COMMENT ON COLUMN "ADMIN"."ADMIN_ID" IS '관리자_ID';

COMMENT ON COLUMN "ADMIN"."ADMIN_PASSWORD" IS '비밀번호';

COMMENT ON COLUMN "ADMIN"."ADMIN_EMAIL" IS '이메일';

COMMENT ON COLUMN "ADMIN"."ADMIN_ROLE" IS '관리자 등급';

CREATE TABLE "TODO" (
	"TODO_ID"	NUMBER		NULL,
	"REF_WORKSPACE_ID"	NUMBER		NULL,
	"REF_WORKS_MEMBER_NO"	NUMBER		NOT NULL,
	"TODO_TITLE"	VARCHAR2(100)		NOT NULL,
	"TODO_CONTENT"	VARCHAR2(500)		NOT NULL,
	"TODO_REG_DATE"	DATE	DEFAULT SYSDATE	NULL,
	"TODO_STATUS"	VARCHAR2(30) DEFAULT 'TODO'	NOT NULL,
	"TODO_START_DATE"	DATE		NULL,
	"TODO_END_DATE"	DATE		NULL
);

COMMENT ON COLUMN "TODO"."TODO_ID" IS 'ToDo ID';

COMMENT ON COLUMN "TODO"."REF_WORKSPACE_ID" IS '워크스페이스 ID';

COMMENT ON COLUMN "TODO"."REF_WORKS_MEMBER_NO" IS '작성자 멤버 No';

COMMENT ON COLUMN "TODO"."TODO_TITLE" IS 'ToDo 이름';

COMMENT ON COLUMN "TODO"."TODO_CONTENT" IS 'ToDo 내용';

COMMENT ON COLUMN "TODO"."TODO_REG_DATE" IS 'ToDo 작성일';

COMMENT ON COLUMN "TODO"."TODO_STATUS" IS 'ToDo 상태';

COMMENT ON COLUMN "TODO"."TODO_START_DATE" IS 'ToDo 시작일';

COMMENT ON COLUMN "TODO"."TODO_END_DATE" IS 'ToDo 종료일';


CREATE TABLE "WORKSPACE" (
	"WORKSPACE_ID"	NUMBER		NULL,
	"WORKSPACE_NAME"	VARCHAR2(40)		NOT NULL,
	"WORKSPACE_INVITE_LINK"	VARCHAR2(300)		NOT NULL,
	"WORKSPACE_REG_DATE"	DATE	DEFAULT SYSDATE	NULL,
	"WORKSPACE_ICON"	VARCHAR2(50)		NULL
);

COMMENT ON COLUMN "WORKSPACE"."WORKSPACE_ID" IS '워크스페이스 ID';

COMMENT ON COLUMN "WORKSPACE"."WORKSPACE_NAME" IS '워크스페이스 이름';

COMMENT ON COLUMN "WORKSPACE"."WORKSPACE_INVITE_LINK" IS '워크스페이스 초대링크';

COMMENT ON COLUMN "WORKSPACE"."WORKSPACE_REG_DATE" IS '워크스페이스 초대링크';

COMMENT ON COLUMN "WORKSPACE"."WORKSPACE_ICON" IS '아이콘 이미지 파일이름';

CREATE TABLE "CHAT" (
	"CHAT_ID"	NUMBER		NULL,
	"REF_CHANNEL_ID"	NUMBER		NOT NULL,
	"REF_WORKS_MEMBER_NO"	NUMBER		NULL,
	"CHAT_CONTENT"	VARCHAR2(4000)		NOT NULL,
	"CHAT_REG_DATE"	DATE  DEFAULT SYSDATE		NOT NULL,
	"REF_FILE_RENAMED_NAME"	VARCHAR2(200)		NULL
);

--ALTER TABLE CHAT MODIFY (CHAT_REG_DATE DEFAULT SYSDATE);
--ALTER TABLE CHAT modify REF_WORKS_MEMBER_NO NULL;
--commit;

COMMENT ON COLUMN "CHAT"."CHAT_ID" IS '채팅 ID';

COMMENT ON COLUMN "CHAT"."REF_CHANNEL_ID" IS '채널 ID';

COMMENT ON COLUMN "CHAT"."REF_WORKS_MEMBER_NO" IS '작성자 멤버 No';

COMMENT ON COLUMN "CHAT"."CHAT_CONTENT" IS '채팅 내용';

COMMENT ON COLUMN "CHAT"."CHAT_REG_DATE" IS '채팅 등록 시간';

COMMENT ON COLUMN "CHAT"."REF_FILE_RENAMED_NAME" IS '첨부파일명';



CREATE TABLE "CHANNEL" (
	"CHANNEL_ID"	NUMBER		NULL,
	"CHANNEL_NAME"	VARCHAR2(40)		NOT NULL,
	"REF_WORKSPACE_ID"	NUMBER		NOT NULL,
	"CHANNEL_PRI_OR_NOT"	CHAR(1)		NOT NULL
);

COMMENT ON COLUMN "CHANNEL"."CHANNEL_ID" IS '채널 ID';

COMMENT ON COLUMN "CHANNEL"."CHANNEL_NAME" IS '채널 이름';

COMMENT ON COLUMN "CHANNEL"."REF_WORKSPACE_ID" IS '워크스페이스 ID';

COMMENT ON COLUMN "CHANNEL"."CHANNEL_PRI_OR_NOT" IS '비공개 채널여부';

CREATE TABLE "WRKS_MEMBER" (
	"WORKS_MEMBER_NO"	NUMBER		NULL,
	"REF_MEMBER_ID"	VARCHAR2(20)		NOT NULL,
	"REF_WORKSPACE_ID"	NUMBER		NOT NULL,
	"WORKS_MEMBER_NICKNAME"	VARCHAR2(30)		NULL,
	"WORKS_MEMBER_ROLE"	CHAR(1)	DEFAULT 'U'	NOT NULL,
	"WORKS_MEMBER_REG_DATE"	DATE	DEFAULT SYSDATE	NULL
);

COMMENT ON COLUMN "WRKS_MEMBER"."WORKS_MEMBER_NO" IS '멤버 No';

COMMENT ON COLUMN "WRKS_MEMBER"."REF_MEMBER_ID" IS '회원 ID';

COMMENT ON COLUMN "WRKS_MEMBER"."REF_WORKSPACE_ID" IS '워크스페이스 ID';

COMMENT ON COLUMN "WRKS_MEMBER"."WORKS_MEMBER_NICKNAME" IS '멤버 닉네임';

COMMENT ON COLUMN "WRKS_MEMBER"."WORKS_MEMBER_ROLE" IS '멤버 권한';

COMMENT ON COLUMN "WRKS_MEMBER"."WORKS_MEMBER_REG_DATE" IS '멤버  등록일';



CREATE TABLE "CALENDAR" (
	"CALENDAR_ID"	NUMBER		NULL,
	"CALENDAR_GOOGLE_CID"	VARCHAR2(300)		NOT NULL,
	"REF_WORKSPACE_ID"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "CALENDAR"."CALENDAR_ID" IS '캘린더 ID';

COMMENT ON COLUMN "CALENDAR"."CALENDAR_GOOGLE_CID" IS '구글캘린더 ID';

COMMENT ON COLUMN "CALENDAR"."REF_WORKSPACE_ID" IS '워크스페이스 ID';

CREATE TABLE "CH_MEMBER" (
	"REF_CHANNEL_ID"	NUMBER		NOT NULL,
	"REF_WORKS_MEMBER_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "CH_MEMBER"."REF_CHANNEL_ID" IS '채널 ID';

COMMENT ON COLUMN "CH_MEMBER"."REF_WORKS_MEMBER_NO" IS '워크스페이스 멤버 No';

CREATE TABLE "HASHTAG" (
	"HASHTAG_NAME"	VARCHAR2(4000)		NULL,
	"HASHTAG_REF_POST_ID"	NUMBER		NULL,
	"HASHTAG_REF_CHAT_ID"	NUMBER		NULL,
	"HASHTAG_PO_CH_SEPARATOR"	CHAR(1)		NULL
);

COMMENT ON COLUMN "HASHTAG"."HASHTAG_NAME" IS '해시태그명';

COMMENT ON COLUMN "HASHTAG"."HASHTAG_REF_POST_ID" IS '게시글 번호';

COMMENT ON COLUMN "HASHTAG"."HASHTAG_REF_CHAT_ID" IS '채팅 ID';

COMMENT ON COLUMN "HASHTAG"."HASHTAG_PO_CH_SEPARATOR" IS '채팅/게시글 구분자';

CREATE TABLE "FILES" (
	"FILE_RENAMED_NAME"	VARCHAR2(200)		NULL,
	"FILE_ORIGINAL_NAME"	VARCHAR2(100)		NOT NULL,
	"FILE_PO_CH_SEPARATOR"	CHAR(1)		NOT NULL,
	"FILE_REF_POST_ID"	NUMBER		NULL,
	"FILE_REF_CHAT_ID"	NUMBER		NULL
);

COMMENT ON COLUMN "FILES"."FILE_RENAMED_NAME" IS '바뀐 파일명';

COMMENT ON COLUMN "FILES"."FILE_ORIGINAL_NAME" IS '원본 파일명';

COMMENT ON COLUMN "FILES"."FILE_PO_CH_SEPARATOR" IS '채팅/게시글 구분자';

COMMENT ON COLUMN "FILES"."FILE_REF_POST_ID" IS '게시글 번호';

COMMENT ON COLUMN "FILES"."FILE_REF_CHAT_ID" IS '채팅 ID';

--PK 제약조건
ALTER TABLE "POST" ADD CONSTRAINT "PK_POST" PRIMARY KEY (
	"POST_ID"
);

ALTER TABLE "CATEGORY" ADD CONSTRAINT "PK_CATEGORY" PRIMARY KEY (
	"CATEGORY_ID"
);

ALTER TABLE "CMT" ADD CONSTRAINT "PK_CMT" PRIMARY KEY (
	"CMT_ID"
);

ALTER TABLE "MEMBER" ADD CONSTRAINT "PK_MEMBER" PRIMARY KEY (
	"MEMBER_ID"
);

ALTER TABLE "ADMIN" ADD CONSTRAINT "PK_ADMIN" PRIMARY KEY (
	"ADMIN_ID"
);

ALTER TABLE "TODO" ADD CONSTRAINT "PK_TODO" PRIMARY KEY (
	"TODO_ID"
);

ALTER TABLE "WORKSPACE" ADD CONSTRAINT "PK_WORKSPACE" PRIMARY KEY (
	"WORKSPACE_ID"
);

ALTER TABLE "CHAT" ADD CONSTRAINT "PK_CHAT" PRIMARY KEY (
	"CHAT_ID"
);

ALTER TABLE "CHANNEL" ADD CONSTRAINT "PK_CHANNEL" PRIMARY KEY (
	"CHANNEL_ID"
);

ALTER TABLE "WRKS_MEMBER" ADD CONSTRAINT "PK_WRKS_MEMBER" PRIMARY KEY (
	"WORKS_MEMBER_NO"
);

ALTER TABLE "CALENDAR" ADD CONSTRAINT "PK_CALENDAR" PRIMARY KEY (
	"CALENDAR_ID"
);

ALTER TABLE "FILES" ADD CONSTRAINT "PK_FILES" PRIMARY KEY (
	"FILE_RENAMED_NAME"
);

--FK 제약조건
ALTER TABLE "POST" ADD CONSTRAINT "FK_CHANNEL_TO_POST_1" FOREIGN KEY (
	"REF_CHANNEL_ID"
)
REFERENCES "CHANNEL" (
	"CHANNEL_ID"
) ON DELETE CASCADE;

ALTER TABLE "POST" ADD CONSTRAINT "FK_WRKS_MEMBER_TO_POST_1" FOREIGN KEY (
	"REF_WORKS_MEMBER_NO"
)
REFERENCES "WRKS_MEMBER" (
	"WORKS_MEMBER_NO"
) ON DELETE SET NULL;

ALTER TABLE "POST" ADD CONSTRAINT "FK_CATEGORY_TO_POST_1" FOREIGN KEY (
	"REF_CATEGORY_ID"
)
REFERENCES "CATEGORY" (
	"CATEGORY_ID"
) ON DELETE SET NULL;

ALTER TABLE "POST" ADD CONSTRAINT "FK_FILES_TO_POST_1" FOREIGN KEY (
	"REF_FILE_RENAMED_NAME"
)
REFERENCES "FILES" (
	"FILE_RENAMED_NAME"
) ON DELETE SET NULL;

ALTER TABLE "CMT" ADD CONSTRAINT "FK_POST_TO_CMT_1" FOREIGN KEY (
	"CMT_REF_POST_ID"
)
REFERENCES "POST" (
	"POST_ID"
) ON DELETE CASCADE;

ALTER TABLE "CMT" ADD CONSTRAINT "FK_WRKS_MEMBER_TO_CMT_1" FOREIGN KEY (
	"REF_WORKS_MEMBER_NO"
)
REFERENCES "WRKS_MEMBER" (
	"WORKS_MEMBER_NO"
) ON DELETE SET NULL;

ALTER TABLE "TODO" ADD CONSTRAINT "FK_WORKSPACE_TO_TODO_1" FOREIGN KEY (
	"REF_WORKSPACE_ID"
)
REFERENCES "WORKSPACE" (
	"WORKSPACE_ID"
) ON DELETE CASCADE;

ALTER TABLE "TODO" ADD CONSTRAINT "FK_WRKS_MEMBER_TO_TODO_1" FOREIGN KEY (
	"REF_WORKS_MEMBER_NO"
)
REFERENCES "WRKS_MEMBER" (
	"WORKS_MEMBER_NO"
) ON DELETE SET NULL;

ALTER TABLE "CHAT" ADD CONSTRAINT "FK_CHANNEL_TO_CHAT_1" FOREIGN KEY (
	"REF_CHANNEL_ID"
)
REFERENCES "CHANNEL" (
	"CHANNEL_ID"
) ON DELETE CASCADE;

ALTER TABLE "CHAT" ADD CONSTRAINT "FK_WRKS_MEMBER_TO_CHAT_1" FOREIGN KEY (
	"REF_WORKS_MEMBER_NO"
)
REFERENCES "WRKS_MEMBER" (
	"WORKS_MEMBER_NO"
) ON DELETE SET NULL;

--채팅에 파일추가 기능되면 씀
--ALTER TABLE "CHAT" ADD CONSTRAINT "FK_FILES_TO_CHAT_1" FOREIGN KEY (
--	"REF_FILE_RENAMED_NAME"
--)
--REFERENCES "FILES" (
--	"FILE_RENAMED_NAME"
--) ON DELETE SET NULL;

ALTER TABLE "CHANNEL" ADD CONSTRAINT "FK_WORKSPACE_TO_CHANNEL_1" FOREIGN KEY (
	"REF_WORKSPACE_ID"
)
REFERENCES "WORKSPACE" (
	"WORKSPACE_ID"
) ON DELETE CASCADE;

ALTER TABLE "WRKS_MEMBER" ADD CONSTRAINT "FK_MEMBER_TO_WRKS_MEMBER_1" FOREIGN KEY (
	"REF_MEMBER_ID"
)
REFERENCES "MEMBER" (
	"MEMBER_ID"
) ON DELETE CASCADE;

ALTER TABLE "WRKS_MEMBER" ADD CONSTRAINT "FK_WORKSPACE_TO_WRKS_MEMBER_1" FOREIGN KEY (
	"REF_WORKSPACE_ID"
)
REFERENCES "WORKSPACE" (
	"WORKSPACE_ID"
) ON DELETE CASCADE;

ALTER TABLE "CALENDAR" ADD CONSTRAINT "FK_WORKSPACE_TO_CALENDAR_1" FOREIGN KEY (
	"REF_WORKSPACE_ID"
)
REFERENCES "WORKSPACE" (
	"WORKSPACE_ID"
) ON DELETE CASCADE;

ALTER TABLE "CH_MEMBER" ADD CONSTRAINT "FK_CHANNEL_TO_CH_MEMBER_1" FOREIGN KEY (
	"REF_CHANNEL_ID"
)
REFERENCES "CHANNEL" (
	"CHANNEL_ID"
) ON DELETE CASCADE;

ALTER TABLE "CH_MEMBER" ADD CONSTRAINT "FK_WRKS_MEMBER_TO_CH_MEMBER_1" FOREIGN KEY (
	"REF_WORKS_MEMBER_NO"
)
REFERENCES "WRKS_MEMBER" (
	"WORKS_MEMBER_NO"
) ON DELETE CASCADE;

ALTER TABLE "HASHTAG" ADD CONSTRAINT "FK_POST_TO_HASHTAG_1" FOREIGN KEY (
	"HASHTAG_REF_POST_ID"
)
REFERENCES "POST" (
	"POST_ID"
) ON DELETE CASCADE;

ALTER TABLE "HASHTAG" ADD CONSTRAINT "FK_CHAT_TO_HASHTAG_1" FOREIGN KEY (
	"HASHTAG_REF_CHAT_ID"
)
REFERENCES "CHAT" (
	"CHAT_ID"
) ON DELETE CASCADE;

ALTER TABLE "FILES" ADD CONSTRAINT "FK_POST_TO_FILES_1" FOREIGN KEY (
	"FILE_REF_POST_ID"
)
REFERENCES "POST" (
	"POST_ID"
) ON DELETE CASCADE;

ALTER TABLE "FILES" ADD CONSTRAINT "FK_CHAT_TO_FILES_1" FOREIGN KEY (
	"FILE_REF_CHAT_ID"
)
REFERENCES "CHAT" (
	"CHAT_ID"
) ON DELETE CASCADE;

--기타 제약조건(체크, 유니크 등등)
ALTER TABLE ADMIN ADD CONSTRAINT CK_ADMIN_ROLE CHECK(ADMIN_ROLE IN ('A','SA'));

ALTER TABLE MEMBER ADD CONSTRAINT CK_MEMBER_GENDER CHECK(GENDER IN ('M','F'));

ALTER TABLE MEMBER ADD CONSTRAINT CK_MEMBER_STATUS CHECK(MEMBER_STATUS IN ('A','DA','D'));

ALTER TABLE WRKS_MEMBER ADD CONSTRAINT CK_WORKS_MEMBER_ROLE CHECK(WORKS_MEMBER_ROLE IN ('A','U'));

ALTER TABLE WORKSPACE ADD CONSTRAINT UQ_WRKS_INVITE_LINK UNIQUE (WORKSPACE_INVITE_LINK);

ALTER TABLE CHANNEL ADD CONSTRAINT CK_CHANNEL_PRI_OR_NOT CHECK(CHANNEL_PRI_OR_NOT IN ('Y','N'));

ALTER TABLE FILES ADD CONSTRAINT CK_FILE_PO_CH_SEPARATOR CHECK(FILE_PO_CH_SEPARATOR IN ('P','C'));

ALTER TABLE HASHTAG ADD CONSTRAINT CK_HASHTAG_PO_CH_SEPARATOR CHECK(HASHTAG_PO_CH_SEPARATOR IN ('P','C'));

ALTER TABLE TODO ADD CONSTRAINT CK_TODO_STATUS CHECK(TODO_STATUS IN ('TODO', 'DOING', 'DONE'));

--시퀀스
CREATE SEQUENCE SEQ_MEM_NO
START WITH 1
INCREMENT BY 1
NOMINVALUE
NOMAXVALUE
NOCYCLE
NOCACHE;

CREATE SEQUENCE SEQ_CAT_NO
START WITH 1
INCREMENT BY 1
NOMINVALUE
NOMAXVALUE
NOCYCLE
NOCACHE;

CREATE SEQUENCE SEQ_WORKS_NO
START WITH 1
INCREMENT BY 1
NOMINVALUE
NOMAXVALUE
NOCYCLE
NOCACHE;

CREATE SEQUENCE SEQ_CH_NO
START WITH 1
INCREMENT BY 1
NOMINVALUE
NOMAXVALUE
NOCYCLE
NOCACHE;

CREATE SEQUENCE SEQ_CAL_NO
START WITH 1
INCREMENT BY 1
NOMINVALUE
NOMAXVALUE
NOCYCLE
NOCACHE;

CREATE SEQUENCE SEQ_POST_NO
START WITH 1
INCREMENT BY 1
NOMINVALUE
NOMAXVALUE
NOCYCLE
NOCACHE;

CREATE SEQUENCE SEQ_CHAT_NO
START WITH 1
INCREMENT BY 1
NOMINVALUE
NOMAXVALUE
NOCYCLE
NOCACHE;

CREATE SEQUENCE SEQ_TODO_NO
START WITH 1
INCREMENT BY 1
NOMINVALUE
NOMAXVALUE
NOCYCLE
NOCACHE;

CREATE SEQUENCE SEQ_CMT_NO
START WITH 1
INCREMENT BY 1
NOMINVALUE
NOMAXVALUE
NOCYCLE
NOCACHE;

commit;

--================================================
-- 사용하지 않게 된 테이블, 시퀀스
--================================================
--DROP TABLE  TODOLIST CASCADE CONSTRAINTS;
--DROP TABLE  TODO_STATUS CASCADE CONSTRAINTS;

--CREATE TABLE "TODOLIST" (
--	"TODOLIST_ID"	NUMBER		NULL,
--	"REF_WORKSPACE_ID"	NUMBER		NOT NULL
--);

--CREATE TABLE "TODO_STATUS" (
--	"TODO_STATUS_ID"	NUMBER		NULL,
--	"TODO_STATUS_NAME"	VARCHAR2(30)		NOT NULL
--);
--
--COMMENT ON COLUMN "TODO_STATUS"."TODO_STATUS_ID" IS 'ToDo 상태 ID';
--
--COMMENT ON COLUMN "TODO_STATUS"."TODO_STATUS_NAME" IS 'ToDo 상태 이름';
--
--COMMENT ON COLUMN "TODOLIST"."TODOLIST_ID" IS 'ToDo리스트 ID';
--
--COMMENT ON COLUMN "TODOLIST"."REF_WORKSPACE_ID" IS '워크스페이스 ID';

--ALTER TABLE "TODO_STATUS" ADD CONSTRAINT "PK_TODO_STATUS" PRIMARY KEY (
--	"TODO_STATUS_ID"
--);

--ALTER TABLE "TODOLIST" ADD CONSTRAINT "PK_TODOLIST" PRIMARY KEY (
--	"TODOLIST_ID"
--);

--CREATE SEQUENCE SEQ_TODOLIST_NO
--START WITH 1
--INCREMENT BY 1
--NOMINVALUE
--NOMAXVALUE
--NOCYCLE
--NOCACHE;

--ToDo 외래키 이전 버전
--ALTER TABLE "TODO" ADD CONSTRAINT "FK_TODOLIST_TO_TODO_1" FOREIGN KEY (
--	"REF_TODOLIST_ID"
--)
--REFERENCES "TODOLIST" (
--	"TODOLIST_ID"
--) ON DELETE CASCADE;
--
--ALTER TABLE "TODO" ADD CONSTRAINT "FK_WRKS_MEMBER_TO_TODO_1" FOREIGN KEY (
--	"REF_WORKS_MEMBER_NO"
--)
--REFERENCES "WRKS_MEMBER" (
--	"WORKS_MEMBER_NO"
--) ON DELETE CASCADE;
--
--ALTER TABLE "TODO" ADD CONSTRAINT "FK_TODO_STATUS_TO_TODO_1" FOREIGN KEY (
--	"REF_TODO_STATUS_ID"
--)
--REFERENCES "TODO_STATUS" (
--	"TODO_STATUS_ID"
--);

--ALTER TABLE "TODOLIST" ADD CONSTRAINT "FK_WORKSPACE_TO_TODOLIST_1" FOREIGN KEY (
--	"REF_WORKSPACE_ID"
--)
--REFERENCES "WORKSPACE" (
--	"WORKSPACE_ID"
--) ON DELETE CASCADE;