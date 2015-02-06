/**
 * 
 */
package strat.mining.stratum.proxy.manager;

import strat.mining.stratum.proxy.model.HashrateModel;

/**
 * @author balazs.grill
 *
 */
public interface IHashrateHistoryStorage {

	void insertHashrate(HashrateModel hashrate);

	void deleteOldHashrate(Long toDeleteBefore);

}
