package data;

public class InfrastructureDesignComponent {
    private String name;
    private double availabilityPercentage;
    private double pricePerYear;

    public InfrastructureDesignComponent(String name, double availabilityPercentage, double pricePerYear){
        this.name = name;
        this.availabilityPercentage = availabilityPercentage;
        this.pricePerYear = pricePerYear;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
    
    public double getAvailabilityPercentage(){
        return this.availabilityPercentage;
    }

    public void setAvailabilityPercentage(double availabilityPercentage){
        this.availabilityPercentage = availabilityPercentage;
    }
    
    public double getPricePerYear(){
        return this.pricePerYear;
    }

    public void setPriceperYear(double pricePerYear){
        this.pricePerYear = pricePerYear;
    }
}