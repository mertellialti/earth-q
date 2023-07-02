package com.ellialti.earthQW.domain.base;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
public class Base {
    @Id
    protected String id;
    protected Date lastUpdate;
}
