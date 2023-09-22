-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS "public"."shopping_cart";
CREATE TABLE "public"."shopping_cart" (
  "id" int8 NOT NULL,
  "name" varchar(50) COLLATE "pg_catalog"."default",
  "image" varchar(100) COLLATE "pg_catalog"."default",
  "user_id" int8 NOT NULL,
  "dish_id" int8,
  "setmeal_id" int8,
  "dish_flavor_id" int8,
  "number" int4 NOT NULL,
  "amount" numeric(10,2) NOT NULL,
  "created_time" timestamp(6)
)
;
COMMENT ON COLUMN "public"."shopping_cart"."id" IS '主鍵';
COMMENT ON COLUMN "public"."shopping_cart"."name" IS '名稱';
COMMENT ON COLUMN "public"."shopping_cart"."image" IS '圖片';
COMMENT ON COLUMN "public"."shopping_cart"."user_id" IS '關聯客戶主键';
COMMENT ON COLUMN "public"."shopping_cart"."dish_id" IS '菜品ID';
COMMENT ON COLUMN "public"."shopping_cart"."setmeal_id" IS '套餐ID';
COMMENT ON COLUMN "public"."shopping_cart"."dish_flavor_id" IS '口味ID';
COMMENT ON COLUMN "public"."shopping_cart"."number" IS '數量';
COMMENT ON COLUMN "public"."shopping_cart"."amount" IS '金額';
COMMENT ON COLUMN "public"."shopping_cart"."created_time" IS '創建時間';
COMMENT ON TABLE "public"."shopping_cart" IS '購物車';

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table shopping_cart
-- ----------------------------
ALTER TABLE "public"."shopping_cart" ADD CONSTRAINT "shopping_cart_pkey" PRIMARY KEY ("id");
