package emt.lab.budgetdeezer.artist_manager.domain.model;

public enum AccountType {
    Trial(10),
    Normal(100),
    Premium(Integer.MAX_VALUE);

    public final int maxCount;

    AccountType(int maxCount) {
        this.maxCount = maxCount;
    }
}