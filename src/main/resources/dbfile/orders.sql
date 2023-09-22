-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS "public"."orders";
CREATE TABLE "public"."orders" (
  "id" int8 NOT NULL,
  "number" varchar(50) COLLATE "pg_catalog"."default",
  "status" int4 NOT NULL,
  "user_id" int8 NOT NULL,
  "address_book_id" int8 NOT NULL,
  "orders_time" timestamp(6) NOT NULL,
  "checkout_time" timestamp(6) NOT NULL,
  "payment_method" int4 NOT NULL,
  "amount" numeric(10,2) NOT NULL,
  "remark" varchar(100) COLLATE "pg_catalog"."default",
  "phone_num" varchar(255) COLLATE "pg_catalog"."default",
  "address" varchar(255) COLLATE "pg_catalog"."default",
  "user_name" varchar(255) COLLATE "pg_catalog"."default",
  "consignee" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."orders"."id" IS '主鍵';
COMMENT ON COLUMN "public"."orders"."number" IS '訂單號';
COMMENT ON COLUMN "public"."orders"."status" IS '訂單狀態：1待付款，2待派送，3已派送，4已完成，5已取消；';
COMMENT ON COLUMN "public"."orders"."user_id" IS '下單客戶';
COMMENT ON COLUMN "public"."orders"."address_book_id" IS '地址ID';
COMMENT ON COLUMN "public"."orders"."orders_time" IS '訂單時間';
COMMENT ON COLUMN "public"."orders"."checkout_time" IS '付款時間';
COMMENT ON COLUMN "public"."orders"."payment_method" IS '支付方式：1微信，2支付寶；';
COMMENT ON COLUMN "public"."orders"."amount" IS '實收金額';
COMMENT ON COLUMN "public"."orders"."remark" IS '備注';
COMMENT ON COLUMN "public"."orders"."phone_num" IS '手機號碼';
COMMENT ON COLUMN "public"."orders"."address" IS '派送地址';
COMMENT ON COLUMN "public"."orders"."user_name" IS '客戶姓名';
COMMENT ON COLUMN "public"."orders"."consignee" IS '收貨人';
COMMENT ON TABLE "public"."orders" IS '訂單表';

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table orders
-- ----------------------------
ALTER TABLE "public"."orders" ADD CONSTRAINT "orders_pkey" PRIMARY KEY ("id");
