public  class Component {
    private int beschikbareOpslag, upTime, processorBelasting;
    private String icon, hostnaam;
    private boolean beschikbaar;

    public Component(int beschikbareOpslag, int upTime, int processorBelasting, String icon, String hostnaam, boolean beschikbaar) {
        this.beschikbareOpslag = beschikbareOpslag;
        this.upTime = upTime;
        this.processorBelasting = processorBelasting;
        this.icon = icon;
        this.hostnaam = hostnaam;
        this.beschikbaar = beschikbaar;
    }
    public int getBeschikbareOpslag() {
        return beschikbareOpslag;
    }
    public int getUpTime() {
        return upTime;
    }
    public int getProcessorBelasting() {
        return processorBelasting;
    }
    public String getIcon() {
        return icon;
    }
    public String getHostnaam() {
        return hostnaam;
    }
    public boolean isBeschikbaar() {
        return beschikbaar;
    }
}
