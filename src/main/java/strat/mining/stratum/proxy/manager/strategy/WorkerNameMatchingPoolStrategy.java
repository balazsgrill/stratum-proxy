/**
 * 
 */
package strat.mining.stratum.proxy.manager.strategy;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import strat.mining.stratum.proxy.exception.ChangeExtranonceNotSupportedException;
import strat.mining.stratum.proxy.exception.NoPoolAvailableException;
import strat.mining.stratum.proxy.exception.TooManyWorkersException;
import strat.mining.stratum.proxy.manager.ProxyManager;
import strat.mining.stratum.proxy.pool.Pool;
import strat.mining.stratum.proxy.worker.WorkerConnection;

/**
 * @author balazs.grill
 *
 */
public class WorkerNameMatchingPoolStrategy implements
		PoolSwitchingStrategyManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(WorkerNameMatchingPoolStrategy.class);
	
	public static final String NAME = "WorkerName";
	
	private final ProxyManager proxyManager;
	
	public WorkerNameMatchingPoolStrategy(ProxyManager proxyManager) {
		this.proxyManager = proxyManager;
	}
	
	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#stop()
	 */
	@Override
	public void stop() {
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#onPoolAdded(strat.mining.stratum.proxy.pool.Pool)
	 */
	@Override
	public void onPoolAdded(Pool pool) {
		updateConnections();
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#onPoolRemoved(strat.mining.stratum.proxy.pool.Pool)
	 */
	@Override
	public void onPoolRemoved(Pool pool) {
		updateConnections();
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#onPoolUpdated(strat.mining.stratum.proxy.pool.Pool)
	 */
	@Override
	public void onPoolUpdated(Pool pool) {
		updateConnections();
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#onPoolDown(strat.mining.stratum.proxy.pool.Pool)
	 */
	@Override
	public void onPoolDown(Pool pool) {
		updateConnections();
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#onPoolUp(strat.mining.stratum.proxy.pool.Pool)
	 */
	@Override
	public void onPoolUp(Pool pool) {
		updateConnections();
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#onPoolStable(strat.mining.stratum.proxy.pool.Pool)
	 */
	@Override
	public void onPoolStable(Pool pool) {
		updateConnections();
	}

	private String getPoolID(String poolname){
		int l = poolname.indexOf('@');
		if (l != -1){
			return poolname.substring(0, l);
		}
		return poolname;
	}
	
	private void updateConnections(){
		for(WorkerConnection wc : proxyManager.getWorkerConnections()){
			try {
				Pool shouldbe = getPoolForConnection(wc);
				if (!shouldbe.equals(wc.getPool())){
					proxyManager.switchPoolForConnection(wc, shouldbe);
				}
			} catch (NoPoolAvailableException e) {
				LOGGER.error(e.getMessage());
			} catch (TooManyWorkersException e) {
				LOGGER.error(e.getMessage());
			} catch (ChangeExtranonceNotSupportedException e) {
				LOGGER.error(e.getMessage());
			}
			
		}
	}
	
	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#getPoolForConnection(strat.mining.stratum.proxy.worker.WorkerConnection)
	 */
	@Override
	public Pool getPoolForConnection(WorkerConnection connection)
			throws NoPoolAvailableException {
		Set<String> ids = connection.getAuthorizedWorkers().keySet();
		
		Pool selection = null;
		int priority = Integer.MAX_VALUE;
		
		for(Pool pool : proxyManager.getPools()){
			if (pool.isStable()){
				if (ids.contains(getPoolID(pool.getName()))){
					int p = pool.getPriority() == null ? Integer.MAX_VALUE-1 : pool.getPriority().intValue();
					if (selection == null || p < priority){
						selection = pool;
						priority = p;
					}
				}
			}
		}
		
		if (selection == null){
			LOGGER.error("No pool found for ID: "+ids);
			throw new NoPoolAvailableException("No pool found for ID: "+ids);
		}
		
		return selection;
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#getConfigurationParameters()
	 */
	@Override
	public Map<String, String> getConfigurationParameters() {
		return Collections.emptyMap();
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#getDetails()
	 */
	@Override
	public Map<String, String> getDetails() {
		return Collections.emptyMap();
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#getName()
	 */
	@Override
	public String getName() {
		return NAME;
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#getDescription()
	 */
	@Override
	public String getDescription() {
		return "This strategy maps pools by worker names";
	}

	/* (non-Javadoc)
	 * @see strat.mining.stratum.proxy.manager.strategy.PoolSwitchingStrategyManager#setParameter(java.lang.String, java.lang.String)
	 */
	@Override
	public void setParameter(String parameterKey, String value) {
		// TODO Auto-generated method stub

	}

}
