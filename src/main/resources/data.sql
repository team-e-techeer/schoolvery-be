INSERT INTO category (id, name, description, moddate, regdate) values (UNHEX(REPLACE(UUID(),'-','')), '한식', '한식입니다.',now(),now());
INSERT INTO category (id, name, description, moddate, regdate) values (UNHEX(REPLACE(UUID(),'-','')), '중식', '중식입니다.',now(),now());
INSERT INTO category (id, name, description, moddate, regdate) values (UNHEX(REPLACE(UUID(),'-','')), '일식', '일식입니다.',now(),now());
INSERT INTO category (id, name, description, moddate, regdate) values (UNHEX(REPLACE(UUID(),'-','')), '아시안', '아시안 입니다.',now(),now());
INSERT INTO category (id, name, description, moddate, regdate) values (UNHEX(REPLACE(UUID(),'-','')), '치킨', '치킨입니다.',now(),now());
INSERT INTO category (id, name, description, moddate, regdate) values (UNHEX(REPLACE(UUID(),'-','')), '피자', '피자입니다.',now(),now());
INSERT INTO category (id, name, description, moddate, regdate) values (UNHEX(REPLACE(UUID(),'-','')), '햄버거', '햄버거입니다.',now(),now());
INSERT INTO category (id, name, description, moddate, regdate) values (UNHEX(REPLACE(UUID(),'-','')), '분식', '분식 입니다.',now(),now());
INSERT INTO category (id, name, description, moddate, regdate) values (UNHEX(REPLACE(UUID(),'-','')), '커피', '커피 입니다.',now(),now());



INSERT INTO school (school_id, school_name) values (UNHEX(REPLACE(UUID(),'-','')), '충북대학교');
INSERT INTO school (school_id, school_name) values (UNHEX(REPLACE(UUID(),'-','')), '이화여자대학교');
INSERT INTO school (school_id, school_name) values (UNHEX(REPLACE(UUID(),'-','')), '한국공학대학교');
INSERT INTO school (school_id, school_name) values (UNHEX(REPLACE(UUID(),'-','')), '성결대학교');


# 일반 유저 or 관리자
INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_USER');
INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_ADMIN');
