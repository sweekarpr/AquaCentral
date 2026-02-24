CREATE TABLE tanks (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    volume DOUBLE PRECISION NOT NULL,
    volume_unit VARCHAR(20) NOT NULL,
    description VARCHAR(2000),
    created_at TIMESTAMP WITH TIME ZONE,
    owner_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE
);

CREATE INDEX idx_tanks_owner_id ON tanks(owner_id);
