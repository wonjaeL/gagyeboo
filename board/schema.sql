CREATE TABLE `t_board` (
	`board_idx` INT(11) NOT NULL AUTO_INCREMENT COMMENT '글번호',
	`title` VARCHAR(300) NOT NULL COMMENT '제목' COLLATE 'utf8_general_ci',
	`contents` MEDIUMTEXT NULL DEFAULT NULL COMMENT '내용' COLLATE 'utf8_general_ci',
	`hit_cnt` INT(11) NULL DEFAULT '0' COMMENT '조회수',
	`reply_cnt` INT(11) NULL DEFAULT '0' COMMENT '댓글수',
	`created_datetime` DATETIME NULL DEFAULT NULL COMMENT '작성시간',
	`creator_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '생성자' COLLATE 'utf8_general_ci',
	`updated_datetime` DATETIME NULL DEFAULT NULL COMMENT '수정시간',
	`updater_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '수정자' COLLATE 'utf8_general_ci',
	`deleted_yn` CHAR(1) NULL DEFAULT 'N' COMMENT '삭제여부' COLLATE 'utf8_general_ci',
	PRIMARY KEY (`board_idx`) USING BTREE,
	INDEX `title` (`title`) USING BTREE
)
COMMENT='게시판'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=8
;

CREATE TABLE `t_reply` (
	`reply_no` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '댓글번호',
	`board_idx` INT(11) NULL DEFAULT NULL COMMENT '게시글ID',
	`reply_memo` VARCHAR(200) NULL DEFAULT NULL COMMENT '댓글' COLLATE 'utf8_general_ci',
	`created_datetime` DATETIME NULL DEFAULT NULL COMMENT '작성일시',
	`creator_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '작성자' COLLATE 'utf8_general_ci',
	PRIMARY KEY (`reply_no`) USING BTREE,
	INDEX `FK_t_reply_t_board` (`board_idx`) USING BTREE,
	CONSTRAINT `FK_t_reply_t_board` FOREIGN KEY (`board_idx`) REFERENCES `board`.`t_board` (`board_idx`) ON UPDATE NO ACTION ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=13
;
