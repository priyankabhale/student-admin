CREATE SCHEMA `studentadmin` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `studclass_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8mgxryqf9pn297qcc2005mn1w` (`studclass_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


CREATE TABLE `stud_class` (
  `id` bigint(20) NOT NULL,
  `class_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


CREATE TABLE `address` (
  `id` bigint(20) NOT NULL,
  `address_string` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


CREATE TABLE `address_stud` (
  `stud_id` bigint(20) NOT NULL,
  `address_id` bigint(20) NOT NULL,
  KEY `FKnbc3nockn5kopixi39stt939y` (`address_id`),
  KEY `FKgb53ksvnuy9dpcxcgfc60rb73` (`stud_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
