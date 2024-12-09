package com.lyohan.bookstore_jpa.dtos;

import java.util.Set;
import java.util.UUID;


public record BookRecordDto(String title,
                            UUID publisherId,
                            Set<UUID> authorsId,
                            String reviewComment) {
}
