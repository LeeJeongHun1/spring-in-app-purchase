package com.springinapppurchase.entity;

import com.springinapppurchase.entity.user.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "subscription_plan")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class SubscriptionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "min_follower")
    private int minFollower;

    @Column(name = "max_follower")
    private int maxFollower;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "reference_name")
    private String referenceName;

    @Column(name = "duration")
    private String duration;

    @Column(name = "type")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private User creator;

    @CreatedDate
    @Column(name = "create_date")
    private Instant createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modifier_id")
    private User modifier;

    @LastModifiedDate
    @Column(name = "modify_date")
    private Instant modifyDate;
}
