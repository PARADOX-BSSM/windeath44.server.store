package paradox.store.global.client.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class User {
    private String userId;
    private String email;
    private Integer money; // 임시 필드 money
    private String name;
    private String role; // USER, ADMIN
    private Long remainToken;
    private Long xp;
    private String levelTitle;
    private int level;
}
