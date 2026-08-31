CREATE TABLE agent_policy (
                              agent_id BIGINT NOT NULL,
                              policy_id BIGINT NOT NULL,
                              PRIMARY KEY (agent_id, policy_id),
                              FOREIGN KEY (agent_id) REFERENCES agent(id),
                              FOREIGN KEY (policy_id) REFERENCES policy(id)
);