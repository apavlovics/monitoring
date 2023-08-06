-- Insert test data

INSERT INTO accounts (username) VALUES
  ('test'),
  ('other');

INSERT INTO records (record_type, created_at, account_id) VALUES
  ('LOGIN', '2018-01-02 20:08:08.123+0:00', 1),
  ('LOGOUT', '2018-01-03 11:30:45.555+0:00', 1);
