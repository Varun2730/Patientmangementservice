-- Table: public.booking

-- DROP TABLE IF EXISTS public.booking;

CREATE TABLE IF NOT EXISTS public.booking
(
    booking_id uuid NOT NULL DEFAULT gen_random_uuid(),
    patient_id integer NOT NULL,
    labcorp_id integer NOT NULL,
    booking_date date NOT NULL,
    slot_type character varying(20) COLLATE pg_catalog."default" NOT NULL,
    booked_at timestamp with time zone NOT NULL DEFAULT now(),
    CONSTRAINT booking_pkey PRIMARY KEY (booking_id),
    CONSTRAINT booking_patient_id_labcorp_id_booking_date_slot_type_key UNIQUE (patient_id, labcorp_id, booking_date, slot_type),
    CONSTRAINT booking_slot_type_check CHECK (slot_type::text = ANY (ARRAY['MORNING'::character varying, 'AFTERNOON'::character varying, 'EVENING'::character varying]::text[]))
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.booking
    OWNER to postgres;

	-- This script populates the 'booking' table with 50 records,
-- linking patients to available lab slots.

INSERT INTO public.booking (patient_id, labcorp_id, booking_date, slot_type) VALUES
(1, 1, '2025-08-10', 'MORNING'),
(2, 2, '2025-08-10', 'EVENING'),
(3, 3, '2025-08-11', 'MORNING'),
(4, 4, '2025-08-12', 'MORNING'),
(5, 5, '2025-08-12', 'AFTERNOON'),
(6, 6, '2025-08-13', 'EVENING'),
(7, 7, '2025-08-14', 'MORNING'),
(8, 8, '2025-08-14', 'AFTERNOON'),
(9, 10, '2025-08-16', 'MORNING'), -- Skipped lab 9 as it had 0 morning slots
(10, 10, '2025-08-16', 'AFTERNOON'),
(11, 11, '2025-08-16', 'MORNING'),
(12, 12, '2025-08-17', 'AFTERNOON'),
(13, 13, '2025-08-17', 'EVENING'),
(14, 14, '2025-08-17', 'MORNING'),
(15, 15, '2025-08-18', 'MORNING'),
(16, 16, '2025-08-18', 'AFTERNOON'),
(17, 17, '2025-08-18', 'MORNING'),
(18, 20, '2025-08-19', 'EVENING'), -- Skipped lab 18 as it had 0 afternoon slots
(19, 19, '2025-08-19', 'AFTERNOON'),
(20, 20, '2025-08-19', 'EVENING'),
(21, 21, '2025-08-20', 'EVENING'),
(22, 22, '2025-08-20', 'MORNING'),
(23, 23, '2025-08-20', 'MORNING'),
(24, 24, '2025-08-21', 'AFTERNOON'),
(25, 25, '2025-08-21', 'AFTERNOON'),
(26, 26, '2025-08-21', 'MORNING'),
(27, 28, '2025-08-22', 'EVENING'), -- Skipped lab 27 as it had 0 morning slots
(28, 28, '2025-08-22', 'EVENING'),
(29, 29, '2025-08-22', 'MORNING'),
(30, 30, '2025-08-23', 'AFTERNOON'),
(31, 31, '2025-08-23', 'MORNING'),
(32, 32, '2025-08-23', 'AFTERNOON'),
(33, 33, '2025-08-24', 'EVENING'),
(34, 34, '2025-08-24', 'MORNING'),
(35, 35, '2025-08-24', 'MORNING'),
(36, 36, '2025-08-25', 'AFTERNOON'),
(37, 37, '2025-08-25', 'MORNING'),
(38, 40, '2025-08-26', 'EVENING'), -- Skipped lab 38 as it had 0 afternoon slots
(39, 39, '2025-08-26', 'AFTERNOON'),
(40, 40, '2025-08-26', 'EVENING'),
(41, 41, '2025-08-26', 'EVENING'),
(42, 42, '2025-08-27', 'MORNING'),
(43, 43, '2025-08-27', 'MORNING'),
(44, 44, '2025-08-27', 'AFTERNOON'),
(45, 45, '2025-08-28', 'AFTERNOON'),
(46, 46, '2025-08-28', 'MORNING'),
(47, 48, '2025-08-29', 'EVENING'), -- Skipped lab 47 as it had 0 morning slots
(48, 48, '2025-08-29', 'EVENING'),
(49, 49, '2025-08-29', 'MORNING'),
(50, 50, '2025-08-29', 'AFTERNOON');
