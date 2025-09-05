package com.diesgut.saga.commons.dto.events;

import java.util.UUID;

public record OrderApprovedEvent(UUID orderId) {
}