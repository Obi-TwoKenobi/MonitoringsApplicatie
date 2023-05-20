package data;

import java.util.ArrayList;
import java.util.Arrays;

public class OptimalInfrastructureDesignGenerator {
    private final ArrayList<FirewallComponent> AVAILABLE_FIREWALLS;
	private final ArrayList<WebserverComponent> AVAILABLE_WEBSERVERS;
	private final ArrayList<DatabaseserverComponent> AVAILABLE_DBSERVERS;

    private InfrastructureDesign generatedInfrastructureDesign = null;

    private double currentLowestPrice = 999999999999999.0;
	
	private final int MIN_FIREWALLS = 1;
	private final int MIN_WEBSERVERS = 1;
	private final int MIN_DATABASESERVERS = 1;
	
	public OptimalInfrastructureDesignGenerator() {
		this.AVAILABLE_FIREWALLS = new ArrayList<FirewallComponent>(Arrays.asList(
				new FirewallComponent("Pfsense", 0.99998, 4000)));
		this.AVAILABLE_WEBSERVERS = new ArrayList<WebserverComponent>(Arrays.asList(
				new WebserverComponent("HAL9001W", 0.80, 2200),
				new WebserverComponent("HAL9002W", 0.90, 3200),
				new WebserverComponent("HAL9003W", 0.95, 5100)));
		this.AVAILABLE_DBSERVERS = new ArrayList<DatabaseserverComponent>(Arrays.asList(
				new DatabaseserverComponent("HAL9001DB", 0.90, 5100),
				new DatabaseserverComponent("HAL9002DB", 0.95, 7700),
				new DatabaseserverComponent("HAL9003DB", 0.98, 12200)));
	}


    private boolean isValidDesign(InfrastructureDesign design, double availabilityPercentage) {
        //TODO finetunen van isValidDesign, werkt volgens mij(Joey) niet helemaal als verwacht.
		boolean desginHasCorrectAvailabilityPercentage = design.calculateAvailabilityPercentage() >= availabilityPercentage;
		
        boolean designIsCheaperThenCheapestGeneratedDesign = design.calculateTotalPrice() <= this.currentLowestPrice;

		boolean desginHasMoreThenMinFirewalls = design.getFirewallLayer().getInfrastructureComponents().size() >= MIN_FIREWALLS;
		boolean designHasMoreThenMinWebservers = design.getWebserverLayer().getInfrastructureComponents().size() >= MIN_WEBSERVERS;
		boolean designHasMoreThenMinDBServers = design.getDatabaseLayer().getInfrastructureComponents().size() >= MIN_DATABASESERVERS;
		
		boolean designHasMoreThenMinComponents = desginHasMoreThenMinFirewalls && designHasMoreThenMinWebservers && designHasMoreThenMinDBServers;

		boolean designHasRightAvailabilityAndPrice = desginHasCorrectAvailabilityPercentage && designIsCheaperThenCheapestGeneratedDesign;
		
		boolean isAcceptableDesign = designHasMoreThenMinComponents && designHasRightAvailabilityAndPrice;
		return isAcceptableDesign;
	}
	
	private void backtrack(int index, InfrastructureDesign design, double targetPercentage) {
		System.out.println(this.generatedInfrastructureDesign);
		if(this.isValidDesign(design, targetPercentage)) {
            try {
                this.currentLowestPrice = design.calculateTotalPrice();
                this.generatedInfrastructureDesign = design.deepCopy();
                return;
            } catch (CloneNotSupportedException e) {
                System.out.println("could not clone object");
            }catch(Exception e){
                e.printStackTrace();
            }
		}

        //TODO backtrack gedeelte verbeteren
        /*
         * we bactracken nu eerst de webservers en dan de database server
         * als je 99,99% invuld krijg je hierdoor geen oplsosing
         * testcode zit in de main.java voordat de applicatie opstart
         */
		for(index = index; index < this.AVAILABLE_WEBSERVERS.size(); index++) {
			design.getWebserverLayer().getInfrastructureComponents().add(AVAILABLE_WEBSERVERS.get(index));
			this.backtrack(index + 1, design, targetPercentage);			
			design.getDatabaseLayer().getInfrastructureComponents().add(AVAILABLE_DBSERVERS.get(index));
			this.backtrack(index + 1, design, targetPercentage);
			design.getDatabaseLayer().getInfrastructureComponents().remove(design.getDatabaseLayer().getInfrastructureComponents().size() - 1);
			design.getWebserverLayer().getInfrastructureComponents().remove(design.getWebserverLayer().getInfrastructureComponents().size() - 1);
		}
	}
	
	public InfrastructureDesign generateOptimizedDesign(double targetPercentage) {
		InfrastructureDesign design = new InfrastructureDesign();
        design.getFirewallLayer().getInfrastructureComponents().add(AVAILABLE_FIREWALLS.get(0));
		backtrack(0, design, targetPercentage);
		return this.generatedInfrastructureDesign;
	}


}
