package com.vaggelis.company.repository;

import com.vaggelis.company.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Attribute Repository
 */
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
    Attribute findAttributeById(Long id);

    Attribute findByValue(String value);
}
