CREATE TABLE customer (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          first_name VARCHAR(50) NOT NULL,
                          last_name VARCHAR(50) NOT NULL,
                          pesel VARCHAR(11) NOT NULL UNIQUE,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          birth_date DATE NOT NULL,
                          agent_id BIGINT,
                          FOREIGN KEY (agent_id) REFERENCES agent(id)
);