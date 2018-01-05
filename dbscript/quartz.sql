CREATE TABLE `schedule_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名',
  `job_group` varchar(255) DEFAULT NULL COMMENT '任务组',
  `method_name` varchar(255) DEFAULT NULL COMMENT '要执行的方法',
  `bean_class` varchar(255) DEFAULT NULL COMMENT '定时任务所在的类路径',
  `status` int(11) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `cron_expression` varchar(255) DEFAULT NULL COMMENT '时间表达式',
  `params` varchar(255) DEFAULT NULL COMMENT '参数',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;