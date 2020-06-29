package emt.lab.budgetdeezer.artist_manager.domain.model;

import emt.lab.budgetdeezer.shared_kernel.domain.base.iValueObject;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Getter
@Embeddable
public class AccountState implements iValueObject {

    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;

    @Column(name = "current_count")
    private int currentCount;

    protected AccountState() {
    }

    private AccountState(AccountType accountType, int currentCount) {
        this.accountType = accountType;

        if (currentCount > accountType.maxCount) {
            throw new IllegalArgumentException("Value isn't allowed");
        }
        this.currentCount = Math.max(currentCount, 0);
    }

    public static AccountState valueOf(AccountType accountType) {
        return new AccountState(accountType, 0);
    }

    public AccountState registerAlbums(int count) {
        return new AccountState(this.accountType, this.currentCount + count);
    }

    public AccountState unregisterAlbums(int count) {
        return new AccountState(this.accountType, this.currentCount - count);
    }

    public AccountState changeAccountType(AccountType type) {
        if (this.accountType.compareTo(type) > 0 && this.currentCount > type.maxCount) {
            throw new IllegalArgumentException("Too many registered albums");
        }

        return new AccountState(type, this.currentCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.accountType, this.currentCount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !this.getClass().equals(obj.getClass())) return false;

        AccountState accountState = (AccountState) obj;
        return Objects.equals(this.accountType, accountState.accountType)
                && Objects.equals(this.currentCount, accountState.currentCount);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", this.accountType, this.currentCount);
    }
}
