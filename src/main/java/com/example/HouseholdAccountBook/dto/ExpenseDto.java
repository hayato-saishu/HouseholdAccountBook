package com.example.HouseholdAccountBook.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
public class ExpenseDto {
    private String id;
    private String userId;
    private String categoryId;
    private String month;
    private int amount;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
