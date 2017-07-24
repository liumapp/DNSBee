CREATE TABLE ZonesFile (
  `id` INTEGER primary key AUTOINCREMENT,
  `type` INTEGER,
  `name` string,
  `text` string,
  `user` string
);

CREATE TABLE User_Passport (
  `id` INTEGER primary key AUTOINCREMENT,
  `userNumber` string UNIQUE ,
  `username` text UNIQUE,
  `passwordSalt` string,
  `salt` string,
  `ticket` text UNIQUE,
  `zones` string default ""
);