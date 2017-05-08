INSERT INTO sec_role VALUES
(1,'超级管理员','R_ADMIN','',0,CURRENT_TIMESTAMP,NULL,NULL),
(2,'设备','R_DEVICE','',0,CURRENT_TIMESTAMP,NULL,NULL);

INSERT INTO sec_permission VALUES
(1,'获得当前会话信息','GET','P_SEC_SESSIONS_GET','/security/sessions','获得当前会话信息权限',0,CURRENT_TIMESTAMP,NULL,NULL),
(2,'人员管理','*','P_SEC_USERS','/security/users','人员管理权限',0,CURRENT_TIMESTAMP,NULL,NULL),
(3,'角色管理','*','P_SEC_ROLES','/security/roles','角色管理权限',0,CURRENT_TIMESTAMP,NULL,NULL),
(4,'权限管理','*','P_SEC_PERMISSIONS','/security/permissions','权限管理权限',0,CURRENT_TIMESTAMP,NULL,NULL),
(5,'数据管理权限','*','P_MGR','/mgr/**','数据管理权限',0,CURRENT_TIMESTAMP,NULL,NULL),
(6,'OPENAPI访问','*','P_OPENAPI','/openapi/**','获得OPENAPI访问权限',0,CURRENT_TIMESTAMP,NULL,NULL);

INSERT INTO sec_role_permission VALUES
(1,1,2),(2,1,1),(3,1,3),(4,1,4),(5,1,5),
(6,2,1),(7,2,6)
;

-- password: z@88888888
INSERT INTO sec_user VALUES
(1,'administrator','460142751@qq.com','13880193638','7d4691f56dea3a1a485d44bfff6c26265995b54a5a0663940eb4a3c88e5b993ca0d976816f9ae242e7c0a45d680e8000f5f73bd346b0b5050e9a9b5e8b1e5436',0,CURRENT_TIMESTAMP,NULL,NULL)
;

INSERT INTO sec_user_role VALUES (1,1,1);