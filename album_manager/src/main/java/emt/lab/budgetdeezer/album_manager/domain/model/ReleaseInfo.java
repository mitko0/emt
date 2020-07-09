package emt.lab.budgetdeezer.album_manager.domain.model;

import emt.lab.budgetdeezer.shared_kernel.domain.base.iValueObject;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReleaseInfo implements iValueObject {
    private LocalDate time;

    protected ReleaseInfo(){
        this.time = LocalDate.now();
    }

    protected ReleaseInfo(LocalDate time){
        this.time = time;
    }
}
