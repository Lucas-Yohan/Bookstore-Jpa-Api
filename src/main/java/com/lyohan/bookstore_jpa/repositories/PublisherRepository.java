package com.lyohan.bookstore_jpa.repositories;


import com.lyohan.bookstore_jpa.models.PublisherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PublisherRepository extends JpaRepository<PublisherModel, UUID> {
}
