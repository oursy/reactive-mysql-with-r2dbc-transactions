package com.example.mysqlr2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Table(value = "`order`")
@Data
public class Order {
    @Id
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String code;
}
