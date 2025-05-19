-- RESET
DROP TABLE IF EXISTS Member_Role_Bridge;
DROP TABLE IF EXISTS Member;
DROP TABLE IF EXISTS Role;

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

-- TABLE
CREATE TABLE Member_Role_Bridge (
	id              BIGSERIAL   PRIMARY KEY,
	member_id       BIGSERIAL,
	role_id         BIGSERIAL,
	create_at       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
	update_at       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
	flag            BOOLEAN     NOT NULL DEFAULT FALSE,
	FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE,
  FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

-- COMMENT
COMMENT ON TABLE Member_Role_Bridge IS '회원 권한 브릿지';

COMMENT ON COLUMN Member_Role_Bridge.id IS '고유번호';
COMMENT ON COLUMN Member_Role_Bridge.member_id IS '회원 고유번호';
COMMENT ON COLUMN Member_Role_Bridge.role_id IS '권한 고유번호';
COMMENT ON COLUMN Member_Role_Bridge.create_at IS '생성 시간';
COMMENT ON COLUMN Member_Role_Bridge.create_at IS '수정 시간';
COMMENT ON COLUMN Member_Role_Bridge.flag IS '플래그';

INSERT INTO Role (name, description) VALUES ('TESTER', '테스터 권한입니다');
INSERT INTO Role (name, description) VALUES ('MEMBER', '회원 권한입니다');
INSERT INTO Member (username, email, display_name, password) VALUES ('tester01', 'tester@gmail.com', '테스터01', '1234');
INSERT INTO Member_Role_Bridge (member_id, role_id) VALUES (1, 1);