SHOW VARIABLES LIKE 'event_scheduler';
SHOW VARIABLES;

SHOW processlist;

SET GLOBAL event_scheduler = ON;

CREATE EVENT `evento_verificar_prestamos`
  ON SCHEDULE
    EVERY 30 MINUTE
    STARTS TIMESTAMP(NOW() + INTERVAL 1 MINUTE) ON COMPLETION PRESERVE ENABLE 
  DO
    CALL thot.verificar_prestamos();

SHOW events