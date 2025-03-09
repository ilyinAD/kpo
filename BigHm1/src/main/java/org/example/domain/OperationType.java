package org.example.domain;

public enum OperationType {
    INCOME("доход"),
    EXPENSE("расход");
    private String title;

    OperationType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }

    public static OperationType fromString(String text) {
        for (OperationType operationType : OperationType.values()) {
            if (operationType.title.equalsIgnoreCase(text)) {
                return operationType;
            }
        }

        return null;
    }
}
