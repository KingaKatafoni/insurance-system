ALTER TABLE policy ADD COLUMN customer_id BIGINT;
ALTER TABLE policy ADD CONSTRAINT fk_policy_customer FOREIGN KEY (customer_id) REFERENCES customer(id);