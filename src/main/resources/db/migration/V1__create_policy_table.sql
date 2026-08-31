CREATE TABLE policy (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        policy_number VARCHAR(20) NOT NULL UNIQUE,
                        type VARCHAR(20) NOT NULL,
                        holder_name VARCHAR(100) NOT NULL,
                        premium_amount DECIMAL(19,2) NOT NULL,
                        start_date DATE NOT NULL,
                        end_date DATE NOT NULL
);