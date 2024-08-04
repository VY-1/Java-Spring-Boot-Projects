package com.social.media.models;

import jakarta.persistence.*;
@Entity
public class SocialProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //add a relationship
    @OneToOne
    @JoinColumn(name = "social_user")
    private SocialUser user;
}
