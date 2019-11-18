
### 关于目录结构
1. 本模板的目录结构适用于服务较为单一的项目，如专注于服务端Api接口的项目，不适合业务模块较多的项目如APP类；
2. 如果模块较多应以业务模块为单位，每个业务模块中单独起controller、service、model等目录结构；

### 用于测试的表结构
```mysql
CREATE TABLE `t_test` (
  `f_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `f_name` varchar(16) DEFAULT NULL COMMENT '名称',
  `f_number` int(11) DEFAULT '0' COMMENT '数量',
  `f_date` date DEFAULT NULL COMMENT '日期',
  `f_status` tinyint(4) DEFAULT '0' COMMENT '状态：-1-失败; 0-初始态; 1-处理中; 2-成功;',
  `f_amount` decimal(12,2) DEFAULT '0.00' COMMENT '金额',
  `f_remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `f_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `f_modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`f_id`) USING BTREE,
  KEY `idx_date` (`f_date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='测试表';
```

### 关于HTTP请求的说明
1. 原生方式：java.net包下的HttpURLConnection、URL
2. 基于Apache的org.apache.http提供的工具包
3. spring提供的org.springframework.web工具包
- 推荐使用2、3两种方式。
