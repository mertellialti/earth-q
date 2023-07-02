package com.ellialti.earthQW.domain.need;
import com.ellialti.earthQW.domain.account.Account;
import com.ellialti.earthQW.domain.base.Base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Need extends Base {
    private String ownerId;
    private Account owner;
    private List<ArrayList> relatedAccountIds;
    private List<Account> relatedAccounts;
    private boolean isActive;
    private boolean isCanceled;
    private boolean isAccepted;
    private Date createdDate;
    private NeedType needType;


    public Need(String ownerId, Account owner, List<ArrayList> relatedAccountIds, List<Account> relatedAccounts, boolean isActive, boolean isCanceled, boolean isAccepted, Date createdDate, NeedType needType) {
        this.ownerId = ownerId;
        this.owner = owner;
        this.relatedAccountIds = relatedAccountIds;
        this.relatedAccounts = relatedAccounts;
        this.isActive = isActive;
        this.isCanceled = isCanceled;
        this.isAccepted = isAccepted;
        this.createdDate = createdDate;
        this.needType = needType;
    }

    public Need() {
    }

    public enum NeedType {
        WATER,
        FOOD,
        EMERGENCY,
        MEDICAL_ASSISTANCE,
        HOUSING,
        CLOTHING
    }
}
