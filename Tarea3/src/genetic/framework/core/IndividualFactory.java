package genetic.framework.core;

/**
 * Interface for the factories that create instances of
 * different extensions of Individual.
 * @author Ignacio Cuevas
 *
 */
public interface IndividualFactory {
	/**
	 * Main factory method.
	 * Creates an Individual according to the implementation.
	 * @return A new Individual.
	 */
	public abstract Individual createIndividual();
}
