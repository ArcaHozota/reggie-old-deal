-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS "public"."user";
CREATE TABLE "public"."user" (
  "id" int8 NOT NULL,
  "name" varchar(50) COLLATE "pg_catalog"."default",
  "phone_num" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "sex" varchar(7) COLLATE "pg_catalog"."default",
  "id_number" varchar(18) COLLATE "pg_catalog"."default",
  "avatar" varchar(500) COLLATE "pg_catalog"."default",
  "status" int4
)
;
COMMENT ON COLUMN "public"."user"."id" IS '主鍵';
COMMENT ON COLUMN "public"."user"."name" IS '姓名';
COMMENT ON COLUMN "public"."user"."phone_num" IS '手機號碼';
COMMENT ON COLUMN "public"."user"."sex" IS '性別：0女，1男；';
COMMENT ON COLUMN "public"."user"."id_number" IS '身份證號';
COMMENT ON COLUMN "public"."user"."avatar" IS '頭像';
COMMENT ON COLUMN "public"."user"."status" IS '狀態：0禁用，1正常；';
COMMENT ON TABLE "public"."user" IS '客戶信息';

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table user
-- ----------------------------
ALTER TABLE "public"."user" ADD CONSTRAINT "user_pkey" PRIMARY KEY ("id");
