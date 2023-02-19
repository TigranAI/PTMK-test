ALTER TABLE users
ADD INDEX username_sex_idx(username(1), sex) VISIBLE;