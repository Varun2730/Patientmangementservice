-- Table: public.labcorp

-- DROP TABLE IF EXISTS public.labcorp;

CREATE TABLE IF NOT EXISTS public.labcorp
(
    labcorp_id integer NOT NULL DEFAULT nextval('labcorp_labcorp_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    email character varying(255) COLLATE pg_catalog."default",
    phone character varying(20) COLLATE pg_catalog."default",
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    latitude double precision,
    longitude double precision,
    CONSTRAINT labcorp_pkey PRIMARY KEY (labcorp_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.labcorp
    OWNER to postgres;

	INSERT INTO public.labcorp (name, email, phone, latitude, longitude) VALUES
('Downtown Health Lab', 'contact@downtown.lab', '212-555-0100', 40.7128, -74.0060),
('Uptown Diagnostics', 'help@uptown.lab', '212-555-0101', 40.7831, -73.9712),
('Westside Medical', 'support@westside.lab', '212-555-0102', 40.7680, -73.9822),
('East Village Labs', 'info@eastvillage.lab', '212-555-0103', 40.7265, -73.9815),
('SoHo Clinical', 'appointments@soho.lab', '212-555-0104', 40.7222, -74.0021),
('Midtown Pathology', 'midtown@pathology.lab', '212-555-0105', 40.7549, -73.9840),
('Brooklyn Bridge Labs', 'brooklyn@bridge.lab', '718-555-0106', 40.7061, -73.9969),
('Queens Medical Center', 'queens@medical.lab', '718-555-0107', 40.7488, -73.8749),
('Bronx Health Services', 'bronx@health.lab', '718-555-0108', 40.8448, -73.8648),
('Staten Island Diagnostics', 'staten@diagnostics.lab', '718-555-0109', 40.5795, -74.1502),
('Financial District Labs', 'fidi@labs.com', '212-555-0110', 40.7075, -74.0113),
('Greenwich Village Health', 'greenwich@health.lab', '212-555-0111', 40.7333, -73.9986),
('Harlem Medical Group', 'harlem@medical.lab', '212-555-0112', 40.8116, -73.9465),
('Chelsea Clinical', 'chelsea@clinical.lab', '212-555-0113', 40.7449, -74.0013),
('Tribeca Diagnostics', 'tribeca@diagnostics.lab', '212-555-0114', 40.7163, -74.0086),
('Upper East Side Labs', 'ues@labs.com', '212-555-0115', 40.7736, -73.9566),
('Upper West Side Health', 'uws@health.lab', '212-555-0116', 40.7870, -73.9754),
('Battery Park Medical', 'battery@park.lab', '212-555-0117', 40.7033, -74.0170),
('Columbus Circle Clinical', 'columbus@circle.lab', '212-555-0118', 40.7680, -73.9822),
('Times Square Diagnostics', 'times@square.lab', '212-555-0119', 40.7580, -73.9855),
('Astoria Health Group', 'astoria@health.lab', '718-555-0120', 40.7644, -73.9235),
('Williamsburg Medical', 'williamsburg@medical.lab', '718-555-0121', 40.7144, -73.9565),
('Bushwick Clinical Labs', 'bushwick@clinical.lab', '718-555-0122', 40.6944, -73.9213),
('Long Island City Health', 'lic@health.lab', '718-555-0123', 40.7447, -73.9485),
('Flushing Diagnostics', 'flushing@diagnostics.lab', '718-555-0124', 40.7675, -73.8331),
('Jamaica Medical Center', 'jamaica@medical.lab', '718-555-0125', 40.7021, -73.7928),
('Fordham Health Services', 'fordham@health.lab', '718-555-0126', 40.8612, -73.8860),
('Riverdale Clinical', 'riverdale@clinical.lab', '718-555-0127', 40.8895, -73.9084),
('Park Slope Labs', 'parkslope@labs.com', '718-555-0128', 40.6713, -73.9791),
('Coney Island Medical', 'coney@island.lab', '718-555-0129', 40.5749, -73.9857),
('Bay Ridge Diagnostics', 'bayridge@diagnostics.lab', '718-555-0130', 40.6263, -74.0328),
('Sheepshead Bay Health', 'sheepshead@health.lab', '718-555-0131', 40.5841, -73.9613),
('Bensonhurst Clinical', 'bensonhurst@clinical.lab', '718-555-0132', 40.6111, -73.9939),
('Crown Heights Labs', 'crownheights@labs.com', '718-555-0133', 40.6713, -73.9434),
('Bed-Stuy Medical', 'bedstuy@medical.lab', '718-555-0134', 40.6828, -73.9451),
('Greenpoint Diagnostics', 'greenpoint@diagnostics.lab', '718-555-0135', 40.7302, -73.9506),
('Sunnyside Health', 'sunnyside@health.lab', '718-555-0136', 40.7428, -73.9221),
('Woodside Clinical', 'woodside@clinical.lab', '718-555-0137', 40.7454, -73.9032),
('Jackson Heights Labs', 'jacksonheights@labs.com', '718-555-0138', 40.7554, -73.8832),
('Elmhurst Medical', 'elmhurst@medical.lab', '718-555-0139', 40.7408, -73.8778),
('Rego Park Diagnostics', 'regopark@diagnostics.lab', '718-555-0140', 40.7254, -73.8608),
('Forest Hills Health', 'foresthills@health.lab', '718-555-0141', 40.7196, -73.8448),
('Kew Gardens Clinical', 'kewgardens@clinical.lab', '718-555-0142', 40.7082, -73.8278),
('Richmond Hill Labs', 'richmondhill@labs.com', '718-555-0143', 40.6990, -73.8234),
('Ozone Park Medical', 'ozonepark@medical.lab', '718-555-0144', 40.6804, -73.8454),
('Howard Beach Diagnostics', 'howardbeach@diagnostics.lab', '718-555-0145', 40.6584, -73.8454),
('Rockaway Park Health', 'rockaway@health.lab', '718-555-0146', 40.5804, -73.8454),
('Broad Channel Clinical', 'broadchannel@clinical.lab', '718-555-0147', 40.6084, -73.8154),
('Tottenville Labs', 'tottenville@labs.com', '718-555-0148', 40.5124, -74.2454),
('Great Kills Medical', 'greatkills@medical.lab', '718-555-0149', 40.5524, -74.1554);