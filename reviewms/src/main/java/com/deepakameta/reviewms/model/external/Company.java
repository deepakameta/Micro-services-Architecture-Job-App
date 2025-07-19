package com.deepakameta.reviewms.model.external;

import com.deepakameta.reviewms.model.Review;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;
    private String name;
    private String address;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews;
}
