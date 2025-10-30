package org.apibp.dwellin.model;

public enum PaymentStatus {
    NOT_REQUIRED,   // If the owner doesn't collect advance
    PENDING,        // Waiting for offline payment
    MARKED_PAID     // Owner confirms payment received offline
}
