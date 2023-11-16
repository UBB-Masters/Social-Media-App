CREATE DATABASE DATA_BASE;

USE DATA_BASE;

CREATE TABLE User(
    userID BIGINT PRIMARY KEY,
    username VARCHAR(100),
    password_hash VARCHAR(100),
    birthdate DATETIME,
    email VARCHAR(100),
    profile_picture VARCHAR(100),
    visibility BIT,
    permission BIT,
    user_status BIT
);

CREATE TABLE GroupChat (
    groupID BIGINT PRIMARY KEY,
    groupName VARCHAR(100)
);

CREATE TABLE GroupChatMembers (
    userID BIGINT,
    groupID BIGINT,
    PRIMARY KEY (userID, groupID),
    FOREIGN KEY (userID) REFERENCES User(userID),
    FOREIGN KEY (groupID) REFERENCES GroupChat(groupID)
);

CREATE TABLE Message(
    messageID BIGINT PRIMARY KEY,
    message_content VARCHAR(1000),
    timestamp DATETIME,
    userID BIGINT,
    FOREIGN KEY (userID) REFERENCES User(userID)
);

CREATE TABLE MessageRecipient(
    messageID BIGINT,
    groupID BIGINT,
    PRIMARY KEY (messageID, groupID),
    FOREIGN KEY (messageID) REFERENCES User(userID),
    FOREIGN KEY (groupID) REFERENCES GroupChat(groupID)
);

CREATE TABLE Hashtag(
    hashtagID BIGINT PRIMARY KEY,
    tag VARCHAR(100)
);

CREATE TABLE Post(
    postID BIGINT PRIMARY KEY,
    content VARCHAR(100),
    timestamp DATETIME,
    userID BIGINT,
    FOREIGN KEY (userID) REFERENCES User(userID)
);

CREATE TABLE Comment(
    commentID BIGINT PRIMARY KEY,
    content VARCHAR(1000),
    timestamp DATETIME,
    userID BIGINT,
    FOREIGN KEY (userID) REFERENCES User(userID)
);

CREATE TABLE PostComment(
    postID BIGINT,
    commentID BIGINT,
    PRIMARY KEY (postID, commentID),
    FOREIGN KEY (postID) REFERENCES User(userID),
    FOREIGN KEY (commentID) REFERENCES GroupChat(groupID)
);

CREATE TABLE Reaction(
    reactionID BIGINT PRIMARY KEY,
    reaction_type VARCHAR(100),
    userID BIGINT,
    FOREIGN KEY (userID) REFERENCES User(userID)
);

CREATE TABLE CommentReaction(
    commentID BIGINT,
    reactionID BIGINT,
    PRIMARY KEY (commentID, reactionID),
    FOREIGN KEY (commentID) REFERENCES Comment(commentID),
    FOREIGN KEY (reactionID) REFERENCES Reaction(reactionID)
);

CREATE TABLE PostReaction(
    reactionID BIGINT,
    postID BIGINT,
    PRIMARY KEY (reactionID, postID),
    FOREIGN KEY (reactionID) REFERENCES Reaction(reactionID),
    FOREIGN KEY (postID) REFERENCES Post(postID)
);

CREATE TABLE Story(
    storyID BIGINT PRIMARY KEY ,
    visibility BIT,
    timestamp DATETIME,
    userID BIGINT,
    hashtagID BIGINT,
    FOREIGN KEY (hashtagID) REFERENCES Hashtag(hashtagID),
    FOREIGN KEY (userID) REFERENCES User(userID)
);

CREATE TABLE StoryReaction(
    storyID BIGINT,
    reactionID BIGINT,
    PRIMARY KEY (storyID, reactionID),
    FOREIGN KEY (storyID) REFERENCES Story(storyID),
    FOREIGN KEY (reactionID) REFERENCES Reaction(reactionID)
);

CREATE TABLE StoryComment(
    storyID BIGINT,
    commentID BIGINT,
    PRIMARY KEY (storyID, commentID),
    FOREIGN KEY (storyID) REFERENCES Story(storyID),
    FOREIGN KEY (commentID) REFERENCES Comment(commentID)
);

INSERT INTO user (userID, username, password_hash, birthdate, email, profile_picture, visibility, permission, user_status)
VALUES
(1, 'john_doe', 'hashed_password_123', '1990-05-15 00:00:00', 'john@example.com', 'profile_pic.jpg', 1, 1, 1),
(2, 'jane_smith', 'hashed_password_456', '1988-10-20 00:00:00', 'jane@example.com', 'avatar.jpg', 1, 1, 1),
(3, 'bob_jackson', 'hashed_password_789', '1995-03-25 00:00:00', 'bob@example.com', 'user_pic.jpg', 1, 1, 1);