# getontop
java getontop test, by nescao

1. go to the db>
2. add>
   CREATE TABLE bank_account (
   id INT NOT NULL AUTO_INCREMENT,
   user_id INT NOT NULL,
   name VARCHAR(255) NOT NULL,
   surname VARCHAR(255) NOT NULL,
   routing_number VARCHAR(255) NOT NULL,
   national_id VARCHAR(255) NOT NULL,
   account_number VARCHAR(255) NOT NULL,
   bank_name VARCHAR(255) NOT NULL,
   PRIMARY KEY (id)
   );

INSERT INTO bank_account (user_id, name, surname, routing_number, national_id, account_number, bank_name)
VALUES (1, 'John', 'Doe', '123456789', '1234567890', '123456789012', 'First National Bank');

INSERT INTO bank_account (user_id, name, surname, routing_number, national_id, account_number, bank_name)
VALUES (2, 'Jane', 'Doe', '987654321', '9876543210', '098765432109', 'Second National Bank');

INSERT INTO bank_account (user_id, name, surname, routing_number, national_id, account_number, bank_name)
VALUES (3, 'Peter', 'Jones', '111111111', '1111111111', '111111111111', 'Third National Bank');

CREATE TABLE user_wallets (
id INT NOT NULL AUTO_INCREMENT,
user_id INT NOT NULL,
balance DECIMAL(10,2) NOT NULL,
creation_date DATETIME NOT NULL,
PRIMARY KEY (id)
);

-- Insert some dummy data
INSERT INTO user_wallets (user_id, balance, creation_date) VALUES (101, 100.00, CURRENT_TIMESTAMP);
INSERT INTO user_wallets (user_id, balance, creation_date) VALUES (102, 200.00, CURRENT_TIMESTAMP);
INSERT INTO user_wallets (user_id, balance, creation_date) VALUES (103, 300.00, CURRENT_TIMESTAMP);


3. compile the application with maven, do maven clean, do maven, verify and then maven install,
4. finnally go to SpringBootH2DatabaseExampleApplication to run the app