package com.example.projectboot.pojo;


//接受分页参数
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class PagePo {
    @TableField(exist = false)
    private Integer size;
    @TableField(exist = false)
    private Integer page;
}
