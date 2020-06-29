package emt.lab.budgetdeezer.shared_kernel.domain.base;

import org.springframework.lang.Nullable;

import java.io.Serializable;

public interface iIdentifiableDomainObject<ID extends Serializable>
        extends iDomainObject {

    @Nullable
    ID getId();
}
