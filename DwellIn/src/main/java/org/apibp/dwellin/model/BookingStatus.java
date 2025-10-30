package org.apibp.dwellin.model;

public enum BookingStatus {
    REQUESTED,          // User requested booking
    APPROVED,           // Property owner approved the booking
    REJECTED,           // Booking denied by owner
    CANCELLED,          // User canceled before approval or later
    CHECK_IN_PENDING,   // Approved, user has not checked in yet
    CHECKED_IN,         // Guest has checked in
    CHECKED_OUT,        // Guest has checked out
    NO_SHOW             // Guest didn't show up
}
