insert into jhi_authority values('ROLE_ADMIN');
insert into jhi_authority values('ROLE_USER');

insert into jhi_user values(1,'system','$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG','System', 'System', 'system@localhost',TRUE, 'en', null,null,'system', '2016-06-13 10:45:27.337',null,null,null);
insert into jhi_user values(2,'anonymoususer','$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO','Anonymous', 'User', 'anonymous@localhost',TRUE, 'en', null,null,'system', '2016-06-13 10:45:27.337',null,null,null);
insert into jhi_user values(3,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC','Admin', 'Admin', 'admin@localhost',TRUE, 'en', null,null,'system', '2016-06-13 10:45:27.337',null,null,null);
insert into jhi_user values(4,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K','User', 'User', 'system@localhost',TRUE, 'en', null,null,'system', '2016-06-13 10:45:27.337',null,null,null);

insert into jhi_user_authority values(1, 'ROLE_ADMIN');
insert into jhi_user_authority values(1, 'ROLE_USER');
insert into jhi_user_authority values(2, 'ROLE_USER');
insert into jhi_user_authority values(4, 'ROLE_USER');
insert into jhi_user_authority values(3, 'ROLE_USER');