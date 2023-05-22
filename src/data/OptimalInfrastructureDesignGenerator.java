package data;

import java.util.ArrayList;
import java.util.Arrays;

public class OptimalInfrastructureDesignGenerator {
    private final ArrayList<FirewallComponent> AVAILABLE_FIREWALLS;
	private final ArrayList<WebserverComponent> AVAILABLE_WEBSERVERS;
	private final ArrayList<DatabaseserverComponent> AVAILABLE_DBSERVERS;
	private final ArrayList<InfrastructureDesignComponent> AVAILABLE_COMPONENTS;

    private InfrastructureDesign generatedInfrastructureDesign = null;

    private double currentLowestPrice = Double.MAX_VALUE;
	
	private final int MAX_FIREWALLS = 1;
	private final int MAX_WEBSERVERS = 6;
	private final int MAX_DATABASESERVERS = 6;
	
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
		this.AVAILABLE_COMPONENTS = new ArrayList<InfrastructureDesignComponent>(Arrays.asList(
				new InfrastructureDesignComponent("HAL9001W", 0.80, 2200),
				new InfrastructureDesignComponent("HAL9002W", 0.90, 3200),
				new InfrastructureDesignComponent("HAL9003W", 0.95, 5100),
				new InfrastructureDesignComponent("HAL9001DB", 0.90, 5100),
				new InfrastructureDesignComponent("HAL9002DB", 0.95, 7700),
				new InfrastructureDesignComponent("HAL9003DB", 0.98, 12200)));
	}


    private boolean isValidDesign(InfrastructureDesign design, double availabilityPercentage) {
        //TODO finetunen van isValidDesign, werkt volgens mij(Joey) niet helemaal als verwacht.
		//TODO volgens mij klopt ie wel zit alleen fout in het gebruik wss(Lars)

		//check of de prijs en beschikbaarheid kloppen
		boolean desginHasCorrectAvailabilityPercentage = design.calculateAvailabilityPercentage() > availabilityPercentage;
        boolean designIsCheaperThenCheapestGeneratedDesign = design.calculateTotalPrice() <= this.currentLowestPrice;

		//checken of er genoeg componente zijn
		boolean desginHasMoreThenMinFirewalls = design.getFirewallLayer().getInfrastructureComponents().size() <= MAX_FIREWALLS;
		boolean designHasMoreThenMinWebservers = design.getWebserverLayer().getInfrastructureComponents().size() <= MAX_WEBSERVERS;
		boolean designHasMoreThenMinDBServers = design.getDatabaseLayer().getInfrastructureComponents().size() <= MAX_DATABASESERVERS;
		boolean designHasMoreThenMinComponents = desginHasMoreThenMinFirewalls && designHasMoreThenMinWebservers && designHasMoreThenMinDBServers;

		//check of de combinatie van beide klopt
		boolean designHasRightAvailabilityAndPrice = desginHasCorrectAvailabilityPercentage && designIsCheaperThenCheapestGeneratedDesign;
		boolean isAcceptableDesign = designHasMoreThenMinComponents && designHasRightAvailabilityAndPrice;

		//wanneer het kan returned het true anders false
		return isAcceptableDesign;
	}

	private void backtrack(int index, InfrastructureDesign design, double targetPercentage) throws Exception {
		System.out.println(design);
		if (this.isValidDesign(design, targetPercentage)) {
			try {
				this.currentLowestPrice = design.calculateTotalPrice();
				this.generatedInfrastructureDesign = design.deepCopy();
			} catch (CloneNotSupportedException e) {
				System.out.println("Could not clone object");
			}
			return;
		}

		// Explore all combinations of webservers and databaseservers
		for (int webIndex = index; webIndex < this.AVAILABLE_WEBSERVERS.size(); webIndex++) {
			design.getWebserverLayer().getInfrastructureComponents().add(AVAILABLE_WEBSERVERS.get(webIndex));
			for (int dbIndex = index; dbIndex < this.AVAILABLE_DBSERVERS.size(); dbIndex++) {
				design.getDatabaseLayer().getInfrastructureComponents().add(AVAILABLE_DBSERVERS.get(dbIndex));
				this.backtrack(index + 1, design, targetPercentage);
				design.getDatabaseLayer().getInfrastructureComponents().remove(design.getDatabaseLayer().getInfrastructureComponents().size() - 1);
			}
			design.getWebserverLayer().getInfrastructureComponents().remove(design.getWebserverLayer().getInfrastructureComponents().size() - 1);
		}
	}
	public InfrastructureDesign generateOptimizedDesign(double targetPercentage) throws Exception {
		InfrastructureDesign design = new InfrastructureDesign();
        design.getFirewallLayer().getInfrastructureComponents().add(AVAILABLE_FIREWALLS.get(0));
		design.getDatabaseLayer().getInfrastructureComponents().add(AVAILABLE_DBSERVERS.get(0));
		design.getWebserverLayer().getInfrastructureComponents().add(AVAILABLE_WEBSERVERS.get(0));
		backtrack(0, design, targetPercentage);
		return this.generatedInfrastructureDesign;
	}


}
