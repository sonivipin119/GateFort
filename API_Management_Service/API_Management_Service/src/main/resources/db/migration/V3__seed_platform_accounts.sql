INSERT INTO management_users (
                              organization_id, role_id, username, password_hash, enabled, created_at, updated_at
)

SELECT NULL,roles.id, 'platform-admin',
       '$2a$12$8E6HeKg4UP2RcLR2CmQANemEDy7/MYRQJ9al8IxZwRfiaPYgXXBhe',
     TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
FROM roles WHERE roles.name = 'PLATFORM_ADMIN';


INSERT INTO management_users (
    organization_id, role_id, username, password_hash, enabled, created_at, updated_at
)

SELECT NULL,roles.id, 'gateway-service',
       '$2a$12$8E6HeKg4UP2RcLR2CmQANemEDy7/MYRQJ9al8IxZwRfiaPYgXXBhe',
       TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
FROM roles WHERE roles.name = 'GATEWAY_SERVICE';
