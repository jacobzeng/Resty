DROP TABLE IF EXISTS sec_user;
CREATE TABLE sec_user (
  id           BIGINT                                NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username     VARCHAR(50)                           UNIQUE NOT NULL
  COMMENT '登录名',
  email        VARCHAR(200) COMMENT '邮箱',
  mobile       VARCHAR(50) COMMENT '手机',
  password     VARCHAR(200)                          NOT NULL COMMENT '密码',
  delete_able  INT(1) NOT NULL DEFAULT 1 COMMENT '0不能删,1可以删',
  created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP   NOT NULL,
  updated_at   TIMESTAMP                             NULL ON UPDATE CURRENT_TIMESTAMP,
  deleted_at   TIMESTAMP                             NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '用户';

DROP TABLE IF EXISTS sec_role;
CREATE TABLE sec_role (
    id         BIGINT                                NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(50)                           NOT NULL COMMENT '名称',
    value      VARCHAR(50)                           NOT NULL COMMENT '值',
    intro      VARCHAR(255) COMMENT '简介',
    delete_able  INT(1) NOT NULL DEFAULT 1 COMMENT '0不能删,1可以删',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP   NOT NULL,
    updated_at TIMESTAMP                             NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP                             NULL
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8
COMMENT = '角色';

DROP TABLE IF EXISTS sec_user_role;
CREATE TABLE sec_user_role (
  id      BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '用户角色';

DROP TABLE IF EXISTS sec_permission;
CREATE TABLE sec_permission (
  id         BIGINT                                NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name       VARCHAR(50)                           NOT NULL
  COMMENT '名称',
  method     VARCHAR(10)                           NOT NULL
  COMMENT '方法',
  value      VARCHAR(50)                           NOT NULL
  COMMENT '值',
  url        VARCHAR(255) COMMENT 'url地址',
  intro      VARCHAR(255) COMMENT '简介',
  pid        BIGINT                                         DEFAULT 0
  COMMENT '父级id',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP   NOT NULL,
  updated_at TIMESTAMP                             NULL ON UPDATE CURRENT_TIMESTAMP,
  deleted_at TIMESTAMP                             NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '权限';

DROP TABLE IF EXISTS sec_role_permission;
CREATE TABLE sec_role_permission (
    id            BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id       BIGINT NOT NULL,
    permission_id BIGINT NOT NULL
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8
COMMENT = '角色权限';