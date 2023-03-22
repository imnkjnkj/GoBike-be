CREATE TABLE "category" (
                        "id" serial PRIMARY KEY,
                        "name" text NOT NULL,
                        "description" text NOT NULL,
                        "created_at" timestamp with time zone DEFAULT (now()),
                        "created_by" int,
                        "updated_at" timestamp with time zone,
                        "updated_by" int,
                        "deleted" boolean DEFAULT false
);

ALTER TABLE news
    ADD category_id integer not null constraint "category_id" references category("id");