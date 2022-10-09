package az.company.bookstore.enums;


public enum EnumStatus {

    ACTIVE(1),
    DEACTIVE(0);

    private int value;

    private EnumStatus(int value){
        this.value=value;
    }

    public int getValue() {
        return value;
    }
}
