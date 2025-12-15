-- DROP SCHEMA carbon_tracker;

CREATE SCHEMA carbon_tracker AUTHORIZATION postgres;
-- carbon_tracker.carbon_activity definition

-- Drop table

-- DROP TABLE carbon_tracker.carbon_activity;

CREATE TABLE carbon_tracker.carbon_activity (
	id uuid DEFAULT carbon_tracker.uuid_generate_v4() NOT NULL,
	activity_type varchar(50) NOT NULL,
	amount numeric(10, 2) NOT NULL,
	activity_time timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	created_by varchar(50) NULL,
	created_at timestamp(6) NOT NULL,
	CONSTRAINT carbon_activity_pkey PRIMARY KEY (id)
);


-- carbon_tracker.emission_factor definition

-- Drop table

-- DROP TABLE carbon_tracker.emission_factor;

CREATE TABLE carbon_tracker.emission_factor (
	id uuid DEFAULT carbon_tracker.uuid_generate_v4() NOT NULL,
	activity_type varchar(50) NOT NULL,
	unit varchar(20) NOT NULL,
	factor_kg numeric(10, 4) NOT NULL,
	effective_from date DEFAULT CURRENT_DATE NULL,
	CONSTRAINT emission_factor_pkey PRIMARY KEY (id)
);


-- carbon_tracker.carbon_emission_result definition

-- Drop table

-- DROP TABLE carbon_tracker.carbon_emission_result;

CREATE TABLE carbon_tracker.carbon_emission_result (
	id uuid DEFAULT carbon_tracker.uuid_generate_v4() NOT NULL,
	carbon_activity_id uuid NOT NULL,
	emission_kg numeric(12, 4) NOT NULL,
	calculated_at timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	calculation_status varchar(50) DEFAULT 'SUCCESS'::character varying NULL,
	CONSTRAINT carbon_emission_result_pkey PRIMARY KEY (id),
	CONSTRAINT fkq8bbvfq0x8pbxwpq767da8ypj FOREIGN KEY (carbon_activity_id) REFERENCES carbon_tracker.carbon_activity(id)
);



-- DROP FUNCTION carbon_tracker.uuid_generate_v1();

CREATE OR REPLACE FUNCTION carbon_tracker.uuid_generate_v1()
 RETURNS uuid
 LANGUAGE c
 PARALLEL SAFE STRICT
AS '$libdir/uuid-ossp', $function$uuid_generate_v1$function$
;

-- DROP FUNCTION carbon_tracker.uuid_generate_v1mc();

CREATE OR REPLACE FUNCTION carbon_tracker.uuid_generate_v1mc()
 RETURNS uuid
 LANGUAGE c
 PARALLEL SAFE STRICT
AS '$libdir/uuid-ossp', $function$uuid_generate_v1mc$function$
;

-- DROP FUNCTION carbon_tracker.uuid_generate_v3(uuid, text);

CREATE OR REPLACE FUNCTION carbon_tracker.uuid_generate_v3(namespace uuid, name text)
 RETURNS uuid
 LANGUAGE c
 IMMUTABLE PARALLEL SAFE STRICT
AS '$libdir/uuid-ossp', $function$uuid_generate_v3$function$
;

-- DROP FUNCTION carbon_tracker.uuid_generate_v4();

CREATE OR REPLACE FUNCTION carbon_tracker.uuid_generate_v4()
 RETURNS uuid
 LANGUAGE c
 PARALLEL SAFE STRICT
AS '$libdir/uuid-ossp', $function$uuid_generate_v4$function$
;

-- DROP FUNCTION carbon_tracker.uuid_generate_v5(uuid, text);

CREATE OR REPLACE FUNCTION carbon_tracker.uuid_generate_v5(namespace uuid, name text)
 RETURNS uuid
 LANGUAGE c
 IMMUTABLE PARALLEL SAFE STRICT
AS '$libdir/uuid-ossp', $function$uuid_generate_v5$function$
;

-- DROP FUNCTION carbon_tracker.uuid_nil();

CREATE OR REPLACE FUNCTION carbon_tracker.uuid_nil()
 RETURNS uuid
 LANGUAGE c
 IMMUTABLE PARALLEL SAFE STRICT
AS '$libdir/uuid-ossp', $function$uuid_nil$function$
;

-- DROP FUNCTION carbon_tracker.uuid_ns_dns();

CREATE OR REPLACE FUNCTION carbon_tracker.uuid_ns_dns()
 RETURNS uuid
 LANGUAGE c
 IMMUTABLE PARALLEL SAFE STRICT
AS '$libdir/uuid-ossp', $function$uuid_ns_dns$function$
;

-- DROP FUNCTION carbon_tracker.uuid_ns_oid();

CREATE OR REPLACE FUNCTION carbon_tracker.uuid_ns_oid()
 RETURNS uuid
 LANGUAGE c
 IMMUTABLE PARALLEL SAFE STRICT
AS '$libdir/uuid-ossp', $function$uuid_ns_oid$function$
;

-- DROP FUNCTION carbon_tracker.uuid_ns_url();

CREATE OR REPLACE FUNCTION carbon_tracker.uuid_ns_url()
 RETURNS uuid
 LANGUAGE c
 IMMUTABLE PARALLEL SAFE STRICT
AS '$libdir/uuid-ossp', $function$uuid_ns_url$function$
;

-- DROP FUNCTION carbon_tracker.uuid_ns_x500();

CREATE OR REPLACE FUNCTION carbon_tracker.uuid_ns_x500()
 RETURNS uuid
 LANGUAGE c
 IMMUTABLE PARALLEL SAFE STRICT
AS '$libdir/uuid-ossp', $function$uuid_ns_x500$function$
;