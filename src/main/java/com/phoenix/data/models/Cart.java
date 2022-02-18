package com.phoenix.data.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
//@Builder
//@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CreationTimestamp
    private LocalDate createdAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) //means save
    private List<Item> itemList;

//    @Transient //dont create or save this attribute on the database

    private Double totalPrice = 0.0;

    public void addItem(Item item){
        if(itemList == null){
            itemList = new ArrayList<>();
        }
        itemList.add(item);
    }

}
