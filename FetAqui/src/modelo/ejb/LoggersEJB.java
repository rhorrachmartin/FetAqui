package modelo.ejb;

import javax.ejb.Stateless;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

/**
 * Clase singleton para generar los loggers necesarios
 * 
 * @author ramon
 *
 */
@Stateless
public class LoggersEJB {

	static volatile LoggersEJB instance = null;
	static Logger logger;

	public LoggersEJB() {
		logger = (Logger) LoggerFactory.getLogger(LoggersEJB.class);
	}

	/**
	 * Método para obtener instancia de la clase
	 * 
	 * @return
	 */
	public static LoggersEJB getInstance() {
		if (instance == null) {
			synchronized (LoggersEJB.class) {
				if (instance == null) {
					instance = new LoggersEJB();
				}
			}
		}
		return instance;
	}

	/**
	 * Método crear logger DEBUG que recibe el mensaje deseado
	 * 
	 * @param debug
	 */
	public void debug(String debug) {
		logger.debug(debug);
	}

	/**
	 * Método para crear logger ERROR que recibe el mensaje de error
	 * 
	 * @param error
	 */
	public void setErrorLogger(String error) {
		logger.error(error);
	}

}
