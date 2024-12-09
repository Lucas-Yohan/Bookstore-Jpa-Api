package com.lyohan.bookstore_jpa.repositories;

import com.lyohan.bookstore_jpa.models.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface AuthorRepository extends JpaRepository<AuthorModel, UUID> {

}
