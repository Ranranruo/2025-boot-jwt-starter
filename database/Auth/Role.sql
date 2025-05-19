-- TABLE
CREATE TABLE Role (
	id              BIGSERIAL   PRIMARY KEY,
	name            VARCHAR(30) NOT NULL,
	description     TEXT,
	create_at       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
	update_at       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
	flag            BOOLEAN     NOT NULL DEFAULT FALSE
);

-- COMMENT
COMMENT ON TABLE Role IS '권한';

COMMENT ON COLUMN Role.id IS '고유번호';
COMMENT ON COLUMN Role.name IS '이름';
COMMENT ON COLUMN Role.create_at IS '생성 시간';
COMMENT ON COLUMN Role.update_at IS '수정 시간';
COMMENT ON COLUMN Role.flag IS '플래그';