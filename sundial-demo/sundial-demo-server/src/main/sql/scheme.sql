CREATE TABLE `sundial_task` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `task_name` VARCHAR(64) NOT NULL COMMENT '任务名称',
  `task_key` VARCHAR(128) NOT NULL COMMENT '任务唯一key',
  `task_type` VARCHAR(32) NOT NULL COMMENT '任务类型',
  `service_name` VARCHAR(64) NOT NULL COMMENT '任务所在的service-name',
  `execution_status` VARCHAR(32) NOT NULL COMMENT '任务执行状态',
  `last_execution_result` VARCHAR(32) DEFAULT NULL COMMENT '上一次执行结果',
  `last_finish_time` TIMESTAMP NULL COMMENT '上一次执行结束的时间',
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_task_key` (`task_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='定时任务表';