CREATE TABLE `contacorrente` (
  `IDconta` INTEGER(6) NOT NULL AUTO_INCREMENT,
  `Pessoa` VARCHAR(60) NOT NULL,
  `Saldo_Conta` DECIMAL(15,2) NOT NULL,
  PRIMARY KEY (`IDconta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO contacorrente (Pessoa,Saldo_Conta) VALUES ('Eder Fialho','2500.00');