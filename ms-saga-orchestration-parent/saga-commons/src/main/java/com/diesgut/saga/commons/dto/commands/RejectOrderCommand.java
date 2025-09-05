package com.diesgut.saga.commons.dto.commands;

import java.util.UUID;

public record RejectOrderCommand(UUID orderId) {
}