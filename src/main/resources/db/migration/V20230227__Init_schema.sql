CREATE TYPE "role_name" AS ENUM (
  'USER',
  'MOD',
  'ADMIN'
);

CREATE TYPE "news_status" AS ENUM (
  'DRAFT',
  'PUBLISH',
  'HIDDEN'
);

CREATE TABLE "user" (
                        "id" serial PRIMARY KEY,
                        "username" text NOT NULL,
                        "email" text NOT NULL unique ,
                        "password" text NOT NULL,
                        "created_at" timestamp DEFAULT (now()),
                        "created_by" int,
                        "updated_at" timestamp,
                        "updated_by" int,
                        "deleted" boolean DEFAULT false
);

CREATE TABLE "role" (
                        "id" serial PRIMARY KEY,
                        "name" role_name NOT NULL
);

CREATE TABLE "user_role" (
                             "user_id" int,
                             "role_id" int,
                             PRIMARY KEY ("user_id", "role_id")
);

CREATE TABLE "news" (
                        "id" serial PRIMARY KEY,
                        "title" text NOT NULL,
                        "description" text NOT NULL,
                        "status" news_status NOT NULL DEFAULT 'DRAFT',
                        "thumbnail" text,
                        "cover_image" text,
                        "sapo" text,
                        "user_id" int,
                        "created_at" timestamp DEFAULT (now()),
                        "created_by" int,
                        "updated_at" timestamp,
                        "updated_by" int,
                        "deleted" boolean DEFAULT false
);

CREATE TABLE "user_like" (
                             "news_id" int,
                             "user_id" int,
                             "liked" boolean DEFAULT false,
                             PRIMARY KEY ("news_id", "user_id")
);

CREATE INDEX ON "user" ("email");

CREATE INDEX ON "user" ("username");

CREATE INDEX ON "news" ("id", "user_id");

ALTER TABLE "user_role" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "user_role" ADD FOREIGN KEY ("role_id") REFERENCES "role" ("id");

ALTER TABLE "news" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "user_like" ADD FOREIGN KEY ("news_id") REFERENCES "news" ("id");

ALTER TABLE "user_like" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");
