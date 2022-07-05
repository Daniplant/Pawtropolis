package game.items.template;

public class Item {
    private String name;
    private String description;

    // It'll will never be modified,
    // I think it's better if it is final
    private final int requiredSlotSpace;

    public String getName() {
        return name;
    }

    public Item(String name, int requiredSlotSpace){
        this.name = name;
        this.requiredSlotSpace = requiredSlotSpace;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRequiredSlotSpace() {
        return requiredSlotSpace;
    }

}
