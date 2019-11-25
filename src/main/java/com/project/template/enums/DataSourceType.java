package com.project.template.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The enum Data source type.
 *
 * @author suibin.wu
 */
@Getter
@AllArgsConstructor
public enum DataSourceType {

    /**
     * Master data source type.
     */
    MASTER("master"),


    /**
     * Slave data source type.
     */
    SLAVE("slave");


    private String name;

}
