--
-- PostgreSQL database dump
--

\restrict SJaXzLkyQZ1rJXzHeakhwN92qWIxoxfNaFCxdIJq7EVfmAinJyGmUuN2nHiOypd

-- Dumped from database version 14.20 (Homebrew)
-- Dumped by pg_dump version 14.20 (Homebrew)

-- Started on 2025-12-16 03:47:26 WIB

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 5 (class 2615 OID 16386)
-- Name: carbon_tracker; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA carbon_tracker;


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 213 (class 1259 OID 16476)
-- Name: carbon_activity; Type: TABLE; Schema: carbon_tracker; Owner: -
--

CREATE TABLE carbon_tracker.carbon_activity (
    id uuid DEFAULT carbon_tracker.uuid_generate_v4() NOT NULL,
    activity_type character varying(50) NOT NULL,
    amount numeric(10,2) NOT NULL,
    activity_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by character varying(50),
    created_at timestamp(6) without time zone NOT NULL
);


--
-- TOC entry 212 (class 1259 OID 16460)
-- Name: carbon_emission_result; Type: TABLE; Schema: carbon_tracker; Owner: -
--

CREATE TABLE carbon_tracker.carbon_emission_result (
    id uuid DEFAULT carbon_tracker.uuid_generate_v4() NOT NULL,
    carbon_activity_id uuid NOT NULL,
    emission_kg numeric(12,4) NOT NULL,
    calculated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    calculation_status character varying(50) DEFAULT 'SUCCESS'::character varying
);


--
-- TOC entry 211 (class 1259 OID 16439)
-- Name: emission_factor; Type: TABLE; Schema: carbon_tracker; Owner: -
--

CREATE TABLE carbon_tracker.emission_factor (
    id uuid DEFAULT carbon_tracker.uuid_generate_v4() NOT NULL,
    activity_type character varying(50) NOT NULL,
    unit character varying(20) NOT NULL,
    factor_kg numeric(10,4) NOT NULL,
    effective_from date DEFAULT CURRENT_DATE
);


--
-- TOC entry 3834 (class 0 OID 16476)
-- Dependencies: 213
-- Data for Name: carbon_activity; Type: TABLE DATA; Schema: carbon_tracker; Owner: -
--

COPY carbon_tracker.carbon_activity (id, activity_type, amount, activity_time, created_by, created_at) FROM stdin;
e334fa47-0ef0-4ecf-b082-cd8613a7a192	DRIVING_CAR	15.50	2025-12-14 19:00:00	CARBON_USER	2025-12-14 19:24:40.411944
f7a3c47c-4db6-4fa6-b8e6-bb227a4b93c3	DRIVING_CAR	15.50	2025-12-14 19:00:00	CARBON_USER	2025-12-14 19:26:56.793447
1ba32196-797f-4fdf-a03e-a23f659ca06b	DRIVING_CAR	15.50	2025-12-14 19:00:00	CARBON_USER	2025-12-14 19:26:57.652913
7628e72e-055b-41cb-907b-cb986097b94e	DRIVING_CAR	15.50	2025-12-14 19:00:00	CARBON_USER	2025-12-14 19:27:04.259314
0f33e58b-31f8-4d15-83fc-c16105f89ee6	DRIVING_CAR	15.50	2025-12-14 19:00:00	CARBON_USER	2025-12-14 19:27:05.101679
02b44ba8-294f-44cd-89be-afbbb0481910	DRIVING_CAR	15.50	2025-12-14 19:00:00	CARBON_USER	2025-12-14 19:28:47.189817
48bd66e9-b4f4-4172-be55-7cf290b80e98	DRIVING_CAR	15.50	2025-12-14 19:00:00	CARBON_USER	2025-12-14 19:28:48.152025
120e7bb7-efac-4248-aa58-82ebf2d71bc3	DRIVING_CAR	15.50	2025-12-14 19:00:00	CARBON_USER	2025-12-14 19:29:02.604894
12051d7e-5633-48d4-b0eb-9e4ff158a34d	DRIVING_CAR	15.50	2025-12-14 19:00:00	CARBON_USER	2025-12-14 20:41:27.426275
65d1f25a-7194-4bdf-a81c-043b9d62bb18	DRIVING_CAR	15.50	2025-12-14 19:00:00	CARBON_USER	2025-12-14 21:59:26.091238
1d516dbc-0850-4fd4-b576-35b2bba024ae	DRIVING_CAR	15.50	2025-12-14 19:00:00	CARBON_USER	2025-12-15 01:44:23.246644
ac44f863-b0f0-4a13-8883-c8ee871a8e91	COOKING_RICE	70.50	2025-12-14 19:00:00	CARBON_USER	2025-12-15 01:44:58.195321
fe58cb99-8c70-4fda-9770-8d173e161535	COOKING_RICE	70.50	2025-12-14 19:00:00	CARBON_USER	2025-12-15 21:48:52.914914
e183d0bc-19e8-498b-bc73-69c5a5acd0a0	COOKING_RICE	70.50	2025-12-14 19:00:00	CARBON_USER	2025-12-15 22:50:37.635667
54312cf9-c35c-4e98-baee-8d7a222ecabd	USING_AIR_CONDITIONER	70.50	2025-12-15 19:00:00	CARBON_USER	2025-12-15 23:01:04.208358
27e446ec-aaaf-415e-8b00-c1d97728d223	USING_AIR_CONDITIONER	70.50	2025-12-15 19:00:00	CARBON_USER	2025-12-15 23:01:33.702879
4fa04c7a-c991-4bf6-aac8-112341eeae94	USING_FAN	70.50	2025-12-17 19:00:00	CARBON_USER	2025-12-16 01:50:01.4105
12129917-2edf-49d6-9e9f-b4fa9d84253a	USING_FAN	70.50	2025-12-17 19:00:00	CARBON_USER	2025-12-16 01:50:23.215886
1587659f-10c2-42c5-ba9c-9132d5dc7cdb	USING_FAN	70.50	2025-12-17 19:00:00	CARBON_USER	2025-12-16 01:51:01.399141
\.


