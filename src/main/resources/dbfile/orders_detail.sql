-- ----------------------------
-- Table structure for orders_detail
-- ----------------------------
DROP TABLE IF EXISTS "public"."orders_detail";
CREATE TABLE "public"."orders_detail" (
  "id" int8 NOT NULL,
  "name" varchar(50) COLLATE "pg_catalog"."default",
  "image" varchar(100) COLLATE "pg_catalog"."default",
  "orders_id" int8 NOT NULL,
  "dish_id" int8,
  "setmeal_id" int8,
  "dish_flavor_id" int8,
  "number" int4 NOT NULL,
  "amount" numeric(10,2) NOT NULL
)
;
COMMENT ON COLUMN "public"."orders_detail"."id" IS '主鍵';
COMMENT ON COLUMN "public"."orders_detail"."name" IS '名字';
COMMENT ON COLUMN "public"."orders_detail"."image" IS '圖片';
COMMENT ON COLUMN "public"."orders_detail"."orders_id" IS '訂單ID';
COMMENT ON COLUMN "public"."orders_detail"."dish_id" IS '菜品ID';
COMMENT ON COLUMN "public"."orders_detail"."setmeal_id" IS '套餐ID';
COMMENT ON COLUMN "public"."orders_detail"."dish_flavor_id" IS '口味ID';
COMMENT ON COLUMN "public"."orders_detail"."number" IS '數量';
COMMENT ON COLUMN "public"."orders_detail"."amount" IS '金額';
COMMENT ON TABLE "public"."orders_detail" IS '訂單明細表';

-- ----------------------------
-- Records of orders_detail
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table orders_detail
-- ----------------------------
ALTER TABLE "public"."orders_detail" ADD CONSTRAINT "order_detail_pkey" PRIMARY KEY ("id");
