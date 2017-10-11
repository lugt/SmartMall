-- phpMyAdmin SQL Dump
-- version 3.4.9
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2017 年 10 月 11 日 17:29
-- 服务器版本: 5.5.20
-- PHP 版本: 5.3.9

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `smartmall`
--

-- --------------------------------------------------------

--
-- 表的结构 `smart_delivery_addr`
--

CREATE TABLE IF NOT EXISTS `smart_delivery_addr` (
  `addrid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(9) DEFAULT NULL,
  `mobile` int(11) DEFAULT NULL,
  `addr` tinytext NOT NULL,
  `memo` tinytext,
  `recver` tinytext,
  `type` int(5) DEFAULT NULL,
  `province` int(2) DEFAULT NULL,
  `district` int(2) DEFAULT NULL,
  `city` int(2) DEFAULT NULL,
  PRIMARY KEY (`addrid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `smart_goods`
--

CREATE TABLE IF NOT EXISTS `smart_goods` (
  `goodid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `name` varchar(50) NOT NULL COMMENT '商品名称',
  `goods_no` varchar(20) NOT NULL COMMENT '商品的货号',
  `model_id` int(11) unsigned NOT NULL COMMENT '模型ID',
  `sell_price` decimal(15,2) NOT NULL COMMENT '销售价格',
  `market_price` decimal(15,2) DEFAULT NULL COMMENT '市场价格',
  `cost_price` decimal(15,2) DEFAULT NULL COMMENT '成本价格',
  `up_time` datetime DEFAULT NULL COMMENT '上架时间',
  `down_time` datetime DEFAULT NULL COMMENT '下架时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `store_nums` int(11) NOT NULL DEFAULT '0' COMMENT '库存',
  `img` varchar(255) DEFAULT NULL COMMENT '原图',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除 0未删除 1已删除 2下架',
  `content` text COMMENT '商品描述',
  `keywords` varchar(255) DEFAULT NULL COMMENT 'SEO关键词',
  `description` varchar(255) DEFAULT NULL COMMENT 'SEO描述',
  `search_words` text COMMENT '产品搜索词库,逗号分隔',
  `weight` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '重量',
  `point` int(11) NOT NULL DEFAULT '0' COMMENT '积分',
  `unit` varchar(10) DEFAULT NULL COMMENT '计量单位',
  `brand_id` int(11) DEFAULT NULL COMMENT '品牌ID',
  `visit` int(11) NOT NULL DEFAULT '0' COMMENT '浏览次数',
  `favorite` int(11) NOT NULL DEFAULT '0' COMMENT '收藏次数',
  `sort` smallint(5) NOT NULL DEFAULT '99' COMMENT '排序',
  `list_img` varchar(255) DEFAULT NULL COMMENT '列表页缩略图 大图',
  `small_img` varchar(255) DEFAULT NULL COMMENT '详细页缩略图 小图',
  `spec_array` text COMMENT '序列化存储规格,key值为规则ID，value为此商品具有的规格值',
  `exp` smallint(5) NOT NULL DEFAULT '0' COMMENT '经验值',
  PRIMARY KEY (`goodid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='商品信息表' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `smart_local_delivery`
--

CREATE TABLE IF NOT EXISTS `smart_local_delivery` (
  `deliverid` int(10) NOT NULL,
  `orderid` int(10) DEFAULT NULL,
  `uid` int(10) NOT NULL,
  `type` int(5) DEFAULT NULL,
  `status` int(5) DEFAULT NULL,
  `sender` text,
  `address` int(10) DEFAULT NULL,
  `carrier` text,
  `starttime` int(10) DEFAULT NULL,
  `reservetime` int(10) DEFAULT NULL,
  `packagetime` int(10) DEFAULT NULL,
  `accepttime` int(10) DEFAULT NULL,
  `confirmtime` int(10) DEFAULT NULL,
  `logs` text,
  PRIMARY KEY (`deliverid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `smart_users`
--

CREATE TABLE IF NOT EXISTS `smart_users` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `usn` varchar(40) NOT NULL,
  `pss` tinytext NOT NULL,
  `name` tinytext,
  `title` tinytext,
  `priv` tinytext,
  `state` int(11) NOT NULL,
  `sess` tinytext,
  `phone` tinytext,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `usn` (`usn`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- 转存表中的数据 `smart_users`
--

INSERT INTO `smart_users` (`uid`, `usn`, `pss`, `name`, `title`, `priv`, `state`, `sess`, `phone`) VALUES
(3, 'cell_13520583918', '64e604787cbf194841e7b68d7cd28786f6c9a0a3ab9f8b0a0e87cb4387ab0107', 'HelloWorld', NULL, 'view,order,deliver,pay', 0, 'jpnaIGRD0hduz4z7CF9', '13520583918'),
(4, 'cell_13520583919', '64e604787cbf194841e7b68d7cd28786f6c9a0a3ab9f8b0a0e87cb4387ab0107', 'HelloWorld', NULL, 'view,order,deliver,pay', 0, 'b95owDwyksLtuH1GuMQ', '13520583919'),
(5, 'cell_13520583920', '64e604787cbf194841e7b68d7cd28786f6c9a0a3ab9f8b0a0e87cb4387ab0107', 'HelloWorld', NULL, 'view,order,deliver,pay', 0, 'ljSnbAvusICVgYkdc1b', '13520583920'),
(6, 'cell_13520583921', '64e604787cbf194841e7b68d7cd28786f6c9a0a3ab9f8b0a0e87cb4387ab0107', 'HelloWorld', NULL, 'view,order,deliver,pay', 0, 'bNDadaLc85DpaQLl0Df', '13520583921'),
(7, 'cell_13520583922', '64e604787cbf194841e7b68d7cd28786f6c9a0a3ab9f8b0a0e87cb4387ab0107', 'HelloWorld', NULL, 'view,order,deliver,pay', 0, 'IRGqapakOQMnr25NyEB', '13520583922');

-- --------------------------------------------------------

--
-- 表的结构 `smart_user_addrs`
--

CREATE TABLE IF NOT EXISTS `smart_user_addrs` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `addr1` int(11) DEFAULT NULL,
  `addr2` int(11) DEFAULT NULL,
  `addr3` int(11) DEFAULT NULL,
  `addr4` int(11) DEFAULT NULL,
  `addr5` int(11) DEFAULT NULL,
  `addr6` int(11) DEFAULT NULL,
  `addr7` int(11) DEFAULT NULL,
  `addr8` int(11) DEFAULT NULL,
  `addr9` int(11) DEFAULT NULL,
  `defaultaddr` int(1) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
