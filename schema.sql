-- this is all the queries used by the app

CREATE TABLE IF NOT EXISTS Users (username TEXT, email TEXT, password TEXT)

CREATE TABLE IF NOT EXISTS Products(pName TEXT, pDesc TEXT, price NUMBER, seller TEXT)

-- admin queries
SELECT * from Products

DELETE FROM Users WHERE username=?

SELECT * from Users

-- seller
INSERT INTO Products(pName, pDesc, price, seller) + Values(?,?,?,?)

UPDATE Products SET pName=?, pDesc=?, price=? WHERE pName =? AND seller =?

DELETE FROM Products WHERE pname=? AND seller=?

-- buyer

SELECT * FROM Products WHERE seller=?

SELECT * FROM Products WHERE pName=?

SELECT * from Products

-- Users SUPER

INSERT INTO Users(username, password, email, role) VALUES (?,?,?,?)