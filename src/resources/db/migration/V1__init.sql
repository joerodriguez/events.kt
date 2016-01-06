CREATE TABLE activity_types (
  id                SERIAL PRIMARY KEY,
  name              VARCHAR(255)
);

CREATE TABLE users (
  id                SERIAL PRIMARY KEY,
  name              VARCHAR(255)
);

CREATE TABLE events (
  id                SERIAL PRIMARY KEY,
  occurs_on         DATE,
  creator_id        INTEGER REFERENCES users (id),
  activity_type_id  INTEGER REFERENCES activity_types (id)
);

CREATE TABLE participants (
  id                SERIAL PRIMARY KEY,
  user_id           INTEGER REFERENCES users (id),
  event_id          INTEGER REFERENCES events (id),
  joined_at         TIMESTAMP
);


insert into activity_types (name)   values ('Skiing'), ('Climbing'), ('Biking');

insert into users (name)            values ('Jill'), ('Paul'), ('John');

insert into events (occurs_on, creator_id, activity_type_id)
values
    ('2015-12-21', 1, 3),
    ('2016-01-21', 2, 1),
    ('2016-02-21', 3, 2),
    ('2016-01-16', 1, 1);

insert into participants (user_id, event_id, joined_at)
values
    (2, 4, now()),
    (2, 3, now()),
    (2, 2, now()),
    (3, 4, now()),
    (3, 2, now()),
    (3, 3, now()),
    (3, 1, now());