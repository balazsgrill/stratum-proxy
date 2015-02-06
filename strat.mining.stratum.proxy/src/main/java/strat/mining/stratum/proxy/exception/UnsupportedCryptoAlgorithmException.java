/**
 * 
 */
package strat.mining.stratum.proxy.exception;

import strat.mining.stratum.proxy.CryptoAlgorithm;

/**
 * @author balazs.grill
 *
 */
public class UnsupportedCryptoAlgorithmException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6506569492796723898L;

	/**
	 * 
	 */
	public UnsupportedCryptoAlgorithmException(CryptoAlgorithm algo) {
		super("Crypto algorithm is not supported: "+algo);
	}

}
