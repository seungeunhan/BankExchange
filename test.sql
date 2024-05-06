-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        8.0.36 - MySQL Community Server - GPL
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- test 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET euckr */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `test`;

-- 테이블 test.account 구조 내보내기
CREATE TABLE IF NOT EXISTS `account` (
  `account_num` char(100) NOT NULL COMMENT '계좌 번호',
  `id` char(100) NOT NULL COMMENT '계정 아이디',
  `USD` double NOT NULL DEFAULT '10000' COMMENT '보유금',
  `JPY` double NOT NULL DEFAULT '10000',
  `THB` double NOT NULL DEFAULT '10000',
  `AUD` double NOT NULL DEFAULT '10000',
  `CAD` double NOT NULL DEFAULT '10000',
  `CHF` double NOT NULL DEFAULT '10000',
  `CNY` double NOT NULL DEFAULT '10000',
  `EUR` double NOT NULL DEFAULT '10000',
  `GBP` double NOT NULL DEFAULT '10000',
  `HKD` double NOT NULL DEFAULT '10000',
  `NZD` double NOT NULL DEFAULT '10000',
  `SGD` double NOT NULL DEFAULT '10000',
  `KRW` double NOT NULL DEFAULT '10000',
  PRIMARY KEY (`account_num`),
  KEY `id` (`id`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=euckr COMMENT='계좌';

-- 테이블 데이터 test.account:~4 rows (대략적) 내보내기
INSERT INTO `account` (`account_num`, `id`, `USD`, `JPY`, `THB`, `AUD`, `CAD`, `CHF`, `CNY`, `EUR`, `GBP`, `HKD`, `NZD`, `SGD`, `KRW`) VALUES
	('0000000000', 'admin', 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 1000),
	('1234-1234-1234', 'aaa', 200, 500, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50000),
	('5915842783', 'aaaa', 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000),
	('9581131012', 'aaaaa', 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000);

-- 테이블 test.bank 구조 내보내기
CREATE TABLE IF NOT EXISTS `bank` (
  `b_name` char(100) NOT NULL,
  `charge` double NOT NULL COMMENT '은행 별 수수료',
  PRIMARY KEY (`b_name`)
) ENGINE=InnoDB DEFAULT CHARSET=euckr COMMENT='은행 별 수수료';

-- 테이블 데이터 test.bank:~16 rows (대략적) 내보내기
INSERT INTO `bank` (`b_name`, `charge`) VALUES
	('BNK경남', 1.75),
	('BNK부산', 1.75),
	('DGB대구', 1.75),
	('IBK기업', 1.75),
	('KB국민', 1.75),
	('KDB산업', 1.5),
	('NH농협', 1.75),
	('SC제일', 1.75),
	('Sh수협', 1.9),
	('광주', 1.75),
	('신한', 1.75),
	('우리', 1.75),
	('전북', 1.75),
	('제주', 1.75),
	('하나', 1.75),
	('한국씨티', 1.75);

-- 테이블 test.currency_info 구조 내보내기
CREATE TABLE IF NOT EXISTS `currency_info` (
  `cur_unit` char(100) NOT NULL COMMENT '화폐 코드',
  `name` char(100) NOT NULL COMMENT '국가명',
  `cur_nm` char(100) NOT NULL COMMENT '화폐 이름',
  PRIMARY KEY (`cur_unit`)
) ENGINE=InnoDB DEFAULT CHARSET=euckr COMMENT='화폐 정보';

-- 테이블 데이터 test.currency_info:~13 rows (대략적) 내보내기
INSERT INTO `currency_info` (`cur_unit`, `name`, `cur_nm`) VALUES
	('AUD', '호주', '호주 달러'),
	('CAD', '캐나다', '캐나다 달러'),
	('CHF', '스위스', '스위스 프랑'),
	('CNY', '중국', '중국 위안'),
	('EUR', '유럽', '유럽 유로'),
	('GBP', '영국', '영국 파운드'),
	('HKD', '홍콩', '홍콩 달러'),
	('JPY(100)', '일본', '일본 엔'),
	('KRW', '한국', '한국 원'),
	('NZD', '뉴질랜드', '뉴질랜드 달러'),
	('SGD', '싱가포르', '싱가포르 달러'),
	('THB', '태국', '태국 바트'),
	('USD', '미국', '미국 달러');

-- 테이블 test.exchange_rate 구조 내보내기
CREATE TABLE IF NOT EXISTS `exchange_rate` (
  `DAY` char(100) NOT NULL COMMENT '날짜',
  `USD` double NOT NULL COMMENT '환율',
  `JPY` double NOT NULL,
  `THB` double NOT NULL,
  `AUD` double NOT NULL,
  `CAD` double NOT NULL,
  `CHF` double NOT NULL,
  `CNY` double NOT NULL,
  `EUR` double NOT NULL,
  `GBP` double NOT NULL,
  `HKD` double NOT NULL,
  `NZD` double NOT NULL,
  `SGD` double NOT NULL,
  `KRW` double NOT NULL,
  PRIMARY KEY (`DAY`)
) ENGINE=InnoDB DEFAULT CHARSET=euckr COMMENT='날짜별 환율';

-- 테이블 데이터 test.exchange_rate:~7 rows (대략적) 내보내기
INSERT INTO `exchange_rate` (`DAY`, `USD`, `JPY`, `THB`, `AUD`, `CAD`, `CHF`, `CNY`, `EUR`, `GBP`, `HKD`, `NZD`, `SGD`, `KRW`) VALUES
	('2024-02-13', 1327.2, 888.8, 36.96, 866.6, 986.62, 1515.33, 184.2, 1429.66, 1675.92, 169.77, 813.57, 986.88, 1),
	('2024-02-14', 1328.4, 882.04, 36.83, 857.15, 979.1, 1497.89, 183.93, 1422.78, 1672.72, 169.88, 804.94, 983.2, 1),
	('2024-02-15', 1336.9, 888.48, 37.01, 868.32, 987.33, 1510.02, 185.09, 1434.49, 1680.02, 170.99, 813.9, 991.8, 1),
	('2024-02-16', 1333.2, 889.48, 36.98, 870.11, 990.31, 1515.34, 184.49, 1436.59, 1680.5, 170.49, 814.52, 990.67, 1),
	('2024-02-17', 1333.2, 889.48, 36.98, 870.11, 990.31, 1515.34, 184.49, 1436.59, 1680.5, 170.49, 814.52, 990.67, 1),
	('2024-02-18', 1333.2, 889.48, 36.98, 870.11, 990.31, 1515.34, 184.49, 1436.59, 1680.5, 170.49, 814.52, 990.67, 1),
	('2024-02-19', 1333, 888.05, 37.02, 870.85, 988.47, 1513.05, 184.64, 1436.71, 1679.91, 170.43, 816.73, 989.86, 1);

-- 테이블 test.ex_history 구조 내보내기
CREATE TABLE IF NOT EXISTS `ex_history` (
  `ex_num` int NOT NULL AUTO_INCREMENT COMMENT '환전 번호',
  `ex_account_num` char(100) NOT NULL COMMENT '환전 계좌 번호',
  `day` date NOT NULL COMMENT '환전일',
  `sell_cur` char(100) NOT NULL COMMENT '판매 화폐',
  `sell_amt` double NOT NULL DEFAULT (0) COMMENT '판매 수량',
  `buy_cur` char(100) NOT NULL COMMENT '구매 화폐',
  `buy_amt` double NOT NULL DEFAULT (0) COMMENT '구매 수량',
  `rate` double NOT NULL COMMENT '환율',
  PRIMARY KEY (`ex_num`),
  KEY `ex_account_num` (`ex_account_num`),
  CONSTRAINT `ex_history_ibfk_1` FOREIGN KEY (`ex_account_num`) REFERENCES `account` (`account_num`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=euckr COMMENT='환전 내역';

-- 테이블 데이터 test.ex_history:~4 rows (대략적) 내보내기
INSERT INTO `ex_history` (`ex_num`, `ex_account_num`, `day`, `sell_cur`, `sell_amt`, `buy_cur`, `buy_amt`, `rate`) VALUES
	(1, '1234-1234-1234', '2024-02-13', '미국 달러', 100, '일본 엔화', 15000, 1.2627),
	(2, '1234-1234-1234', '2024-02-12', '일본 엔화', 5000, '한국 원화', 44400, 889.25),
	(3, '1234-1234-1234', '2024-02-11', '한국 원화', 50000, '미국 달러', 37.66, 1329.2),
	(4, '1234-1234-1234', '2024-02-10', '한국 원화', 20000, '일본 엔화', 2251.97, 889.25);

-- 테이블 test.member 구조 내보내기
CREATE TABLE IF NOT EXISTS `member` (
  `id` char(100) NOT NULL COMMENT '계정 아이디',
  `pwd` char(255) CHARACTER SET euckr COLLATE euckr_korean_ci NOT NULL COMMENT '계정 비밀번호',
  `name` char(10) NOT NULL COMMENT '회원 이름',
  `email` char(100) NOT NULL COMMENT '회원 이메일',
  `phone_num` char(100) NOT NULL COMMENT '휴대폰 번호',
  `telecom` char(200) NOT NULL,
  `admin` tinyint(1) DEFAULT '0' COMMENT '관리자 권한 여부',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=euckr COMMENT='회원 정보';

-- 테이블 데이터 test.member:~4 rows (대략적) 내보내기
INSERT INTO `member` (`id`, `pwd`, `name`, `email`, `phone_num`, `telecom`, `admin`) VALUES
	('aaa', '81dc9bdb52d04dc20036dbd8313ed055', '나', 'aaa@aaa.aaa', '010-0000-0001', 'LG U+', 0),
	('aaaa', '47bce5c74f589f4867dbd57e9ca9f808', 'aaa', 'aaa', 'aaa', 'LG U+', 0),
	('aaaaa', '47bce5c74f589f4867dbd57e9ca9f808', 'aaa', 'aaa', 'aaa', 'KT', 0),
	('admin', '21232f297a57a5a743894a0e4a801fc3', '관리자', 'admin@gmail.com', '010-0000-0000', 'KT', 1);

-- 테이블 test.qa 구조 내보내기
CREATE TABLE IF NOT EXISTS `qa` (
  `qa_num` int NOT NULL AUTO_INCREMENT COMMENT '1대1 상담 번호',
  `id` char(100) NOT NULL COMMENT '상담 회원 계정 id',
  `qa_date` date NOT NULL COMMENT '상담일자',
  `qa_memo` char(100) NOT NULL COMMENT '상담 사유',
  PRIMARY KEY (`qa_num`),
  KEY `id` (`id`),
  CONSTRAINT `qa_ibfk_1` FOREIGN KEY (`id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=euckr COMMENT='1대1 상담 내역';

-- 테이블 데이터 test.qa:~0 rows (대략적) 내보내기

-- 테이블 test.trade_history 구조 내보내기
CREATE TABLE IF NOT EXISTS `trade_history` (
  `trade_num` int NOT NULL AUTO_INCREMENT COMMENT '교환 번호',
  `trader_account_1` char(100) NOT NULL COMMENT '교환자1 계좌 번호',
  `trader_account_2` char(100) CHARACTER SET euckr COLLATE euckr_korean_ci DEFAULT NULL COMMENT '교환자2 계좌 번호',
  `day` date NOT NULL COMMENT '교환일',
  `sell_cur` char(100) NOT NULL COMMENT '판매 화폐',
  `sell_amt` int NOT NULL COMMENT '판매 수량',
  `buy_cur` char(100) NOT NULL COMMENT '구매 화폐',
  `buy_amt` int NOT NULL COMMENT '구매 수량',
  `rate` double NOT NULL COMMENT '환율',
  `check1` tinyint(1) NOT NULL DEFAULT '0' COMMENT '거래 신청 여부',
  `check2` tinyint(1) NOT NULL DEFAULT '0' COMMENT '거래 완료 여부',
  PRIMARY KEY (`trade_num`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=euckr;

-- 테이블 데이터 test.trade_history:~11 rows (대략적) 내보내기
INSERT INTO `trade_history` (`trade_num`, `trader_account_1`, `trader_account_2`, `day`, `sell_cur`, `sell_amt`, `buy_cur`, `buy_amt`, `rate`, `check1`, `check2`) VALUES
	(57, 'admin', NULL, '2024-02-14', 'CHF', 233, 'AUD', 34551233, 0, 0, 0),
	(58, 'admin', NULL, '2024-02-14', 'EUR', 233, 'THB', 2147483647, 0, 0, 0),
	(59, 'admin', NULL, '2024-02-14', 'JPY', 455, 'USD', 2147483647, 0, 0, 0),
	(60, 'admin', NULL, '2024-02-14', 'AUD', 322, 'USD', 344, 0, 0, 0),
	(61, 'admin', NULL, '2024-02-14', 'USD', 333, 'CAD', 333, 0, 0, 0),
	(62, 'admin', NULL, '2024-02-14', 'USD', 123, 'AUD', 123, 0, 0, 0),
	(63, 'admin', NULL, '2024-02-14', 'USD', 344, 'AUD', 344, 0, 0, 0),
	(65, 'admin', NULL, '2024-02-14', 'USD', 445, 'JPY', 445, 0, 0, 0),
	(66, 'admin', NULL, '2024-02-14', 'USD', 345, 'NZD', 455, 0, 0, 0),
	(67, 'admin', NULL, '2024-02-14', 'JPY', 333, 'USD', 123213213, 0, 1, 0),
	(68, 'admin', NULL, '2024-02-15', 'AUD', 213, 'JPY', 213, 0, 0, 0);

-- 테이블 test.withdraw_history 구조 내보내기
CREATE TABLE IF NOT EXISTS `withdraw_history` (
  `with_num` int NOT NULL AUTO_INCREMENT COMMENT '출금 번호',membermember
  `id` char(100) CHARACTER SET euckr COLLATE euckr_korean_ci NOT NULL COMMENT '출금 계좌 번호',
  `date` date NOT NULL COMMENT '출금 날짜',
  `cur_unit` char(100) NOT NULL COMMENT '출금 화폐',
  `amount` double NOT NULL DEFAULT (0) COMMENT '출금 수량',
  `b_name` char(100) NOT NULL COMMENT '은행 명',
  `check` tinyint(1) NOT NULL DEFAULT '0' COMMENT '수령 여부',
  PRIMARY KEY (`with_num`),
  KEY `b_name` (`b_name`),
  KEY `account_num` (`id`) USING BTREE,
  CONSTRAINT `FK_withdraw_history_test.account` FOREIGN KEY (`id`) REFERENCES `account` (`id`),
  CONSTRAINT `withdraw_history_ibfk_1` FOREIGN KEY (`b_name`) REFERENCES `bank` (`b_name`)
) ENGINE=InnoDB DEFAULT CHARSET=euckr COMMENT='출금 내역';

-- 테이블 데이터 test.withdraw_history:~0 rows (대략적) 내보내기

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
