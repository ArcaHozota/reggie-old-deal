-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS "public"."category";
CREATE TABLE "public"."category" (
  "id" int8 NOT NULL,
  "type" int4,
  "name" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "sort" int4 NOT NULL,
  "created_time" timestamp(6) NOT NULL,
  "updated_time" timestamp(6) NOT NULL,
  "created_user" int8 NOT NULL,
  "updated_user" int8 NOT NULL,
  "is_deleted" varchar(7) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."category"."id" IS '主鍵';
COMMENT ON COLUMN "public"."category"."type" IS '類型：1、菜品分類；2、套餐分類；';
COMMENT ON COLUMN "public"."category"."name" IS '分類名稱';
COMMENT ON COLUMN "public"."category"."sort" IS '順序';
COMMENT ON COLUMN "public"."category"."created_time" IS '創建時間';
COMMENT ON COLUMN "public"."category"."updated_time" IS '更新時間';
COMMENT ON COLUMN "public"."category"."created_user" IS '創建人';
COMMENT ON COLUMN "public"."category"."updated_user" IS '修改者';
COMMENT ON COLUMN "public"."category"."is_deleted" IS '邏輯刪除字段';
COMMENT ON TABLE "public"."category" IS '菜品及套餐分類';

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO "public"."category" VALUES (1397844263642378242, 1, '湘菜', 1, '2021-05-27 09:16:58', '2021-07-15 20:25:23', 1, 1, 'visible');
INSERT INTO "public"."category" VALUES (1397844303408574465, 1, '川菜', 2, '2021-05-27 09:17:07', '2021-06-02 14:27:22', 1, 1, 'visible');
INSERT INTO "public"."category" VALUES (1397844391040167938, 1, '粤菜', 3, '2021-05-27 09:17:28', '2021-07-09 14:37:13', 1, 1, 'visible');
INSERT INTO "public"."category" VALUES (1413341197421846529, 1, '飲料', 11, '2021-07-09 11:36:15', '2021-07-09 14:39:15', 1, 1, 'visible');
INSERT INTO "public"."category" VALUES (1413342269393674242, 2, '商務套餐', 5, '2021-07-09 11:40:30', '2021-07-09 14:43:45', 1, 1, 'visible');
INSERT INTO "public"."category" VALUES (1413384954989060097, 1, '主食', 12, '2021-07-09 14:30:07', '2021-07-09 14:39:19', 1, 1, 'visible');
INSERT INTO "public"."category" VALUES (1413386191767674881, 2, '兒童套餐', 6, '2021-07-09 14:35:02', '2021-07-09 14:39:05', 1, 1, 'visible');
INSERT INTO "public"."category" VALUES (1594870808064147458, 1, '淮揚菜', 4, '2022-11-22 10:50:07', '2022-11-22 10:50:07', 1, 1, 'visible');
INSERT INTO "public"."category" VALUES (1594871261233528834, 2, '精緻川菜套餐', 7, '2022-11-22 10:51:55', '2022-11-22 10:51:55', 1, 1, 'visible');
INSERT INTO "public"."category" VALUES (1594871505178443778, 2, '超辣湘菜套餐', 9, '2022-11-22 10:52:53', '2023-09-16 10:54:59.038331', 1, 1, 'removed');
INSERT INTO "public"."category" VALUES (1596466856024866818, 1, '精品魯菜', 10, '2022-11-26 20:32:14', '2023-09-16 10:55:08.516573', 1, 1, 'removed');
INSERT INTO "public"."category" VALUES (1594871383115808770, 1, '魯菜', 8, '2022-11-22 10:52:24', '2023-09-20 11:35:00.955787', 1, 1, 'visible');

-- ----------------------------
-- Indexes structure for table category
-- ----------------------------
CREATE UNIQUE INDEX "idx_category_name" ON "public"."category" USING btree (
  "name" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table category
-- ----------------------------
ALTER TABLE "public"."category" ADD CONSTRAINT "category_pkey" PRIMARY KEY ("id");
