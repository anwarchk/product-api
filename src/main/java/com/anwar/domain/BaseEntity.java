package com.anwar.domain;

import javax.persistence.*;

/**
 * @author Anwar
 */

@MappedSuperclass
public abstract class BaseEntity {

    private Long id;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
