package com.diesgut.saga.commons.dto.commands;

import java.util.UUID;


public record ApproveOrderCommand(UUID orderId) {
}