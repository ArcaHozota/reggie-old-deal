-- ----------------------------
-- Table structure for setmeal
-- ----------------------------
DROP TABLE IF EXISTS "public"."setmeal";
CREATE TABLE "public"."setmeal" (
  "id" int8 NOT NULL,
  "category_id" int8 NOT NULL,
  "name" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "price" numeric(10,2) NOT NULL,
  "status" int4,
  "code" varchar(32) COLLATE "pg_catalog"."default",
  "description" varchar(512) COLLATE "pg_catalog"."default",
  "image" varchar(255) COLLATE "pg_catalog"."default",
  "created_time" timestamp(6) NOT NULL,
  "updated_time" timestamp(6) NOT NULL,
  "created_user" int8 NOT NULL,
  "updated_user" int8 NOT NULL,
  "is_deleted" varchar(7) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."setmeal"."id" IS '主鍵';
COMMENT ON COLUMN "public"."setmeal"."category_id" IS '菜品分類ID';
COMMENT ON COLUMN "public"."setmeal"."name" IS '套餐名稱';
COMMENT ON COLUMN "public"."setmeal"."price" IS '套餐價格';
COMMENT ON COLUMN "public"."setmeal"."status" IS '狀態：0停用，1啓用；';
COMMENT ON COLUMN "public"."setmeal"."code" IS '編碼';
COMMENT ON COLUMN "public"."setmeal"."description" IS '描述信息';
COMMENT ON COLUMN "public"."setmeal"."image" IS '圖片';
COMMENT ON COLUMN "public"."setmeal"."created_time" IS '創建時間';
COMMENT ON COLUMN "public"."setmeal"."updated_time" IS '更新時間';
COMMENT ON COLUMN "public"."setmeal"."created_user" IS '創建人';
COMMENT ON COLUMN "public"."setmeal"."updated_user" IS '修改者';
COMMENT ON COLUMN "public"."setmeal"."is_deleted" IS '邏輯刪除字段';
COMMENT ON TABLE "public"."setmeal" IS '套餐';

-- ----------------------------
-- Records of setmeal
-- ----------------------------
INSERT INTO "public"."setmeal" VALUES (1152412980932517888, 1594871261233528834, '麻辣川菜套餐', 77500.00, 0, '', '麻辣鮮香回味無窮', '272e27cc-98b6-448e-8bda-3b01866f3842.jpg', '2023-09-16 02:17:45', '2023-09-19 10:53:28.017147', 1, 1, 'removed');
INSERT INTO "public"."setmeal" VALUES (1415580119015145474, 1413386191767674881, '兒童套餐A計劃', 4000.00, 1, '', '兒時記憶回味無窮', 'cb67e750-12ff-4c15-b4a0-57c7c74b3f08.jpg', '2021-07-15 15:52:55', '2023-09-20 11:30:01.740221', 1415576781934608386, 1, 'visible');
INSERT INTO "public"."setmeal" VALUES (1702870054868918274, 1413342269393674242, '優惠燒烤套餐B計劃', 69800.00, 1, '', '量大優惠好吃不貴', 'a3edac12-6765-4ca7-9632-567836101c88.jpeg', '2023-09-16 11:20:33', '2023-09-22 19:57:31.625273', 1, 1, 'visible');

-- ----------------------------
-- Indexes structure for table setmeal
-- ----------------------------
CREATE UNIQUE INDEX "idx_setmeal_name" ON "public"."setmeal" USING btree (
  "name" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table setmeal
-- ----------------------------
ALTER TABLE "public"."setmeal" ADD CONSTRAINT "setmeal_pkey" PRIMARY KEY ("id");
