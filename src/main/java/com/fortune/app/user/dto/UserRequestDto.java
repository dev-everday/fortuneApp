package com.fortune.app.user.dto;

import com.fortune.app.user.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.fortune.app.user.validation.UserValidationGroups.SignUpGroup;
import static com.fortune.app.user.validation.UserValidationGroups.LogInGroup;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {
    @NotNull(groups = SignUpGroup.class, message = "name is required.")
    private String name;

    @NotNull(groups = SignUpGroup.class, message = "nickname is required.")
    private String nickname;

    @NotNull(groups = SignUpGroup.class, message = "birth is required.")
    private Date birth;

    @NotNull(groups = SignUpGroup.class, message = "email is required.")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "Invalid email format. Please enter a valid email."
    )
    private String email;

    @NotNull(groups = {LogInGroup.class, SignUpGroup.class}, message = "provider is required.")
    private String provider;

    @NotNull(groups = {LogInGroup.class, SignUpGroup.class}, message = "providerUid is required.")
    private String providerUid;

    @NotNull(groups = {LogInGroup.class, SignUpGroup.class}, message = "accessToken is required.")
    private String accessToken;

    @NotNull(groups = SignUpGroup.class, message = "refreshToken is required.")
    private String refreshToken;

    public static UserRequestDto mapToDto(User entity) {
        return UserRequestDto.builder()
                .name(entity.getName())
                .email(entity.getEmail())
                .birth(entity.getBirth())
                .nickname(entity.getNickname())
                .provider(entity.getProvider())
                .providerUid(entity.getProviderUid())
                .accessToken(entity.getAccessToken())
                .refreshToken(entity.getRefreshToken())
                .build();
    }
}