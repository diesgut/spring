package com.diesgut.segurity.features.user.dto;

import java.util.List;

public record UserDto(Long id, String username, boolean enabled, List<String> roles) {}