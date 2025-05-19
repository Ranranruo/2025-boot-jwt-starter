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