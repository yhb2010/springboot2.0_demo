DROP TABLE IF EXISTS `tb_message_package_no`;
CREATE TABLE `tb_message_package_no` (
  `no` int(11) DEFAULT '0' COMMENT '消息包编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息包编号表';