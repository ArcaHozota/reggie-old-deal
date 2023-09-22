-- ----------------------------
-- Table structure for address_book
-- ----------------------------
DROP TABLE IF EXISTS "public"."address_book";
CREATE TABLE "public"."address_book" (
  "id" int8 NOT NULL,
  "user_id" int8 NOT NULL,
  "consignee" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "sex" varchar(7) COLLATE "pg_catalog"."default" NOT NULL,
  "phone_num" varchar(11) COLLATE "pg_catalog"."default" NOT NULL,
  "province_code" varchar(12) COLLATE "pg_catalog"."default",
  "province_name" varchar(32) COLLATE "pg_catalog"."default",
  "city_code" varchar(12) COLLATE "pg_catalog"."default",
  "city_name" varchar(32) COLLATE "pg_catalog"."default",
  "district_code" varchar(12) COLLATE "pg_catalog"."default",
  "district_name" varchar(32) COLLATE "pg_catalog"."default",
  "detail" varchar(200) COLLATE "pg_catalog"."default",
  "label" varchar(100) COLLATE "pg_catalog"."default",
  "is_default" int2 NOT NULL,
  "created_time" timestamp(6) NOT NULL,
  "updated_time" timestamp(6) NOT NULL,
  "created_user" int8 NOT NULL,
  "updated_user" int8 NOT NULL,
  "is_deleted" varchar(7) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."address_book"."id" IS '主鍵';
COMMENT ON COLUMN "public"."address_book"."user_id" IS '用戶ID';
COMMENT ON COLUMN "public"."address_book"."consignee" IS '收貨人';
COMMENT ON COLUMN "public"."address_book"."sex" IS '性別';
COMMENT ON COLUMN "public"."address_book"."phone_num" IS '手機號';
COMMENT ON COLUMN "public"."address_book"."province_code" IS '省級行政區劃編號';
COMMENT ON COLUMN "public"."address_book"."province_name" IS '省級名稱';
COMMENT ON COLUMN "public"."address_book"."city_code" IS '市級行政區劃編號';
COMMENT ON COLUMN "public"."address_book"."city_name" IS '市級名稱';
COMMENT ON COLUMN "public"."address_book"."district_code" IS '區級行政區劃編號';
COMMENT ON COLUMN "public"."address_book"."district_name" IS '區級名稱';
COMMENT ON COLUMN "public"."address_book"."detail" IS '詳細住址';
COMMENT ON COLUMN "public"."address_book"."label" IS '標簽';
COMMENT ON COLUMN "public"."address_book"."is_default" IS '默認：0否，1是；';
COMMENT ON COLUMN "public"."address_book"."created_time" IS '創建時間';
COMMENT ON COLUMN "public"."address_book"."updated_time" IS '更新時間';
COMMENT ON COLUMN "public"."address_book"."created_user" IS '創建人';
COMMENT ON COLUMN "public"."address_book"."updated_user" IS '修改者';
COMMENT ON COLUMN "public"."address_book"."is_deleted" IS '邏輯刪除字段';
COMMENT ON TABLE "public"."address_book" IS '地址管理';

-- ----------------------------
-- Records of address_book
-- ----------------------------
INSERT INTO "public"."address_book" VALUES (1417414526093082626, 1417012167126876162, '小明', '1', '13812345678', NULL, NULL, NULL, NULL, NULL, NULL, '昌平区金燕龙办公楼', '公司', 1, '2021-07-20 17:22:12', '2021-07-20 17:26:33', 1417012167126876162, 1417012167126876162, 'visible');
INSERT INTO "public"."address_book" VALUES (1417414926166769666, 1417012167126876162, '小李', '1', '13512345678', NULL, NULL, NULL, NULL, NULL, NULL, '测试', '家', 0, '2021-07-20 17:23:47', '2021-07-20 17:23:47', 1417012167126876162, 1417012167126876162, 'visible');

-- ----------------------------
-- Primary Key structure for table address_book
-- ----------------------------
ALTER TABLE "public"."address_book" ADD CONSTRAINT "address_book_pkey" PRIMARY KEY ("id");