--
-- TOC entry 3833 (class 0 OID 16460)
-- Dependencies: 212
-- Data for Name: carbon_emission_result; Type: TABLE DATA; Schema: carbon_tracker; Owner: -
--

COPY carbon_tracker.carbon_emission_result (id, carbon_activity_id, emission_kg, calculated_at, calculation_status) FROM stdin;
6a7ab3af-2ecf-4e65-8974-359bbb9d2ef7	e334fa47-0ef0-4ecf-b082-cd8613a7a192	2.9807	2025-12-15 00:23:00.158284	SUCCESS
b18b9158-9291-4812-9c10-95f56514ab67	e334fa47-0ef0-4ecf-b082-cd8613a7a192	2.9807	2025-12-15 01:02:22.732676	SUCCESS
cf4f7bf7-0a0d-4b27-ade7-b945d061e29e	1d516dbc-0850-4fd4-b576-35b2bba024ae	3.2550	2025-12-15 01:44:23.250171	SUCCESS
1e23ac77-8091-4d23-8621-950d8cc15923	ac44f863-b0f0-4a13-8883-c8ee871a8e91	49.3500	2025-12-15 01:44:58.196724	SUCCESS
732fa462-e2d8-49e4-9697-b3134475b573	fe58cb99-8c70-4fda-9770-8d173e161535	49.3500	2025-12-15 21:48:52.919687	SUCCESS
933b8319-92fe-4a3f-8327-92a561cda23f	e334fa47-0ef0-4ecf-b082-cd8613a7a192	2.9807	2025-12-15 21:50:10.395263	SUCCESS
4a16ded2-5f94-4c7d-854c-aaa1ef7dcffb	1587659f-10c2-42c5-ba9c-9132d5dc7cdb	3.5250	2025-12-16 01:51:01.399942	SUCCESS
067c9d07-e0b7-4037-91f1-d28ab8b03dba	e334fa47-0ef0-4ecf-b082-cd8613a7a192	2.9807	2025-12-16 02:47:34.697231	SUCCESS
\.


--
-- TOC entry 3832 (class 0 OID 16439)
-- Dependencies: 211
-- Data for Name: emission_factor; Type: TABLE DATA; Schema: carbon_tracker; Owner: -
--

COPY carbon_tracker.emission_factor (id, activity_type, unit, factor_kg, effective_from) FROM stdin;
edfc51fa-8dc0-4896-b8f1-0a39b6cb5694	DRIVING_CAR	KM	0.2100	2025-12-14
09cb9f87-f5b9-4913-9e49-32fb345f3c4a	DRIVING_MOTOR	KM	0.1100	2025-12-14
78a76ff7-bce3-4943-8501-232e0b4f028b	USING_FAN	HOUR	0.0500	2025-12-14
e8cac46e-f33a-4308-a72a-3e1276f96293	CHARGING_LAPTOP	KWH	0.4000	2025-12-14
42db6b7b-dc38-4f93-9e82-b4e0b59ea4a1	USING_AIR_CONDITIONER	KWH	0.9000	2025-12-14
9987b984-817b-4a13-a00b-7505526372a9	COOKING_RICE	HOUR	0.7000	2025-12-14
\.


--
-- TOC entry 3691 (class 2606 OID 16482)
-- Name: carbon_activity carbon_activity_pkey; Type: CONSTRAINT; Schema: carbon_tracker; Owner: -
--

ALTER TABLE ONLY carbon_tracker.carbon_activity
    ADD CONSTRAINT carbon_activity_pkey PRIMARY KEY (id);


--
-- TOC entry 3689 (class 2606 OID 16466)
-- Name: carbon_emission_result carbon_emission_result_pkey; Type: CONSTRAINT; Schema: carbon_tracker; Owner: -
--

ALTER TABLE ONLY carbon_tracker.carbon_emission_result
    ADD CONSTRAINT carbon_emission_result_pkey PRIMARY KEY (id);


--
-- TOC entry 3687 (class 2606 OID 16445)
-- Name: emission_factor emission_factor_pkey; Type: CONSTRAINT; Schema: carbon_tracker; Owner: -
--

ALTER TABLE ONLY carbon_tracker.emission_factor
    ADD CONSTRAINT emission_factor_pkey PRIMARY KEY (id);


--
-- TOC entry 3692 (class 2606 OID 16487)
-- Name: carbon_emission_result fkq8bbvfq0x8pbxwpq767da8ypj; Type: FK CONSTRAINT; Schema: carbon_tracker; Owner: -
--

ALTER TABLE ONLY carbon_tracker.carbon_emission_result
    ADD CONSTRAINT fkq8bbvfq0x8pbxwpq767da8ypj FOREIGN KEY (carbon_activity_id) REFERENCES carbon_tracker.carbon_activity(id);


-- Completed on 2025-12-16 03:47:27 WIB

--
-- PostgreSQL database dump complete
--

\unrestrict SJaXzLkyQZ1rJXzHeakhwN92qWIxoxfNaFCxdIJq7EVfmAinJyGmUuN2nHiOypd

