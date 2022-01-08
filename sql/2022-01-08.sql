# 完善支付宝异步回调通知
alter table sys_order change trade_no out_trade_no bigint auto_increment comment '商家订单号';
alter table sys_order add trade_no varchar(30) comment '支付宝交易号，支付宝交易凭证号' after out_trade_no;
alter table sys_order add total_amount varchar(10) not null comment '订单金额。单位为人民币（元），精确到小数点后 2 位' after trade_no;
alter table sys_order add receipt_amount varchar(10) comment '实收金额' after total_amount;
INSERT INTO qxx.platform_info (type, `key`, value, `desc`, create_user, create_time, update_user, update_time)
VALUES ('alipay', 'seller_id', '', '支付宝开放平台账号ID', 1, '2022-01-08 21:39:00', null, null);