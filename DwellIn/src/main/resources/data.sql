------------------------------------------------------------
-- USERS
------------------------------------------------------------
INSERT INTO users (phone_no, name, mail, gender, dob, password, address, role)
VALUES ('9991112222', 'Alice Guest', 'alice@example.com', 'FEMALE', '1990-05-10', 'password1', 'Guest Address', 'GUEST');

INSERT INTO users (phone_no, name, mail, gender, dob, password, address, role)
VALUES ('8883334444', 'Bob Owner', 'bob@example.com', 'MALE', '1985-03-14', 'password2', 'Owner Address', 'OWNER');

INSERT INTO users (phone_no, name, mail, gender, dob, password, address, role)
VALUES ('7775556666', 'Charlie Both', 'charlie@example.com', 'MALE', '1992-09-20', 'password3', 'Both Address', 'BOTH');


------------------------------------------------------------
-- ADMINS
------------------------------------------------------------
INSERT INTO admins (
    email, full_name, password, phone_number, employee_id,
    department, admin_role, status, is_active, created_at
)
VALUES (
           'admin@dwellin.com', 'Super Admin', 'admin123', '9990001111',
           'EMP001', 'OPERATIONS', 'SUPER_ADMIN', 'ACTIVE', TRUE, CURRENT_TIMESTAMP
       );


------------------------------------------------------------
-- PROPERTY 1
------------------------------------------------------------
INSERT INTO properties (
    owner_id, full_name, preferred_name, business_name,
    business_registration_number, business_register_authority,
    contact_address, contact_phone, contact_email,
    local_permit_required, local_permit_details, tourism_dept_registered,
    commercial_license_required, gst_applicable,
    property_title, property_address, city, state, country, pin_code,
    property_type, number_of_rooms, max_guests,
    has_smoke_detector, has_fire_extinguisher, meets_safety_standards,
    description, price_per_night, verified, active, listing_created_date
)
VALUES (
           (SELECT id FROM users WHERE phone_no='8883334444'),
           'Bob Owner', NULL, 'Bob Rentals',
           'BRN1234', 'Local Authority',
           'Owner Address', '8883334444', 'bob@example.com',
           TRUE, 'Permit Approved', TRUE,
           TRUE, FALSE,
           'Cozy Mountain Retreat', '123 Hill Road', 'Manali', 'Himachal Pradesh', 'India', '175131',
           'VILLA', 3, 6,
           TRUE, TRUE, TRUE,
           'Beautiful mountain-side stay with great views.',
           3500.0, TRUE, TRUE, CURRENT_DATE
       );


------------------------------------------------------------
-- PROPERTY 2
------------------------------------------------------------
INSERT INTO properties (
    owner_id, full_name, preferred_name, business_name,
    business_registration_number, business_register_authority,
    contact_address, contact_phone, contact_email,
    local_permit_required, tourism_dept_registered,
    property_title, property_address, city, state, country, pin_code,
    property_type, number_of_rooms, max_guests,
    has_smoke_detector, has_fire_extinguisher, meets_safety_standards,
    description, price_per_night, verified, active, listing_created_date
)
VALUES (
           (SELECT id FROM users WHERE phone_no='7775556666'),
           'Charlie Both', NULL, 'Charlie Stays',
           'CRN5678', 'Gov Authority',
           'Both Address', '7775556666', 'charlie@example.com',
           FALSE, FALSE,
           'City Apartment Prime Location', '45 MG Road', 'Bangalore', 'Karnataka', 'India', '560001',
           'APARTMENT', 2, 4,
           TRUE, FALSE, TRUE,
           'Modern apartment in the heart of the city.',
           2200.0, FALSE, TRUE, CURRENT_DATE
       );


------------------------------------------------------------
-- BOOKING 1
------------------------------------------------------------
INSERT INTO bookings (
    guest_id, property_id,
    check_in, check_out,
    guest_count, booking_mode, booking_status,
    payment_status, payment_reference,
    created_at, updated_at, notes
)
VALUES (
           (SELECT id FROM users WHERE phone_no='9991112222'),
           (SELECT id FROM properties WHERE property_title='Cozy Mountain Retreat'),
           '2024-02-10T10:00',
           '2024-02-12T11:00',
           2, 'INSTANT', 'APPROVED',
           'MARKED_PAID', 'UPI123TEST',
           CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
           'Looking forward to the stay'
       );


------------------------------------------------------------
-- BOOKING 2
------------------------------------------------------------
INSERT INTO bookings (
    guest_id, property_id,
    check_in, check_out,
    guest_count, booking_mode, booking_status,
    payment_status, created_at, updated_at
)
VALUES (
           (SELECT id FROM users WHERE phone_no='9991112222'),
           (SELECT id FROM properties WHERE property_title='City Apartment Prime Location'),
           '2024-03-05T09:00',
           '2024-03-08T10:00',
           3, 'REQUEST', 'REQUESTED',
           'PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
       );


------------------------------------------------------------
-- REVIEW 1
------------------------------------------------------------
INSERT INTO reviews (
    reviewer_id, property_id, booking_id,
    rating, comment, review_type, status,
    is_anonymous, host_response, host_response_date,
    created_at, updated_at
)
VALUES (
           (SELECT id FROM users WHERE phone_no='9991112222'),
           (SELECT id FROM properties WHERE property_title='Cozy Mountain Retreat'),
           (SELECT booking_id FROM bookings WHERE notes='Looking forward to the stay'),
           5, 'Amazing stay! Highly recommended.',
           'GUEST_TO_PROPERTY', 'ACTIVE',
           FALSE, 'Thank you for staying with us!', CURRENT_TIMESTAMP,
           CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
       );


------------------------------------------------------------
-- REVIEW 2
------------------------------------------------------------
INSERT INTO reviews (
    reviewer_id, property_id, booking_id,
    rating, comment, review_type, status,
    is_anonymous, created_at, updated_at
)
VALUES (
           (SELECT id FROM users WHERE phone_no='9991112222'),
           (SELECT id FROM properties WHERE property_title='City Apartment Prime Location'),
           (SELECT booking_id FROM bookings WHERE guest_count=3),
           4, 'Great apartment, clean and well located.',
           'GUEST_TO_PROPERTY', 'ACTIVE',
           FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
       );
