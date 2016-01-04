package de.uka.ipd.sdq.beagle.core.expressions;

/**
 * A evaluable expression containing constants.
 * 
 * @author Annika Berger
 *
 */
public final class ConstantExpression implements EvaluableExpression {

	/**
	 * Inizialises a new constant expression with a given {@code value}.
	 * 
	 * @param value which should be assigned to the expression
	 */
	private ConstantExpression(final double value) {
	}

	/**
	 * Looks for the constant expression belonging to a input {@code value}.
	 *
	 * @param value whose expression should be returned
	 * @return the constant expression belonging to the given {@code value}
	 */
	public static ConstantExpression forValue(final double value) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.uka.ipd.sdq.beagle.core.expressions.EvaluableExpression#receive(de.uka.sdq.
	 * beagle. core.expressions.EvaluableExpressionVisitor)
	 */
	@Override
	public void receive(final EvaluableExpressionVisitor visitor) {

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.uka.ipd.sdq.beagle.core.expressions.EvaluableExpression#evaluate(de.uka.sdq.
	 * beagle. core.expressions.EvaluableVariableAssignment)
	 */
	@Override
	public double evaluate(final EvaluableVariableAssignment variableAssignments) {
		return 0;
	}

}
