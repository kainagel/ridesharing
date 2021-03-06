package rideSharing;

import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.population.Person;
import org.matsim.core.scoring.ScoringFunction;
import org.matsim.core.scoring.ScoringFunctionFactory;
import org.matsim.core.scoring.SumScoringFunction;
import org.matsim.core.scoring.functions.CharyparNagelActivityScoring;
import org.matsim.core.scoring.functions.CharyparNagelAgentStuckScoring;
import org.matsim.core.scoring.functions.CharyparNagelLegScoring;
import org.matsim.core.scoring.functions.CharyparNagelMoneyScoring;
import org.matsim.core.scoring.functions.ScoringParameters;

public class RideShareScoringFunctionFactory implements ScoringFunctionFactory {

	Scenario scenario;
	
	void ScoringFuctionFactory(){
	}
	
	void ScoringFuctionFacory(Scenario scenario){
		this.scenario = scenario;
	}
	
	public RideShareScoringFunctionFactory(final Scenario scenario){
		this.scenario = scenario;
	}
	
	@Override
	public ScoringFunction createNewScoringFunction(Person person) {
				
		SumScoringFunction sumScoringFunction = new SumScoringFunction();
		
		final ScoringParameters params =
				new ScoringParameters.Builder(scenario, person.getId()).build();
		sumScoringFunction.addScoringFunction(new CharyparNagelActivityScoring(params));
		sumScoringFunction.addScoringFunction(new CharyparNagelLegScoring(params, scenario.getNetwork()));
		sumScoringFunction.addScoringFunction(new CharyparNagelMoneyScoring(params));
		sumScoringFunction.addScoringFunction(new CharyparNagelAgentStuckScoring(params));	
		sumScoringFunction.addScoringFunction(new RideShareActivityScoring(params));

		
		return sumScoringFunction;
	}

}
