-- 프로젝트 실행 시 해당 파일에 적힌 SQL 명령어 자동 실행
insert into user values(90001, sysdate(), 'User1', 'Pass1', '701010-1111111');
insert into user values(90002, sysdate(), 'User2', 'Pass2', '801010-2222222');
insert into user values(90003, sysdate(), 'User3', 'Pass3', '901010-3333333');

insert into post values(10001, 'My first post', 90001);
insert into post values(10002, 'My second post', 90001);
