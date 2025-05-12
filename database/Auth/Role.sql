-- TABLE
CREATE TABLE Role (
	id              BIGSERIAL   PRIMARY KEY,
	name            VARCHAR(30) NOT NULL,
	description     TEXT,
	create_date TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
	flag            BOOLEAN     NOT NULL DEFAULT FALSE
);

-- COMMENT
COMMENT ON TABLE Role IS '권한';

COMMENT ON COLUMN Role.id IS '고유번호';
COMMENT ON COLUMN Role.name IS '이름';
COMMENT ON COLUMN Role.description IS '설명';
COMMENT ON COLUMN Role.create_date IS '생성 시간';
COMMENT ON COLUMN Role.flag IS '플래그';