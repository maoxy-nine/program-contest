package com.maoxy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author maoxy
 * @date 2023/08/18 10:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Customer {
    private String email;
    private String phoneNumber;
}
