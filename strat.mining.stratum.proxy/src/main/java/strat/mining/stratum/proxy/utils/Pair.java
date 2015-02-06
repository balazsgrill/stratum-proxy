/**
 * 
 */
package strat.mining.stratum.proxy.utils;

import com.google.common.base.Objects;

/**
 * @author balazs.grill
 *
 */
public class Pair<U, T> {

	private final U first;
	private final T second;
	
	public Pair(U first, T second) {
		this.first = first;
		this.second = second;
	}

	public U getFirst() {
		return first;
	}
	
	public T getSecond() {
		return second;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(first, second);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Pair<?, ?>){
			return Objects.equal(first, ((Pair<?,?>) obj).first) && Objects.equal(second, ((Pair<?,?>) obj).second);
		}
		return super.equals(obj);
	}
	
}
