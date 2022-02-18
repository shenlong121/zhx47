package top.zhx47.admin.datasource.enums;

public enum DaysEnum {
    Month1("Month1", 30),
    Month12("Month12", 365),
    Month3("Month3", 90),
    Month6("Month6", 180),
    Week1("Week1", 7);

    private final String type;
    private final Integer days;

    DaysEnum(String type, Integer days) {
        this.type = type;
        this.days = days;
    }

    public static int getDays(String type) {
        DaysEnum[] daysEnums = values();
        for (DaysEnum daysEnum : daysEnums) {
            if (daysEnum.getType().equals(type)) {
                return daysEnum.getDays();
            }
        }
        return 0;
    }

    public String getType() {
        return type;
    }

    public Integer getDays() {
        return days;
    }
}
