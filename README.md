Used database: postgresql 9.6

Table for saving request logs:
CREATE TABLE public.request_logs(
	id bigserial PRIMARY KEY,
	login text UNIQUE NOT NULL,
	request_count integer NOT NULL
)