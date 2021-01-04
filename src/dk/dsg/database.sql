CREATE TABLE Category (
    id INT IDENTITY(1,1) PRIMARY KEY,
    catName VARCHAR (128) NOT NULL
);

CREATE TABLE Movie (
    id INT IDENTITY(1,1) PRIMARY KEY,
    movieName VARCHAR (256) NOT NULL,
    rating INT NOT NULL,
    filePath VARCHAR(256) NOT NULL,
    lastView DATE NOT NULL
);

CREATE TABLE MovieCat (
    id INT IDENTITY(1,1) PRIMARY KEY,
    categoryId INT,
    movieId INT,
    FOREIGN KEY (categoryId) REFERENCES Category(id),
    FOREIGN KEY (movieId) REFERENCES Movie(id)
);