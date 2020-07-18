-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: projekat
-- ------------------------------------------------------
-- Server version	5.7.27-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `anagram`
--

DROP TABLE IF EXISTS `anagram`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anagram` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pitanje` varchar(80) COLLATE utf8_bin NOT NULL,
  `odgovor` varchar(80) COLLATE utf8_bin NOT NULL,
  `rebus` int(11) DEFAULT NULL,
  `slika` varchar(80) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anagram`
--

LOCK TABLES `anagram` WRITE;
/*!40000 ALTER TABLE `anagram` DISABLE KEYS */;
INSERT INTO `anagram` VALUES (1,'Krasan je odmor','jadransko more',0,'/'),(2,'Ura bekrije','bure rakije',0,'/'),(3,'Preki driblaju blesave','vlada republike srbije',0,'/'),(4,'Fin auto','fiat uno',0,'/'),(5,'Lažni stid kupača','nudistička plaža',0,'/'),(6,'Blago zlima','globalizam',0,'/'),(7,'Brufen i po','ibuprofen',0,'/'),(8,'Posle rada','raspodela',0,'/'),(9,'Vraški Rus je pesnička legenda','aleksandar sergejevič puškin',0,'/'),(10,'Mudrina','umni rad',0,'/'),(11,'On karakter đavola','vođa narko kartela',0,'/'),(12,'Rebus','ključić oko vrata',1,'resources/rebus.jpg'),(13,'Rebus','naopako',1,'resources/prvi.jpg'),(14,'Rebus','raspust',1,'resources/drugi.jpg'),(15,'Rebus','glavni',1,'resources/treci.jpg'),(16,'Rebus','trubač',1,'resources/cetvrti.jpg'),(17,'Rebus','rezanac',1,'resources/peti.png'),(18,'Rebus','pospan',1,'resources/sesti.jpg'),(19,'Rebus','podlaktica',1,'resources/sedmi.jpg'),(20,'Rebus','nauka',1,'resources/osmi.jpg'),(21,'Dosta rani tkivo','radioaktivnost',0,'/'),(22,'Rebus','grom',1,'resources/deseti.jpg');
/*!40000 ALTER TABLE `anagram` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `igradana`
--

DROP TABLE IF EXISTS `igradana`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `igradana` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datum` date NOT NULL,
  `id_anagram` int(11) NOT NULL,
  `id_pehar` int(11) NOT NULL,
  `id_vesala` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `datum_UNIQUE` (`datum`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `igradana`
--

LOCK TABLES `igradana` WRITE;
/*!40000 ALTER TABLE `igradana` DISABLE KEYS */;
INSERT INTO `igradana` VALUES (1,'2019-09-04',1,1,1),(2,'2019-09-05',3,1,2),(3,'2019-09-07',3,1,4),(4,'2019-09-06',5,2,1),(5,'2019-09-08',2,1,2),(6,'2019-09-09',4,2,3),(7,'2019-09-10',7,1,7),(8,'2019-09-11',8,1,9),(9,'2019-09-12',3,2,5),(10,'2019-09-13',7,1,8),(11,'2019-09-25',2,1,2),(12,'2019-09-30',6,1,7),(13,'2019-09-14',4,2,4),(14,'2019-09-15',5,3,3),(15,'2019-09-16',8,4,12),(16,'2019-09-17',8,1,1),(17,'2019-09-18',6,1,2),(18,'2019-09-19',4,4,13),(19,'2019-09-20',22,3,7),(20,'2019-09-21',22,6,11),(21,'2019-09-22',17,6,10),(22,'2019-09-24',19,4,10);
/*!40000 ALTER TABLE `igradana` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `igraodanas`
--

DROP TABLE IF EXISTS `igraodanas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `igraodanas` (
  `datum` date NOT NULL,
  `username` varchar(45) COLLATE utf8_bin NOT NULL,
  `da_li_je_igrao` int(11) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `igraodanas`
--

LOCK TABLES `igraodanas` WRITE;
/*!40000 ALTER TABLE `igraodanas` DISABLE KEYS */;
INSERT INTO `igraodanas` VALUES ('2019-09-17','ana',1),('2019-09-20','braja',1),('2019-09-21','gile',1),('2019-09-20','iva',1),('2019-09-18','janko',1),('2019-09-21','laza',1),('2019-09-21','leo',1),('2019-09-22','marko',1),('2019-09-21','ogi',1),('2019-09-19','straja',0),('2019-09-22','toma',1);
/*!40000 ALTER TABLE `igraodanas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik` (
  `username` varchar(45) CHARACTER SET utf8 NOT NULL,
  `password` varchar(64) CHARACTER SET utf8 NOT NULL,
  `ime` varchar(45) CHARACTER SET utf8 NOT NULL,
  `prezime` varchar(45) CHARACTER SET utf8 NOT NULL,
  `tip` varchar(45) CHARACTER SET utf8 NOT NULL,
  `pitanje` varchar(45) CHARACTER SET utf8 NOT NULL,
  `odgovor` varchar(45) CHARACTER SET utf8 NOT NULL,
  `email` varchar(45) CHARACTER SET utf8 NOT NULL,
  `pol` varchar(45) CHARACTER SET utf8 NOT NULL,
  `zanimanje` varchar(45) CHARACTER SET utf8 NOT NULL,
  `jmbg` varchar(45) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES ('admin','9ANYM8uRGj6GNJA+SY/4SwXUtm9zxhVa8/WLIq01A/Q=','Administrator','Administratorovic','administrator','Godina rođenja?','CYW4iaH+T04fuSUGGsb7IkfxCHX1/L5j7sKrVe1olw4=','admin@hotmail.com','muski','admin','9876543210987'),('ana','9ANYM8uRGj6GNJA+SY/4SwXUtm9zxhVa8/WLIq01A/Q=','Ana','Stakic','takmicar','Ime kućnog ljubimca?','SGjFuZBMzMKo/MVUnLQeDoIov9vOdXEy8Pv0CUYC4bQ=','anastakic26@gmail.com','zenski','student','2609997715138'),('braja','9ANYM8uRGj6GNJA+SY/4SwXUtm9zxhVa8/WLIq01A/Q=','Lazar','Brajovic','takmicar','Omiljeni film?','JS8QyDYQ68oaBZwLroJV66L5W+TR17z6idckioLZ8RE=','braja@gmail.com','muski','student','0807997710054'),('gile','SgrdIKgJWdXfeTT5cKn8M54ZtmQUnKaKlUBtda2ZTxo=','Nikola','Dragic','takmicar','Omiljeni posao?','LmVR/bAZMOE0YWBXD7jpkUsOTXIUCQIWUOPdOaPx4X0=','gile@hotmail.com','muski','odbojkas','1111111111111'),('iva','9ANYM8uRGj6GNJA+SY/4SwXUtm9zxhVa8/WLIq01A/Q=','Iva','Kujundzic','takmicar','Omiljeni film?','JS8QyDYQ68oaBZwLroJV66L5W+TR17z6idckioLZ8RE=','iva@gmail.com','zenski','student','0807997710054'),('janko','9ANYM8uRGj6GNJA+SY/4SwXUtm9zxhVa8/WLIq01A/Q=','Stefan','Jankovic','takmicar','Ime najboljeg prijatelja?','8uXXVoO/eOYaUZUg1KX9lxZdqBTFua2liBQUh58eOUU=','janko@gmail.com','muski','student','0807997710054'),('laza','9ANYM8uRGj6GNJA+SY/4SwXUtm9zxhVa8/WLIq01A/Q=','Lazar','Lazic','takmicar','Omiljeni fudbalski klub?','7PcQESJ+Zl05pblYukw5SPP8urDE7JlG3a8I4G1Sg3o=','laza@yahoo.com','muski','student','1234567890123'),('leo','9ANYM8uRGj6GNJA+SY/4SwXUtm9zxhVa8/WLIq01A/Q=','Leo','Dog','takmicar','Omiljena igracka?','PLCVYmQ4dVcnmULIyB66mo61xfkxKld55QXLXhVVua0=','leo@pet.com','muski','pas','1212998839940'),('marko','9ANYM8uRGj6GNJA+SY/4SwXUtm9zxhVa8/WLIq01A/Q=','Marko','Kujundzic','takmicar','p','o','m@gmail.com','muski','student','0807997710054'),('ogi','9ANYM8uRGj6GNJA+SY/4SwXUtm9zxhVa8/WLIq01A/Q=','Ognjen','Ognjanovic','takmicar','Omiljeni film?','lzqKpL7ig9mRR62Yu7BLFfXnTMLix8GH7s0ZzLgs9vc=','ogi@gmail.com','muski','student','0807997710054'),('straja','9ANYM8uRGj6GNJA+SY/4SwXUtm9zxhVa8/WLIq01A/Q=','Strahinja','Stefanovic','takmicar','Omiljeni film?','eUg10eHa0BP5C0GxeeeU/x1ruklC0KvauW/FwePOE20=','straja@gmail.com','muski','student','0807997710054'),('supervizor','9ANYM8uRGj6GNJA+SY/4SwXUtm9zxhVa8/WLIq01A/Q=','Supervizor','Supervizorovic','supervizor','Ime najboljeg prijatelja?','wcIksDzZvHtqhtd/XazkAZF2bEhc1V3EjK+ayHMzXW8=','supervizor@gmail.com','muski','supervizor','1234567890123'),('toma','9ANYM8uRGj6GNJA+SY/4SwXUtm9zxhVa8/WLIq01A/Q=','Tomislav','Kujundzic','takmicar','Omiljeni film?','JS8QyDYQ68oaBZwLroJV66L5W+TR17z6idckioLZ8RE=','toma@yahoo.com','muski','covek','0807997710054');
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pehar`
--

DROP TABLE IF EXISTS `pehar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pehar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pitanje_broj_1` varchar(80) COLLATE utf8_bin NOT NULL,
  `odgovor_broj_1` varchar(80) COLLATE utf8_bin NOT NULL,
  `pitanje_broj_2` varchar(80) COLLATE utf8_bin NOT NULL,
  `odgovor_broj_2` varchar(80) COLLATE utf8_bin NOT NULL,
  `pitanje_broj_3` varchar(80) COLLATE utf8_bin NOT NULL,
  `odgovor_broj_3` varchar(80) COLLATE utf8_bin NOT NULL,
  `pitanje_broj_4` varchar(80) COLLATE utf8_bin NOT NULL,
  `odgovor_broj_4` varchar(80) COLLATE utf8_bin NOT NULL,
  `pitanje_broj_5` varchar(80) COLLATE utf8_bin NOT NULL,
  `odgovor_broj_5` varchar(80) COLLATE utf8_bin NOT NULL,
  `pitanje_broj_6` varchar(80) COLLATE utf8_bin NOT NULL,
  `odgovor_broj_6` varchar(80) COLLATE utf8_bin NOT NULL,
  `pitanje_broj_7` varchar(80) COLLATE utf8_bin NOT NULL,
  `odgovor_broj_7` varchar(80) COLLATE utf8_bin NOT NULL,
  `pitanje_broj_8` varchar(80) COLLATE utf8_bin NOT NULL,
  `odgovor_broj_8` varchar(80) COLLATE utf8_bin NOT NULL,
  `pitanje_broj_9` varchar(80) COLLATE utf8_bin NOT NULL,
  `odgovor_broj_9` varchar(80) COLLATE utf8_bin NOT NULL,
  `pitanje_broj_10` varchar(80) COLLATE utf8_bin NOT NULL,
  `odgovor_broj_10` varchar(80) COLLATE utf8_bin NOT NULL,
  `pitanje_broj_11` varchar(80) COLLATE utf8_bin NOT NULL,
  `odgovor_broj_11` varchar(80) COLLATE utf8_bin NOT NULL,
  `pitanje_broj_12` varchar(80) COLLATE utf8_bin NOT NULL,
  `odgovor_broj_12` varchar(80) COLLATE utf8_bin NOT NULL,
  `pitanje_broj_13` varchar(80) COLLATE utf8_bin NOT NULL,
  `odgovor_broj_13` varchar(80) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pehar`
--

LOCK TABLES `pehar` WRITE;
/*!40000 ALTER TABLE `pehar` DISABLE KEYS */;
INSERT INTO `pehar` VALUES (1,'Vrsta ratnog broda','krstarica','Mala krasta','krastica','Stara žena','starica','Sarin nadimak','sarita','Čovek koji koristi rešeto','sitar','Ime glumice Hayworth','rita','Konji u narodnim pesmama','ati','Prkos ili','inat','Stara mera za tečnost','pinta','Zelenkasta rđa','patina','Izvršiti napad','napasti','Država u Aziji','pakistan','Odobrenje ili','pristanak'),(2,'Kraljevski dvorac u blizini Madrida','eskorijal','Vozači kola','kolesari','Splavari ili','skelari','Nemački šahista Emanuel','lasker','Programski jezik za početnike','karel','Vodeni tok','reka','Vodeni ljuskar','rak','Takmičenje u brzini','trka','Drugi naziv za ulaznice','karte','Osoba koja pravi četke','četkar','Obrtač ili','okretač','Žene koje se bave stočarstvom','stočarke','Glasnik ili','skoroteča'),(3,'Osa simetrije u geometriji','simetrala','Po godinama mlad, a po ponašanju star','starmali','Sledbenik Lamaizma','lamaist','Krečiti zidove kao moler','malati','Država kojoj je glavni grad Valeta','malta','Bez njega nema zanata','alat','Aždaja, zmaj, čudovište','ala','Vrsta lepog cveta','kala','Atinska skadarlija','plaka','Crkvica sa jednim oltarom','kapela','Oni koji peckaju, bockaju, zadirkuju','peckala','Kapaljke','kapalice','Kritičar do sitnica, formalista','cepidlaka'),(4,'Produkti, fabrikati','proizvodi','Onaj koji izaziva sumnju, nepoverenje','podozriv','Dobre zabave, letnji događaji','provodi','Porod, potomstvo (književno)','rodivo','Rodno mesto Mihajla Pupina','idvor','Ime glumice Hepbern','odri','Ime rok zvezde Stjuarta','rod','Drage volje','rado','Koja je boja vedrog neba','modra','Premeravanje, odmeravanje','odmera','Žene kućepaziteljke','domarke','Pristalica vladavine naroda','demokrat','Veliki inkvizitor Španije','torkemada'),(5,'Kontinent oko južnog pola','antarktik','Natrčati (narodni izraz)','natrkati','Vrsta vage (množina)','kantari','Borac','ratnik','Češki tvorac animiranih filmova Jirži','trnka','Sunčev zrak','trak','Otpadak od sena','tar','Uzrast, stas','rast','Čovek koji koristi rešeto','sitar','Kaže se da toliko ima čuda','trista','Umetnici','artisti','Maštati, sanjariti','snatriti','Bezbožnik','antihrist'),(6,'Životinja sa Sumatre','orangutan','Mitološki tragač za zlatnim runom','argonaut','Unazad, unatraške','unatrag','Nar, šipak, mogren','granat','Deblja trska od koje se pravi nameštaj','ratan','Povreda, ozleda','rana','Sin princa Valijanta','arn','Planeta sunčevog sistema','uran','Poznat je i arterski','bunar','Sinonim za čalmu','turban','Ono koje se odnosi na barut','barutno','Grubo, surovo','brutalno','Glavni grad Mongolije','ulanbator');
/*!40000 ALTER TABLE `pehar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rezultati`
--

DROP TABLE IF EXISTS `rezultati`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rezultati` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8_bin NOT NULL,
  `anagram` int(11) NOT NULL,
  `mojbroj` int(11) NOT NULL,
  `vesala` int(11) NOT NULL,
  `zanimljivageografija` int(11) NOT NULL,
  `pehar` int(11) NOT NULL,
  `ukupno` int(11) NOT NULL,
  `datum` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=706 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezultati`
--

LOCK TABLES `rezultati` WRITE;
/*!40000 ALTER TABLE `rezultati` DISABLE KEYS */;
INSERT INTO `rezultati` VALUES (668,'marko',10,10,10,10,10,50,'2019-09-18'),(669,'iva',10,0,10,30,10,60,'2019-09-18'),(670,'toma',0,0,0,20,20,40,'2019-09-18'),(671,'ogi',10,10,10,12,12,54,'2019-09-18'),(672,'janko',0,0,0,12,12,24,'2019-09-17'),(673,'marko',10,10,10,32,26,88,'2019-09-17'),(674,'ana',10,10,10,0,0,30,'2019-09-16'),(675,'straja',10,10,10,28,6,64,'2019-09-15'),(676,'toma',0,0,0,0,26,26,'2019-09-14'),(677,'janko',10,0,0,16,20,46,'2019-09-13'),(678,'iva',10,10,10,20,20,70,'2019-09-13'),(679,'braja',0,0,10,22,6,38,'2019-09-12'),(680,'laza',10,0,10,20,18,58,'2019-09-09'),(681,'marko',10,0,0,0,0,10,'2019-09-09'),(682,'braja',10,0,0,6,10,26,'2019-09-08'),(683,'ana',10,10,10,10,10,50,'2019-09-08'),(684,'janko',0,0,10,20,4,34,'2019-09-07'),(685,'ogi',10,10,10,10,18,58,'2019-09-04'),(686,'iva',10,10,10,0,2,32,'2019-09-03'),(687,'laza',10,10,10,24,22,76,'2019-08-31'),(688,'braja',10,0,0,2,2,14,'2019-08-30'),(689,'toma',10,10,10,20,18,68,'2019-08-30'),(690,'marko',10,0,10,24,10,54,'2019-09-19'),(691,'laza',10,10,10,22,14,66,'2019-09-19'),(694,'marko',10,0,0,12,16,38,'2019-09-20'),(695,'laza',10,0,10,16,0,36,'2019-09-20'),(696,'braja',10,10,10,4,12,46,'2019-09-20'),(697,'iva',0,0,0,0,0,0,'2019-09-20'),(698,'ogi',10,10,0,6,10,36,'2019-09-21'),(699,'leo',0,10,10,0,0,20,'2019-09-21'),(700,'gile',10,10,10,16,12,58,'2019-09-21'),(701,'laza',10,0,0,8,16,34,'2019-09-21'),(702,'marko',10,10,0,10,4,34,'2019-09-21'),(703,'toma',10,0,0,0,0,10,'2019-09-21'),(704,'toma',10,0,0,20,14,44,'2019-09-22'),(705,'marko',10,10,10,18,20,68,'2019-09-22');
/*!40000 ALTER TABLE `rezultati` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vesanje`
--

DROP TABLE IF EXISTS `vesanje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vesanje` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rec_koja_se_pogadja` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vesanje`
--

LOCK TABLES `vesanje` WRITE;
/*!40000 ALTER TABLE `vesanje` DISABLE KEYS */;
INSERT INTO `vesanje` VALUES (1,'katastrofa'),(2,'jednakost'),(3,'polimerizacija'),(4,'pravosuđe'),(5,'oligarhija'),(6,'hrastovina'),(7,'nagrabusiti'),(8,'optimalan'),(9,'osakatiti'),(10,'praistorija'),(11,'anarhija'),(12,'popokatepetl'),(13,'košmar');
/*!40000 ALTER TABLE `vesanje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zanimljiva_geografija`
--

DROP TABLE IF EXISTS `zanimljiva_geografija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zanimljiva_geografija` (
  `tip` varchar(50) COLLATE utf8_bin NOT NULL,
  `pojam` varchar(50) COLLATE utf8_bin NOT NULL,
  `id` int(100) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=550 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zanimljiva_geografija`
--

LOCK TABLES `zanimljiva_geografija` WRITE;
/*!40000 ALTER TABLE `zanimljiva_geografija` DISABLE KEYS */;
INSERT INTO `zanimljiva_geografija` VALUES ('drzava','angola',1),('drzava','alzir',2),('drzava','albanija',3),('drzava','australija',4),('drzava','austrija',5),('drzava','belgija',6),('drzava','belorusija',7),('drzava','bosna i hercegovina',8),('drzava','bolivija',9),('drzava','brazil',10),('drzava','bahrein',11),('drzava','bugarska',12),('drzava','brazil',13),('drzava','cad',14),('drzava','ceska',15),('drzava','cile',16),('drzava','crna gora',17),('drzava','danska',18),('drzava','dominikanska republika',19),('drzava','ekvador',20),('drzava','etiopija',21),('drzava','engleska',22),('drzava','estonija',23),('drzava','filipini',24),('drzava','francuska',25),('drzava','finska',26),('drzava','gabon',27),('drzava','gana',28),('drzava','grcka',29),('drzava','gruzija',30),('drzava','gvineja',31),('drzava','holandija',32),('drzava','honduras',33),('drzava','hrvatska',34),('drzava','izrael',35),('drzava','italija',36),('drzava','indija',37),('drzava','iran',38),('drzava','irak',39),('drzava','irska',40),('drzava','japan',41),('drzava','jemen',42),('drzava','južna afrika',43),('drzava','jermenija',44),('drzava','južna koreja',45),('drzava','katar',46),('drzava','kambodža',47),('drzava','kuvajt',48),('drzava','kanada',49),('drzava','kipar',50),('drzava','kazahstan',51),('drzava','litvanija',52),('drzava','letonija',53),('drzava','lihtenštajn',54),('drzava','laos',55),('drzava','monako',56),('drzava','maldivi',57),('drzava','meksiko',58),('drzava','madjarska',59),('drzava','moldavija',60),('drzava','maroko',61),('drzava','nemačka',62),('drzava','nepal',63),('drzava','nigerija',64),('drzava','norveška',65),('drzava','obala slonovace',66),('drzava','oman',67),('drzava','pakistan',68),('drzava','paragvaj',69),('drzava','poljska',70),('drzava','portugalija',71),('drzava','ruanda',72),('drzava','rusija',73),('drzava','rumunija',74),('drzava','sad',75),('drzava','slovacka',76),('drzava','slovenija',77),('drzava','san marino',78),('drzava','senegal',79),('drzava','saudijska arabija',80),('drzava','sejšeli',81),('drzava','srbija',82),('drzava','škotska',83),('drzava','španija',84),('drzava','šri lanka',85),('drzava','švajcarska',86),('drzava','švedska',87),('drzava','togo',88),('drzava','tanzanija',89),('drzava','tunis',90),('drzava','turska',91),('drzava','uganda',92),('drzava','ujedinjeni arapski emirati',93),('drzava','uzbekistan',94),('drzava','ukrajina',95),('drzava','uzbekistan',96),('drzava','velika britanija',97),('drzava','vanuatu',98),('drzava','vels',99),('drzava','venecuela',100),('drzava','vijetnam',101),('drzava','zimbabve',102),('drzava','zambija',103),('grad','abu dabi',104),('grad','amsterdam',105),('grad','astana',106),('grad','aleksandrija',107),('grad','akra',108),('grad','ankara',109),('grad','atina',110),('grad','antalija',111),('grad','beograd',112),('grad','bangkok',113),('grad','berlin',114),('grad','budimpešta',115),('grad','bec',116),('grad','bratislava',117),('grad','bern',118),('grad','brisel',119),('grad','cacak',120),('grad','cikago',121),('grad','dalas',122),('grad','damask',123),('grad','denver',124),('grad','detroit',125),('grad','el paso',126),('grad','fritaun',127),('grad','filadelfija',128),('grad','finiks',129),('grad','gabone',130),('grad','gitega',131),('grad','hag',132),('grad','helsinki',133),('grad','hjuston',134),('grad','havana',135),('grad','islamabad',136),('grad','indijanopolis',137),('grad','jagodina',138),('grad','jerusalim',139),('grad','kairo',140),('grad','kabul',141),('grad','katmandu',142),('grad','kijev',143),('grad','kopenhagen',144),('grad','kikinda',145),('grad','kruševac',146),('grad','kragujevac',147),('grad','las vegas',148),('grad','lisabon',149),('grad','lozana',150),('grad','london',151),('grad','loznica',152),('grad','luksemburg',153),('grad','manila',154),('grad','minsk',155),('grad','montreal',156),('grad','meksiko siti',157),('grad','moskva',158),('grad','najrobi',159),('grad','niš',160),('grad','nikozija',161),('grad','novi sad',162),('grad','novi pazar',163),('grad','nju orleans',164),('grad','nju jork',165),('grad','nju delhi',166),('grad','oslo',167),('grad','otava',168),('grad','panama',169),('grad','pariz',170),('grad','pancevo',171),('grad','pirot',172),('grad','prag',173),('grad','požega',174),('grad','požarevac',175),('grad','rim',176),('grad','riga ',177),('grad','rejkjavik',178),('grad','rio de žaneiro',179),('grad','šabac',180),('grad','san francisko',181),('grad','san antonio',182),('grad','santo domingo',183),('grad','sarajevo',184),('grad','sofija',185),('grad','smederevo',186),('grad','tbilisi',187),('grad','tokio',188),('grad','toronto',189),('grad','ulan bator',190),('grad','uagadugu',191),('grad','valjevo',192),('grad','vatikan',193),('grad','vranje',194),('grad','vršac',195),('grad','zagreb',196),('grad','zrenjanin',197),('jezero','aralsko',198),('jezero','albertovo',199),('jezero','bajkalsko',200),('jezero','belo',201),('jezero','borsko',202),('jezero','ejr',203),('jezero','gazivode',204),('jezero','hjuron',205),('jezero','kaspijsko',206),('jezero','marakaibo',207),('jezero','micigen',208),('jezero','nikaragva',209),('jezero','onego',210),('jezero','ontario',211),('jezero','palic',212),('jezero','potpecko',213),('jezero','srebrno',214),('jezero','šumsko',215),('jezero','tanganjika',216),('jezero','turkana',217),('jezero','veliko medvedje',218),('jezero','veliko slano',219),('jezero','viktorijino',220),('jezero','vlasinsko',221),('jezero','zlatarsko',222),('jezero','zlatiborsko',223),('jezero','djeravica',224),('jezero','djerdap',225),('jezero','djerdapsko',226),('jezero','palicko',227),('planina','ararat',228),('planina','avala',229),('planina','beljanica',230),('planina','cemernik',231),('planina','cer',232),('planina','crni vrh',233),('planina','dinarske',234),('planina','dukat',235),('planina','durmitor',236),('planina','fruška gora',237),('planina','fudži',238),('planina','goc',239),('planina','golija ',240),('planina','hajla',241),('planina','ivan',242),('planina','jagodnja',243),('planina','jahorina',244),('planina','jastrebac',245),('planina','kopaonik',246),('planina','kozara',247),('planina','kukavica',248),('planina','leotar',249),('planina','lovcen',250),('planina','ljubic',251),('planina','maljen',252),('planina','olimp',253),('planina','ozren',254),('planina','povlen',255),('planina','radan',256),('planina','rogozna',257),('planina','šar',258),('planina','šar planina',259),('planina','stara',260),('planina','stara planina',261),('planina','stolovi',262),('planina','suva',263),('planina','suva planina',264),('planina','tara',265),('planina','tresibaba',266),('planina','vitorog',267),('planina','zlatar',268),('planina','zlatibor',269),('reka','amazon',270),('reka','beli timok',271),('reka','beli drim',272),('reka','begej',273),('reka','bosna',274),('reka','cehotina',275),('reka','cemernica',276),('reka','crni drim',277),('reka','crni timok',278),('reka','drim',279),('reka','drava',280),('reka','dunav',281),('reka','drina',282),('reka','euro',283),('reka','eurota',284),('reka','gang',285),('reka','hadson',286),('reka','hoangho',287),('reka','ibar',288),('reka','ind',289),('reka','jadar',290),('reka','jordan',291),('reka','južna morava',292),('reka','kolubara',293),('reka','kongo',294),('reka','lepenica',295),('reka','lim',296),('reka','ljig',297),('reka','misisipi',298),('reka','morava',299),('reka','mlava ',300),('reka','nera',301),('reka','neretva',302),('reka','orinoko',303),('reka','odra',304),('reka','ob',305),('reka','pcinja',306),('reka','plitvica',307),('reka','rzav',308),('reka','sava',309),('reka','tara',310),('reka','tamiš',311),('reka','tisa',312),('reka','timok',313),('reka','ub',314),('reka','uvac',315),('reka','una',316),('reka','veternica',317),('reka','velika morava',318),('reka','volga',319),('reka','zambezi',320),('reka','zapadna morava',321),('reka','zeta',322),('reka','ždralica',323),('reka','djetinja',324),('zivotinja','ajkula',325),('zivotinja','albatros',326),('zivotinja','antilopa',327),('zivotinja','ara',328),('zivotinja','ala',329),('zivotinja','babun',330),('zivotinja','bivo',331),('zivotinja','boa',332),('zivotinja','caplja',333),('zivotinja','cincila',334),('zivotinja','cvrcak',335),('zivotinja','dabar',336),('zivotinja','detlic',337),('zivotinja','emu',338),('zivotinja','flamingo',339),('zivotinja','foka',340),('zivotinja','galeb',341),('zivotinja','grgec',342),('zivotinja','gavran',343),('zivotinja','haringa',344),('zivotinja','hijena',345),('zivotinja','hrcak',346),('zivotinja','irvas',347),('zivotinja','jazavac',348),('zivotinja','jegulja',349),('zivotinja','jež',350),('zivotinja','kolibri',351),('zivotinja','lasta',352),('zivotinja','lav',353),('zivotinja','lemur',354),('zivotinja','losos',355),('zivotinja','los',356),('zivotinja','macka',357),('zivotinja','majmun',358),('zivotinja','miš',359),('zivotinja','medved',360),('zivotinja','noj',361),('zivotinja','nosorog',362),('zivotinja','orao',363),('zivotinja','panda',364),('zivotinja','prepelica',365),('zivotinja','puma',366),('zivotinja','rak',367),('zivotinja','ris',368),('zivotinja','roda',369),('zivotinja','šaran',370),('zivotinja','štuka',371),('zivotinja','tigar',372),('zivotinja','tvor',373),('zivotinja','veverica',374),('zivotinja','vrana',375),('zivotinja','zebra',376),('zivotinja','zec',377),('zivotinja','ždral',378),('biljka','avokado',379),('biljka','ananas',380),('biljka','bagrem',381),('biljka','brokoli',382),('biljka','banana',383),('biljka','cempres',384),('biljka','cia',385),('biljka','cvekla',386),('biljka','dinja',387),('biljka','dunja',388),('biljka','dud',389),('biljka','eukaliptus',390),('biljka','fikus',391),('biljka','grozdje',392),('biljka','heljda',393),('biljka','hmelj',394),('biljka','hibiskus',395),('biljka','hrast',396),('biljka','jagoda',397),('biljka','jabuka',398),('biljka','keleraba',399),('biljka','kruška ',400),('biljka','kivi',401),('biljka','kelj',402),('biljka','limun',403),('biljka','lipa',404),('biljka','ljiljan',405),('biljka','ljubicica',406),('biljka','malina',407),('biljka','mango',408),('biljka','lubenica',409),('biljka','malina',410),('biljka','nar',411),('biljka','narandža',412),('biljka','orah',413),('biljka','paprika',414),('biljka','paradajz',415),('biljka','pomorandža',416),('biljka','ruzmarin',417),('biljka','šargarepa',418),('biljka','šljiva',419),('biljka','smokva',420),('biljka','suncokret',421),('biljka','tikvica',422),('biljka','trešnja',423),('biljka','urma',424),('biljka','vanila',425),('biljka','višnja',426),('biljka','žalfija',427),('biljka','zelje',428),('biljka','zova',429),('biljka','djumbir',430),('muzicka_grupa','abba',431),('muzicka_grupa','ac dc',432),('muzicka_grupa','aerosmith',433),('muzicka_grupa','bajaga i instruktori',434),('muzicka_grupa','bitlsi',435),('muzicka_grupa','beattles',436),('muzicka_grupa','coldplay',437),('muzicka_grupa','crvena jabuka',438),('muzicka_grupa','ekv',439),('muzicka_grupa','ekatarina velika',440),('muzicka_grupa','queen',441),('muzicka_grupa','kvin',442),('muzicka_grupa','maroon 5',443),('muzicka_grupa','metallica',444),('muzicka_grupa','metalika',445),('muzicka_grupa','mobi dik',446),('muzicka_grupa','negativ',447),('muzicka_grupa','magazin',448),('muzicka_grupa','neverne bebe',449),('muzicka_grupa','riblja corba',450),('muzicka_grupa','tap 011',451),('muzicka_grupa','van gog',452),('muzicka_grupa','black eyed peas',453),('grad','istanbul',454),('grad','venecija',455),('drzava','vatikan',456),('biljka','visibaba',457),('biljka','biber',458),('zivotinja','buba mara',459),('drzava','burkina faso',460),('muzicka_grupa','beogradski sindikat',461),('grad','ozren',462),('biljka','orhideja',463),('zivotinja','oktopod',464),('zivotinja','oktopus',465),('zivotinja','curka',466),('grad','dizeldorf',467),('planina','dinaridi',468),('drzava','egipat',469),('reka','eufrat',470),('reka','tigar',471),('grad','cernobil',473),('biljka','cicak',474),('grad','hanioti',475),('planina','himalaji',476),('zivotinja','hobotnica',477),('biljka','humus',478),('grad','hvar',479),('grad','hamburg',480),('biljka','boranija',481),('drzava','alžir',482),('grad','antverpen',483),('zivotinja','anakonda',484),('grad','?i?evac',485),('drzava','nemacka',486),('grad','tirana',487),('planina','tibet',488),('biljka','tulipan',489),('grad','cer',490),('zivotinja','cvrcak',491),('reka','temza',492),('biljka','trava',493),('zivotinja','jaguar',494),('grad','edinburg',495),('grad','firenca',496),('grad','teheran',497),('zivotinja','leopard',498),('jezero','njasa',499),('planina','njegos',500),('reka','njemen',501),('zivotinja','njorka',502),('grad','zlo',504),('drzava','džibuti',505),('grad','džakarta',506),('jezero','džordžovo',507),('planina','džepska',508),('reka','džepska',509),('biljka','džanarika',510),('biljka','iris',511),('biljka','dudinja',512),('zivotinja','vuk',514),('grad','šid',515),('zivotinja','šarplaninac',516),('biljka','šišarka',517),('muzicka_grupa','štrokavi mozak',518),('grad','ljubljana',519),('zivotinja','ljudozder',520),('muzicka_grupa','lja corba',521),('grad','ostrog',522),('grad','trst',523),('jezero','komo',524),('zivotinja','kameleon',525),('biljka','kukuruz',526),('muzicka_grupa','kvins',527),('grad','trstenik',528),('grad','negotin',529),('biljka','nana',530),('jezero','lugano',531),('planina','langa',532),('biljka','lavanda',533),('muzicka_grupa','lexington',534),('grad','minhen',535),('planina','medvednik',536),('biljka','musmula',537),('reka','rajna',538),('zivotinja','rakun',539),('jezero','gruzansko',540),('zivotinja','gepard',541),('muzicka_grupa','guns and roses',542),('grad','ipsvic',543),('grad','ren',544),('grad','solun',545),('jezero','skadarsko',546),('zivotinja','slon',547),('grad','osijek',548),('zivotinja','orangutan',549);
/*!40000 ALTER TABLE `zanimljiva_geografija` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-22 11:18:53
