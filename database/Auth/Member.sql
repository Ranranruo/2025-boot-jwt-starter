-- TABLE
CREATE TABLE Member (
	id          BIGSERIAL    PRIMARY KEY,
	username     VARCHAR(20)  NOT NULL UNIQUE,
	email        VARCHAR(20)  NOT NULL,
	display_name VARCHAR(12)  NOT NULL DEFAULT '유저',
	password     VARCHAR(100) NOT NULL,
	create_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
	update_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
	flag         BOOLEAN      NOT NULL DEFAULT FALSE
);

-- COMMENT
COMMENT ON TABLE Member IS '회원';

COMMENT ON COLUMN Member.id IS '고유번호';
COMMENT ON COLUMN Member.email IS '이메일';
COMMENT ON COLUMN Member.username IS '이름';
COMMENT ON COLUMN Member.display_name IS '닉네임';
COMMENT ON COLUMN Member.password IS '비밀번호';
COMMENT ON COLUMN Member.create_at IS '생성 시간';
COMMENT ON COLUMN Member.update_at IS '수정 시간';
COMMENT ON COLUMN Member.flag IS '플래그';