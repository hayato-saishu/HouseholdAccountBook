package com.example.HouseholdAccountBook.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    @Id
    private String id; // PK
    private String userId; // ユーザーID
    private String categoryId; // カテゴリーID
    private String month; // 経費月
    private int amount; // 金額
    private String note; // 備考
    private LocalDateTime createdAt; // 記録作成日時
    private LocalDateTime updatedAt; // 記録更新日時
}
