SHOW VARIABLES LIKE 'event_scheduler';
SHOW VARIABLES;

SHOW processlist;

SET GLOBAL event_scheduler = ON;

DROP EVENT IF EXISTS evento_verificar_procesos;
CREATE EVENT `evento_verificar_procesos`
  ON SCHEDULE
    EVERY 5 MINUTE
    STARTS TIMESTAMP(NOW() + INTERVAL 1 MINUTE) ON COMPLETION PRESERVE ENABLE 
  DO
	CALL thot.verificar_procesos()

SHOW events
