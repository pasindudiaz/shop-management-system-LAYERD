package model.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Order {
    private String orderId;
    private String cusId;
    private String date;
    private  Integer total;

}

