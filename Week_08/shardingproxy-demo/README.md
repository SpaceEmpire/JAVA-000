Week08 作业题目（周四）：
2.（必做）设计对前面的订单表数据进行水平分库分表，拆分 2 个库，每个库 16 张表。
并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，上传到 Github。

#### springboot + shardingproxy (5.0.0-alpha)

* 查询

```
curl -X POST \
  http://127.0.0.1:8088/order-service/order/find \
  -H 'content-type: application/x-www-form-urlencoded' \
  -d orderId=543725524401762304
  
[INFO ] 09:50:45.606 [ShardingSphere-Command-2] ShardingSphere-SQL - Logic SQL: select * from t_order where order_id = 543725524401762304
[INFO ] 09:50:45.629 [ShardingSphere-Command-2] ShardingSphere-SQL - SQLStatement: MySQLSelectStatement(limit=Optional.empty, lock=Optional.empty)
[INFO ] 09:50:45.631 [ShardingSphere-Command-2] ShardingSphere-SQL - Actual SQL: ds_0 ::: select * from t_order_0 where order_id = 543725524401762304
[INFO ] 09:50:45.631 [ShardingSphere-Command-2] ShardingSphere-SQL - Actual SQL: ds_1 ::: select * from t_order_0 where order_id = 543725524401762304
```

* 添加

```
curl -X POST \
  http://127.0.0.1:8088/order-service/order/add \
  -H 'content-type: application/json' \
  -d '{
	"userId":9,
	"amount":100,
	"status":1
}'

[INFO ] 09:32:09.252 [ShardingSphere-Command-1] ShardingSphere-SQL - Logic SQL: insert into t_order(`user_id`,`amount`,`status`,`create_time`)
        values (9,100,1,unix_timestamp(now()))
[INFO ] 09:32:09.252 [ShardingSphere-Command-1] ShardingSphere-SQL - SQLStatement: MySQLInsertStatement(setAssignment=Optional.empty, onDuplicateKeyColumns=Optional.empty)
[INFO ] 09:32:09.252 [ShardingSphere-Command-1] ShardingSphere-SQL - Actual SQL: ds_0 ::: insert into t_order_1(`user_id`,`amount`,`status`,`create_time`, order_id)
        values (9, 100, 1, unix_timestamp(now()), 543725785706901505)
[INFO ] 09:32:09.253 [ShardingSphere-Command-1] ShardingSphere-SQL - Actual SQL: ds_1 ::: insert into t_order_1(`user_id`,`amount`,`status`,`create_time`, order_id)
        values (9, 100, 1, unix_timestamp(now()), 543725785706901505)
```

* 删除

```
curl -X POST \
  http://127.0.0.1:8088/order-service/order/del 
  -H 'content-type: application/x-www-form-urlencoded' \
  -d orderId=1
  
[INFO ] 09:36:43.625 [ShardingSphere-Command-5] ShardingSphere-SQL - Logic SQL: delete from t_order where order_id=1
[INFO ] 09:36:43.626 [ShardingSphere-Command-5] ShardingSphere-SQL - SQLStatement: MySQLDeleteStatement(orderBy=Optional.empty, limit=Optional.empty)
[INFO ] 09:36:43.626 [ShardingSphere-Command-5] ShardingSphere-SQL - Actual SQL: ds_0 ::: delete from t_order_1 where order_id=1
[INFO ] 09:36:43.626 [ShardingSphere-Command-5] ShardingSphere-SQL - Actual SQL: ds_1 ::: delete from t_order_1 where order_id=1  
```

* 更新

```
curl -X POST \
  http://127.0.0.1:8088/order-service/order/update \
  -H 'content-type: application/json' \
  -d '{
	"orderId":543725524401762304,
	"status":3
}'

[INFO ] 09:51:32.674 [ShardingSphere-Command-0] ShardingSphere-SQL - Logic SQL: update t_order set `status`=3,update_time=unix_timestamp(now())
        where order_id=543725524401762304
[INFO ] 09:51:32.674 [ShardingSphere-Command-0] ShardingSphere-SQL - SQLStatement: MySQLUpdateStatement(orderBy=Optional.empty, limit=Optional.empty)
[INFO ] 09:51:32.674 [ShardingSphere-Command-0] ShardingSphere-SQL - Actual SQL: ds_0 ::: update t_order_0 set `status`=3,update_time=unix_timestamp(now())
        where order_id=543725524401762304
[INFO ] 09:51:32.674 [ShardingSphere-Command-0] ShardingSphere-SQL - Actual SQL: ds_1 ::: update t_order_0 set `status`=3,update_time=unix_timestamp(now())
        where order_id=543725524401762304
```
