-- insert data table user
INSERT INTO "user" (name) VALUES ('Alice');
INSERT INTO "user" (name) VALUES ('Bob');
INSERT INTO "user" (name) VALUES ('Charlie');

-- insert data table post
INSERT INTO post (title, content, author, user_id) VALUES ('First Post', 'This is the content of the first post.', 'Alice', 1);
INSERT INTO post (title, content, author, user_id) VALUES ('Second Post', 'This is the content of the second post.', 'Alice', 1);
INSERT INTO post (title, content, author, user_id) VALUES ('Third Post', 'This is the content of the third post.', 'Bob', 2);
INSERT INTO post (title, content, author, user_id) VALUES ('Fourth Post', 'This is the content of the fourth post.', 'Charlie', 3);
INSERT INTO post (title, content, author, user_id) VALUES ('Fifth Post', 'This is the content of the fifth post.', 'Charlie', 3);

-- Insert Comments for Post
INSERT INTO comment (name, post_id) VALUES ('Comment 1 for Post 1', 1);
INSERT INTO comment (name, post_id) VALUES ('Comment 1 for Post 2', 2);
INSERT INTO comment (name, post_id) VALUES ('Comment 2 for Post 2', 2);