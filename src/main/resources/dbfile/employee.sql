-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS "public"."employee";
CREATE TABLE "public"."employee" (
  "id" int8 NOT NULL,
  "name" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "username" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "phone_num" varchar(11) COLLATE "pg_catalog"."default" NOT NULL,
  "sex" varchar(7) COLLATE "pg_catalog"."default" NOT NULL,
  "id_number" varchar(18) COLLATE "pg_catalog"."default" NOT NULL,
  "status" int4 NOT NULL,
  "created_time" timestamp(6) NOT NULL,
  "updated_time" timestamp(6) NOT NULL,
  "created_user" int8 NOT NULL,
  "updated_user" int8 NOT NULL
)
;
COMMENT ON COLUMN "public"."employee"."id" IS '主鍵';
COMMENT ON COLUMN "public"."employee"."name" IS '用戶名稱';
COMMENT ON COLUMN "public"."employee"."username" IS '賬號名稱';
COMMENT ON COLUMN "public"."employee"."password" IS '密碼
';
COMMENT ON COLUMN "public"."employee"."phone_num" IS '手機號碼';
COMMENT ON COLUMN "public"."employee"."sex" IS '性別';
COMMENT ON COLUMN "public"."employee"."id_number" IS '身份證號';
COMMENT ON COLUMN "public"."employee"."status" IS '狀態：0禁用，1正常；';
COMMENT ON COLUMN "public"."employee"."created_time" IS '創建時間';
COMMENT ON COLUMN "public"."employee"."updated_time" IS '更新時間';
COMMENT ON COLUMN "public"."employee"."created_user" IS '創建人';
COMMENT ON COLUMN "public"."employee"."updated_user" IS '修改者';
COMMENT ON TABLE "public"."employee" IS '員工信息';

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO "public"."employee" VALUES (1, '約翰', 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', '13812312312', 'Male', '110101199001010047', 1, '2021-05-06 17:20:07', '2021-05-10 02:24:09', 1, 1);
INSERT INTO "public"."employee" VALUES (1702717474108903426, '詹姆斯', 'James', '96E79218965EB72C92A549DD5A330112', '13876281296', 'Male', '110420765324761', 1, '2023-09-16 01:14:15.452158', '2023-09-16 01:14:15.452158', 1, 1);
INSERT INTO "public"."employee" VALUES (1591332146269470722, '張三', 'zhangsan', '96E79218965EB72C92A549DD5A330112', '13462437422', 'Male', '132847678617286548', 1, '2022-11-12 16:28:44', '2023-09-22 19:57:01.679914', 1, 1);

-- ----------------------------
-- Indexes structure for table employee
-- ----------------------------
CREATE UNIQUE INDEX "idx_username" ON "public"."employee" USING btree (
  "username" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table employee
-- ----------------------------
ALTER TABLE "public"."employee" ADD CONSTRAINT "employee_pkey" PRIMARY KEY ("id");
