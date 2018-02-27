DROP TABLE IF EXISTS scratch_record;

CREATE TABLE scratch_record(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  uid BIGINT ,
  result DECIMAL,
  create_time LONG
);

DROP TABLE IF EXISTS today_scratch_pool;
CREATE TABLE today_scratch_pool(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  price DECIMAL NULL ,
  uid BIGINT,
  name VARCHAR(5),
  scratched BOOLEAN
);

