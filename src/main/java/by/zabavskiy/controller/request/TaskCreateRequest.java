package by.zabavskiy.controller.request;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;

@Data
public class TaskCreateRequest {

    private String name;

    private String description;

    private Date startDate;

    private Date endDate;

    private Long status;

}


