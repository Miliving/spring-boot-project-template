package com.project.template.pojo.req;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author suibin.wu
 */
@Data
public class RequestVO {

    @NotBlank(message="名称不能为空")
    private String name;

    @NotNull(message = "手机号不能空")
    private String mobile;

    @Min(value = 10, message = "数量不能少于10")
    private Integer number;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
}
